package com.chemutai.personal;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

public class ReturnTicketActivity extends AppCompatActivity {

    @BindView(R.id.btnSelectReturnDate)
    FancyButton btnSelectReturnDate;

    @BindView(R.id.btnReturnPickupLocation)
            FancyButton btnReturnPickupLocation;

    String TAG = "RETURN_TICKET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_ticket);


        selectReturnDateClick();
    }



    @OnClick(R.id.btnSelectReturnDate)
    public void selectReturnDateClick(){
        Calendar newCalendar=Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear=monthOfYear+1;
                String day=(dayOfMonth+"").length()==1? "0"+dayOfMonth:dayOfMonth+"";
                String month=(monthOfYear+"").length()==1? "0"+monthOfYear:monthOfYear+"";

                String date = year+"-"+month+"-"+day;
                Log.d(TAG, "onDateSet: "+date);
                btnSelectReturnDate.setText(date);
                // activitydate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
        datePickerDialog.show();
    }

    @OnClick(R.id.btnReturnPickupLocation)
    public void getReturnPickupLocation(){
        startActivity(new Intent(ReturnTicketActivity.this, MapsActivity.class));
    }
}
