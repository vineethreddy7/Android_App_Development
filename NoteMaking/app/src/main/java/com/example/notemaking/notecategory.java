package com.example.notemaking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class notecategory extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Toolbar tbC;
    ListView LV;

    ArrayList<Notes> notestemp = new ArrayList<Notes>();

    public void fill(){
        notestemp.add(new Notes("Business",null,null,null,null,1));
        notestemp.add(new Notes("Sports",null,null,null,null,2));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notecategory);
        tbC=findViewById(R.id.tB3);
        setSupportActionBar(tbC);
        fill();
        LV=findViewById(R.id.lV);
        LV.setAdapter(new list_category_adapter(this,notestemp));
        LV.setOnItemClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.categorymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}