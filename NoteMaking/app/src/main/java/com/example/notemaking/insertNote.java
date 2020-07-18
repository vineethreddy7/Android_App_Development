package com.example.notemaking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class insertNote extends AppCompatActivity  {

    Toolbar TB2;
    EditText nTitle,nDesc;
    private ImageView iv;
    Calendar c;
    String tDate;
    String cTime;
    public static final int pickImage = 10;
    private Uri path;
    private Bitmap image;
    byte[] byteArray;
    String str;
    byte[] imgarray;
    Bitmap selectedImage;
    Button play;
    int i = 0;
    String audipath;
    MediaRecorder mr;
    MediaPlayer mp;
    final int REQUEST_PERMISSION_CODE = 1000;
    private FusedLocationProviderClient client;
    Double lat,lon;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note);
        nTitle = findViewById(R.id.title);
        nDesc = findViewById(R.id.description);
        TB2 = findViewById(R.id.tB2);
        iv = findViewById(R.id.iV1);
       // play = findViewById(R.id.temp);
        client = LocationServices.getFusedLocationProviderClient(this);
        requestloc();

        if(ActivityCompat.checkSelfPermission(insertNote.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        client.getLastLocation().addOnSuccessListener(insertNote.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                }
            }
        });
        // mic = findViewById(R.id.mic);
       // mic.setOnClickListener(this);
        setSupportActionBar(TB2);
        getSupportActionBar().setTitle("New Note");
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
        inflater.inflate(R.menu.save_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void requestloc(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {
            if (resultCode == RESULT_OK && data != null) {
                selectedImage = (Bitmap) data.getExtras().get("data");
              //  ByteArrayOutputStream stream = new ByteArrayOutputStream();
               // selectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
              //  byteArray = stream.toByteArray();

              //  str = new String(byteArray, StandardCharsets.UTF_8);
             str = convertToBase64(selectedImage);



                Bitmap newimg = convertToBitmap(str);

                //imgarray = str.getBytes();

               // Bitmap newimg = BitmapFactory.decodeByteArray(imgarray, 0, imgarray.length);

                iv.setImageBitmap(newimg);
            }
        }
    }
    public String convertToBase64(Bitmap bitmap) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
        byte[] byteArray = os.toByteArray();
        return Base64.encodeToString(byteArray, 0);
    }

    public Bitmap convertToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap bitmapResult = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmapResult;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.savebtn){
            Log.d("Location",""+lat+" "+lon);
          //  Intent i = getIntent();
          //  String audpath = i.getStringExtra("audiopath");

            Notes n = new Notes(notecategory.sname,nTitle.getText().toString(),nDesc.getText().toString(),tDate,cTime,str.toString(),audioactivity.audpath,lat,lon);
            SubjectsClass s = new SubjectsClass(notecategory.sname);
            NotesDB db = new NotesDB(this);

            long id11 = db.addNote(n);
          //  Notes test = db.getnote(id);
            db.addSubject(s);
            Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show();
            goToMain();
        }
        if(item.getItemId()==R.id.delbtn){
            Toast.makeText(this,"Note Not Saved",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        if(item.getItemId()==R.id.cambtn){
                Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
        }

        if(item.getItemId()==R.id.mic){

         startActivity(new Intent(this,audioactivity.class));
            }
        if (item.getItemId()==android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();

                }
                break;
            }
        }
    }

    private void goToMain() {
        Intent intent  = new Intent(this,MainActivity.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}