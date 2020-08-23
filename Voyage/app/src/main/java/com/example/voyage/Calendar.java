package com.example.voyage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {
    CalendarView c;
    TextView datetv;
    Button datesave;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        c = findViewById(R.id.cV);
        datetv = findViewById(R.id.tvDate);
        datesave = findViewById(R.id.btndatedone);
        Intent in2 = getIntent();
        name = in2.getStringExtra("name");
        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1+1) + "-" + i2 + "-" + i;
                datetv.setText(date);
            }
        });

        datesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Calendar.this,bookingpage.class);
                i.putExtra("date",datetv.getText());
                i.putExtra("name",name);
                startActivity(i);
            }
        });
    }
}