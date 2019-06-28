package com.example.dietapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainLoginActivity extends AppCompatActivity {
    private EditText mUserName=null,mUserPassword=null,mUserEmail=null;
    public final static String USER_EMAIL="useremail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        mUserEmail=(EditText)findViewById(R.id.user_email);
        ImageView logo=(ImageView)findViewById(R.id.logo);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.logo_stay_fit);
        RoundedBitmapDrawable round= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        round.setCircular(true);
        logo.setImageDrawable(round);
        mUserName=(EditText)findViewById(R.id.user_email);
        mUserPassword=(EditText)findViewById(R.id.user_password);
    }

    public void GoogleSignUp(View view) {

    }

    public void userLogin(View view) {
        if(mUserName.getText().toString().length()!=0 && mUserPassword.getText().toString().length()!=0){
            Intent toUserDetails=new Intent(this,UserDetailsLogin.class);
            toUserDetails.putExtra(USER_EMAIL,mUserEmail.getText().toString());
            startActivity(toUserDetails);
        }
        else{
            Toast.makeText(getApplicationContext(),"Fill the required user details",Toast.LENGTH_SHORT).show();
        }
    }
}
