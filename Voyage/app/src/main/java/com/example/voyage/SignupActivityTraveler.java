package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SignupActivityTraveler extends AppCompatActivity {
    Button ButtonTravCreate, ButtonTravCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup_traveler);
        ButtonTravCreate = (Button) findViewById(R.id.HostButtonCreate);
        ButtonTravCancel = (Button) findViewById(R.id.HostButtonCancel);
        ButtonTravCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivityTraveler.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ButtonTravCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivityTraveler.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}