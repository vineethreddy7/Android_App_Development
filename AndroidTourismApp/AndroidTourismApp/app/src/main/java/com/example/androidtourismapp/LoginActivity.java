package com.example.androidtourismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user, pass;
    Button login;
    int count = 0; //a class level variable to count the number of failed login trial
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.usertxt);
        pass = findViewById(R.id.passtxt);
        login = findViewById(R.id.logbtn);
        login.setOnClickListener(this);
    }
    public void onClick(View view) {
        if (count < 3)
            if (user.getText().toString().equalsIgnoreCase("ritesh") && pass.getText().toString().equals("ritesh123"))
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else if (user.getText().toString().equalsIgnoreCase("vineeth") && pass.getText().toString().equals("vineeth123")){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else if (user.getText().toString().equalsIgnoreCase("bhanu") && pass.getText().toString().equals("bhanu123")){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else if (user.getText().toString().equalsIgnoreCase("malay") && pass.getText().toString().equals("malay123")){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "Invalid username or password ", Toast.LENGTH_LONG).show();
                count++;//count the failed login trials
            }
        else{
            //after the fourth failed login
            Toast.makeText(getApplicationContext(), "Access denied", Toast.LENGTH_LONG).show();
            login.setEnabled(false);
        }
    }
}
