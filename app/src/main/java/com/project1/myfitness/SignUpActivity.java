package com.project1.myfitness;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    EditText userFullName, userPassword, userEmail, userGender, userHeight, userWeight;
    Button back, next;
    RadioGroup radioGroup;
    RadioButton r1, r2,r3;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int goalId = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);
        userFullName = findViewById(R.id.userFullName);
        userPassword = findViewById(R.id.userPassword);
        userEmail = findViewById(R.id.userEmail);
        userGender = findViewById(R.id.userGender);
        userHeight = findViewById(R.id.userHeight);
        userWeight = findViewById(R.id.userWeight);
        radioGroup = findViewById(R.id.goalRadioButton);
        r1 = findViewById(R.id.goal1);
        r2 = findViewById(R.id.goal2);
        r3 = findViewById(R.id.goal3);
        back = findViewById(R.id.backButton);
        next = findViewById(R.id.nextButton);
        sharedPreferences = getSharedPreferences("LoginPageActivity",MODE_PRIVATE);
        editor = sharedPreferences.edit();

                back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainLogin.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userFullName.getText().toString();
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();
                String gender = userGender.getText().toString();
                Float height = Float.parseFloat(userHeight.getText().toString());
                Float weight = Float.parseFloat(userWeight.getText().toString());
                Float BMI  = weight / ((height/100) * (height/100));
                if(radioGroup.getCheckedRadioButtonId() == R.id.goal1)
                    goalId = 1;
                if(radioGroup.getCheckedRadioButtonId() == R.id.goal2)
                    goalId = 2;
                if(radioGroup.getCheckedRadioButtonId() == R.id.goal3)
                    goalId = 3;

                if(name.isEmpty() || email.isEmpty() || password.isEmpty())
                    Toast.makeText(SignUpActivity.this,"Field(s) are empty",Toast.LENGTH_SHORT).show();
                //submitToDB(name,email,password,gender,height,weight,BMI,goalId);

                DatabaseHelper db = new DatabaseHelper(SignUpActivity.this);
                Boolean b = db.addUser(name,email,password,gender,height,weight,BMI,goalId);
                int userID = db.getUserID(email);
                Boolean setDef = db.setDefaultProgress(userID);
                editor.putString("gender",gender);
                editor.putInt("progress",0);
                editor.putInt("goalID",goalId);
                editor.apply();
                Toast.makeText(SignUpActivity.this,"Successfully signed up",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUpActivity.this, LoginPageActivity.class));
            }
        });
    }
    
    /*private void submitToDB(String name, String email, String password, String gender, Float height, Float weight,Float BMI, int goalId) {
        String url = "http://192.168.16.108/demo/insert-1.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
          @Override
          protected Map<String,String> getParams() throws AuthFailureError {
            Map<String,String> map = new HashMap<>();
            map.put("userName",name);
            map.put("userEmail",email);
            map.put("userPassword",password);
            map.put("userGender",gender);
            map.put("userHeight",String.valueOf(height));
            map.put("userWeight",String.valueOf(weight));
            map.put("userBMI",String.valueOf(BMI));
            map.put("userGoalID",String.valueOf(2));

            return map;

          }
        };

        requestQueue.add(stringRequest);
    }*/
}