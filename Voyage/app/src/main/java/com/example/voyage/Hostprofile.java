package com.example.voyage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Hostprofile extends AppCompatActivity implements View.OnClickListener {
TextView HFName3tv,HLName3tv,HPhone3tv,HEmail3tv;
ImageButton HImageiv;
Button HSave,HCancel,imagebtn;
DataBase db;
Hosts h;
Bitmap selectedImage;
String str = "";
String email;
Bitmap newimage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostprofile);
        HFName3tv = findViewById(R.id.tv3HFName);
        HLName3tv = findViewById(R.id.tv3HLName);
        HPhone3tv = findViewById(R.id.tv3HPhone);
        HEmail3tv = findViewById(R.id.tv3HEmail);
        HImageiv = findViewById(R.id.ibHost);
        HSave = findViewById(R.id.btn3HSave);
        HCancel = findViewById(R.id.btnCancel3);
        //imagebtn = (Button)findViewById(R.id.btnImg);
        db = new DataBase(this);
        Intent i = getIntent();
        email = i.getStringExtra("email");
        h = db.getHost(email);
        HFName3tv.setText(h.getFirstname());
        HLName3tv.setText(h.getLastname());
        HEmail3tv.setText(h.getEmail());
        HPhone3tv.setText(h.getPhone());
        HImageiv.setImageBitmap(convertToBitmap(h.getImage()));
        HImageiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageSelect();
            }
        });

        HSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h.setFirstname(HFName3tv.getText().toString());
                h.setLastname(HLName3tv.getText().toString());
                h.setEmail(HEmail3tv.getText().toString());
                h.setPhone(HPhone3tv.getText().toString());
                if(str.equals("")) {
                    h.setImage(h.getImage());
                }
                else{
                    h.setImage(str);
                }
                db.editHost(h);
                Toast.makeText(Hostprofile.this,"Successful",Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void imageSelect(){
        final CharSequence[] choice = {"Take a Photo","Pick from Gallery","Cancel"};
        Log.d("...ddd...","its done");
        AlertDialog.Builder b = new AlertDialog.Builder(Hostprofile.this);
        b.setTitle("Add Image");

        b.setItems(choice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(choice[i].equals("Take a Photo")){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);
                }
                else if(choice[i].equals("Pick from Gallery")){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,1);
                }
                else if(choice[i].equals("Cancel")){
                    dialogInterface.dismiss();
                }
            }
        });
        b.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED) {
            if (resultCode == RESULT_OK && data != null) {
                  if (requestCode == 0) {
                selectedImage = (Bitmap) data.getExtras().get("data");
                str = convertToBase64(selectedImage);
                      newimage = convertToBitmap(str);


                  }
          if (requestCode == 1) {
                Uri selected = data.getData();
                String[] path = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selected, path, null, null, null);
                c.moveToFirst();
                int index = c.getColumnIndex(path[0]);
                String pic = c.getString(index);
                str = pic;
                newimage = (BitmapFactory.decodeFile(pic));


             // str = convertToBase64(bp);
                c.close();
            }

            }
            HImageiv.setImageBitmap(newimage);
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
    public void onClick(View view) {

    }
}



/*    if (requestCode == 1) {
        Uri selected = data.getData();
        String[] path = {MediaStore.Images.Media.DATA};
        Cursor c = getContentResolver().query(selected, path, null, null, null);
        c.moveToFirst();
        int index = c.getColumnIndex(path[0]);
        str = c.getString(index);
        c.close();

        }

        */

/*
h.setFirstname(HFName3tv.getText().toString());
                h.setLastname(HLName3tv.getText().toString());
                h.setEmail(HEmail3tv.getText().toString());
                h.setPhone(HPhone3tv.getText().toString());
                h.setImage(str);
                db.editHost(h);
                Toast.makeText(Hostprofile.this,"Successful",Toast.LENGTH_SHORT).show();
 */