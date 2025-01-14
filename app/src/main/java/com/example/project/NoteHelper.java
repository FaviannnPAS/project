package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteHelper extends SQLiteOpenHelper {

    private static final String DATABASE="DATABASE";
    private static final int VERSION=2;


    public NoteHelper(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table my_table(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,description TEXT)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists my_table");



    }

    public void insertData (String title, String description) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",title);
        values.put("description",description);
        database.insert("my_table",null,values);




    }


    public Cursor showData(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

       Cursor cursor = sqLiteDatabase.rawQuery("select * from my_table",null);

       return cursor;


    }


//   Hapus data
    public void deleteData(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("my_table", "id=?", new String[]{String.valueOf(id)});
        database.close();
}

// Update Data
    public void updateData(int id, String newTitle, String newDesc) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", newTitle);
        values.put("description", newDesc);

        // Update data berdasarkan id
        database.update("my_table", values, "id = ?", new String[]{String.valueOf(id)});
    }











}
