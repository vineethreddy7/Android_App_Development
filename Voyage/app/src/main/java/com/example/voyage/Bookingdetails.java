package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Bookingdetails extends AppCompatActivity {
    TextView bidtv,bnametv,bdatetv,bprivetv, bservicetv, btaxtv, bfinaltv;
    Button done;
    DataBase db;
    Booking b;
    long id;
    Double servicecharge;
    Double price, tax, finalprice;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingdetails);
        bidtv = findViewById(R.id.tvfinalbid);
        bnametv = findViewById(R.id.tvfinalbname);
        bdatetv = findViewById(R.id.tvfinalbdate);
        bprivetv = findViewById(R.id.tvfinalbprice);
        bservicetv = findViewById(R.id.tvservice);
        btaxtv = findViewById(R.id.tvTax);
        bfinaltv = findViewById(R.id.tvfinalbfinal);
        done = findViewById(R.id.btnDone);
        bookingpage bp = new bookingpage();
        Intent i = getIntent();
        id = i.getLongExtra("id",0);
        Log.d("ID is ",""+id);
        email = i.getStringExtra("email");
        Log.d("Email789 is",""+email);
        db = new DataBase(this);
        b = db.getBooking(id);
        bidtv.setText(String.valueOf(b.getId()));
        bnametv.setText(i.getStringExtra("name"));
        bdatetv.setText(i.getStringExtra("date"));
        price = i.getDoubleExtra("price",0);
        bprivetv.setText("$"+String.valueOf(price));
        servicecharge = price*10/100;
        tax = (price+servicecharge)*13/100;
        bservicetv.setText("$"+String.valueOf(Math.round(servicecharge)));
        btaxtv.setText("$"+String.valueOf(Math.round(tax)));
        finalprice = price + servicecharge + tax;
        bfinaltv.setText("$"+String.valueOf(Math.round(finalprice)));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(Bookingdetails.this,HomeScreenActivity.class);
                it2.putExtra("email",email);
                startActivity(it2);
            }
        });

    }
}