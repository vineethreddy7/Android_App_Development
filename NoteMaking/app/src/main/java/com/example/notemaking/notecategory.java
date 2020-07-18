package com.example.notemaking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class notecategory extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Toolbar tbC;
    ListView LV;
    EditText cname;
    public static String sname;
    String search;

    ArrayList<String> subjects = new ArrayList<String>();
    ArrayList<String> subs1 = new ArrayList<String>();

    NotesDB db = new NotesDB(this);
    List<Notes> n;
    int i=0;

   // ArrayList<Notes> notestemp = new ArrayList<Notes>();

    public void fill(){
        n=db.getNotes11();
        for(i=0;i<n.size();i++){
            if(!subjects.contains(n.get(i).getSubject())) {
                subjects.add(n.get(i).getSubject());
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notecategory);
        tbC=findViewById(R.id.tB3);
        cname=findViewById(R.id.catName);
        setSupportActionBar(tbC);

        fill();
        LV=findViewById(R.id.lV);
        LV.setAdapter(new list_category_adapter(this,subjects));
        LV.setOnItemClickListener(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.categorymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.addCat){
            subjects.add(cname.getText().toString());
            LV.refreshDrawableState();
            Toast.makeText(this,"Category Added",Toast.LENGTH_SHORT).show();

        }
        if(item.getItemId()==R.id.delCat){
            subjects.remove(cname.getText().toString());
            Toast.makeText(this,"Category Deleted",Toast.LENGTH_SHORT).show();

        }
        if(item.getItemId()==R.id.searchCat){
            subs1.clear();
            subs1.addAll(subjects);
            if(cname.getText().toString()!=null) {
                if (subs1.contains(cname.getText().toString())) {
                    search = cname.getText().toString();
                    subs1.clear();
                    subs1.add(cname.getText().toString());
                    LV.setAdapter(new list_category_adapter(this,subs1));
                }
                else{
                    LV.setAdapter(new list_category_adapter(this,subjects));

                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        sname = subjects.get(i);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}