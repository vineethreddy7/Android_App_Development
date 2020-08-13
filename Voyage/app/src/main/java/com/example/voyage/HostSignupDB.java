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

public class HostSignupDB extends SQLiteOpenHelper {


    private static final String DataBaseName = "HostDB1.db";
    private static final String TableName = "hostdata";
    private static final String HostFirstName = "hostfirstname";
    private static final String HostLastName = "hostlastname";
    private static final String HostEmail = "hostemail";
    private static final String HostPhone = "hostphone";
    private static final String HostPassword = "hostpassword";

    public HostSignupDB(Context context) {
        super(context, DataBaseName, null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TableName+"("+HostFirstName+" TEXT,"+HostLastName+" TEXT,"+HostEmail+" TEXT PRIMARY KEY NOT NULL UNIQUE,"+HostPhone+" TEXT,"+HostPassword+" TEXT"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TableName);
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
        long i = db.insert(TableName,null,c);
        Log.d("Inserted",""+i);
    }

    public Hosts getHost(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TableName,new String[]{HostFirstName,HostLastName,HostEmail,HostPhone,HostPassword},HostEmail+"=?",new String[]{String.valueOf(email)},null,null,null,null);
        Log.d("String ",""+c);
        if(!c.equals(null)){
            c.moveToFirst();

        Hosts h = new Hosts(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
        return h;}
        else if(c.equals(null)) {
            return null;
        }
        return null;
    }
}
