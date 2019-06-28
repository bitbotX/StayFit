package com.example.dietapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GoalsActivity extends AppCompatActivity {

    private EditText mGoalWeight=null,mUserWeight=null;
    private TextView mUserWeightDisplay=null;
    private ImageButton mIncWeight=null,mDecWeight=null;
    private Button mOk=null;
    private float increased=0,decreased=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        mGoalWeight=(EditText)findViewById(R.id.goal_weight);
        mUserWeight=(EditText)findViewById(R.id.current_weight);
        mUserWeightDisplay=(TextView)findViewById(R.id.display_user_weight);
        mIncWeight=(ImageButton)findViewById(R.id.increase_weight);
        mDecWeight=(ImageButton)findViewById(R.id.decrease_weight);
        mOk=(Button)findViewById(R.id.display_changes);
        mIncWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increased= (float) (Float.parseFloat(mUserWeight.getText().toString())+.5);
                mUserWeight.setText(String.valueOf(increased));
                displayChanges(v);
            }
        });
        mDecWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreased=(float)(Float.parseFloat(mUserWeight.getText().toString())-0.5);
                if(decreased < 0){
                    Toast.makeText(getApplicationContext(),"Weight can't be negative.",Toast.LENGTH_SHORT).show();
                }
                else{
                    mUserWeight.setText(String.valueOf(decreased));
                    displayChanges(v);
                }
            }
        });
    }

    public void displayChanges(View view) {
        String weightMeasure=null;
        float weightJourney=0;
        if(Float.parseFloat(mGoalWeight.getText().toString()) < Float.parseFloat(mUserWeight.getText().toString())){
            weightMeasure="lost";
            weightJourney= Float.parseFloat(mUserWeight.getText().toString()) - Float.parseFloat(mGoalWeight.getText().toString());
            mUserWeightDisplay.setText("0 of "+String.valueOf(weightJourney)+" "+weightMeasure);
        }
        else if(Float.parseFloat(mGoalWeight.getText().toString()) > Float.parseFloat(mUserWeight.getText().toString())){
            weightMeasure="gained";
            weightJourney= Float.parseFloat(mGoalWeight.getText().toString()) - Float.parseFloat(mUserWeight.getText().toString());
            mUserWeightDisplay.setText("0 of "+String.valueOf(weightJourney)+" "+weightMeasure);
        }
    }
}
