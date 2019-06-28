package com.example.dietapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.UnknownServiceException;

public class CaloriePerDay extends AppCompatActivity {
    private String uName = null;
    private float uCals = 0;
    private TextView rName = null, rCals = null;
    private Button mNext=null;
    public static final String C_USER_EMAIL="useremail";
    public static final String C_USER_NAME="cusername";
    public static final String C_USER_AGE="cuserage";
    public static final String C_USER_ACTIVITY="cuseractivity";
    public static final String C_USER_HEIGHT="cuserheight";
    public static final String C_USER_WEIGHT="cuserweight";
    public static final String C_USER_HEALTH="cuserhealth";
    public static final String C_USER_CALORIES="cusercalories";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_per_day);
        uName = getIntent().getStringExtra(UserDetailsLogin.USER_NAME_KEY);
        uCals = getIntent().getFloatExtra(UserDetailsLogin.USER_CALORIES, 0);
        rName = (TextView) findViewById(R.id.Cuser_name);
        rCals = (TextView) findViewById(R.id.calorie_intake);
        rName.setText(uName);
        rCals.setText(String.valueOf(uCals));
        mNext=(Button)findViewById(R.id.nextHS);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeScreenIntent=new Intent(CaloriePerDay.this,NavigationHomeScreen.class);
                homeScreenIntent.putExtra(C_USER_EMAIL,getIntent().getStringExtra(UserDetailsLogin.D_USER_EMAIL));
                homeScreenIntent.putExtra(C_USER_NAME,getIntent().getStringExtra(UserDetailsLogin.USER_NAME_KEY));
                homeScreenIntent.putExtra(C_USER_HEALTH,getIntent().getStringExtra(UserDetailsLogin.D_USER_HEALTH));
                homeScreenIntent.putExtra(C_USER_AGE,getIntent().getStringExtra(UserDetailsLogin.D_USER_AGE));
                homeScreenIntent.putExtra(C_USER_ACTIVITY,getIntent().getStringExtra(UserDetailsLogin.D_USER_ACTIVITY));
                homeScreenIntent.putExtra(C_USER_HEIGHT,getIntent().getStringExtra(UserDetailsLogin.D_USER_HEIGHT));
                homeScreenIntent.putExtra(C_USER_WEIGHT,getIntent().getStringExtra(UserDetailsLogin.D_USER_WEIGHT));
                homeScreenIntent.putExtra(C_USER_CALORIES,uCals);
                startActivity(homeScreenIntent);
                Log.d("MainActivity","Opening Home Screen");
                Log.d("MainActivity","Calorie Intent (email passed): "+getIntent().getStringExtra(UserDetailsLogin.D_USER_EMAIL));
                Log.d("MainActivity","Calorie Intent (name passed): "+getIntent().getStringExtra(UserDetailsLogin.USER_NAME_KEY));
                Log.d("MainActivity","Calorie Intent (health passed):"+getIntent().getStringExtra(UserDetailsLogin.D_USER_HEALTH));
                Log.d("MainActivity","Calorie Intent (age passed):"+getIntent().getStringExtra(UserDetailsLogin.D_USER_AGE));;
                Log.d("MainActivity","Calorie Intent (activity passed):"+getIntent().getStringExtra(UserDetailsLogin.D_USER_ACTIVITY));
                Log.d("MainActivity","Calorie Intent (height passed):"+getIntent().getStringExtra(UserDetailsLogin.D_USER_HEIGHT));
                Log.d("MainActivity","Calorie Intent (weight passed):"+getIntent().getStringExtra(UserDetailsLogin.D_USER_WEIGHT));

            }
        });
    }
}
