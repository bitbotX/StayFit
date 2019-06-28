package com.example.dietapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CaloriesProportion extends AppCompatActivity {

    private TextView totalCalsTextView=null,breakfastCalsTextView=null,lunchCalsTextView=null,dinnerCalsTextView=null;
    private float division=0;
    private int totalCals=BreakfastMeals.totalCalories+LunchMeals.totalLunchCalories+DinnerMeals.totalDinnerCalories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        totalCals=0;
        setContentView(R.layout.activity_calories_proportion);
        Log.d("MainActivity","Calories Proportion : "+NavigationHomeScreen.Calories);
        totalCalsTextView=(TextView)findViewById(R.id.total_cal);
        breakfastCalsTextView=(TextView)findViewById(R.id.brk_cl);
        lunchCalsTextView=(TextView)findViewById(R.id.lun_cal);
        dinnerCalsTextView=(TextView)findViewById(R.id.din_cal);
        totalCalsTextView.setText("0 of "+Math.round(NavigationHomeScreen.Calories)+" calories");
        division=Math.round(NavigationHomeScreen.Calories/3);
        breakfastCalsTextView.setText("0 of "+division+" cal");
        lunchCalsTextView.setText("0 of "+division+" cal");
        dinnerCalsTextView.setText("0 of "+division+" cal");
    }

    public void loadBreakfastMeals(View view) {
        Intent BreakfastMealsIntent=new Intent(CaloriesProportion.this,BreakfastMeals.class);
        startActivityForResult(BreakfastMealsIntent,1);
    }

    public void loadLunchMeals(View view) {
        Intent LunchMealsIntent=new Intent(CaloriesProportion.this,LunchMeals.class);
        startActivityForResult(LunchMealsIntent,2);
    }

    public void loadDinnerMeals(View view) {
        Intent DinnerMealsIntent=new Intent(CaloriesProportion.this,DinnerMeals.class);
        startActivityForResult(DinnerMealsIntent,3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int brk_cals=0,lunch_cals=0,dinner_cals=0;
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            brk_cals=(int) data.getIntExtra(BreakfastMeals.breakfastCalsSelected,0);
            Toast.makeText(getApplicationContext(),String.valueOf(brk_cals),Toast.LENGTH_SHORT).show();
            breakfastCalsTextView.setText(String.valueOf(brk_cals)+" of "+division);
            totalCals=brk_cals+LunchMeals.totalLunchCalories+dinner_cals;
            totalCalsTextView.setText(totalCals+" of "+Math.round(NavigationHomeScreen.Calories)+" calories");
        }
        else if(requestCode==2){
            lunch_cals=(int) data.getIntExtra(LunchMeals.lunchCalsSelected,0);
            Toast.makeText(getApplicationContext(),String.valueOf(lunch_cals),Toast.LENGTH_SHORT).show();
            lunchCalsTextView.setText(String.valueOf(lunch_cals)+" of "+division);
            totalCals=BreakfastMeals.totalCalories+lunch_cals+dinner_cals;
            totalCalsTextView.setText(totalCals+" of "+Math.round(NavigationHomeScreen.Calories)+" calories");
        }
        else if(requestCode==3){
            dinner_cals=(int) data.getIntExtra(DinnerMeals.dinnerCalsSelected,0);
            Toast.makeText(getApplicationContext(),String.valueOf(dinner_cals),Toast.LENGTH_SHORT).show();
            dinnerCalsTextView.setText(String.valueOf(dinner_cals)+" of "+division);
            totalCals=BreakfastMeals.totalCalories+LunchMeals.totalLunchCalories+dinner_cals;
            totalCalsTextView.setText(totalCals+" of "+Math.round(NavigationHomeScreen.Calories)+" calories");
        }
    }
}
