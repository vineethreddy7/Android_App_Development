package com.example.notemaking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.UUID;

public class audioactivity extends AppCompatActivity {
    Toolbar tb;
    Button record,stop,play,pause;
    String audipath;
    MediaRecorder mr;
    MediaPlayer mp;
    final int REQUEST_PERMISSION_CODE = 1000;
    public static String audpath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audioactivity);
        tb = findViewById(R.id.tB6);
        record = findViewById(R.id.record);
        stop = findViewById(R.id.stop);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Audio");

        if(!checkpermission())
            request();

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkpermission()){
                    audipath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + UUID.randomUUID().toString() + "_audio_record.3gp";
                    setuprec();
                    try {
                        mr.prepare();
                        mr.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    play.setEnabled(false);
                    pause.setEnabled(false);
                    Toast.makeText(audioactivity.this, "Recording", Toast.LENGTH_SHORT).show();

                }
                else{
                    request();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mr.stop();
                audpath = audipath;
                stop.setEnabled(false);
                play.setEnabled(true);
                record.setEnabled(true);
                pause.setEnabled(false);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause.setEnabled(true);
                stop.setEnabled(false);
                record.setEnabled(false);

                mp = new MediaPlayer();
                try {
                    mp.setDataSource(audipath);
                    mp.prepare();
                }catch (IOException e){
                    e.printStackTrace();
                }
                mp.start();
                Toast.makeText(audioactivity.this, "Playing", Toast.LENGTH_SHORT).show();


            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop.setEnabled(false);
                record.setEnabled(true);
                pause.setEnabled(false);
                play.setEnabled(true);

                if(mp != null){
                    mp.stop();
                    mp.release();
                    setuprec();

                }
            }
        });
    }
    private void setuprec(){
        mr = new MediaRecorder();
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mr.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mr.setOutputFile(audipath);

    }
    private boolean checkpermission(){

        int write_external  = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int audio_result = ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO);
        return write_external == PackageManager.PERMISSION_GRANTED && audio_result == PackageManager.PERMISSION_GRANTED;
    }
    private void request(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO},REQUEST_PERMISSION_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.audiomenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.save){
           // Intent i = new Intent(this,insertNote.class);
          //  i.putExtra("audiopath",audipath);
          //  startActivity(i);
            audpath = audipath;
            Toast.makeText(audioactivity.this, "Audio Saved", Toast.LENGTH_SHORT).show();


        }
        if(item.getItemId()==R.id.discard){
            Intent i = new Intent(this,insertNote.class);
            startActivity(i);


        }
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}