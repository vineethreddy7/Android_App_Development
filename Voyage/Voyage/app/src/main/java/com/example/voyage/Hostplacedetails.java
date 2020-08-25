package com.example.voyage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Hostplacedetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {
    int f;
    TextView onametv,opricetv,oplacetv,odesctv;
    Spinner osp;
    ImageButton ibO;
    Button donebtn,cancelbtn,delbtn;
    Bitmap selectedImage;
    String str = "";
    Bitmap newimage;
    String email;
    String type = "";
    DataBase db;
    Offering of;
   // CheckBox c;
    String offer;
    String name;
    RadioGroup rg;
    RadioButton ybtn,nbtn;
    Long id11;
    final List<Bitmap> bm = new ArrayList<>();
    Bitmap bp;
    String values[];

    Resources r;


    String items[] = {"","Water Activities","Mountain Activities","Hotels","Restaurants","Cottages","Monument Visits","Snow Activities","Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostplacedetails);
        onametv = findViewById(R.id.tvOName);
        opricetv = findViewById(R.id.tvOPrice);
        oplacetv = findViewById(R.id.tvOPlace);
        odesctv = findViewById(R.id.tvODesc);
        osp = findViewById(R.id.spOType);
        rg = findViewById(R.id.rG);
        ybtn = findViewById(R.id.btnYes);
        nbtn = findViewById(R.id.btnNo);
        Resources r = getResources();
        values = r.getStringArray(R.array.categories);
      // ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.categories,android.R.layout.simple_spinner_item);
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        osp.setAdapter(aa);
        osp.setOnItemSelectedListener(this);
        ibO = findViewById(R.id.ivOImage);
        donebtn = findViewById(R.id.btnODone);
        cancelbtn = findViewById(R.id.btnOCancel);
        delbtn = findViewById(R.id.delBtn);
        Intent i = getIntent();

        email = i.getStringExtra("email");
        name = i.getStringExtra("name");
        db = new DataBase(this);
        ibO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageSelect();
            }
        });
        rg.setOnCheckedChangeListener(this);
        Intent intent = getIntent();
        f = intent.getIntExtra("flag",0);
        Log.d("Flag",""+f);
       if(f == 2){

           Toast.makeText(this,""+i.getStringExtra("name"),Toast.LENGTH_SHORT).show();
         //  Log.d("Email is",""+email);

            Offering o = db.getO(i.getStringExtra("name"));
            onametv.setText(o.getName());
            opricetv.setText(o.getPrice().toString());
            oplacetv.setText(o.getPlace());
            odesctv.setText(o.getDescription());
            str = o.getPhoto();
            ibO.setImageBitmap(convertToBitmap(o.getPhoto()));
            Log.d("Offer is ",""+o.getOffer());
            if(o.getOffer().equals("Yes")){
               ybtn.setChecked(true);
               nbtn.setChecked(false);
            }
            else if(o.getOffer().equals("No")){
                nbtn.setChecked(true);
                ybtn.setChecked(false);
            }
        }
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("The value is ",""+Double.valueOf(opricetv.getText().toString()));


                DataBase db = new DataBase(Hostplacedetails.this);
                if(f == 1) {
                    Offering o = new Offering(onametv.getText().toString(),str,Double.valueOf(opricetv.getText().toString()),oplacetv.getText().toString(),1.0,1.0,1.0,odesctv.getText().toString(),email,"",type,offer);

                    long id = db.addOffering(o);
                    Toast.makeText(Hostplacedetails.this, "Offering Added " + id, Toast.LENGTH_SHORT).show();
                }
                else if(f == 2){
                    of = db.getO(name);
                   // id11 = of.getId();
                    Log.d("Id is",""+id11);
                    of.setName(onametv.getText().toString());
                    of.setPrice(Double.valueOf(opricetv.getText().toString()));
                    of.setPlace(oplacetv.getText().toString());
                    of.setDescription(odesctv.getText().toString());
                    of.setPhoto(str);
                    of.setOffer(offer);
                    Toast.makeText(Hostplacedetails.this, "Offering Edited " + onametv.getText().toString(), Toast.LENGTH_SHORT).show();
                    int a = db.editOffering(of);
                  //  Toast.makeText(Hostplacedetails.this, "Offering Edited " + a, Toast.LENGTH_SHORT).show();
                }
                Intent it3 = new Intent(getApplicationContext(),Available_Activity.class);
                it3.putExtra("email",email);
                startActivity(it3);
            }
        });
       cancelbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(Hostplacedetails.this,Available_Activity.class));
           }
       });
       delbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               id11 = db.getO(name).getId();
               db.delOffering(id11);
               startActivity(new Intent(getApplicationContext(),Available_Activity.class));
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
                    if(ActivityCompat.checkSelfPermission(Hostplacedetails.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(Hostplacedetails.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                        return;
                    }
                    Intent intt = new Intent(Intent.ACTION_GET_CONTENT);
                  //  intt.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                    intt.setType("image/*");
                    startActivityForResult(intt,1);
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
                else if (requestCode == 1) {
                    ClipData cd = data.getClipData();

                    if(cd != null){
                        for(int i = 0; i<cd.getItemCount();i++){
                            Uri imgu = cd.getItemAt(i).getUri();
                            try{
                                InputStream is = getContentResolver().openInputStream(imgu);
                                bp = BitmapFactory.decodeStream(is);
                                bm.add(bp);

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
                    str = convertToBase64(bm.get(0));
                    newimage = bm.get(0);
                }
            }
            ibO.setImageBitmap(newimage);
        }
    }

    public String convertToBase64(Bitmap bitmap) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
        byte[] byteArray = os.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public Bitmap convertToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap bitmapResult = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmapResult;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        type = items[i];
        Log.d("Values are",""+type);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCheckedChanged(RadioGroup r, int i) {
        int id = r.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton)findViewById(id);
        offer = rb.getText().toString();
    }
}