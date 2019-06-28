package com.example.dietapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class NavigationHomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
        private DrawerLayout drawerLayout=null;

        private TextView setUserName=null,setUserEmail=null;

        private ImageView setUserImage=null;
        public static float Calories=0;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Calories=getIntent().getFloatExtra(CaloriePerDay.C_USER_CALORIES,0);
            // Setting all the references
            setContentView(R.layout.activity_navigation_home_screen);

            // Logging for debugging purposes
            Log.d("MainActivity","HomeScreen");
            Log.d("MainActivity","HomeScreen : "+getIntent().getStringExtra(CaloriePerDay.C_USER_NAME));
            Log.d("MainActivity","HomeScreen : "+getIntent().getStringExtra(CaloriePerDay.C_USER_EMAIL));
            Log.d("MainActivity","HomeScreen (age): "+getIntent().getStringExtra(CaloriePerDay.C_USER_AGE));
            Log.d("MainActivity","HomeScreen (activity): "+getIntent().getStringExtra(CaloriePerDay.C_USER_ACTIVITY));
            Log.d("MainActivity","HomeScreen (health): "+getIntent().getStringExtra(CaloriePerDay.C_USER_HEALTH));
            Log.d("MainActivity","HomeScreen (height): "+getIntent().getStringExtra(CaloriePerDay.C_USER_HEIGHT));
            Log.d("MainActivity","HomeScreen (weight): "+getIntent().getStringExtra(CaloriePerDay.C_USER_WEIGHT));
            Log.d("MainActivity","HomeScreen (total calories): "+Calories);

            // Setting up the support action bar
            Toolbar mToolbar = findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);


            // Setting the user name and user email id in the navbar

            drawerLayout = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            // Getting the header view to change the children view's text and images

            View hView =  navigationView.getHeaderView(0);
            setUserName=(TextView)hView.findViewById(R.id.nav_bar_user_name);
            setUserName.setText(getIntent().getStringExtra(CaloriePerDay.C_USER_NAME));
            setUserEmail=(TextView)hView.findViewById(R.id.nav_bar_user_email);
            setUserEmail.setText(getIntent().getStringExtra(CaloriePerDay.C_USER_EMAIL));

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_home);
            }
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        // Cases for each navigation drawer item
        switch (menuItem.getItemId()) {

            // Changes the home screen frame layout ( id : fragment_container ) to the Home Fragment
            case R.id.nav_home:
                getSupportFragmentManager()
                        .beginTransaction()
                            .replace(R.id.fragment_container,new HomeFragment()).commit();
                break;

            // Changes the home screen frame layout ( id : fragment_container ) to the Profile Fragment
            case R.id.nav_profile:
                FragmentManager custom=getSupportFragmentManager();
                FragmentTransaction transact=custom.beginTransaction();
                Fragment profileFragment=new ProfileFragment();
                Bundle mBundle=new Bundle();
                mBundle.putString("nUserAge",getIntent().getStringExtra(CaloriePerDay.C_USER_AGE));
                mBundle.putString("nUserActivity",getIntent().getStringExtra(CaloriePerDay.C_USER_ACTIVITY));
                mBundle.putString("nUserName",getIntent().getStringExtra(CaloriePerDay.C_USER_NAME));
                mBundle.putString("nUserWeight",getIntent().getStringExtra(CaloriePerDay.C_USER_WEIGHT));
                mBundle.putString("nUserHeight",getIntent().getStringExtra(CaloriePerDay.C_USER_HEIGHT));
                mBundle.putString("nUserHealth",getIntent().getStringExtra(CaloriePerDay.C_USER_HEALTH));
                profileFragment.setArguments(mBundle);
                transact.replace(R.id.fragment_container,profileFragment).commit();
                break;

            // Changes the home screen frame layout ( id : fragment_container ) to More Fragment
            case R.id.nav_more:
                getSupportFragmentManager()
                        .beginTransaction()
                            .replace(R.id.fragment_container,new MoreFragment()).commit();
                break;

            // Displays the app sharing information
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

            // Changes the home screen frame layout ( id : fragment_container ) to About Fragment
            case R.id.nav_about:
                getSupportFragmentManager()
                        .beginTransaction()
                            .replace(R.id.fragment_container,new AboutFragment()).commit();
                break;
        }

        // Drawer Close Compatibility
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
