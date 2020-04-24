package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class detailspage extends AppCompatActivity {
    TextView nameTv,allTv,actTv,dTv,rTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailspage);
        nameTv=findViewById(R.id.tvCon);
        allTv=findViewById(R.id.tvAll);
        actTv=findViewById(R.id.tvAct);
        dTv=findViewById(R.id.tvD);
        rTv=findViewById(R.id.tvR);

        nameTv.setText(MainActivity.cname);
        allTv.setText(String.format("%d",MainActivity.allcases));
        actTv.setText(String.format("%d",MainActivity.active));
        dTv.setText(String.format("%d",MainActivity.deaths));
        rTv.setText(String.format("%d",MainActivity.recovery));

    }
}
