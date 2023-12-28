package com.project1.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ChestActivity extends AppCompatActivity {

    private final String M = "M";
    private final String F = "F";

    ImageView chest1, chest2, chest3;
    TextView chestT1, chestT2, chestT3;
    CheckBox chestEx1, chestEx2, chestEx3;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    int goalID, userID;
    String gender;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest);
        chest1 = findViewById(R.id.chestI1);
        chest2 = findViewById(R.id.chestI2);
        chest3 = findViewById(R.id.chestI3);
        chestT1 = findViewById(R.id.chestW1);
        chestT2 = findViewById(R.id.chestW2);
        chestT3 = findViewById(R.id.chestW3);
        chestEx1 = findViewById(R.id.chestEx1);
        chestEx2 = findViewById(R.id.chestEx2);
        chestEx3 = findViewById(R.id.chestEx3);
        sharedPreferences = getSharedPreferences("LoginPageActivity",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        goalID = sharedPreferences.getInt("goalID",0);
        userID = sharedPreferences.getInt("userID",0);
        progress = sharedPreferences.getInt("progress",0);
        gender = sharedPreferences.getString("gender","");


        if(goalID == 1 && gender.equals(M)){
            chest1.setImageResource(R.drawable.knee_push_ups);
            chest2.setImageResource(R.drawable.wall_push_ups);
            chest3.setImageResource(R.drawable.push_ups);
        }

        if(goalID == 1 && gender.equals(F)){
            chest1.setImageResource(R.drawable.women_knee_pushups);
            chest2.setImageResource(R.drawable.women_wall_pushups);
            chest3.setImageResource(R.drawable.women_push_ups);
        }

        if(goalID == 2 && gender.equals(M)){
            chest1.setImageResource(R.drawable.push_ups);
            chest2.setImageResource(R.drawable.incline_push_ups);
            chest3.setImageResource(R.drawable.decline_push_ups);
        }

        if(goalID == 2 && gender.equals(F)){
            chest1.setImageResource(R.drawable.women_push_ups);
            chest2.setImageResource(R.drawable.women_incline_push_up);
            chest3.setImageResource(R.drawable.women_decline_push_up);
        }

        if(goalID == 3 && gender.equals(M)){
            chest1.setImageResource(R.drawable.push_ups);
            chest2.setImageResource(R.drawable.incline_push_ups);
            chest3.setImageResource(R.drawable.decline_push_ups);
        }

        if(goalID == 3 && gender.equals(F)){
            chest1.setImageResource(R.drawable.women_push_ups);
            chest2.setImageResource(R.drawable.women_incline_push_up);
            chest3.setImageResource(R.drawable.women_decline_push_up);
        }

        DatabaseHelper db = new DatabaseHelper(ChestActivity.this);
        List<String> exercises = db.getExercisesByGoalID(goalID);
        chestT1.setText(exercises.get(0));
        chestT2.setText(exercises.get(1));
        chestT3.setText(exercises.get(2));


        chestEx1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("chestEx1Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    chestEx1.setVisibility(View.GONE);
                    chestT1.setText("Achieved!");
                }
                db.setProgress(userID);
            }
        });

        chestEx2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("chestEx2Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    chestEx2.setVisibility(View.GONE);
                    chestT2.setText("Achieved!");
                }
                db.setProgress(userID);
            }
        });

        chestEx3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("chestEx3Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    chestEx3.setVisibility(View.GONE);
                    chestT3.setText("Achieved!");
                }
                db.setProgress(userID);
            }
        });

        chestEx1.setChecked(sharedPreferences.getBoolean("chestEx1Checked", false));
        chestEx2.setChecked(sharedPreferences.getBoolean("chestEx2Checked", false));
        chestEx3.setChecked(sharedPreferences.getBoolean("chestEx3Checked", false));





    }
}