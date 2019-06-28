package com.example.dietapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class UserDetailsLogin extends AppCompatActivity {
    public final static String USER_NAME_KEY="username";
    public final static String USER_CALORIES="usercalories";
    public final static String D_USER_EMAIL="duseremail";
    public final static String D_USER_WEIGHT="userweight";
    public final static String D_USER_HEIGHT="userheight";
    public final static String D_USER_AGE="userage";
    public final static String D_USER_ACTIVITY="useractivity";
    public final static String D_USER_HEALTH="userhealthcondition";

    private final String[]gender={
      "Male","Female","Others"
    };
    private final String[]activities={
      "Sedentary","Low active","Active","Very active"
    };
    private final String[]healthIssues={
      "None","Physical Injury","Diabetes","Thyroid","Heart Disease"
    };
    private Button submitButton=null;
    private Spinner genderSpinner=null,healthSpinner=null,activity_Spinner=null;
    private EditText userName=null,userHeight=null,userWeight=null,userPhone=null,userAge=null,userAddress=null;
    private String selectedGender=null,selectedDayToDayActivity=null,selectedHealthCondition=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_login);
        genderSpinner=(Spinner)findViewById(R.id.gender_spinner);

        //Gender Spinner onClick Listener
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGender = (String) parent.getItemAtPosition(position);
                Log.d("MainActivity",selectedGender);
                Toast.makeText(getApplicationContext(), "Selected Gender : " + selectedGender, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Kindly  select a gender.",Toast.LENGTH_SHORT).show();
            }
        });
        healthSpinner=(Spinner)findViewById(R.id.health_spinner);

        //Health Spinner onClick Listener
        healthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedHealthCondition = (String) parent.getItemAtPosition(position);
                Log.d("MainActivity",selectedHealthCondition);
                Toast.makeText(getApplicationContext(), "Selected Health Condition : " + selectedHealthCondition, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        activity_Spinner=(Spinner)findViewById(R.id.activity_spinner);

        //Activity Spinner onClick Listener
        activity_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDayToDayActivity = (String) parent.getItemAtPosition(position);
                Log.d("MainActivity",selectedDayToDayActivity);
                Toast.makeText(getApplicationContext(), "You're : " + selectedDayToDayActivity, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Kindly select an activity item describing your day to day actvities",Toast.LENGTH_SHORT).show();
            }
        });
        submitButton=(Button)findViewById(R.id.submit_button);
        userName=(EditText)findViewById(R.id.username);
        userHeight=(EditText)findViewById(R.id.user_height);
        userWeight=(EditText)findViewById(R.id.user_weight);
        userPhone=(EditText)findViewById(R.id.phone_number);
        userAge=(EditText)findViewById(R.id.user_age);
        userAddress=(EditText)findViewById(R.id.user_location);

        //Submit Button onClick Listener
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(userName.getText().toString())){ Toast.makeText(getApplicationContext(),"Kindly enter a user name.",Toast.LENGTH_SHORT).show();}
                else if(genderSpinner.getSelectedItem()==null){ Toast.makeText(getApplicationContext(),"Kindly select your gender.",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(userPhone.getText().toString())){ Toast.makeText(getApplicationContext(),"Kindly enter a phone number.",Toast.LENGTH_SHORT).show();}
                else if(userPhone.getText().toString().length()<10){ Toast.makeText(getApplicationContext(),"Kindly enter a valid phone number.",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(userAddress.getText().toString())){ Toast.makeText(getApplicationContext(),"Kindly enter an address.",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(userHeight.getText().toString())){ Toast.makeText(getApplicationContext(),"Kindly enter your height.",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(userWeight.getText().toString())){ Toast.makeText(getApplicationContext(),"Kindly enter your weight.",Toast.LENGTH_SHORT).show();}
                else if(TextUtils.isEmpty(userAge.getText().toString())){ Toast.makeText(getApplicationContext(),"Kindly enter your age.",Toast.LENGTH_SHORT).show();}
                else{
                    Float calcCalories=calculateCalories();
                    Intent calorieIntent=new Intent(UserDetailsLogin.this,CaloriePerDay.class);
                    calorieIntent.putExtra(USER_NAME_KEY,userName.getText().toString());
                    calorieIntent.putExtra(USER_CALORIES,calcCalories);
                    calorieIntent.putExtra(D_USER_EMAIL,getIntent().getStringExtra(MainLoginActivity.USER_EMAIL));
                    calorieIntent.putExtra(D_USER_WEIGHT,userWeight.getText().toString());
                    calorieIntent.putExtra(D_USER_HEIGHT,userHeight.getText().toString());
                    calorieIntent.putExtra(D_USER_AGE,userAge.getText().toString());
                    calorieIntent.putExtra(D_USER_ACTIVITY,selectedDayToDayActivity);
                    calorieIntent.putExtra(D_USER_HEALTH,selectedHealthCondition);
                    Log.d("MainActivity",String.valueOf(calcCalories));
                    Log.d("MainActivity",getIntent().getStringExtra(MainLoginActivity.USER_EMAIL));
                    Log.d("MainActivity","Details Activity (health): "+selectedHealthCondition);
                    Log.d("MainActivity","Details Activity (activity): "+selectedDayToDayActivity);
                    Log.d("MainActivity","Details Activity (age): "+userAge.getText().toString());
                    Log.d("MainActivity","Details Activity (weight): "+userWeight.getText().toString());
                    Log.d("MainActivity","Details Activity (height): "+userHeight.getText().toString());
                    startActivity(calorieIntent);
                }
            }
        });
        ArrayAdapter<String>genderAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,gender);
        genderSpinner.setAdapter(genderAdapter);
        ArrayAdapter<String>activityAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,activities);
        activity_Spinner.setAdapter(activityAdapter);
        ArrayAdapter<String>healthAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,healthIssues);
        healthSpinner.setAdapter(healthAdapter);
    }
    private float calculateCalories(){
        float calories=0;
        float convUserWeight=Float.parseFloat(userWeight.getText().toString());
        int convUserAge=Integer.parseInt(userAge.getText().toString());
        int convUserHeight=Integer.parseInt(userHeight.getText().toString());
        if(selectedGender.equals("Male") && selectedDayToDayActivity.equals("Sedentary")){
            calories= (float) ((10*(convUserWeight)+6.25*(convUserHeight)-5*convUserAge+5)*1.0);
        }
        else if(selectedGender.equals("Male") && selectedDayToDayActivity.equals("Low active")){
            calories= (float) ((10*(convUserWeight)+6.25*(convUserHeight)-5*convUserAge+5)*1.12);
        }
        else if(selectedGender.equals("Male") && selectedDayToDayActivity.equals("Active")){
            calories= (float) ((10*(convUserWeight)+6.25*(convUserHeight)-5*convUserAge+5)*1.27);
        }
        else if(selectedGender.equals("Male") && selectedDayToDayActivity.equals("Very active")){
            calories= (float) ((10*(convUserWeight)+6.25*(convUserHeight)-5*convUserAge+5)*1.54);
        }
        else if(selectedGender.equals("Female") && selectedDayToDayActivity.equals("Sedentary")){
            calories= (float) ((10*(convUserWeight)+6.25*(convUserHeight)-5*convUserAge-161)*1.0);
        }
        else if(selectedGender.equals("Female") && selectedDayToDayActivity.equals("Low active")){
            calories= (float) ((10*(convUserWeight)+6.25*(convUserHeight)-5*convUserAge-161)*1.14);
        }
        else if(selectedGender.equals("Female") && selectedDayToDayActivity.equals("Active")){
            calories= (float) ((10*(convUserWeight)+6.25*(convUserHeight)-5*convUserAge-161)*1.27);
        }
        else if(selectedGender.equals("Female") && selectedDayToDayActivity.equals("Very active")){
            calories= (float) ((10*(convUserWeight)+6.25*(convUserHeight)-5*convUserAge-161)*1.45);
        }
        return calories;
    }
}
