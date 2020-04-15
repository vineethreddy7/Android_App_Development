package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner foodSp,cusSp;
    TextView priceTv;
    CheckBox spicyCb;
    ImageView foodImg;
    String cuz[]={"Indian","Italian"};
    Food foods[] = new Food[6];
    ArrayList<String> cuzFoods= new ArrayList<String>();

public void enterFoodDetails(){
    foods[0]=new Food("Biryani",12.99,"biryani","Indian",true);
    foods[1]=new Food("Chole Bhature",10.5,"chole","Indian",true);
    foods[2]=new Food("Chicken Masala",11.99,"masala","Indian",true);
    foods[3]=new Food("Pasta",9.99,"pasta","Italian",false);
    foods[4]=new Food("Pizza",13.99,"pizza","Italian",false);
    foods[5]=new Food("Lasanga",10.99,"lazanga","Italian",false);

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //enter food details
        enterFoodDetails();


        foodSp=findViewById(R.id.spFood);
        cusSp=findViewById(R.id.spCuzin);
        priceTv=findViewById(R.id.tvPrice);
        spicyCb=findViewById(R.id.cbSpicy);
        foodImg=findViewById(R.id.imageView);

        ArrayAdapter ca=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,cuz);
        ca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cusSp.setAdapter(ca);

        cusSp.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    //get the cusine name which is the selectedCuz
    String selectedCuz = cuz[i];

    //clear the arraylist
     cuzFoods.clear();
    //search in the original array the foods array for any food belongs to the selectedCuz
    for(i=0;i<foods.length;i++)
        if(foods[i].getCusinine().equals(selectedCuz))
            //add the food to the cuzFood list
            cuzFoods.add(foods[i].getName());
        ArrayAdapter fa=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,cuzFoods);
        fa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSp.setAdapter(fa);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
