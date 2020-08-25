package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class Maps extends AppCompatActivity {
    String name, place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent i1 = getIntent();
        name = i1.getStringExtra("name");
        place = i1.getStringExtra("place");
        Uri u = Uri.parse("geo:0,0?q="+name+","+place);
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        i.setPackage("com.google.android.apps.maps");
        startActivity(i);
    }
}