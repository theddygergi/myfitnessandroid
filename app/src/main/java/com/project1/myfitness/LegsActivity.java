package com.project1.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LegsActivity extends AppCompatActivity {

    private final String M = "M";
    private final String F = "F";
    TextView legst1, legst2, legst3;
    ImageView legs1, legs2,legs3;
    CheckBox legsEx1, legsEx2, legsEx3;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int goalID, userID;
    String gender;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs);
        legs1 = findViewById(R.id.legsI1);
        legs2 = findViewById(R.id.legsI2);
        legs3 = findViewById(R.id.legsI3);
        legst1 = findViewById(R.id.legsT1);
        legst2 = findViewById(R.id.legsT2);
        legst3 = findViewById(R.id.legsT3);
        legsEx1 = findViewById(R.id.legsEx1);
        legsEx2 = findViewById(R.id.legsEx2);
        legsEx3 = findViewById(R.id.legsEx3);

        sharedPreferences = getSharedPreferences("LoginPageActivity",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        goalID = sharedPreferences.getInt("goalID",0);
        userID = sharedPreferences.getInt("userID",0);
        progress = sharedPreferences.getInt("progress",0);
        gender = sharedPreferences.getString("gender","");

        if(goalID == 1 && gender.equals(M)){
            legs1.setImageResource(R.drawable.squats);
            legs2.setImageResource(R.drawable.crunches);
            legs3.setImageResource(R.drawable.calf_raises);
        }

        if(goalID == 1 && gender.equals(F)){
            legs1.setImageResource(R.drawable.women_squats);
            legs2.setImageResource(R.drawable.women_crunches);
            legs3.setImageResource(R.drawable.women_calf_raises);
        }

        if(goalID == 2 && gender.equals(M)){
            legs1.setImageResource(R.drawable.squats);
            legs2.setImageResource(R.drawable.crunches);
            legs3.setImageResource(R.drawable.calf_raises_with_weight);
        }

        if(goalID == 2 && gender.equals(F)){
            legs1.setImageResource(R.drawable.women_squats);
            legs2.setImageResource(R.drawable.women_crunches);
            legs3.setImageResource(R.drawable.women_calf_raises);
        }

        if(goalID == 3 && gender.equals(M)){
            legs1.setImageResource(R.drawable.pistol_squats);
            legs2.setImageResource(R.drawable.bulgarian_split_squats);
            legs3.setImageResource(R.drawable.calf_raises_with_weight);
        }

        if(goalID == 3 && gender.equals(F)){
            legs1.setImageResource(R.drawable.women_pistol_squats);
            legs2.setImageResource(R.drawable.women_bulgarian_split_squats);
            legs3.setImageResource(R.drawable.women_calf_raises);
        }

        DatabaseHelper db = new DatabaseHelper(LegsActivity.this);
        List<String> exercises = db.getExercisesByGoalID(goalID);
        legst1.setText(exercises.get(9));
        legst2.setText(exercises.get(10));
        legst3.setText(exercises.get(11));

        legsEx1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("legsEx1Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    legsEx1.setVisibility(View.GONE);
                    legst1.setText("Achieved!");
                }
                db.setProgress(userID);
            }
        });

        legsEx2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("legsEx2Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    legsEx2.setVisibility(View.GONE);
                    legst2.setText("Achieved!");
                }
                db.setProgress(userID);
            }
        });

        legsEx3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("legsEx3Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    legsEx3.setVisibility(View.GONE);
                    legst3.setText("Achieved!");
                }
                db.setProgress(userID);
            }
        });

        legsEx1.setChecked(sharedPreferences.getBoolean("legsEx1Checked", false));
        legsEx2.setChecked(sharedPreferences.getBoolean("legsEx2Checked", false));
        legsEx3.setChecked(sharedPreferences.getBoolean("legsEx3Checked", false));

        if(sharedPreferences.getString("isLoggedIn", "").equals("false")){
            editor.putBoolean("legsEx1Checked", false);
            editor.putBoolean("legsEx1Checked", false);
            editor.putBoolean("legsEx1Checked", false);
            editor.apply();
        }

    }
}