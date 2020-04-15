package com.example.teststudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et1,et2;
    Button LBtn;
    public static String spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.etName);
        et2=findViewById(R.id.etPwd);
        LBtn=findViewById(R.id.bLogin);

        LBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(et1.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Enter UserName",Toast.LENGTH_LONG).show();
        }
        else if(et2.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_LONG).show();
        }
        else if(!et1.getText().toString().equals("student1") || !et2.getText().toString().equals("123456")){
            Toast.makeText(getApplicationContext(),"Invalid UserName or Password",Toast.LENGTH_LONG).show();
        }
        else if(et1.getText().toString().equals("student1") && et2.getText().toString().equals("123456")){
            spn=et1.getText().toString();
            Intent intent = new Intent(this,secongpage.class);
            startActivity(intent);
        }
    }
}
