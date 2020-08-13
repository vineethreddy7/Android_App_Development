package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HostSignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Button ButtonHostCreate, ButtonHostCancel;
    TextView hfnameTv,hlnameTv,hemailTv,hphoneTv,hpwdTv,hrpwdTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_host_signup);
        hfnameTv = findViewById(R.id.tvHFName);
        hlnameTv = findViewById(R.id.tvHLName);
        hemailTv = findViewById(R.id.tv1HEmail);
        hphoneTv = findViewById(R.id.tvHPhone);
        hpwdTv = findViewById(R.id.tv1HPwd);
        hrpwdTv = findViewById(R.id.tvHRPwd);
       // Spinner spinner = findViewById(R.id.Businessspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Bcategories,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  spinner.setAdapter(adapter);
      //  spinner.setOnItemSelectedListener(this);
        ButtonHostCreate = (Button) findViewById(R.id.HostButtonCreate);
        ButtonHostCancel = (Button) findViewById(R.id.HostButtonCancel);
        ButtonHostCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hpwdTv.getText().toString().equals(hrpwdTv.getText().toString())) {
                    Hosts host = new Hosts(hfnameTv.getText().toString(), hlnameTv.getText().toString(), hemailTv.getText().toString(), hphoneTv.getText().toString(), hpwdTv.getText().toString());
                    HostSignupDB db = new HostSignupDB(HostSignupActivity.this);
                    db.addHost(host);
                    Toast.makeText(HostSignupActivity.this,"Profile Created",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HostSignupActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        });
        ButtonHostCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HostSignupActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onClick(View view) {

    }
}