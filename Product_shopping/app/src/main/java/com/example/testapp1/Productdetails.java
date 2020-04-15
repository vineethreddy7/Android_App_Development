package com.example.testapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Productdetails extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    TextView Name1Tv,Price2Tv,finalprice;
    ImageView Img22;
    RadioGroup r;
    CheckBox c1,c2;
    Button click;
    double price1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Name1Tv=findViewById(R.id.tv2Name);
        Price2Tv=findViewById((R.id.tv2Price));
        Img22=findViewById(R.id.img1);
        r=findViewById(R.id.rg);
        c1=findViewById(R.id.cb1);
        c2=findViewById(R.id.cb2);
        click=findViewById(R.id.btn);
        finalprice=findViewById(R.id.fin);

        Name1Tv.setText(MainActivity.spn);
        Price2Tv.setText(String.format("%.2f",MainActivity.spp));
        Img22.setImageResource(MainActivity.spi);

        r.setOnCheckedChangeListener(this);
        c1.setOnCheckedChangeListener(this);
        c2.setOnCheckedChangeListener(this);
        click.setOnClickListener(this);

        price1=Double.parseDouble(Price2Tv.getText().toString());


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group.getCheckedRadioButtonId()==R.id.rg1)
        {
            price1*=1.5;
        }
        else if(group.getCheckedRadioButtonId()==R.id.rg2)
        {
            price1+=1;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(c1.isChecked()){
            price1+=10;
        }
        else
        {
            price1-=10;
        }
        if(c2.isChecked()){
            price1+=5;
        }
        else
        {
            price1-=5;
        }
    }

    @Override
    public void onClick(View v) {
        finalprice.setText(String.format("%.2f",price1));
        price1=0;
    }
}
/*if(firstT.getText().toString().equals("") || secondT.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(),"Please enter both numbers",Toast.LENGTH_LONG).show();
   else{
            num1 = Double.parseDouble(firstT.getText().toString());
            num2 = Double.parseDouble(secondT.getText().toString());
        }*/