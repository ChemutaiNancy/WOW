package com.chemutai.personal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView busImage;
    private TextView longDistanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        busImage = findViewById(R.id.imgBus);
        longDistanceText = findViewById(R.id.txtLongDistance);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        longDistanceText.startAnimation(myanim);
        busImage.startAnimation(myanim);
        final Intent intent = new Intent(this, RegisterActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2000);{

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

    }


}
