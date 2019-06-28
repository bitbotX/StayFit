package com.example.dietapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class ProfileFragment extends Fragment {

    private TextView pUserName=null,pUserAge=null,pUserWeight=null,pUserActivity=null,pUserHealth=null,pUserHeight=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //((NavigationHomeScreen) Objects.requireNonNull(getActivity())).setActionBarTitle("Profile");
        View profileView=inflater.inflate(R.layout.fragment_profile, container, false);
        pUserName=(TextView)profileView.findViewById(R.id.profile_user_name);
        pUserAge=(TextView)profileView.findViewById(R.id.profile_user_age);
        pUserWeight=(TextView)profileView.findViewById(R.id.profile_user_weight);
        pUserActivity=(TextView)profileView.findViewById(R.id.profile_daily_activity);
        pUserHealth=(TextView)profileView.findViewById(R.id.profile_medical_condition);
        pUserHeight=(TextView)profileView.findViewById(R.id.profile_user_height);
        assert getArguments() != null;
        pUserName.setText(getArguments().getString("nUserName"));
        pUserAge.setText(getArguments().getString("nUserAge"));
        pUserWeight.setText(getArguments().getString("nUserWeight"));
        pUserHealth.setText(getArguments().getString("nUserHealth"));
        pUserHeight.setText(getArguments().getString("nUserHeight"));
        pUserActivity.setText(getArguments().getString("nUserActivity"));
        return profileView;
    }
}