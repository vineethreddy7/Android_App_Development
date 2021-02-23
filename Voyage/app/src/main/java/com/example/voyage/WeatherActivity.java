package com.example.voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.android.volley.Request.Method.*;

public class WeatherActivity extends AppCompatActivity {
    TextView wplacetv, wdatetv, wtemptv, wforecasttv,wdesctv;
    EditText placeEt;

    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        wplacetv = findViewById(R.id.tvwPlace);
        wdatetv = findViewById(R.id.tvwDate);
        wtemptv = findViewById(R.id.tvwTemp);
        wforecasttv = findViewById(R.id.tvwforecast);
        wdesctv = findViewById(R.id.tvwdesc);
        placeEt = findViewById(R.id.etplace);
        search = findViewById(R.id.btnws);
       // weather(43.65,-79.63);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = placeEt.getText().toString();
                weather1(name);
                name = "";
            }
        });

    }

    public void weather(Double lat,Double lon){
       // String url = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=ca1f1182e1b8a0a482c9ffbbbe67ca42";
        String url = "api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=ca1f1182e1b8a0a482c9ffbbbe67ca42";
        JsonObjectRequest jo = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject obj = response.getJSONObject("main");
                    JSONArray arr = response.getJSONArray("weather");
                    JSONObject o = arr.getJSONObject(0);
                    String t = String.valueOf(obj.getDouble("temp"));
                    String place = response.getString("name");

                    wplacetv.setText(place);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(jo);
    }

    public void weather1(String name){
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+name+"&appid=ca1f1182e1b8a0a482c9ffbbbe67ca42";
       // String url = "api.openweathermap.org/data/2.5/weather?q="+name+"&appid=8cbf81fca0fcd5bb9d45012dd8c44bcc";
        JsonObjectRequest jo = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject obj = response.getJSONObject("main");
                    JSONArray arr = response.getJSONArray("weather");
                    JSONObject o = arr.getJSONObject(0);
                    String t = String.valueOf(obj.getDouble("temp"));
                    String place = response.getString("name");
                    String forecast = o.getString("main");
                    String desc = o.getString("description");
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat f = new SimpleDateFormat("EEEE:MM-dd-YYYY");
                    wplacetv.setText(place);
                    wdatetv.setText(f.format(c.getTime()));
                    wtemptv.setText(t);
                    wforecasttv.setText(forecast);
                    wdesctv.setText(desc);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(jo);
    }

    public void weather2(String name){
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+name+"&cnt=10"+"&appid=ca1f1182e1b8a0a482c9ffbbbe67ca42";
        JsonObjectRequest jo = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject obj = response.getJSONObject("main");
                    JSONArray arr = response.getJSONArray("list");
                    JSONObject o = arr.getJSONObject(0);
                    String t = String.valueOf(obj.getDouble("temp"));
                    String place = response.getString("name");



                    wplacetv.setText(place);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(jo);
    }



}