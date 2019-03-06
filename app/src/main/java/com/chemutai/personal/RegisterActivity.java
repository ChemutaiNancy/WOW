package com.chemutai.personal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chemutai.personal.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etFirstName, etLastName, etPhoneNo, etEmail, etPassword;
    private FancyButton btnRegister;
    private TextView txtAlreadyRegistered;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    String TAG = "REGISTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*if (SharedPreferencesManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), BookingActivity.class));
            return;
        }*/

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null)
                {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                    return;
                }
            }
        };

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        txtAlreadyRegistered = findViewById(R.id.txtAlreadyRegistered);

        progressDialog = new ProgressDialog(this);

        btnRegister.setOnClickListener(this);
        txtAlreadyRegistered.setOnClickListener(this);
    }


    private void registerUser(){
        final String fname = etFirstName.getText().toString().trim();
        final String lname = etLastName.getText().toString().trim();
        final String phoneno = etPhoneNo.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        if (fname.isEmpty() || lname.isEmpty() || phoneno.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Fill all the values", Toast.LENGTH_SHORT).show();
            return;
        }

        User u = new User(fname, lname, phoneno, email, password);
        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (!task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registration not successful", Toast.LENGTH_SHORT).show();
                    /*Log.d(TAG, "onComplete: .register");*/
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    String userId = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference fb = FirebaseDatabase.getInstance().getReference("users/"+userId);
                    Map userInfo = new HashMap<>();
                    userInfo.put("first_name", fname);
                    userInfo.put("last_name", lname);
                    userInfo.put("phone_no", phoneno);
                    userInfo.put("email", email);
                   /* userInfo.put("password", password);*/

                    fb.setValue(userInfo);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    public void userLogin(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (view == btnRegister){
            registerUser();
        }
        if (view == txtAlreadyRegistered){
            userLogin();
        }
    }
}
