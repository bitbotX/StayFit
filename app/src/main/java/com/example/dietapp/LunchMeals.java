package com.example.dietapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LunchMeals extends AppCompatActivity {

    private String lunchMeals[]={"Roti","Curd","Green Gram Dal","Cucumber","Tur Dal","Homemade Chicken Curry","Red Gram Dal"};
    private int lunchMealCalories[]={170,90,137,45,134,200,125};
    private ListView lunchListView=null;
    private TextView displayLunchCals=null;
    public static int totalLunchCalories=0;
    private Button lunchMealsDone=null;
    public static final String lunchCalsSelected="breakfastCals";

    ArrayList<Long> selectedLunchIds=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_meals);
        totalLunchCalories=0;
        lunchListView = (ListView) findViewById(R.id.dinner_list_view);
        displayLunchCals = (TextView) findViewById(R.id.lunch_cals);
        ArrayAdapter<String> lunchMealsAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,lunchMeals);
        lunchListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lunchListView.setAdapter(lunchMealsAdapter);
        lunchMealsDone=(Button)findViewById(R.id.lunch_meals_done);
        lunchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long selected=lunchListView.getItemIdAtPosition(position);
                if(!selectedLunchIds.contains(selected)){
                    selectedLunchIds.add(selected);
                    totalLunchCalories+=lunchMealCalories[position];
                    displayLunchCals.setText(String.valueOf(totalLunchCalories)+" Cals");
                }
                else{
                    Log.d("Main","Already Existing");
                }
            }
        });
        lunchMealsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent=new Intent();
                resultIntent.putExtra(lunchCalsSelected,totalLunchCalories);
                Log.d("MainActivity",String.valueOf(totalLunchCalories));
                setResult(2,resultIntent);
                finish();
            }
        });


    }
}
