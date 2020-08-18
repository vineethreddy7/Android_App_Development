package com.example.voyage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

public class DataBase extends SQLiteOpenHelper {


    private static final String DataBaseName = "DB6.db";



    public DataBase(Context context) {
        super(context, DataBaseName, null, 2);
    }

    //Host table
    private static final String TableName = "hostdata";
    private static final String HostFirstName = "hostfirstname";
    private static final String HostLastName = "hostlastname";
    private static final String HostEmail = "hostemail";
    private static final String HostPhone = "hostphone";
    private static final String HostPassword = "hostpassword";
    private static final String HostImage = "hostimage";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TableName+"("+HostFirstName+" TEXT,"+HostLastName+" TEXT,"+HostEmail+" TEXT PRIMARY KEY NOT NULL UNIQUE,"+HostPhone+" TEXT,"+HostPassword+" TEXT,"+HostImage+" TEXT"+")");
        db.execSQL("CREATE TABLE "+tablename+"("+travellerFname+" TEXT,"+travellerLname+" TEXT,"+travellerEmail+" TEXT PRIMARY KEY NOT NULL UNIQUE,"+travellerPhone+" TEXT,"+travellerPwd+" TEXT,"+travellerImg+" TEXT"+")");
        db.execSQL("CREATE TABLE "+offertablename+"("+oid+" INTEGER PRIMARY KEY AUTOINCREMENT,"+oname+" TEXT,"+ophoto+" TEXT,"+oprice+" TEXT,"+oplace+" TEXT,"+olatitude+" TEXT,"+olongitude+" TEXT,"+odescription+" TEXT,"+orating+" TEXT,"+oreviews+" TEXT,"+otype+" TEXT,"+ohostemail+" TEXT,"+" FOREIGN KEY ("+ohostemail+") REFERENCES "+TableName+"("+HostEmail+"));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TableName);
        db.execSQL("DROP TABLE IF EXISTS "+tablename);
        db.execSQL("DROP TABLE IF EXISTS "+offertablename);
        onCreate(db);
    }

    public void addHost(Hosts h){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(HostFirstName,h.getFirstname());
        c.put(HostLastName,h.getLastname());
        c.put(HostEmail,h.getEmail());
        c.put(HostPhone,h.getPhone());
        c.put(HostPassword,h.getPassword());
        c.put(HostImage,h.getImage());
        long i = db.insert(TableName,null,c);
        Log.d("Inserted",""+i);
    }

    public Hosts getHost(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TableName,new String[]{HostFirstName,HostLastName,HostEmail,HostPhone,HostPassword,HostImage},HostEmail+"=?",new String[]{String.valueOf(email)},null,null,null,null);
        Log.d("String ",""+c);
        if(!c.equals(null)){
            c.moveToFirst();

        Hosts h = new Hosts(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
        return h;}
        else if(c.equals(null)) {
            return null;
        }
        return null;
    }

    public void editHost(Hosts h){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(HostFirstName,h.getFirstname());
        c.put(HostLastName,h.getLastname());
        c.put(HostEmail,h.getEmail());
        c.put(HostPhone,h.getPhone());
        c.put(HostPassword,h.getPassword());
        c.put(HostImage,h.getImage());
        int i = db.update(TableName,c,HostEmail+"=?",new String[]{String.valueOf(h.getEmail())});
        Log.d("Table Updated",""+i);
    }

    //traveller table
    private static final String tablename = "travellerdata";
    private static final String travellerFname = "travellerfname";
    private static final String travellerLname = "travellerlname";
    private static final String travellerEmail = "travelleremail";
    private static final String travellerPhone = "travellerphone";
    private static final String travellerPwd = "travellerpwd";
    private static final String travellerImg = "travellerimage";



    public void addTraveller(Travellers t){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(travellerFname,t.getFname());
        c.put(travellerLname,t.getLname());
        c.put(travellerEmail,t.getEmail());
        c.put(travellerPhone,t.getPhone());
        c.put(travellerPwd,t.getPassword());
        c.put(travellerImg,t.getImage());
        long i = db.insert(tablename,null,c);
        Log.d("Inserted",""+i);

    }

    public Travellers getTraveller(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(tablename,new String[]{travellerFname,travellerLname,travellerEmail,travellerPhone,travellerPwd,travellerImg},travellerEmail+"=?",new String[]{String.valueOf(email)},null,null,null,null);
        if(c!=null){
            c.moveToFirst();
        }
        Travellers t = new Travellers(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
        return t;

    }

    public void editTraveller(Travellers t){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(travellerFname,t.getFname());
        c.put(travellerLname,t.getLname());
        c.put(travellerEmail,t.getEmail());
        c.put(travellerPhone,t.getPhone());
        c.put(travellerPwd,t.getPassword());
        c.put(travellerImg,t.getImage());
        int i = db.update(tablename,c,travellerEmail+"=?",new String[]{String.valueOf(t.getEmail())});
        Log.d("Table Updated",""+i);

    }

    //offerings table
    private static final String offertablename = "offeringstable";
    private static final String oid = "offeringsid";
    private static final String oname = "offeringname";
    private static final String ophoto = "offeringphoto";
    private static final String oprice = "offeringprice";
    private static final String oplace = "offeringplace";
    private static final String olatitude = "latitude";
    private static final String olongitude = "longitude";
    private static final String orating = "offeringrating";
    private static final String odescription = "offeringdescription";
    private static final String ohostemail = "hostemail";
    private static final String oreviews = "offeringreviews";
    private static final String otype = "offeringtype";

    public void addOffering(Offering o){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(oname,o.getName());
        c.put(ophoto,o.getPhoto());
        c.put(oprice,o.getPhoto());
        c.put(oplace,o.getPlace());
        c.put(olatitude,o.getLatitude());
        c.put(olongitude,o.getLongitude());
        c.put(orating,o.getRating());
        c.put(odescription,o.getDescription());
        c.put(ohostemail,o.getHostemail());
        c.put(oreviews,o.getReview());
        c.put(otype,o.getType());

        long id = db.insert(offertablename,null,c);
        Log.d("inserted",""+id);
    }

    

}
