package com.project1.myfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PlanActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button joinWorkout, joinMeal;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.planButton);
        joinWorkout = findViewById(R.id.joinWorkout);
        joinMeal = findViewById(R.id.joinMeal);
        sharedPreferences = getSharedPreferences("LoginPageActivity", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                if(itemID == R.id.homeButton){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if(itemID == R.id.classesButton){
                    startActivity(new Intent(getApplicationContext(),ClassesActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                if(itemID == R.id.planButton){
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

        joinWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPreferences.getString("isLoggedIn","").equals("true"))
                    startActivity(new Intent(PlanActivity.this,WorkoutActivity.class));
                else
                    Toast.makeText(PlanActivity.this,"You must log in!",Toast.LENGTH_SHORT).show();
            }
        });

        joinMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPreferences.getString("isLoggedIn","").equals("true"))
                    startActivity(new Intent(PlanActivity.this, MealActivity.class));
                else
                    Toast.makeText(PlanActivity.this,"You must log in!",Toast.LENGTH_SHORT).show();

            }
        });

    }
}