package com.example.extenalpratice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context)
    {
        super(context,"patient.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table patient(id INTEGER PRIMARY KEY AUTOINCREMENT,name Text,disease Text,isMedication Text,arrivalDate String,cost INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists patient");
    }

    public Boolean insertData(String name,String disease,String isMedication,String arrivalDate,int cost)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("disease",disease);
        contentValues.put("isMedication",isMedication);
        contentValues.put("arrivalDate",arrivalDate);
        contentValues.put("cost",cost);
        long result = DB.insert("patient",null,contentValues);
        if(result>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor getData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from patient",null);
        return cursor;
    }

    public Boolean updateData(int id,String name,String disease,String isMedication,String arrivalDate,int cost)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("disease",disease);
        contentValues.put("isMedication",isMedication);
        contentValues.put("arrivalDate",arrivalDate);
        contentValues.put("cost",cost);

        Cursor cursor = DB.rawQuery("select * from patient where id = ?",new String[] {id + ""});
        if(cursor.getCount()>0)
        {
            long result = DB.update("patient",contentValues,"id=?",new String[] {id+""});
            if(result>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }

    }



    public boolean deleteData(int id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from patient where id=?",new String[] {id+""});
        if(cursor.getCount()>0)
        {
            long result = DB.delete("patient","id=?",new String[] {id+""});
            if(result>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    public Cursor searchDataId(int id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from patient where id=?",new String[] {id+""});
        return cursor;
    }

    public Cursor searchDataName(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from patient where name=?",new String[] {name});
        return cursor;
    }
}
