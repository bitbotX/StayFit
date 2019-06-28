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
import android.widget.Toast;

import java.util.ArrayList;

public class BreakfastMeals extends AppCompatActivity {

    private String Meals[]={"Banana (ripe)","Dosa (plain)","Oats","Idli (plain)","Wheat , Brown Bread","Wheat chapati","Boiled Egg"};
    private int Calories[]={117,302,299,48,136,146,89};
    private ListView mListView=null;
    private TextView displayCals=null;
    public static int totalCalories=0;
    private Button mealsDone=null;
    public static final String breakfastCalsSelected="breakfastCals";

    ArrayList<Long> selectedIds=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_meals);
        totalCalories=0;
        mListView = (ListView) findViewById(R.id.list_view);
        displayCals = (TextView) findViewById(R.id.cals);
        ArrayAdapter<String> mAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,Meals);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mListView.setAdapter(mAdapter);
        mealsDone=(Button)findViewById(R.id.meals_done);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long selected=mListView.getItemIdAtPosition(position);
                if(!selectedIds.contains(selected)){
                    selectedIds.add(selected);
                    totalCalories+=Calories[position];
                    displayCals.setText(String.valueOf(totalCalories)+" Cals");
                }
                else{
                    Log.d("Main","Already Existing");
                }
            }
        });
        mealsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent=new Intent();
                resultIntent.putExtra(breakfastCalsSelected,totalCalories);
                Log.d("MainActivity",String.valueOf(totalCalories));
                setResult(1,resultIntent);
                finish();
            }
        });
    }
}
