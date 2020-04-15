package com.example.teststudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class secongpage extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    TextView tvn,tvf,tvh,f;
    RadioGroup r;
    Spinner s;
    ListView lvf;
    Button btnb;
    CheckBox cba,cbb;
    ArrayList<Courses> cdata= new ArrayList<Courses>();
    String course[] = {"Java","Swift","iOS","Android","Database"};
    double fees = 0,hours=0;
    int req = 0;

    public void fill(){
        cdata.add(new Courses("Java",1300,6));
        cdata.add(new Courses("Swift",1500,5));
        cdata.add(new Courses("iOS",1350,5));
        cdata.add(new Courses("Android",1400,7));
        cdata.add(new Courses("Database",1000,4));
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secongpage);
        fill();
        tvn=findViewById(R.id.name2Tv);
        tvf=findViewById(R.id.tvFFees);
        tvh=findViewById(R.id.tvTHours);
        r=findViewById(R.id.rg);
        s=findViewById(R.id.sp);
        lvf=findViewById(R.id.lv);
        cba=findViewById(R.id.cb1);
        cbb=findViewById(R.id.cb2);
        btnb=findViewById(R.id.fpp);
        f=findViewById(R.id.fin);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,course);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(aa);

        s.setOnItemSelectedListener(this);
        lvf.setOnItemClickListener(this);

        r.setOnCheckedChangeListener(this);
        cba.setOnCheckedChangeListener(this);
        cbb.setOnCheckedChangeListener(this);
        btnb.setOnClickListener(this);




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        lvf.setAdapter(new listAdapter(this, cdata));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group.getCheckedRadioButtonId()==R.id.rb1)
        {
            req=21;
        }
        else if(group.getCheckedRadioButtonId()==R.id.rb2)
        {
            req=19;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        fees+=cdata.get(i).getFees();
        hours+=cdata.get(i).getHours();

        tvf.setText(String.format("%.2f",fees));
        tvh.setText(String.format("%.2f",hours));

    }





    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(cba.isChecked())
        {
            fees+=1000;
        }
        else
        {
            fees-=1000;
        }
        if(cbb.isChecked())
        {
            fees+=700;
        }
        else
        {
            fees-=700;
        }
    }

    @Override
    public void onClick(View v) {
        f.setText(String.format("%.2f",fees));
        fees=0;hours=0;
    }


}
