package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Timeout extends AppCompatActivity {

    Button btnGotoSettings;
    TextView timer;
    TextView info;
    int walkVal;
    int workVal;
    String mode;
    Countdown c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeout);

        timer = (TextView) findViewById(R.id.timer);
        info = (TextView) findViewById(R.id.info);

        workVal = getIntent().getIntExtra("work", 60);
        walkVal = getIntent().getIntExtra("walk", 5);
        mode = getIntent().getStringExtra("mode");

        // set image according to mode

        ImageView iv = (ImageView)findViewById(R.id.image);
        if(mode.equals("walk")) {
            iv.setImageResource(R.drawable.walk);
        } else {
            iv.setImageResource(R.drawable.work);
        }

        // Testing!!!! to reverse multiplying by 60
        Log.i("timeout1", "1");

        final CountDownTimer c = getCountdown(workVal, walkVal, mode);
        c.start();

//        Countdown c =  new Countdown(timer,info,135);
//        c.start();

        btnGotoSettings = (Button) findViewById(R.id.btnGotoSettings);
        btnGotoSettings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                c.cancel();
                startActivity(new Intent(Timeout.this, Settings.class));
                overridePendingTransition(0, 0);
            }
        });
    }


    public CountDownTimer getCountdown (int work, int walk, final String mode) {

        int milisec;

        if(mode.equals("work")) {
            milisec = 60 * work * 1000;
        } else {
            milisec = 60 * walk * 1000;
        }


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

                Intent timeoutActivity;

                setContentView(R.layout.activity_break);
                timeoutActivity = new Intent(Timeout.this, Break.class);
                timeoutActivity.putExtra("walk", walkVal);
                timeoutActivity.putExtra("work", workVal);

                if(mode.equals("work")) {
                    timeoutActivity.putExtra("mode", "walk");
                } else {
                    timeoutActivity.putExtra("mode", "work");
                }

                startActivity(timeoutActivity);
                overridePendingTransition(0, 0);

            }
        };
    }


//    @Override
//    public void onClick(View v) {
//        Log.i("tag", "Jump to setup");
//        setContentView(R.layout.activity_setup);
//    }
}
