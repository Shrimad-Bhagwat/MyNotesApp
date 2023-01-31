package com.shrimadbhagwat.mynotesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Table Name
    public  static final String TABLE_NAME= "NOTES";

    //Table Columns
    public  static final String _ID = "_id";
    public  static final String SUBJECT = "subject";
    public  static final String DESCRIPTION = "description";

    //Database Info
    static final String DB_NAME = "MASTER_ANDROID_APP.DB";

    //Database Version
    static final int DB_VERSION = 1;

    //Createing table query
    private static final String CREATE_TABLE = "create table "+
            TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            SUBJECT + " TEXT NOT NULL, "+
            DESCRIPTION + " TEXT);";

    //Constructor
    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
