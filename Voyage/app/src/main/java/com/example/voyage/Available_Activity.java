package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.List;

public class Available_Activity extends AppCompatActivity {
    RecyclerView hostplacesRV;
    Button addPlace,delPlace;
    DataBase db;
    String email;
    int flag = 0;

    list_offering_adapter ad;
    List<Offering> o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_available_);
        db = new DataBase(this);
        Intent i = getIntent();
        email = i.getStringExtra("email");
        o = db.getOfferings(email);
        hostplacesRV = findViewById(R.id.rvHostPlaces);
        addPlace = findViewById(R.id.btnAddplace);
      //  delPlace = findViewById(R.id.btnPlacedel);

        hostplacesRV.setLayoutManager(new LinearLayoutManager(this));
        ad = new list_offering_adapter(this,o);
        hostplacesRV.setAdapter(ad);

        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                Intent i = new Intent(Available_Activity.this,Hostplacedetails.class);
                i.putExtra("flag",flag);
                i.putExtra("email",email);
                startActivity(i);

            }
        });
    }
}