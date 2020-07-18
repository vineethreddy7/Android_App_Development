package com.example.notemaking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class editNote extends AppCompatActivity {
    Toolbar TB;
    EditText nTitle,nDesc;
    Calendar c;
    String tDate;
    String cTime;
    NotesDB db;
    Notes n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Intent i = getIntent();
        Long id = i.getLongExtra("id",0);
        nTitle = findViewById(R.id.editTitile);

        nDesc = findViewById(R.id.editDesc);
        TB = findViewById(R.id.tB5);
        setSupportActionBar(TB);
        db = new NotesDB(this);
        n = db.getnote(id);
        nTitle.setText(n.getName());
        nDesc.setText(n.getDescription());
        getSupportActionBar().setTitle(n.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length()!=0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable e) {

            }
        });

        c = Calendar.getInstance();
        tDate = c.get(Calendar.MONTH)+"/"+(c.get(Calendar.DAY_OF_MONTH)+1)+"/"+c.get(Calendar.YEAR);
        cTime = c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.save){
            n.setName(nTitle.getText().toString());
            n.setDescription(nDesc.getText().toString());
            n.setDate(tDate);
            n.setTime(cTime);
            int id = db.editNote(n);

            Toast.makeText(this,"Note Updated",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),NoteDetails.class);
            i.putExtra("id",n.getId());
            startActivity(i);
            //goToMain();
        }
        if(item.getItemId()==R.id.notsave){
            Toast.makeText(this,"Note Not Updated",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent intent  = new Intent(this,NoteDetails.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}