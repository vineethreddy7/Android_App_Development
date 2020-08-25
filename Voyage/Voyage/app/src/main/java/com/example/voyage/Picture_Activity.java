package com.example.voyage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Picture_Activity extends AppCompatActivity {
    Button pick,addimgbtn;
    ImageView pickIV;
    EditText uploadimagename;
    final List<Bitmap> bm = new ArrayList<>();
    String str;
    Bitmap bp;
    int count1 = 0;
    int count2 = 0;

    ArrayList<String> imgs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_picture_);
        pick = findViewById(R.id.btnPick);
        pickIV = findViewById(R.id.ivUpload);
        addimgbtn = findViewById(R.id.btnaddimages);
        uploadimagename = findViewById(R.id.uploadimagenametv);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(Picture_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Picture_Activity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                    return;
                }
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                i.setType("image/*");
                startActivityForResult(i,1);
            }
        });
        addimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase db = new DataBase(Picture_Activity.this);
              //  Log.d("Images are ",""+imgs.size());
                for(int j = 0;j<imgs.size();j++) {
                    Images im = new Images(uploadimagename.getText().toString(), imgs.get(j));
                    db.addImages(im);
                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){

            ClipData cd = data.getClipData();

            if(cd != null){
                for(int i = 0; i<cd.getItemCount();i++){
                    Uri imgu = cd.getItemAt(i).getUri();
                    try{
                        InputStream is = getContentResolver().openInputStream(imgu);
                        bp = BitmapFactory.decodeStream(is);
                        bm.add(bp);
                        imgs.add(convertToBase64(bp));

                    }catch(FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
            }
            else{
                Uri u = data.getData();
                try{
                    InputStream is = getContentResolver().openInputStream(u);
                    bp = BitmapFactory.decodeStream(is);
                    bm.add(bp);
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(final Bitmap b : bm){
                        count1++;
                        str = convertToBase64(b);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pickIV.setImageBitmap(b);
                            }
                        });
                        try{
                            Thread.sleep(2000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
    public String convertToBase64(Bitmap bitmap) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
       // bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        byte[] byteArray = os.toByteArray();
        return Base64.encodeToString(byteArray, 0);
    }
}