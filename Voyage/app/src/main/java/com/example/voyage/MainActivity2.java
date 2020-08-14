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

public class MainActivity2 extends AppCompatActivity {
    Button loginHost,signupHost;
    TextView HEmailTv1,HPwdTv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        HEmailTv1 = findViewById(R.id.tv1HEmail);
        HPwdTv1 = findViewById(R.id.tv1hPwd);
        loginHost = (Button) findViewById(R.id.hostlogin);
        signupHost = (Button) findViewById(R.id.hostsignup);
        loginHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase db = new DataBase(MainActivity2.this);
                Hosts h = db.getHost(HEmailTv1.getText().toString());
                if(h.equals(0)){
                    Toast.makeText(MainActivity2.this,"User Not Found",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(HPwdTv1.getText().toString().equals(h.getPassword())){
                        Intent intent = new Intent(MainActivity2.this, HostHomeScreenActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
        signupHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, HostSignupActivity.class);
                startActivity(intent);
            }
        });
    }
}