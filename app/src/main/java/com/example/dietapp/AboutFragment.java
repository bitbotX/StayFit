package com.example.dietapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;


public class AboutFragment extends Fragment {
    public AboutFragment(){
        //Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //((NavigationHomeScreen) Objects.requireNonNull(getActivity())).setActionBarTitle("About");
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}