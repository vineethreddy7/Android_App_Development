package com.example.notemaking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NotesDB extends SQLiteOpenHelper {

    private static final String DataBaseName = "NotesDB4.db";
    private static final String TableName = "notesdata";
    private static final String ColID = "id";
    private static final String SubName = "subject";
    private static final String ColName = "name";
    private static final String Desc = "description";
    private static final String Date = "date";
    private static final String Time = "time";
    private static final String SubTableName = "subjects";
    private static final String sname = "subjectname";
    private static final String Imageurl = "image";
    private static final String Audiopath = "audio";
    private static final String Latitude = "latitude";
    private static final String Longitude = "longitude";
    public static ArrayList<Long> ids = new ArrayList<>();

    public NotesDB(Context context){
        super(context,DataBaseName,null,2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+SubTableName+"("+sname+" TEXT"+")");
        db.execSQL("CREATE TABLE "+TableName +"("+ColID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+SubName+" TEXT,"+ColName+" TEXT,"+Desc+" TEXT,"+Date+" TEXT,"+Time+" TEXT, "+Imageurl+" TEXT, "+Audiopath+" TEXT, "+Latitude+" TEXT, "+Longitude+" TEXT "+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TableName);
        db.execSQL("DROP TABLE IF EXISTS "+SubTableName);
        onCreate(db);
    }

    public long addNote(Notes note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(SubName,note.getSubject());
        c.put(ColName,note.getName());
        c.put(Desc,note.getDescription());
        c.put(Date,note.getDate());
        c.put(Time,note.getTime());
        c.put(Imageurl,note.getImg());
        c.put(Audiopath,note.getAudio());
        c.put(Latitude,note.getLatitude());
        c.put(Longitude,note.getLongitude());

        long ID = db.insert(TableName,null,c);
        Log.d("inserted",""+ID);
        return ID;
    }

    public long addSubject(SubjectsClass nn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c1 = new ContentValues();
        c1.put(sname,nn.getSubjectname());
        long id1 = db.insert(SubTableName,null,c1);
        Log.d("Subject inserted",""+id1);
        return id1;
    }

    public Notes getnote(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TableName,new String[]{ColID,SubName,ColName,Desc,Date,Time,Imageurl,Audiopath,Latitude,Longitude},ColID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(c!=null){
            c.moveToFirst();
        }
        Notes note = new Notes(c.getLong(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getDouble(8),c.getDouble(9));
        return note;
    }

    public List<Notes> getNotes(String sub){
        ids.clear();
        SQLiteDatabase db = this.getWritableDatabase();
        List<Notes> total = new ArrayList<>();
        String q = "SELECT * FROM "+TableName+" WHERE "+SubName+"="+"\"" + sub + "\""+" ORDER BY "+ColID+" DESC";
        Cursor c1 = db.rawQuery(q,null);
        if(c1.moveToFirst()){
            do{

                Notes nn = new Notes();
                nn.setId(c1.getLong(0));
                ids.add(c1.getLong(0));
                Log.d("id11",""+c1.getLong(0));
                nn.setSubject(c1.getString(1));
                nn.setName(c1.getString(2));
                nn.setDescription(c1.getString(3));
                nn.setDate(c1.getString(4));
                nn.setTime(c1.getString(5));
                nn.setImg(c1.getString(6));
                nn.setAudio(c1.getString(7));
                nn.setLatitude(c1.getDouble(8));
                nn.setLongitude(c1.getDouble(9));
                total.add(nn);

            }while(c1.moveToNext());
        }
        return total;
    }

    public List<Notes> getNotes11(){
        SQLiteDatabase db1 = this.getWritableDatabase();
        List<Notes> subtot = new ArrayList<>();
        String r = "SELECT * FROM "+TableName+" ORDER BY "+ColID+" DESC";
        Cursor c11 = db1.rawQuery(r,null);
        if(c11.moveToFirst()){
            do{
                Notes n2 = new Notes();
                n2.setId(c11.getLong(0));
                Log.d("id11",""+c11.getLong(0));
                n2.setSubject(c11.getString(1));
                n2.setName(c11.getString(2));
                n2.setDescription(c11.getString(3));
                n2.setDate(c11.getString(4));
                n2.setTime(c11.getString(5));
                subtot.add(n2);

            }while(c11.moveToNext());
        }
        return subtot;
    }

    void delNote(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TableName,ColID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public int editNote(Notes n){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(ColName,n.getName());
        c.put(Desc,n.getDescription());
        c.put(Date,n.getDate());
        c.put(Time,n.getTime());
        c.put(Imageurl,n.getImg());
        c.put(Audiopath,n.getAudio());
        c.put(Latitude,n.getLatitude());
        c.put(Longitude,n.getLongitude());
        return db.update(TableName,c,ColID+"=?",new String[]{String.valueOf(n.getId())});
    }
}
