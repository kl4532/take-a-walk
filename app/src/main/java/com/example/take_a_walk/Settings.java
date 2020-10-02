package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    Button btnStartCycle;
    EditText workVal;
    EditText walkVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        workVal = (EditText) findViewById(R.id.inputWork);
        walkVal = (EditText) findViewById(R.id.inputWalk);

//      Validate inputs
        workVal.addTextChangedListener(new TextChangeListener<EditText>(workVal) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                btnStartCycle.setEnabled(isValid(workVal));
            }
        });
        walkVal.addTextChangedListener(new TextChangeListener<EditText>(walkVal) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                btnStartCycle.setEnabled(isValid(walkVal));
            }
        });

        btnStartCycle = (Button) findViewById(R.id.btnStartCycle);
        btnStartCycle.setOnClickListener(Settings.this);
    }

    @Override
    public void onClick(View v) {
        Intent timeoutActivity = new Intent(Settings.this, Timeout.class);
        timeoutActivity.putExtra("mode", "work");
        timeoutActivity.putExtra("work", Integer.parseInt(workVal.getText().toString()));
        timeoutActivity.putExtra("walk", Integer.parseInt(walkVal.getText().toString()));

        this.startActivity(timeoutActivity);
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    public boolean isValid(EditText input) {
        if(input.getText().toString().isEmpty() || input.getText().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}