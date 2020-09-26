package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Timeout extends AppCompatActivity {

    Button btnGotoSettings;
    TextView timer;
    TextView info;
    int breakVal;
    int workVal;
    int work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeout);

        timer = (TextView) findViewById(R.id.timer);
        info = (TextView) findViewById(R.id.info);

        breakVal = getIntent().getIntExtra("break", 5);
        workVal = getIntent().getIntExtra("work", 60);

        work = workVal*60;

        Log.i("work", Integer.toString(workVal));
        Log.i("break", Integer.toString(breakVal));


        CountDownTimer c = getCountdown(work);
        c.start();

//        Countdown c =  new Countdown(timer,info,135);
//        c.start();

        btnGotoSettings = (Button) findViewById(R.id.btnGotoSettings);
        btnGotoSettings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Timeout.this, Settings.class));
            }
        });
    }


    public CountDownTimer getCountdown (int sec) {

        int milisec = sec * 1000;

        return new CountDownTimer(milisec, 1000) {

            public void onTick(long milisecToEnd) {

                long seconds = milisecToEnd/1000;
                String outMinutes = "00", outSeconds = "00";

                if(seconds >= 60) {
                    outMinutes = Long.toString(milisecToEnd/1000/60);
                    outSeconds = Long.toString(seconds % 60);
                    timer.setText(outMinutes + ":" + (seconds % 60 > 9 ? outSeconds : "0" + outSeconds ));
                } else {
                    timer.setText(outMinutes + ":" + (seconds>9 ? Long.toString(seconds) : "0" + Long.toString(seconds)));
                }

            }

            public void onFinish() {
                setContentView(R.layout.activity_break);
                Intent timeoutActivity = new Intent(Timeout.this, Break.class);
                timeoutActivity.putExtra("break", breakVal);

                startActivity(timeoutActivity);
            }
        };
    }


//    @Override
//    public void onClick(View v) {
//        Log.i("tag", "Jump to setup");
//        setContentView(R.layout.activity_setup);
//    }
}
