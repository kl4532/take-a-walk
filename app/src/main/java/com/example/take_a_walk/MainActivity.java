package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Show settings view at start
        Intent setupActivity = new Intent(MainActivity.this, Settings.class);
        this.startActivity(setupActivity);
        overridePendingTransition(0, 0);

    }

}