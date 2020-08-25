package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class Reservation_Activity extends AppCompatActivity {
    RecyclerView resRv;
    list_reservation_adapter ad;
    DataBase db;
    String email;
    List<Booking> b = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reservation_);
        Intent i = getIntent();
        email = i.getStringExtra("email");
        Log.d("Email that we got is",""+email);
        db = new DataBase(this);
        resRv = findViewById(R.id.rvReservations);
        b = db.getBooking1(email);
        resRv.setLayoutManager(new LinearLayoutManager(this));
        ad = new list_reservation_adapter(this,b);
        ad.notifyDataSetChanged();
        resRv.setAdapter(ad);
    }
}