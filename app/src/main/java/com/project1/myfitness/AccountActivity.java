package com.project1.myfitness;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class AccountActivity extends AppCompatActivity  {

    Button login, logout, getReminder, cancelReminder, showTime;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    BottomNavigationView bottomNavigationView;
    MaterialTimePicker picker;
    TextView accountUserName, accountEmail;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    String mail;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.accountButton);
        sharedPreferences = getSharedPreferences("LoginPageActivity",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        login = findViewById(R.id.loginSignUpButton);
        logout = findViewById(R.id.logOutButton);
        accountUserName = findViewById(R.id.accountUserName);
        accountEmail = findViewById(R.id.accountEmail);
        //getReminder = findViewById(R.id.getRemindersButton);
        //cancelReminder = findViewById(R.id.cancelRemindersButton);
        //nextReminder = findViewById(R.id.nextReminder);
        //showTime = findViewById(R.id.timePickButton);
        //createNotificationChannel();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();
                if (itemID == R.id.homeButton) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemID == R.id.classesButton) {
                    startActivity(new Intent(getApplicationContext(), ClassesActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                else if (itemID == R.id.planButton) {
                    startActivity(new Intent(getApplicationContext(), PlanActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                else if(itemID == R.id.accountButton){
                    return true;
                }
                return false;
            }
        });
        String email = sharedPreferences.getString("mail", "");
        String name = sharedPreferences.getString("name","");

        if(sharedPreferences.getString("isLoggedIn","").equals("true")){
            accountUserName.setTextSize(20);
            accountUserName.setText("Full Name: "+ name);
            accountEmail.setTextSize(20);
            accountEmail.setText("Email: " + email);
        }
        else {
            accountUserName.setTextSize(40);
            accountUserName.setText("Full Name: ");
            accountEmail.setTextSize(40);
            accountEmail.setText("Email: ");
        }




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, MainLogin.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("isLoggedIn", "false");
                editor.commit();
                Toast.makeText(AccountActivity.this,"Logout Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AccountActivity.this, MainLogin.class));
            }
        });

        /*getReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });

        showTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        cancelReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }

    private void cancelAlarm() {
        Intent intent = new Intent(this,RemindersActivity.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent, PendingIntent.FLAG_IMMUTABLE);
        if(alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this,"Alarm cancelled",Toast.LENGTH_SHORT).show();
    }

    private void setAlarm(){
            if (calendar != null) {
                alarmManager = (AlarmManager) getSystemService((Context.ALARM_SERVICE));
                Intent intent = new Intent(this, RemindersActivity.class);
                pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
                if (alarmManager != null) {
                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                    Toast.makeText(this, "Alarm set for " + nextReminder.getText(), Toast.LENGTH_SHORT).show();
                }
            } else {
                // Handle the case where the calendar is not initialized.
                // You might want to show an error message or handle it appropriately.
                Toast.makeText(this, "Calendar not initialized", Toast.LENGTH_SHORT).show();
            }
    }
    private void showTimePicker() {
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select notification time")
                .build();

        picker.show(getSupportFragmentManager(), "MyFitness");
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onClick(View v) {
                if(picker.getHour() > 12) {
                    nextReminder.setText(
                           String.format("%02d",(picker.getHour()-12) +" " +  String.format("%02d",(picker.getMinute()))) + "PM");
                }
                else {
                    nextReminder.setText(picker.getHour() + " : "+ picker.getMinute() + "AM");
                }

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar.set(Calendar.MINUTE, picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);

            }
        });
    }

    private void createNotificationChannel() {
        CharSequence name = "MyFitnessNotifications";
        String description = "Notifications for workouts";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel("MyFitness",name,importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

    }*/
}}
