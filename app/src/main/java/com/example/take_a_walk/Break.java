package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Vibrator;

import java.util.Timer;
import java.util.TimerTask;

public class Break extends AppCompatActivity {
    Button btnStartAWalk;
    int walkVal;
    int workVal;
    String mode;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break);

        workVal = getIntent().getIntExtra("work", 60);
        walkVal = getIntent().getIntExtra("walk", 5);
        mode = getIntent().getStringExtra("mode");

//        Log.i("breakVal", Integer.toString(breakVal));

        btnStartAWalk = (Button) findViewById(R.id.btnStartAWalk);

        if(mode.equals("work")) {
            btnStartAWalk.setText("Go to work");
        } else {
            btnStartAWalk.setText("take a walk");
        }

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//
//        new Timer().scheduleAtFixedRate(new TimerTask(){
//            @Override
//            public void run(){
//                // vibrate, dont forget to clear timer
//            }
//        },0,3000);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }


        btnStartAWalk.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setContentView(R.layout.activity_timeout);
                Intent timeoutActivity = new Intent(Break.this, Timeout.class);
                timeoutActivity.putExtra("mode", mode);
                timeoutActivity.putExtra("work", workVal);
                timeoutActivity.putExtra("walk", walkVal);

                startActivity(timeoutActivity);
                overridePendingTransition(0, 0);
            }
        });


    }
}