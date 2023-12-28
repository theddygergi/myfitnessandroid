package com.project1.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ArmsActivity extends AppCompatActivity {

    private final String M = "M";
    private final String F = "F";
    TextView armst1, armst2, armst3;
    ImageView arms1, arms2,arms3;
    CheckBox armsEx1, armsEx2, armsEx3;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int goalID, userID;
    String gender;
    int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms);
        armst1 = findViewById(R.id.armsT1);
        armst2 = findViewById(R.id.armsT2);
        armst3 = findViewById(R.id.armsT3);
        arms1 = findViewById(R.id.arms1);
        arms2 = findViewById(R.id.arms2);
        arms3 = findViewById(R.id.arms3);
        armsEx1 = findViewById(R.id.armsEx1);
        armsEx2 = findViewById(R.id.armsEx2);
        armsEx3 = findViewById(R.id.armsEx3);

        sharedPreferences = getSharedPreferences("LoginPageActivity",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        goalID = sharedPreferences.getInt("goalID",0);
        userID = sharedPreferences.getInt("userID",0);
        progress = sharedPreferences.getInt("progress",0);
        gender = sharedPreferences.getString("gender","");

        if(goalID == 1 && gender.equals(M)){
            arms1.setImageResource(R.drawable.bicep_curl);
            arms2.setImageResource(R.drawable.triceps_dips);
            arms3.setImageResource(R.drawable.knee_push_ups);
        }


        if(goalID == 1 && gender.equals(F)){
            arms1.setImageResource(R.drawable.women_bicep_curl);
            arms2.setImageResource(R.drawable.women_tricep_dips);
            arms3.setImageResource(R.drawable.women_knee_pushups);
        }

        if(goalID == 2 && gender.equals(M)){
            arms1.setImageResource(R.drawable.hammer_curls);
            arms2.setImageResource(R.drawable.triceps_dips);
            arms3.setImageResource(R.drawable.knee_push_ups);
        }


        if(goalID == 2 && gender.equals(F)){
            arms1.setImageResource(R.drawable.women_hammer_curl);
            arms2.setImageResource(R.drawable.women_tricep_dips);
            arms3.setImageResource(R.drawable.women_knee_pushups);
        }

        if(goalID == 3 && gender.equals(M)){
            arms1.setImageResource(R.drawable.hammer_curls);
            arms2.setImageResource(R.drawable.triceps_dips);
            arms3.setImageResource(R.drawable.knee_push_ups);
        }


        if(goalID == 3 && gender.equals(F)){
            arms1.setImageResource(R.drawable.women_hammer_curl);
            arms2.setImageResource(R.drawable.women_tricep_dips);
            arms3.setImageResource(R.drawable.women_knee_pushups);
        }

        DatabaseHelper db = new DatabaseHelper(ArmsActivity.this);
        List<String> exercises = db.getExercisesByGoalID(goalID);
        armst1.setText(exercises.get(3));
        armst2.setText(exercises.get(4));
        armst3.setText(exercises.get(5));


        armsEx1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("armsEx1Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    armsEx1.setVisibility(View.GONE);
                    armst1.setText("Achieved!");

                }
                db.setProgress(userID);
            }
        });

        armsEx2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("armsEx2Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    armsEx2.setVisibility(View.GONE);
                    armst2.setText("Achieved!");

                }
                db.setProgress(userID);
            }
        });

        armsEx3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("armsEx3Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    armsEx3.setVisibility(View.GONE);
                    armst3.setText("Achieved!");

                }
                db.setProgress(userID);
            }
        });

        armsEx1.setChecked(sharedPreferences.getBoolean("armsEx1Checked", false));
        armsEx2.setChecked(sharedPreferences.getBoolean("armsEx2Checked", false));
        armsEx3.setChecked(sharedPreferences.getBoolean("armsEx3Checked", false));





    }
}