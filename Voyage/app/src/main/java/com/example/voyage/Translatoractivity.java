package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;

import java.util.concurrent.Flow;

public class Translatoractivity extends AppCompatActivity {
    EditText etTranslate;
    Button translateBtn;
    TextView tvTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translatoractivity);
        etTranslate = findViewById(R.id.tvtranslateactual);
        translateBtn = findViewById(R.id.btnTranslate);
        tvTranslate = findViewById(R.id.tvTranslated);

        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TranslateAPI tAPI = new TranslateAPI(Language.AUTO_DETECT,Language.ENGLISH,etTranslate.getText().toString());

                tAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
                    @Override
                    public void onSuccess(String s) {
                        tvTranslate.setText(s);
                    }

                    @Override
                    public void onFailure(String s) {

                    }
                });
            }
        });
    }
}