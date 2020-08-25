package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class Mybookings extends AppCompatActivity {
    RecyclerView mbRV;
    list_mybooking_adapter ab;
    DataBase db;
    List<Booking> b;
    String email;
    Offering o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybookings);
        db = new DataBase(this);
        Intent i = getIntent();
        email = i.getStringExtra("email");
        b = db.getBooking2(email);
        mbRV = findViewById(R.id.rvMybooking);
        mbRV.setLayoutManager(new LinearLayoutManager(this));
        ab = new list_mybooking_adapter(this,b,db);
        ab.notifyDataSetChanged();
        mbRV.invalidate();
        mbRV.setAdapter(ab);
    }
}