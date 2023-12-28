package com.project1.myfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClassesActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button joinZumba, joinYoga, joinSteps;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.classesButton);
        sharedPreferences = getSharedPreferences("LoginPageActivity", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        joinZumba = findViewById(R.id.joinZumba);
        joinYoga = findViewById(R.id.joinYoga);
        joinSteps = findViewById(R.id.joinSteps);

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

        DatabaseHelper db = new DatabaseHelper(ClassesActivity.this);

        joinZumba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClassesActivity.this)
                        .setTitle("Welcome to the zumba class")
                        .setMessage(db.getClassDescription(2))
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                DatabaseHelper db = new DatabaseHelper(ClassesActivity.this);
                if(sharedPreferences.getString("isLoggedIn","").equals("true")) {
                    boolean userID = db.addUserToClass(sharedPreferences.getInt("userID", 0), 2);
                    if (userID) {
                        Toast.makeText(ClassesActivity.this, "Joined Class!", Toast.LENGTH_SHORT).show();
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                else {
                    Toast.makeText(ClassesActivity.this, "You must be logged in !", Toast.LENGTH_SHORT).show();

                }
            }
        });

        joinYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClassesActivity.this)
                        .setTitle("Welcome to the yoga class")
                        .setMessage(db.getClassDescription(1))
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                DatabaseHelper db = new DatabaseHelper(ClassesActivity.this);
                if(sharedPreferences.getString("isLoggedIn","").equals("true")) {
                    boolean userID = db.addUserToClass(sharedPreferences.getInt("userID", 0), 1);
                    if (userID) {
                        Toast.makeText(ClassesActivity.this, "Joined Class!", Toast.LENGTH_SHORT).show();
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                else {
                    Toast.makeText(ClassesActivity.this, "You must be logged in !", Toast.LENGTH_SHORT).show();

                }
            }
        });

        joinSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClassesActivity.this)
                        .setTitle("Welcome to the steps class")
                        .setMessage(db.getClassDescription(3))
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                DatabaseHelper db = new DatabaseHelper(ClassesActivity.this);
                if(sharedPreferences.getString("isLoggedIn","").equals("true")) {
                    boolean userID = db.addUserToClass(sharedPreferences.getInt("userID", 0), 3);
                    if (userID) {
                        Toast.makeText(ClassesActivity.this, "Joined Class!", Toast.LENGTH_SHORT).show();
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                else {
                    Toast.makeText(ClassesActivity.this, "You must be logged in !", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}