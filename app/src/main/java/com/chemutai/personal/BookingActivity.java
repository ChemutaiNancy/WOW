package com.chemutai.personal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chemutai.personal.adapters.CustomRouteAdapter;
import com.chemutai.personal.models.Route;
import com.google.firebase.auth.FirebaseAuth;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import mehdi.sakout.fancybuttons.FancyButton;

import static com.chemutai.personal.utils.Constants.ROUTE_URL;

public class BookingActivity extends AppCompatActivity {

    @BindView(R.id.etNumberOfSeats)
    EditText etNumberofSeats;

    @BindView(R.id.checkBoxReturnTicket)
    CheckBox chkReturn;
    @BindView(R.id.btnSelectDate)
    FancyButton btnDate;

    private ProgressDialog progressDialog;

    @BindView(R.id.routesLinearLayout)
    LinearLayout routesLinearLayout;
    @BindView(R.id.listRoutes)
    RecyclerView listRoutes;
    CustomRouteAdapter mAdapter;
    ArrayList<Route> mRouteArrayList;

    Animation mSlideUp, mSlideDown;

    int flowValue=0;
   /* int next = 0;

    int [] act = new int[]{
            R.layout.activity_booking,
            R.layout.activity_seats,
            R.layout.activity_payment
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        if (FirebaseAuth.getInstance().getCurrentUser()==null){//if user is not logged in
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        mSlideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        mSlideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        mRouteArrayList=new ArrayList<>();
        /*mRouteArrayList.add(new Route(1,"Nairobi-Mombasa", 2000, "10:45","Night","6 Seats"));
        mRouteArrayList.add(new Route(1,"Nairobi-Kisumu", 2000, "10:45","Night","6 Seats"));
        mRouteArrayList.add(new Route(1,"Nairobi-Homabay", 2000, "10:45","Night","6 Seats"));*/


        mAdapter=new CustomRouteAdapter(this, mRouteArrayList);
        listRoutes.setLayoutManager(new LinearLayoutManager(this));
        listRoutes.setAdapter(mAdapter);
        checkboxValidation();
        fetchRoute();
        etNumberofSeats.getText();
    }

    private void checkboxValidation() {
        chkReturn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    startActivity(new Intent(BookingActivity.this, ReturnTicketActivity.class));
                } else{

                }
            }
        });

    }


    @OnClick(R.id.btnRoute)
    public void btnRouteClick() {
    //    startActivity(new Intent(this, RouteActivity.class));
        flowValue=1;
        updateDispaly();

    }


    @OnClick(R.id.btnSelectPickupLocation)
    public void btnSelectPickupLocation(){
        startActivity(new Intent(BookingActivity.this, MapsActivity.class));
    }
    @OnClick(R.id.btnNext)
    public void getBookingDetails() {
        String travelDate = btnDate.getText().toString();
        String returnDate = chkReturn.getText().toString();
        String seats = etNumberofSeats.getText().toString();
       // String pickupLocation =


        startActivity(new Intent(BookingActivity.this, SeatsActivity.class));
    }

    @OnClick(R.id.btnConfirm)
    public void btnConfirm() {
        flowValue=0;
        updateDispaly();
    }


    String TAG ="BOOKING_ACTIVITY";
    @OnClick(R.id.btnSelectDate)
    public void btnSelectDateClick() {

        Calendar newCalendar=Calendar.getInstance();
        DatePickerDialog  datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear=monthOfYear+1;
                String day=(dayOfMonth+"").length()==1? "0"+dayOfMonth:dayOfMonth+"";
                String month=(monthOfYear+"").length()==1? "0"+monthOfYear:monthOfYear+"";

                String date = year+"-"+month+"-"+day;
                Log.d(TAG, "onDateSet: "+date);
                btnDate.setText(date);
               // activitydate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
        datePickerDialog.show();
    }


    private void updateDispaly(){
        if (routesLinearLayout.getVisibility() == View.VISIBLE) {
            routesLinearLayout.setAnimation(mSlideDown);
        }
        routesLinearLayout.setVisibility(View.GONE);

        if (flowValue == 1) {
            routesLinearLayout.startAnimation(mSlideUp);
            routesLinearLayout.setVisibility(View.VISIBLE);
        }
    }

    public void fetchRoute(){
        String url = ROUTE_URL;
        AsyncHttpClient client = new AsyncHttpClient();
        /*progressDialog.show();*/
        /*Log.d(TAG, "fetchRoute: "+url);*/

        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progressDialog.dismiss();
                Toast.makeText(BookingActivity.this, "Failed to fetch routes", Toast.LENGTH_SHORT).show();
                /*Log.d(TAG, "onFailure: failed"+statusCode);*/
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
               /* Log.d(TAG, "onSuccess:  "+responseString);*/
                progressDialog.dismiss();
                try {
                    JSONArray ja = new JSONArray(responseString);
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject obj = ja.getJSONObject(i);
                        int route_id = obj.getInt("route_id");
                        String routeName = obj.getString("route_name");
                        double cost = obj.getDouble("cost");
                        String time = obj.getString("time");
                        String seats = "10 seats";

                        Route r = new Route(route_id, routeName, cost, time, seats);
                        mRouteArrayList.add(r);
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    /*Toast.makeText(BookingActivity.this, "Error while processing", Toast.LENGTH_SHORT).show();*/
                }
            }
        });
    }

    /* @OnClick(R.id.checkboxConfirm)
    public void onCheckboxConfirm(){
        if (checkboxConfirm.isChecked()){

        }else{

        }
    }*/

    /*    @OnClick(R.id.checkBoxReturnTicket)
    public void checkBoxReturn() {
        //    startActivity(new Intent(this, RouteActivity.class));
        flowValue=1;
        checkboxDispaly();

    }

    private void checkboxDispaly() {
        if (returnTicketLayout.getVisibility() == View.VISIBLE) {
            returnTicketLayout.setAnimation(mSlideDown);
        }
        returnTicketLayout.setVisibility(View.GONE);

        if (flowValue == 1) {
            returnTicketLayout.startAnimation(mSlideUp);
            returnTicketLayout.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.checkBoxReturnTicket)
    public void checkboxReturn() {
        flowValue=0;
        checkboxDispaly();
    }*/
}

