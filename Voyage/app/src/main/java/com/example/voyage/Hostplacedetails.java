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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Hostplacedetails extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    int f;
    TextView onametv,opricetv,oplacetv,odesctv;
    Spinner osp;
    ImageButton ibO;
    Button donebtn,cancelbtn;
    Bitmap selectedImage;
    String str = "";
    Bitmap newimage;
    String email;
    String type = "";

    String items[] = {"Boating","Kayak","Trekking","Scuba Diving","River Raft","Surfing","Skydive","SnowBoard","Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostplacedetails);
        onametv = findViewById(R.id.tvOName);
        opricetv = findViewById(R.id.tvOPrice);
        oplacetv = findViewById(R.id.tvOPlace);
        odesctv = findViewById(R.id.tvODesc);
        osp = findViewById(R.id.spOType);

        ArrayAdapter aa = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        osp.setAdapter(aa);
        osp.setOnItemSelectedListener(this);
        ibO = findViewById(R.id.ivOImage);
        donebtn = findViewById(R.id.btnODone);
        cancelbtn = findViewById(R.id.btnOCancel);
        Intent i = getIntent();
        email = i.getStringExtra("email");
        ibO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageSelect();
            }
        });
        Intent intent = getIntent();
        f = intent.getIntExtra("flag",0);
        Log.d("Flag",""+f);
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Offering o = new Offering(onametv.getText().toString(),str,Float.parseFloat(opricetv.getText().toString()),oplacetv.getText().toString(),0.0,0.0,0.0,odesctv.getText().toString(),email,"",type);
                DataBase db = new DataBase(Hostplacedetails.this);
                long id = db.addOffering(o);
                Toast.makeText(Hostplacedetails.this,"Offering Added "+id,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Hostplacedetails.this,Available_Activity.class));
            }
        });
    }

    private void imageSelect(){
        final CharSequence[] choice = {"Take a Photo","Pick from Gallery","Cancel"};
        Log.d("...ddd...","its done");
        AlertDialog.Builder b = new AlertDialog.Builder(Hostplacedetails.this);
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
            ibO.setImageBitmap(newimage);
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        type = items[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}