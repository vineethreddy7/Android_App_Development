package com.example.notemaking;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;


public class NoteDetails extends AppCompatActivity {
    Toolbar tB;
    TextView detailsTV;
    NotesDB db;
    Notes n;
    Button play,pause;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        detailsTV = findViewById(R.id.tvDetails);
        tB=findViewById(R.id.tB4);
        setSupportActionBar(tB);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);

        Intent i = getIntent();
        Long id = i.getLongExtra("id",0);
        //Toast.makeText(this,""+id,Toast.LENGTH_SHORT).show();

        db = new NotesDB(this);
        n = db.getnote(id);
        getSupportActionBar().setTitle(n.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailsTV.setText(n.getDescription());

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause.setEnabled(true);


                mp = new MediaPlayer();
                try {
                    mp.setDataSource(n.getAudio());
                    mp.prepare();
                }catch (IOException e){
                    e.printStackTrace();
                }
                mp.start();
                Toast.makeText(NoteDetails.this, "Playing", Toast.LENGTH_SHORT).show();


            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pause.setEnabled(false);
                play.setEnabled(true);

                if(mp != null){
                    mp.stop();
                    mp.release();

                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.editbtn){
                Intent i = new Intent(this,editNote.class);
                i.putExtra("id",n.getId());
                startActivity(i);
        }
        if(item.getItemId()==R.id.delnote){
            db.delNote(n.getId());
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.map){
            Intent i = new Intent(this,MapsActivity.class);
            i.putExtra("latitude",n.getLatitude());
            i.putExtra("longitude",n.getLongitude());
            startActivity(i);
        }
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}