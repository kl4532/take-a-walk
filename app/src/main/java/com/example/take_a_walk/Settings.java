package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements View.OnClickListener{

    Button btnStartCycle;
    TextView workVal;
    TextView walkVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        workVal = (TextView) findViewById(R.id.inputWork);
        walkVal = (TextView) findViewById(R.id.inputWalk);

        btnStartCycle = (Button) findViewById(R.id.btnStartCycle);
        btnStartCycle.setOnClickListener(Settings.this);
    }

    @Override
    public void onClick(View v) {
        setContentView(R.layout.activity_timeout);
        Intent timeoutActivity = new Intent(Settings.this, Timeout.class);
        timeoutActivity.putExtra("mode", "work");
        timeoutActivity.putExtra("work", Integer.parseInt(workVal.getText().toString()));
        timeoutActivity.putExtra("walk", Integer.parseInt(walkVal.getText().toString()));

        this.startActivity(timeoutActivity);
        overridePendingTransition(0, 0);

    }
}