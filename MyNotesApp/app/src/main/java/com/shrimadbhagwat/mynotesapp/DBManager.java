package com.shrimadbhagwat.mynotesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    //Constructor
    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException  {
        dbHelper= new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return  this;
    }

    public void close() {
        dbHelper.close();
    }

    //Insert
    public void insert(String subject, String description){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT,subject);
        contentValues.put(DatabaseHelper.DESCRIPTION,description);
        database.insert(DatabaseHelper.TABLE_NAME,null,contentValues);

    }

    public Cursor fetch()  {
        String[] columns = new String[] {DatabaseHelper._ID,
        DatabaseHelper.SUBJECT, DatabaseHelper.DESCRIPTION};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,columns,
                null,
                null,
                null,
                null,
                null);
        if (cursor!=null){
            cursor.moveToFirst();

        }
        return  cursor;
    }

    public int update(long _id, String subject, String description){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT,subject);
        contentValues.put(DatabaseHelper.DESCRIPTION,description);
        String query = DatabaseHelper._ID + " = " + _id;
        int i = database.update(DatabaseHelper.TABLE_NAME,
                contentValues, query,null);
        return  i;
    }

    public void delete(long _id) {
        String query = DatabaseHelper._ID + " = " + _id;
        database.delete(DatabaseHelper.TABLE_NAME, query,null);
    }


}
