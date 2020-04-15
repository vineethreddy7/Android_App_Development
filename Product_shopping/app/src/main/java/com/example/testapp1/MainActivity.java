package com.example.testapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    ListView lv;
    ArrayList<products> prod = new ArrayList<products>();
    String brands[] = {"Apple","Samsung"};
    ArrayList<products> selprod = new ArrayList<products>();
    Spinner sp;
    public static String spn;
    public static double spp;
    public static int spi;

    public void fill(){
        prod.add(new products("Apple","Apple TV",10.99,R.drawable.appletv));
        prod.add(new products("Samsung","Buds Plus",7.99,R.drawable.budsplus));
        prod.add(new products("Apple","Iphone 11 Pro",9.33,R.drawable.iphone11pro));
        prod.add(new products("Apple","Iphone 11 Pro Max",12.33,R.drawable.iphone11promax));
        prod.add(new products("Samsung","s20 Plus",9.33,R.drawable.s20plus));
        prod.add(new products("Apple","Macbook Pro 13",14.99,R.drawable.macbookpro13));
        prod.add(new products("Samsung","S20 Untra",13.33,R.drawable.s20ultra));
        prod.add(new products("Apple","Watch",4.99,R.drawable.watch));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fill();
       lv=findViewById(R.id.lvProducts);
        //lv.setAdapter(new List_Adapter(this,prod));
        sp=findViewById(R.id.spBrand);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,brands);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(aa);

        sp.setOnItemSelectedListener(this);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

            selprod.clear();
            String make = prod.get(i).getBrand();
            for (int j = 0; j < prod.size(); j++) {
                if (prod.get(j).getBrand() == make) {
                    selprod.add(prod.get(j));
                }
            }
            lv.setAdapter(new List_Adapter(this, selprod));


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

            spn=selprod.get(i).getName();
            spp=selprod.get(i).getPrice();
            spi=selprod.get(i).getImage();
            Intent intent = new Intent(this,Productdetails.class);
            startActivity(intent);
    }
}
