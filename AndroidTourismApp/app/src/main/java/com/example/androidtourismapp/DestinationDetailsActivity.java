package com.example.androidtourismapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DestinationDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv1,et,et2;
    ImageView iv;
    Button bn,vmap;
    public static String cityname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_details);
        tv1=findViewById(R.id.textView7);
        tv1.setText(MainActivity.name);
        et=findViewById(R.id.textView8);
        et.setText(MainActivity.text);
        et2=findViewById(R.id.textView9);
        et2.setText(String.format("%.2f",MainActivity.price));
        iv=findViewById(R.id.imageView3);
        iv.setImageResource(MainActivity.img);
        bn = findViewById(R.id.bookbtn);
        vmap=findViewById(R.id.button2);
        bn.setOnClickListener(this);
        vmap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bn)
        {
            Intent intent = new Intent(this, Booking.class);
            startActivity(intent);
        }
        else if(v==vmap)
        {
            cityname=tv1.getText().toString();

            Intent intent = new Intent(this,MapsActivity.class);
            startActivity(intent);
        }
    }
}
