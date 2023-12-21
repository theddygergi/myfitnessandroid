package com.project1.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class StepsCounter extends AppCompatActivity implements SensorEventListener {

    TextView stepsCounter, distanceCounter, timeCounter;
    Button pauseButton;
    TextView stepCountTarget;
    SensorManager sensorManager;
    Sensor stepCountSensor;
    int stepCount = 0;
    ProgressBar progressBar;
    boolean isPaused = false;
    long timePaused = 0, startTime;
    float stepLength = 0.726f; //in meters
    int stepCountTargett = 5000;

    Handler timeHandler = new Handler();
    Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis/1000);
            int min = seconds/60;
            timeCounter.setText(String.format(String.valueOf(Locale.getDefault()),"Time: %02d:%02d",min,seconds));
            timeHandler.postDelayed(this,1000);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(stepCountSensor != null){
            sensorManager.unregisterListener(this);
            timeHandler.removeCallbacks(timeRunnable);
        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(stepCountSensor != null){
            sensorManager.registerListener( this,stepCountSensor,SensorManager.SENSOR_DELAY_NORMAL);
            timeHandler.postDelayed(timeRunnable,0);
        }
    }

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_counter);

        stepsCounter = findViewById(R.id.stepsViewer);
        distanceCounter = findViewById(R.id.distanceView);
        timeCounter = findViewById(R.id.timeView);
        stepCountTarget = findViewById(R.id.stepCountTarget);
        pauseButton = findViewById(R.id.onPause);
        progressBar = findViewById(R.id.progressBar);
        startTime = System.currentTimeMillis();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        progressBar.setMax(stepCountTargett);
        stepCountTarget.setText("Step goal: " + stepCountTargett);

        if(stepCountSensor == null){
            stepCountTarget.setText("Step counter not available");
        }

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPaused) {
                    isPaused = false;
                    pauseButton.setText("Pause");
                    startTime = System.currentTimeMillis()-timePaused;
                }
                else {
                    isPaused = true;
                    pauseButton.setText("Resume");
                    timeHandler.removeCallbacks(timeRunnable);
                    timePaused = System.currentTimeMillis()-startTime;
                }
            }
        });


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            stepCount = (int) event.values[0];
            stepsCounter.setText("Step count: " + stepCount);
            progressBar.setProgress(stepCount);
        }

        if(stepCount >= stepCountTargett){
            stepCountTarget.setText("Step goal achieved!");
        }

        float distanceInKilometers = stepCount * stepLength / 100;
        distanceCounter.setText(String.format(Locale.getDefault(),"Distance: %02f",distanceInKilometers));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
