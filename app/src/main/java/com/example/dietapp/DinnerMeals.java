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

public class DinnerMeals extends AppCompatActivity {

    private String dinnerMeals[]={"Roti","Curd","Green Gram Dal","Cucumber","Tur Dal","Homemade Chicken Curry","Red Gram Dal"};
    private int dinnerMealCalories[]={170,90,137,45,134,200,125};
    private ListView dinnerListView=null;
    private TextView displayDinnerCals=null;
    public static int totalDinnerCalories=0;
    private Button dinnerMealsDone=null;
    public static final String dinnerCalsSelected="breakfastCals";

    ArrayList<Long> selectedDinnerIds=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_meals);

        dinnerListView = (ListView) findViewById(R.id.dinner_list_view);
        displayDinnerCals = (TextView) findViewById(R.id.dinner_cals);
        ArrayAdapter<String> lunchMealsAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,dinnerMeals);
        dinnerListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        dinnerListView.setAdapter(lunchMealsAdapter);
        dinnerMealsDone=(Button)findViewById(R.id.dinner_meals_done);
        dinnerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long selected=dinnerListView.getItemIdAtPosition(position);
                if(!selectedDinnerIds.contains(selected)){
                    selectedDinnerIds.add(selected);
                    totalDinnerCalories+=dinnerMealCalories[position];
                    displayDinnerCals.setText(String.valueOf(totalDinnerCalories)+" Cals");
                }
                else{
                    Log.d("Main","Already Existing");
                }
            }
        });
        dinnerMealsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent=new Intent();
                resultIntent.putExtra(dinnerCalsSelected,totalDinnerCalories);
                Log.d("MainActivity",String.valueOf(totalDinnerCalories));
                setResult(3,resultIntent);
                finish();
            }
        });
    }
}
