package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        LaunchLogo launchLogo = new LaunchLogo();
        launchLogo.start();
    }
     private class LaunchLogo extends Thread{
        public void run(){
           try{
               sleep(2300);
           }catch(InterruptedException e){
               e.printStackTrace();
            }
           Intent intent = new Intent(SplashScreenActivity.this, OptionActivity.class);
           startActivity(intent);
           SplashScreenActivity.this.finish();

        }
     }

}