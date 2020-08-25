package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button mainTrav, signupTrav;
    TextView TEmailtv, TPwdTv;
  //  String email = TEmailtv.getText().toString();
    String e11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        TEmailtv = findViewById(R.id.tv2TEmail);
        TPwdTv = findViewById(R.id.tv2Tpwd);
        mainTrav = (Button) findViewById(R.id.btnTLogin);
        signupTrav = (Button) findViewById(R.id.btnTSignup);
        mainTrav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase db = new DataBase(MainActivity.this);
                Travellers t = db.getTraveller(TEmailtv.getText().toString());
                if (TPwdTv.getText().toString().equals(t.getPassword())) {
                    Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
                    intent.putExtra("email", t.getEmail());
                    startActivity(intent);

                }
            }
        });
        signupTrav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivityTraveler.class);
                startActivity(intent);
            }
        });


    }


}