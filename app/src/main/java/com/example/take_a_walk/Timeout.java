package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
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
    Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeout);

        timer = (TextView) findViewById(R.id.timer);
        info = (TextView) findViewById(R.id.info);

        workVal = getIntent().getIntExtra("work", 60);
        walkVal = getIntent().getIntExtra("walk", 5);
        mode = getIntent().getStringExtra("mode");


//      Get up vibrator instance with Singleton
        Singleton app = Singleton.getInstance();
        vibrator = (Vibrator) app.getSystemService(Context.VIBRATOR_SERVICE);

//       Set image according to mode
        ImageView iv = (ImageView)findViewById(R.id.image);
        if(mode.equals("walk")) {
            iv.setImageResource(R.drawable.walk_img);
        } else {
            iv.setImageResource(R.drawable.work_img);
        }

//        Testing!!!! to reverse multiplying by 60
//        Log.i("timeout1", "1");

        final CountDownTimer c = getCountdown(workVal, walkVal, mode);
        c.start();

        btnGotoSettings = (Button) findViewById(R.id.btnGotoSettings);
        btnGotoSettings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                c.cancel();
                startActivity(new Intent(Timeout.this, Settings.class));
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });
    }


    public CountDownTimer getCountdown (int work, int walk, final String mode) {

        long milisec;

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

                vibrate(0, 100, 5000, true);

                startActivity(timeoutActivity);
                overridePendingTransition(0, 0);
            }
        };
    }

    public void vibrate(int delay, int vibration, int sleep, boolean repeat) {
        long[] pattern = {delay, vibration, sleep};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, repeat ? 0 : -1),
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .build());
        } else {
            vibrator.vibrate(pattern, 0);
        }
    }
}
