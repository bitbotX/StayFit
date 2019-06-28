package com.example.dietapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WaterIntakePerday extends AppCompatActivity {

    private TextView mWaterIntakeDisplay=null;
    private ImageButton incWaterIntake=null,decWaterIntake=null;
    private int glasses_count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_intake_perday);
        mWaterIntakeDisplay=(TextView)findViewById(R.id.display_water_glass_intake);
        incWaterIntake=(ImageButton)findViewById(R.id.increase_water_intake);
        decWaterIntake=(ImageButton)findViewById(R.id.decrease_water_intake);
        incWaterIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glasses_count++;
                if(glasses_count > 9){
                    Toast.makeText(getApplicationContext(),"Congratulations , you've completed the water intake for today.",Toast.LENGTH_SHORT).show();
                }
                else{
                    mWaterIntakeDisplay.setText(glasses_count+" of 9 glasses");
                }
            }
        });
        decWaterIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glasses_count--;
                if(glasses_count < 0){
                    Toast.makeText(getApplicationContext(),"You can't go longer without water.",Toast.LENGTH_SHORT).show();
                    glasses_count=0;
                }
                else{
                    mWaterIntakeDisplay.setText(String.valueOf(glasses_count)+" of 9 glasses");
                }
            }
        });
    }
}
