package com.example.take_a_walk;

import android.os.CountDownTimer;
import android.widget.TextView;


public class Countdown {

    private int sec;
    private TextView timer;
    private TextView info;

    public Countdown(TextView timer, TextView info, int seconds) {
        this.timer = timer;
        this.info = info;
        this.sec = seconds;
    }

    public CountDownTimer getCountdown() {

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
                    info.setText("done!");
                }
            };

    }

    public void start() {
        this.getCountdown().start();
    }

    public void stop() {
        this.getCountdown().cancel();
    }
}
