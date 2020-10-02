package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
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
    Button btnStart;
    int walkVal;
    int workVal;
    String mode;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break);

        workVal = getIntent().getIntExtra("work", 60);
        walkVal = getIntent().getIntExtra("walk", 5);
        mode = getIntent().getStringExtra("mode");

        btnStart = (Button) findViewById(R.id.btnStart);

        if(mode.equals("work")) {
            btnStart.setText("Go to work");
        } else {
            btnStart.setText("take a walk");
        }

        Singleton app = Singleton.getInstance();
        vibrator = (Vibrator) app.getSystemService(Context.VIBRATOR_SERVICE);

        btnStart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent timeoutActivity = new Intent(Break.this, Timeout.class);
                timeoutActivity.putExtra("mode", mode);
                timeoutActivity.putExtra("work", workVal);
                timeoutActivity.putExtra("walk", walkVal);

                vibrator.cancel();

                startActivity(timeoutActivity);
                overridePendingTransition(0, 0);
            }
        });

    }
//    moved to timeout , remove from break
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