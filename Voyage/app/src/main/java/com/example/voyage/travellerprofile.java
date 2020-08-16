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
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class travellerprofile extends AppCompatActivity {
    TextView FnameTtv,LnameTtv,PhoneTtv,EmailTtv;
    ImageButton TImgbtn;
    Button TSaveB,TCancelB;
    DataBase db;
    Travellers t;
    Bitmap selectedImage;
    String str;
    String email;
    Bitmap newimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travellerprofile);
        FnameTtv = findViewById(R.id.tv3TFname);
        LnameTtv = findViewById(R.id.tv3TLname);
        PhoneTtv = findViewById(R.id.tv3TPhone);
        EmailTtv = findViewById(R.id.tv3TEmail);
        TImgbtn = findViewById(R.id.ibTraveller);
        TSaveB = findViewById(R.id.btnTSave);
        TCancelB = findViewById(R.id.btnTCancel);
        db = new DataBase(this);
        Intent i = getIntent();
        email = i.getStringExtra("email");
        t = db.getTraveller(email);
        String img = t.getImage();
        FnameTtv.setText(t.getFname());
        LnameTtv.setText(t.getLname());
        PhoneTtv.setText(t.getPhone());
        EmailTtv.setText(t.getEmail());
        byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
        Bitmap br = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);

        TImgbtn.setImageBitmap(br);
        TImgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageSelect();
            }
        });

        TSaveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setFname(FnameTtv.getText().toString());
                t.setLname(LnameTtv.getText().toString());
                t.setEmail(EmailTtv.getText().toString());
                t.setPhone(PhoneTtv.getText().toString());
                t.setImage(str);
                db.editTraveller(t);
                Toast.makeText(travellerprofile.this,"Successful",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void imageSelect(){
        final CharSequence[] choice = {"Take a Photo","Pick from Gallery","Cancel"};
        Log.d("...ddd...","its done");
        AlertDialog.Builder b = new AlertDialog.Builder(travellerprofile.this);
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
            TImgbtn.setImageBitmap(newimage);
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

}