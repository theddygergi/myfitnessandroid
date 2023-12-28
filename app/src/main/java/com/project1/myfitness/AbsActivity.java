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

public class AbsActivity extends AppCompatActivity {

    private final String M = "M";
    private final String F = "F";
    TextView abst1, abst2, abst3;
    ImageView abs1, abs2,abs3;
    CheckBox absEx1, absEx2, absEx3;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int goalID, userID;
    String gender;
    int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs);
        abs1 = findViewById(R.id.absI1);
        abs2 = findViewById(R.id.absI2);
        abs3 = findViewById(R.id.absI3);
        abst1 = findViewById(R.id.absT1);
        abst2 = findViewById(R.id.absT2);
        abst3 = findViewById(R.id.absT3);
        absEx1 = findViewById(R.id.absEx1);
        absEx2 = findViewById(R.id.absEx2);
        absEx3 = findViewById(R.id.absEx3);
        sharedPreferences = getSharedPreferences("LoginPageActivity",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        goalID = sharedPreferences.getInt("goalID",0);
        userID = sharedPreferences.getInt("userID",0);
        progress = sharedPreferences.getInt("progress",0);
        gender = sharedPreferences.getString("gender","");

        if(goalID == 1 && gender.equals(M)){
            abs1.setImageResource(R.drawable.crunches);
            abs2.setImageResource(R.drawable.leg_raises);
            abs3.setImageResource(R.drawable.russian_twists);
        }

        if(goalID == 1 && gender.equals(F)){
            abs1.setImageResource(R.drawable.women_crunches);
            abs2.setImageResource(R.drawable.women_leg_raises);
            abs3.setImageResource(R.drawable.women_russian_twists);
        }

        if(goalID == 2 && gender.equals(M)){
            abs1.setImageResource(R.drawable.crunches);
            abs2.setImageResource(R.drawable.leg_raises);
            abs3.setImageResource(R.drawable.russian_twists);
        }

        if(goalID == 2 && gender.equals(F)){
            abs1.setImageResource(R.drawable.women_crunches);
            abs2.setImageResource(R.drawable.women_leg_raises);
            abs3.setImageResource(R.drawable.women_russian_twists);
        }

        if(goalID == 3 && gender.equals(M)){
            abs1.setImageResource(R.drawable.leg_raises);
            abs2.setImageResource(R.drawable.crunches);
            abs3.setImageResource(R.drawable.russian_twists);
        }

        if(goalID == 3 && gender.equals(F)){
            abs1.setImageResource(R.drawable.women_leg_raises);
            abs2.setImageResource(R.drawable.women_crunches);
            abs3.setImageResource(R.drawable.women_russian_twists);
        }

        DatabaseHelper db = new DatabaseHelper(AbsActivity.this);
        List<String> exercises = db.getExercisesByGoalID(goalID);
        abst1.setText(exercises.get(6));
        abst2.setText(exercises.get(7));
        abst3.setText(exercises.get(8));


        absEx1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("absEx1Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    absEx1.setVisibility(View.GONE);
                    abst1.setText("Achieved!");

                }
                db.setProgress(userID);
            }
        });

        absEx2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("absEx2Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    absEx2.setVisibility(View.GONE);
                    abst2.setText("Achieved!");

                }
                db.setProgress(userID);
            }
        });

        absEx3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("absEx3Checked", isChecked);
                editor.apply();
                if (isChecked) {
                    absEx3.setVisibility(View.GONE);
                    abst3.setText("Achieved!");

                }
                db.setProgress(userID);
            }
        });

        absEx1.setChecked(sharedPreferences.getBoolean("absEx1Checked", false));
        absEx2.setChecked(sharedPreferences.getBoolean("absEx2Checked", false));
        absEx3.setChecked(sharedPreferences.getBoolean("absEx3Checked", false));


    }
}