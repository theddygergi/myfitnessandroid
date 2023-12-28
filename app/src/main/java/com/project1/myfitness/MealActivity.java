package com.project1.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MealActivity extends AppCompatActivity {

    TextView breakfastText, lunchText, dinnerText;
    BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int goalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.planButton);
        breakfastText = findViewById(R.id.breakfastFood);
        lunchText = findViewById(R.id.lunchFood);
        dinnerText = findViewById(R.id.dinnerFood);
        sharedPreferences = getSharedPreferences("LoginPageActivity",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        goalID = sharedPreferences.getInt("goalID",0);
        DatabaseHelper db = new DatabaseHelper(MealActivity.this);

        if(sharedPreferences.getString("isLoggedIn","").equals("true")) {
            List<String> foods = db.getFood(goalID);
            breakfastText.setText(foods.get(0));
            lunchText.setText(foods.get(1));
            dinnerText.setText(foods.get(2));
        }else {
            breakfastText.setText("You must be logged in!");
            lunchText.setText("You must be logged in!");
            dinnerText.setText("You must be logged in!");
        }
    }
}