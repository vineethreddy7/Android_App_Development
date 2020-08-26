package com.example.voyage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeScreenActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, SearchView.OnQueryTextListener, AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    NavigationView nv;
    //private Spinner spinner;
    RecyclerView rv, offersrv;
    List<details_place> place;
    list_place_adapter ad;
    List<details_place> placelist = new ArrayList<>();
    String email;
    DataBase db;
    List<Offering> offering;
    Button draw;
    SearchView sv;
    String search = "";
    List<String> places = new ArrayList<>();
    List<Offering> searchoffering = new ArrayList<>();
    String type;
    Spinner sp;
    String values[];
    Resources r;
    int flag = 0;
    List<Offering> newsearchoffering = new ArrayList<>();
    list_offer_adapter ae;
    List<Offering> offerOffering = new ArrayList<>();
    final long totaltime = Long.MAX_VALUE;
    final int period = 20;
    final int height = 20;
    int a;

    static String email11;
    String e;



    String items[] = {"Select","Water Activities","Mountain Activities","Hotels","Restaurants","Cottages","Monument Visits","Snow Activities","Others"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_screen);
        Spinner spinner = findViewById(R.id.spinnerCat);
        Intent i = getIntent();
         email = i.getStringExtra("email");
        rv = findViewById(R.id.rVHome);
        offersrv = findViewById(R.id.rvOffers);
        draw = findViewById(R.id.btntvlDrawer);
        sv = findViewById(R.id.searchbar);
        nv = findViewById(R.id.nV);
        sp = findViewById(R.id.spinnerCat);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);
        r= getResources();
        values = r.getStringArray(R.array.categories);
       // add();
        Intent i11 = getIntent();
        email11 = i11.getStringExtra("email");
        Log.d("Email111 is",""+email11);
        db = new DataBase(this);
        offering = db.getOfferings1();
        places.clear();
        for(int k=0;k<offering.size();k++){
            places.add(offering.get(0).getPlace());
        }
        offerOffering.clear();
        for(int g = 0;g<offering.size();g++){
            Log.d("Offers are",""+offering.get(g).getOffer());
            if(offering.get(g).getOffer().equals("Yes")){
                offerOffering.add(offering.get(g));
            }
            else if(offering.get(g).getOffer().equals("No")){
                break;
            }
        }
        nv.setNavigationItemSelectedListener(this);

        //getE();


        offersrv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        ae = new list_offer_adapter(this,offerOffering);

        offersrv.setAdapter(ae);
        scroll();



        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if(count < offerOffering.size()){
                    offersrv.scrollToPosition(count++);
                    handler.postDelayed(this,2000);
                }
                else{
                    count = 0;
                }


            }
        };

        handler.postDelayed(runnable,2000);


        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPP(view);
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        ad = new list_place_adapter(this,offering);
        ad.notifyDataSetChanged();

        rv.setAdapter(ad);


        sv.setOnQueryTextListener(this);




    }



    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.profile_menu);
        popup.show();
    }

    public void showPP(View v){
        PopupMenu pp = new PopupMenu(this,v);
        pp.setOnMenuItemClickListener(this);
        pp.inflate(R.menu.drawer_menu);
        pp.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.profile){
            Intent intent = new Intent(HomeScreenActivity.this, travellerprofile.class);
            intent.putExtra("email",email);
            startActivity(intent);
        }
        if(id == R.id.signout){
            Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(id == R.id.bookings){
            Intent itt = new Intent(this,Mybookings.class);
            itt.putExtra("email",email11);
            Log.d("Emailbook is",""+email11);
            startActivity(itt);
        }

        if(id == R.id.weather){
            Intent it1 = new Intent(this,WeatherActivity.class);
            startActivity(it1);
        }
        if(id == R.id.translator){
            startActivity(new Intent(this,Translatoractivity.class));
        }

        return false;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        type = items[i];
      /*  if(!type.equals("")){
        Toast.makeText(this, "" + type, Toast.LENGTH_SHORT).show();
        searchoffering.clear();
        newsearchoffering.clear();
        if (!search.equals(null)) {
            for (int n = 0; n < offering.size(); n++) {
                if (offering.get(n).getPlace().equals(search)) {
                    Log.d("THey are", offering.get(n).getPlace() + " " + n);
                    searchoffering.add(offering.get(n));

                }
            }
            for (int k = 0; k < searchoffering.size(); k++) {
                if (searchoffering.get(k).getType().equals(type)) {
                    Log.d("Traveller types are", searchoffering.get(k).getPlace() + " " + type);
                    newsearchoffering.add(searchoffering.get(k));
                }
            }
            //  ad = new list_place_adapter(this, newsearchoffering);
        } else if(search.equals(null)) {
            newsearchoffering.clear();
            for (int p = 0; p < offering.size(); p++) {
                if (offering.get(p).getType().equals(type)) {
                    Log.d("Traveller types are", offering.get(p).getType() + " " + type);
                    newsearchoffering.add(offering.get(p));
                }
            }

        }

        ad = new list_place_adapter(this, newsearchoffering);
    }
        else{
            ad = new list_place_adapter(this,offering);
        }
        rv.setAdapter(ad);
      //  searchoffering.clear();
        */
      Log.d("Spiiner value is",""+type);
      if(!type.equals("Select")){
          searchoffering.clear();
          for(int b=0;b<offering.size();b++){
              if(offering.get(b).getType().equals(type)){
                  searchoffering.add(offering.get(b));
              }
          }
          ad = new list_place_adapter(this,searchoffering);
          rv.setAdapter(ad);
      }
      else if(type.equals("Select")){
          ad = new list_place_adapter(this,offering);
          rv.setAdapter(ad);
      }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        search = s;
       // Log.d("Search is",""+search);
        searchoffering.clear();
       if(!s.equals(null)){
        for(int m = 0;m<offering.size();m++) {
            if(offering.get(m).getPlace().equals(s)){
                Log.d("THey are",offering.get(m).getPlace()+" "+s);
                searchoffering.add(offering.get(m));

            }
        }
           ad = new list_place_adapter(this,searchoffering);


        }
        flag = 1;
        rv.setAdapter(ad);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Log.d("the value is","this");
        ad = new list_place_adapter(this,offering);
        rv.setAdapter(ad);
        return true;
    }

    private void scroll() {


        a = 0;

        final int d = 500;


        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable run = new Runnable() {

            @Override
            public void run() {

                a++;

                if (a<offerOffering.size())
                {
                    offersrv.scrollToPosition(a);

                }else if (a==offerOffering.size())
                {

                    a=-1;

                }

                handler.postDelayed(this, d);
            }
        };
        handler.postDelayed(run, 500);




    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.bookings){
            Intent itt = new Intent(this,Mybookings.class);
            itt.putExtra("email",email);
            startActivity(itt);
        }

        if(id == R.id.weather){
            Intent it1 = new Intent(this,WeatherActivity.class);
            startActivity(it1);
        }
        if(id == R.id.translator){
            startActivity(new Intent(this,Translatoractivity.class));
        }
        return false;
    }
}