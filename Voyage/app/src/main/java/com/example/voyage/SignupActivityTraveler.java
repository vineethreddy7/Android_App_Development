package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivityTraveler extends AppCompatActivity {
    Button ButtonTravCreate, ButtonTravCancel;
    TextView tfnameTv,tlnameTv,temailTv,tphoneTv,tpwdTv,trpwdTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup_traveler);
        tfnameTv = findViewById(R.id.tvTFName);
        tlnameTv = findViewById(R.id.tvTLName);
        temailTv = findViewById(R.id.tv1TEmail);
        tphoneTv = findViewById(R.id.tvTPhone);
        tpwdTv = findViewById(R.id.tv1TPwd);
        trpwdTv = findViewById(R.id.tvTRPwd);
        ButtonTravCreate = (Button) findViewById(R.id.TButtonCreate);
        ButtonTravCancel = (Button) findViewById(R.id.TButtonCancel);
        ButtonTravCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tpwdTv.getText().toString().equals(trpwdTv.getText().toString())) {
                    Travellers t = new Travellers(tfnameTv.getText().toString(),tlnameTv.getText().toString(),temailTv.getText().toString(),tphoneTv.getText().toString(),tpwdTv.getText().toString());
                    DataBase db = new DataBase(SignupActivityTraveler.this);
                    db.addTraveller(t);
                    Toast.makeText(SignupActivityTraveler.this,"Profile Created",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivityTraveler.this, MainActivity.class);
                    startActivity(intent);
                }
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