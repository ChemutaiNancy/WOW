
package com.chemutai.personal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.chemutai.personal.models.Seat;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import mehdi.sakout.fancybuttons.FancyButton;


public class SeatsActivity extends AppCompatActivity {

    @BindView(R.id.btn1) FancyButton btn1;
    @BindView(R.id.btn2) FancyButton btn2;
    @BindView(R.id.btn3) FancyButton btn3;
    @BindView(R.id.btn4) FancyButton btn4;
    @BindView(R.id.btn5) FancyButton btn5;
    @BindView(R.id.btn6) FancyButton btn6;
    @BindView(R.id.btn7) FancyButton btn7;
    @BindView(R.id.btn8) FancyButton btn8;
    @BindView(R.id.btn9) FancyButton btn9;
    @BindView(R.id.btn10) FancyButton btn10;
    @BindView(R.id.btn11) FancyButton btn11;
    @BindView(R.id.btn12) FancyButton btn12;
    @BindView(R.id.btn13) FancyButton btn13;
    @BindView(R.id.btn14) FancyButton btn14;
    @BindView(R.id.btn15) FancyButton btn15;
    @BindView(R.id.btn16) FancyButton btn16;
    @BindView(R.id.btn17) FancyButton btn17;
    @BindView(R.id.btn18) FancyButton btn18;
    @BindView(R.id.btn19) FancyButton btn19;
    @BindView(R.id.btn20) FancyButton btn20;
    @BindView(R.id.btn21) FancyButton btn21;
    @BindView(R.id.btn22) FancyButton btn22;
    @BindView(R.id.btn23) FancyButton btn23;
    @BindView(R.id.btn24) FancyButton btn24;
    @BindView(R.id.btn25) FancyButton btn25;
    @BindView(R.id.btn26) FancyButton btn26;
    @BindView(R.id.btn27) FancyButton btn27;
    @BindView(R.id.btn28) FancyButton btn28;
    @BindView(R.id.btn29) FancyButton btn29;
    @BindView(R.id.btn30) FancyButton btn30;
    @BindView(R.id.btn31) FancyButton btn31;
    @BindView(R.id.btn32) FancyButton btn32;
    @BindView(R.id.btn33) FancyButton btn33;
    @BindView(R.id.btn34) FancyButton btn34;
    @BindView(R.id.btn35) FancyButton btn35;
    @BindView(R.id.btn36) FancyButton btn36;
    @BindView(R.id.btn37) FancyButton btn37;
    @BindView(R.id.btn38) FancyButton btn38;
    @BindView(R.id.btn39) FancyButton btn39;
    @BindView(R.id.btn40) FancyButton btn40;
    @BindView(R.id.btn41) FancyButton btn41;
    @BindView(R.id.btn42) FancyButton btn42;
    @BindView(R.id.btn43) FancyButton btn43;
    @BindView(R.id.btn44) FancyButton btn44;
    @BindView(R.id.btn45) FancyButton btn45;
    @BindView(R.id.btn46) FancyButton btn46;
    @BindView(R.id.btn47) FancyButton btn47;
    @BindView(R.id.btn48) FancyButton btn48;
    @BindView(R.id.btn49) FancyButton btn49;
    @BindView(R.id.btn50) FancyButton btn50;
    @BindView(R.id.btn51) FancyButton btn51;

    @BindView(R.id.btnConfirm) FancyButton btnConfirm;
    @BindView(R.id.btnNext) FancyButton btnNext;

    ArrayList<FancyButton> fancyButtonList;
    String TAG="BOOKING_SEAT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);
        ButterKnife.bind(this);

        Boolean booked = true;

      /*String [] booked={"1", "3", "4", "37", "51", "23", "16", "30", "8", "5"};*/

      fancyButtonList = new ArrayList<>();
      fancyButtonList.addAll(Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16,
              btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn30, btn31, btn32,
              btn33, btn34, btn35, btn36, btn37, btn38, btn39, btn40, btn41, btn42, btn43, btn44, btn45, btn46, btn47, btn48,
              btn49, btn50, btn51));

        for (int i = 0; i < fancyButtonList.size(); i++) {
            FancyButton btn=fancyButtonList.get(i);
            Log.d(TAG, "onCreate: "+(i+1));
            try {
                btn.setTag(""+(i+1));
            }catch (Exception e){
                Log.d(TAG, "onCreate: button null at position "+i);
            }

        }

        for (FancyButton btn:fancyButtonList){
            String tag=btn.getTag().toString();
            if (booked.equals(tag)){
            }
            /*for (String t:booked) {
              if (tag.equals(t)){
                  btn.setEnabled(false);
                  btn.setBackgroundColor(Color.parseColor("#909090"));
              } *//*else{
                  btn.setEnabled(true);
                  btn.setBackgroundColor(Color.parseColor("#03e9fd"));
              }*//*
            }*/
        }
       View.OnClickListener listner=new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               FancyButton btn= (FancyButton) view;
               Toast.makeText(SeatsActivity.this, ""+btn.getTag().toString(), Toast.LENGTH_SHORT).show();
               //my changes

            /*   if (btn.setEnabled(true)){
                   btn.getTag().toString();
                   btn.setClickable(false);
               } else {
                   btn.getTag().toString();
                   btn.setClickable(true);
               }*/
           }
       };
       for (FancyButton btn:fancyButtonList){
           btn.setOnClickListener(listner);
       }

       /*String clicked;

       switch (listner.getClass()){
           case R.id.btn1:
               clicked = btn1.getText().toString();
               break;
       }
       fancyButtonList.add(clicked);*/
        saveChosenSeats();
        goNext();
    }

   @OnClick(R.id.btnConfirm)
   public void saveChosenSeats(){

   }

   @OnClick(R.id.btnNext)
    public void goNext(){
        startActivity(new Intent(this, PaymentActivity.class));
        finish();
   }



}
