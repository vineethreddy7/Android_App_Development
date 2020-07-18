package com.example.notemaking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    
    RecyclerView RV;
    Toolbar TB;
    Spinner sp;
    NotesDB db;
    list_note_adapter ad;
    List<Notes> note;
    String items[] = {"Sort by Title","Sort by Date"};
    ArrayList<Notes> selects = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Long> ids1 = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new NotesDB(this);
        note = db.getNotes(notecategory.sname);
        RV = findViewById(R.id.rV); 
        TB = findViewById(R.id.tB);
        sp = findViewById(R.id.sp);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(aa);

        sp.setOnItemSelectedListener(this);
        setSupportActionBar(TB);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RV.setLayoutManager(new LinearLayoutManager(this));
        ad = new list_note_adapter(this,note);
        RV.setAdapter(ad);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add){
            Intent i = new Intent(this,insertNote.class);
            startActivity(i);
        }
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selects.clear();

        names.clear();
        if (items[i] == "Sort by Title") {

            selects.clear();
            names.clear();
            ids1.clear();
                ids1.addAll(NotesDB.ids);
                for(int j=0;j<ids1.size();j++){
                    names.add(db.getnote(ids1.get(j)).getName());
                  //  Log.d("ids are",""+db.getnote(ids1.get(j)).getName());

                }
            String sel[] = new String[names.size()];

            Arrays.fill(sel,null);
                for(int k =0;k<names.size();k++){
                    sel[k] = db.getnote(ids1.get(k)).getName();
                }

                Arrays.sort(sel);
                for(int f=0;f<sel.length;f++){
                    for(int d=0;d<ids1.size();d++) {
                        if (sel[f].equals(db.getnote(ids1.get(d)).getName())) {
                            selects.add(db.getnote(ids1.get(d)));
                            Log.d("id name ",""+db.getnote(ids1.get(f)).getName()+sel[d]);

                          //  break;
                        }
                    }
                }
                RV.setAdapter(new list_note_adapter(this,selects));


            }
        else{
            RV.setAdapter(new list_note_adapter(this,note));
        }

    }







    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
