package com.example.androidtourismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    public static int cNum;
    ListView destinationList;
    private static final String TAG = "MainActivity";
    ArrayList<Destination> destinations = new ArrayList<Destination>();
    public static String name;
    public static double price;
    public static String text;
    public static int img;
    public static double alat,along;
    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;


    public void fillDestinations(){
        destinations.add(new Destination("New York",2300,"New York City (NYC), often called the City of New York or simply New York (NY), is the most populous city in the United States. With an estimated 2018 population of 8,398,748 distributed over about 302.6 square miles (784 km2), New York is also the most densely populated major city in the United States.[10] Located at the southern tip of the U.S. state of New York, the city is the center of the New York metropolitan area, the largest metropolitan area in the world by urban landmass.[11] With almost 20 million people in its metropolitan statistical area and approximately 23 million in its combined statistical area, it is one of the world's most populous megacities. New York City has been described as the cultural, financial, and media capital of the world",R.drawable.usa, 40.73061,-73.935242));
        destinations.add(new Destination("Toronto",8500,"Toronto is the provincial capital of Ontario and the most populous city in Canada, with a population of 2,731,571 as of 2016.[14] Current to 2016, the Toronto census metropolitan area (CMA), of which the majority is within the Greater Toronto Area (GTA), held a population of 5,928,040, making it Canada's most populous CMA. The city is the anchor of the Golden Horseshoe, an urban agglomeration of 9,245,438 people (as of 2016) surrounding the western end of Lake Ontario.Toronto is an international centre of business, finance, arts, and culture, and is recognized as one of the most multicultural and cosmopolitan cities in the world.",R.drawable.toronto, 43.642567, -79.387054));
        destinations.add(new Destination("Sydney",27000,"Sydney is the state capital of New South Wales and the most populous city in Australia and Oceania. Located on Australia's east coast, the metropolis surrounds Port Jackson and extends about 70 km (43.5 mi) on its periphery towards the Blue Mountains to the west, Hawkesbury to the north, the Royal National Park to the south and Macarthur to the south-west. Sydney is made up of 658 suburbs, 40 local government areas and 15 contiguous regions. Residents of the city are known as \"Sydneysiders\". As of June 2019, Sydney's estimated metropolitan population was 5,312,163 and is home to approximately 65% of the state's population",R.drawable.sydney, -33.85416325 , 151.20916583));
        destinations.add(new Destination("London",28000,"London, city, capital of the United Kingdom. It is among the oldest of the world’s great cities—its history spanning nearly two millennia—and one of the most cosmopolitan. By far Britain’s largest metropolis, it is also the country’s economic, transportation, and cultural centre. London is situated in southeastern England, lying astride the River Thames some 50 miles (80 km) upstream from its estuary on the North Sea. ",R.drawable.london, 51.510357, -0.116773));
        destinations.add(new Destination("Tokyo",24900,"Tokyo is the capital of Japan and the most populous of the country's 47 prefectures. Located at the head of Tokyo Bay, the prefecture forms part of the Kantō region on the central Pacific coast of Japan's main island, Honshu. Tokyo is the political, economic, and cultural center of Japan, and houses the seat of the Emperor and the national government. The Greater Tokyo Area, which includes several neighboring prefectures, is the largest urban economy and the most populous metropolitan area in the world, with more than 38.1 million residents as of 2017.",R.drawable.tokyo, 35.658581, 139.745438));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillDestinations();
        destinationList=findViewById(R.id.lvDestination);
        destinationList.setAdapter(new DestinationAdapter(this, destinations));
        destinationList.setOnItemClickListener(this);
        mDisplayDate = (EditText) findViewById(R.id.etDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year ;
                mDisplayDate.setText(date);
            }
        };
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        name = destinations.get(i).getDestinationName();
        price = destinations.get(i).getDestinationPrice();
        text = destinations.get(i).getDestinationText();
        img = destinations.get(i).getDestinationImage();
        alat=destinations.get(i).getLat();
        along=destinations.get(i).getLon();

        Intent intent = new Intent(this,DestinationDetailsActivity.class);
        startActivity(intent);

    }
}
