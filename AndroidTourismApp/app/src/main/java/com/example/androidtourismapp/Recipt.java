package com.example.androidtourismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Recipt extends AppCompatActivity {
    EditText etn,etd,eta,etc;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipt);

       etn=findViewById(R.id.dName);
        etn.setText(Booking.name1);

        etd=findViewById(R.id.dDestination);
        etd.setText(MainActivity.name);

        eta=findViewById(R.id.dAmount);
        eta.setText(String.format("%.2f",MainActivity.price));

        etc=findViewById(R.id.dCard);
        etc.setText(Booking.number);


    }


}
