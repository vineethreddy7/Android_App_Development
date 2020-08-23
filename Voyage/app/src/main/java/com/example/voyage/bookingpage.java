package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class bookingpage extends AppCompatActivity {
    DataBase db;
    Offering o;
    TextView tvbname,tvbplace,tvbrating,tvbdesc;
    ImageView ivbimage;
    Button booking, datepick;
    String name;
    String pickdate = "Pick a Date";
    String email1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingpage);
        Intent i = getIntent();
        name = i.getStringExtra("name");

        Log.d("Name2",""+name);
        db = new DataBase(this);
        o = db.getO(name);
        tvbname = findViewById(R.id.tvbookingName);
        tvbplace = findViewById(R.id.tvbookingPlace);
        tvbrating = findViewById(R.id.tvbookingRating);
        tvbdesc = findViewById(R.id.tvbookingDesc);
        ivbimage = findViewById(R.id.ivbookingImage);
        booking = findViewById(R.id.btnbookingBook);
        datepick = findViewById(R.id.btndatepick);
        tvbname.setText(o.getName());
        tvbplace.setText(o.getPlace());
        tvbrating.setText(String.valueOf(o.getRating()));
        tvbdesc.setText(o.getDescription());
        ivbimage.setImageBitmap(convertToBitmap(o.getPhoto()));
        booking.setText("Book now at $"+o.getPrice());
        Intent intt = getIntent();
        pickdate = intt.getStringExtra("date");

            datepick.setText(pickdate);

        HomeScreenActivity hm = new HomeScreenActivity();
        email1 = hm.email11;
        Log.d("Email is ",""+email1);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Booking b = new Booking(pickdate,o.getId(),o.getHostemail(),email1);
                long id = db.addBooing(b);
                Toast.makeText(bookingpage.this,"Booking Added "+id,Toast.LENGTH_SHORT).show();
            }
        });
        datepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in1 = new Intent(bookingpage.this,Calendar.class);
                in1.putExtra("name",name);
                startActivity(in1);
            }
        });

    }

    public Bitmap convertToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap bitmapResult = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmapResult;
    }
}