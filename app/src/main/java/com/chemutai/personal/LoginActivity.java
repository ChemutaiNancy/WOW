package com.chemutai.personal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etPassword;
    private FancyButton btnLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       /* if (SharedPreferencesManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), BookingActivity.class));
            return;
        }*/
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(this, BookingActivity.class));
            finish();
        } else{
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        }
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait... ");

        btnLogin.setOnClickListener(this);
    }

    private void userLogin(){
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(!task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "sign in error", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, BookingActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }



    @Override
    public void onClick(View view) {
        if (view == btnLogin){
            userLogin();
        }
    }
}
  /*StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.LOGIN_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (!jsonObject.getBoolean("error")){
                        SharedPreferencesManager.getInstance(getApplicationContext())
                                .userLogin(
                                        jsonObject.getInt("user_id"),
                                        jsonObject.getString("first_name"),
                                        jsonObject.getString("middle_name"),
                                        jsonObject.getString("last_name"),
                                        jsonObject.getString("national_id"),
                                        jsonObject.getString("phone_no"),
                                        jsonObject.getString("email")
                                );
                        *//*Toast.makeText(getApplicationContext(), "User login successful", Toast.LENGTH_SHORT).show();*//*
                        startActivity(new Intent(getApplicationContext(), BookingActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phone_no", phoneno);
                params.put("password", password);
                return params;
            }
            /* RequestHandler.getInstance(this).addToRequestQueue(stringRequest);*/

