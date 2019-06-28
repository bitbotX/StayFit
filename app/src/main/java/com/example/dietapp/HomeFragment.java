package com.example.dietapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class HomeFragment extends Fragment {

    private ImageButton mCals=null,mWater=null,mGoals=null;
    public static final String HF_USER_CALORIES="hfusercalories";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //((NavigationHomeScreen) Objects.requireNonNull(getActivity())).setActionBarTitle("Home");

        //Getting the fragment view to fetch all the children views inside this fragment
        View view = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        //Setting all the Image Buttons in this fragment
        mCals=(ImageButton)view.findViewById(R.id.ib_cals);

        mWater=(ImageButton)view.findViewById(R.id.ib_water);

        mGoals=(ImageButton)view.findViewById(R.id.ib_goals);

        //Setting the OnClickListeners on all buttons

        mCals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calorieProportionIntent=new Intent(getActivity(),CaloriesProportion.class);
                calorieProportionIntent.putExtra(HF_USER_CALORIES,NavigationHomeScreen.Calories);
                Log.d("MainActivity",String.valueOf(NavigationHomeScreen.Calories));
                startActivity(calorieProportionIntent);
            }
        });

        mWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent waterIntent=new Intent(getActivity(),WaterIntakePerday.class);
                startActivity(waterIntent);
            }
        });

        mGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goalsIntent=new Intent(getActivity(),GoalsActivity.class);
                startActivity(goalsIntent);
            }
        });
        //Returning the Home Fragment
        return view;
    }

}