package com.project1.myfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView  walking, workout;
    ProgressBar progressBar;
    TextView nameWelcomeText, welcomeText;
    BottomNavigationView bottomNavigationView;

    String name = null;
    String mail;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavView);

        SharedPreferences sharedPreferences = getSharedPreferences("LoginPageActivity",MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();

        walking = findViewById(R.id.walkingIcon);
        workout = findViewById(R.id.workoutIcon);
        nameWelcomeText = findViewById(R.id.nameWelcomeText);
        progressBar = findViewById(R.id.mainProgressBar);
        welcomeText = findViewById(R.id.welcomeText);
        bottomNavigationView.setSelectedItemId(R.id.homeButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                if(itemID == R.id.homeButton){
                    return true;
                }
                else if(itemID == R.id.classesButton){
                    startActivity(new Intent(getApplicationContext(),ClassesActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                if(itemID == R.id.planButton){
                    startActivity(new Intent(getApplicationContext(),PlanActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                if(itemID == R.id.accountButton){
                    startActivity(new Intent(getApplicationContext(),AccountActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });

        String name = sharedPreferences.getString("name","");
        if(sharedPreferences.getString("isLoggedIn","").equals("true")){
            nameWelcomeText.setText("Hey, "+ name + "!");
            welcomeText.setText("Welcome back!");
        }
        else {
            nameWelcomeText.setText("Hey guest!");
            welcomeText.setText("Welcome to our app!");
        }

        walking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StepsCounter.class));
            }
        });

        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPreferences.getString("isLoggedIn","").equals("true")){
                    startActivity(new Intent(MainActivity.this, PlanActivity.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "You must be logged in !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}