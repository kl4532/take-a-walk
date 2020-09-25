package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class Timeout extends AppCompatActivity implements View.OnClickListener {

    Button btnGotoSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeout);

        //Intialization Button


        btnGotoSettings = (Button) findViewById(R.id.btnGotoSettings);

        btnGotoSettings.setOnClickListener(Timeout.this);

        Log.i("tag", "Created");

        //Here MainActivity.this is a Current Class Reference (context)
    }

    @Override
    public void onClick(View v) {
        Log.i("tag", "Jump to setup");
        setContentView(R.layout.activity_setup);
    }
}