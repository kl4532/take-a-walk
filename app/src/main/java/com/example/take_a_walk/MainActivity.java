package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnStartCycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        //Intialization Button

        btnStartCycle = (Button) findViewById(R.id.btnStartCycle);

        btnStartCycle.setOnClickListener(MainActivity.this);
        //Here MainActivity.this is a Current Class Reference (context)
        Log.i("tag", "Created main activity");


    }

    @Override
    public void onClick(View v) {
        setContentView(R.layout.activity_timeout);
    }
}