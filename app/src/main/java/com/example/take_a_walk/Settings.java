package com.example.take_a_walk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    Button btnStartCycle;
    EditText inputWork;
    EditText inputWalk;
    int workVal;
    int walkVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        inputWork = (EditText) findViewById(R.id.inputWork);
        inputWalk = (EditText) findViewById(R.id.inputWalk);

//        Get values when redirected from TimeoutActivity
        workVal = getIntent().getIntExtra("work", 0);
        walkVal = getIntent().getIntExtra("walk", 0);

        if(workVal>0 && walkVal>0) {
            inputWork.setText(Integer.toString(workVal));
            inputWalk.setText(Integer.toString(walkVal));
        }
        
//        Validate inputs
        inputWork.addTextChangedListener(new TextChangeListener<EditText>(inputWork) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                btnStartCycle.setEnabled(isValid(inputWork));
            }
        });
        inputWalk.addTextChangedListener(new TextChangeListener<EditText>(inputWalk) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                btnStartCycle.setEnabled(isValid(inputWalk));
            }
        });

        btnStartCycle = (Button) findViewById(R.id.btnStartCycle);
        btnStartCycle.setOnClickListener(Settings.this);
    }

    @Override
    public void onClick(View v) {
        Intent timeoutActivity = new Intent(Settings.this, Timeout.class);
        timeoutActivity.putExtra("mode", "work");
        timeoutActivity.putExtra("work", Integer.parseInt(inputWork.getText().toString()));
        timeoutActivity.putExtra("walk", Integer.parseInt(inputWalk.getText().toString()));

        this.startActivity(timeoutActivity);
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    public boolean isValid(EditText input) {
        if(input.getText().toString().isEmpty()) {
            return false;
        }

        int value = Integer.parseInt(input.getText().toString());

        if (value==0) {
            return false;
        }

        return true;
    }
}