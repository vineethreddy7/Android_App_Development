package com.example.androidtourismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Booking extends AppCompatActivity implements View.OnClickListener{
    EditText cName;

    EditText cNum;
    Button pb;
    public static String name1,number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        cName = findViewById(R.id.CustomerName);
        cNum = findViewById(R.id.CardNumber);
        pb = findViewById(R.id.payBtn);
        pb.setOnClickListener(this);
    }
    public void onClick(View v) {
            name1=cName.getText().toString();
            number=cNum.getText().toString();
            Intent intent = new Intent(this, Recipt.class);
            startActivity(intent);
    }

}
