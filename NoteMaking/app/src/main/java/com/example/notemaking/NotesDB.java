package com.example.notemaking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDB extends SQLiteOpenHelper {

    private static final String DataBaseName = "NotesDB.db";
    private static final String TableName = "notes";
    private static final String ColID = "id";
    private static final String SubName = "subject";
    private static final String ColName = "name";
    private static final String Desc = "description";
    private static final String Date = "date";
    private static final String Time = "time";

    public NotesDB(Context context){
        super(context,DataBaseName,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table notes "+"(id integer primary key AUTOINCREMENT,subject text,name text,description text,date text,time text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }
}
