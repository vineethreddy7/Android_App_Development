package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Othersvisited extends AppCompatActivity {
    RecyclerView rvPOV;
    list_pov_adapter pov;
   DataBase db;
    List<Booking> b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othersvisited);
        db = new DataBase(this);
        b = db.getBooking3();
        rvPOV = findViewById(R.id.rvPOV);
        rvPOV.setLayoutManager(new LinearLayoutManager(this));
        pov = new list_pov_adapter(this,b,db);
        pov.notifyDataSetChanged();
        rvPOV.setAdapter(pov);
    }
}