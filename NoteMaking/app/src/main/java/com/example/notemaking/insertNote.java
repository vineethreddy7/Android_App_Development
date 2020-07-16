package com.example.notemaking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;


public class insertNote extends AppCompatActivity {

    Toolbar TB2;
    EditText nTitle,nDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note);
        nTitle = findViewById(R.id.title);
        nDesc = findViewById(R.id.description);
        TB2 = findViewById(R.id.tB2);
        setSupportActionBar(TB2);
        getSupportActionBar().setTitle("New Note");
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}