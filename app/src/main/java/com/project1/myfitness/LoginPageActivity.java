package com.project1.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginPageActivity extends AppCompatActivity {

    EditText email, password;
    Button back, next;
    TextView logintext;
    SharedPreferences sharedPreferences, accountSharedPreferences;
    SharedPreferences.Editor editor, accountEditor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_page);
        sharedPreferences = getSharedPreferences("LoginPageActivity",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(sharedPreferences.getString("isLoggedIn","").equals("true")){
            startActivity(new Intent(LoginPageActivity.this, MainActivity.class));
            finishAffinity();
        }
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        back = findViewById(R.id.backButton);
        next = findViewById(R.id.nextButton);
        logintext = findViewById(R.id.loginText);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();

                /*if(usernameValue.equals("") || passwordValue.equals("")){
                    Toast.makeText(this,"Cannot have empty values", Toast.LENGTH_SHORT).show();
                }*/

                if(emailValue.equals(null)){
                    email.setError("Please enter an email");
                    email.requestFocus();
                }

                if(passwordValue.equals(null)){
                    password.setError("Please enter a password");
                    password.requestFocus();
                }
                //loginUser(usernameValue, passwordValue);

                DatabaseHelper db = new DatabaseHelper(LoginPageActivity.this);
                boolean user = db.getUser(emailValue, passwordValue);
                String name = db.getUserByEmail(emailValue);
                int id = db.getUserID(emailValue);

                if(user){
                    editor.putInt("userID", id);
                    editor.putString("mail",emailValue);
                    editor.putString("name",name);
                    editor.apply();
                    editor.putString("isLoggedIn", "true");
                    editor.commit();

                    Toast.makeText(LoginPageActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPageActivity.this, MainActivity.class);
                    startActivity(intent);

                }
                else
                    Toast.makeText(LoginPageActivity.this,"Login Unsuccessful", Toast.LENGTH_SHORT).show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPageActivity.this, MainLogin.class);
                startActivity(intent);
            }
        });
    }

    /*private void loginUser(String usernameValue, String passwordValue) {
        String url = "http://192.168.16.108/demo/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                editor.putString("isLoggedIn", "true");
                editor.commit();
                Toast.makeText(LoginPageActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginPageActivity.this, MainActivity.class);
                intent.putExtra("name",usernameValue);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginPageActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        }){
          @Override
          protected Map<String,String> getParams() throws AuthFailureError {
              Map<String,String> map = new HashMap<>();
              map.put("clientEmail", usernameValue);
              map.put("clientPassword",passwordValue);
              return map;
          }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }*/
}