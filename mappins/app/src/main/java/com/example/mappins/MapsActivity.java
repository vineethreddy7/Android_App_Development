package com.example.mappins;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback/*, GoogleMap.OnMapClickListener, SearchView.OnQueryTextListener*/ {

    private GoogleMap mMap;
    TextView tv;
    int i = 0;
    String letters[] = {"A","B","C","D","E"};
    Double lat[] = new Double[5];
    Double lon[] = new Double[5];
    Double midlat=0.0,midlong=0.0;
    Double midlat1=0.0,midlong1=0.0;
    float totdist = 0;
    float dist2 = 0;
    SearchView s;
    int j=0;


    float dist = 0;
    private Double SphericalUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tv = findViewById(R.id.TV);
        s=findViewById(R.id.sv);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, MapsActivity.this);

      //  s.setOnQueryTextListener(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
     //   mMap.setOnMapClickListener(this);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

  /*  @Override
    public void onMapClick(LatLng latLng) {
        PolylineOptions pl = new PolylineOptions();
        Polyline pp;
        final ArrayList<LatLng> polygonPoints = new ArrayList<>();



        MarkerOptions mk = new MarkerOptions();
        MarkerOptions mk1 = new MarkerOptions();
        mk.position(latLng);
        if(i<5){
            mk.title(letters[i]);

          //  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
            mMap.addMarker(mk).showInfoWindow();
          //  lat[i]=latLng.latitude;
            lat[i]=latLng.latitude;
            lon[i] = latLng.longitude;

          //  polygonPoints.add(new LatLng(latLng.latitude, latLng.longitude));

            if(i>0) {
                Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(lat[i-1], lon[i-1]), new LatLng(lat[i],lon[i]))
                        .width(5)
                        .color(Color.RED));
                midlat = (lat[i-1]+lat[i])/2;
                midlong = (lon[i-1]+lon[i])/2;

                Location loc1 = new Location("");
                Location loc2 = new Location("");
                loc1.setLatitude(lat[i-1]);
                loc1.setLongitude(lon[i-1]);
                loc2.setLatitude(lat[i]);
                loc2.setLongitude(lon[i]);

                dist = loc1.distanceTo(loc2);
                dist = dist/1000;
                totdist+=dist;
                tv.setText(String.valueOf(totdist));

                mk1.position(new LatLng(midlat,midlong));
                mk1.title(String.valueOf(dist));

                mMap.addMarker(mk1).showInfoWindow();

            }
            i++;
            j++;

        }
        if(i==5){
            MarkerOptions mk2 = new MarkerOptions();
            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(lat[i-1], lon[i-1]), new LatLng(lat[0],lon[0]))
                    .width(5)
                    .color(Color.RED));
            midlat1 = (lat[i-1]+lat[0])/2;
            midlong1 = (lon[i-1]+lon[0])/2;

            Location loc3 = new Location("");
            Location loc4 = new Location("");
            loc3.setLatitude(lat[i-1]);
            loc3.setLongitude(lon[i-1]);
            loc4.setLatitude(lat[0]);
            loc4.setLongitude(lon[0]);

            dist2 = loc3.distanceTo(loc4);
            dist2=dist2/1000;
            totdist+=dist2;
            tv.setText(String.valueOf(totdist));


            polygonPoints.add(new LatLng(lat[0], lon[0]));
            polygonPoints.add(new LatLng(lat[1], lon[1]));

            polygonPoints.add(new LatLng(lat[2], lon[2]));
            polygonPoints.add(new LatLng(lat[3], lon[3]));
            polygonPoints.add(new LatLng(lat[4], lon[4]));
            polygonPoints.add(new LatLng(lat[0], lon[0]));




            mk2.position(new LatLng(midlat1,midlong1));
            mk2.title(String.valueOf(dist2));
            final Polygon polygon = mMap.addPolygon(new PolygonOptions()
                    .addAll(polygonPoints)
                    .strokeColor(Color.BLUE)
                    .fillColor(Color.argb(50, 255, 0, 0)));

            mMap.addMarker(mk2).showInfoWindow();
        }







    }


    @Override
    public boolean onQueryTextSubmit(String st) {
        String location  = s.getQuery().toString();
        List<Address> addL = null;

        if(location!=null || !location.equals("")){
            Geocoder geo = new Geocoder(MapsActivity.this);
            try{
                addL = geo.getFromLocationName(location,1);
            }catch (IOException e){
                e.printStackTrace();
            }
            Address address = addL.get(0);
            LatLng ll = new LatLng(address.getLatitude(),address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(ll));
            lat[j]=ll.latitude;
            lon[j]=ll.longitude;
            j++;
            i++;

        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }*/
}