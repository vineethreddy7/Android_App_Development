package com.example.voyage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupMenu;

public class HostHomeScreenActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    Button buttonUploadPicture,buttonReservation,buttonAvailable,buttonOffer;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_host_home_screen);
        buttonUploadPicture = (Button) findViewById(R.id.uploadPictureButton);
        buttonReservation = (Button) findViewById(R.id.ReservationButton);
        buttonAvailable = (Button) findViewById(R.id.AvailableButton);
        buttonOffer = (Button) findViewById(R.id.OffersButton);
        buttonUploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HostHomeScreenActivity.this, Picture_Activity.class);
                startActivity(intent);
            }
        });
        buttonReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HostHomeScreenActivity.this, Reservation_Activity.class);
                startActivity(intent);
            }
        });
        buttonAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HostHomeScreenActivity.this, Available_Activity.class);
                startActivity(intent);
            }
        });
        buttonOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HostHomeScreenActivity.this, Offers_Activity.class);
                startActivity(intent);
            }
        });
    }
    public void showPopupH(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.profile_menu);
        popup.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.profile){
            profileDialog();
        }
        if(id == R.id.signout){
            Intent intent = new Intent(HostHomeScreenActivity.this, MainActivity2.class);
            startActivity(intent);
        }
        return false;
    }
    public void profileDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopup = getLayoutInflater().inflate(R.layout.activity_hostprofile, null);
        dialogBuilder.setView(contactPopup);
        dialog = dialogBuilder.create();
        dialog.show();
    }


}