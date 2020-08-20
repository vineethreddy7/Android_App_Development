package com.example.voyage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, AdapterView.OnItemSelectedListener {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    //private Spinner spinner;
    RecyclerView rv;
    List<details_place> place;
    list_place_adapter ad;
    List<details_place> placelist = new ArrayList<>();
    String email;
    DataBase db;
    List<Offering> offering;

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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
       // add();
        db = new DataBase(this);
        offering = db.getOfferings();
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        ad = new list_place_adapter(this,offering);
        rv.setAdapter(ad);


    }


    public void add(){
        placelist.add(new details_place("London","$2000","UK",R.drawable.london));
        placelist.add(new details_place("Sydney","$3000","Australia",R.drawable.sydney));

    }

    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.profile_menu);
        popup.show();
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
        return false;
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
    String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}