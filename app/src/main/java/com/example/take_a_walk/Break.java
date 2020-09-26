package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Break extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break);

        int test = getIntent().getIntExtra("break", 5);

        Log.i("breakVal", Integer.toString(test));

    }
}