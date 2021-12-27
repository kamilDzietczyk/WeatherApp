package com.example.weatherapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "WeatherDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "WeatherApp";
    private static final String ID_COL = "id";
    private static final String CITY_COL = "CITY";
    private static final String SKY_COL = "sky";
    private static final String DESC_COL = "desc";
    private static final String TEMP_COL = "temp";
    private static final String PRESS_COL = "press";
    private static final String WIND_COL = "wind";
    private static final String DATA_TIME_COL = "searchTime";

    public DBHandler(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CITY_COL + " TEXT,"
                + SKY_COL + " TEXT,"
                + DESC_COL + " TEXT,"
                + TEMP_COL + " TEXT,"
                + PRESS_COL + " TEXT,"
                + WIND_COL + " TEXT,"
                + DATA_TIME_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addCity(String city,String sky,String desc,String temp,String press,String wind, String data_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CITY_COL, city);
        values.put(SKY_COL, sky);
        values.put(DESC_COL, desc);
        values.put(TEMP_COL, temp);
        values.put(PRESS_COL, press);
        values.put(WIND_COL, wind);
        values.put(DATA_TIME_COL, data_time);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<DBObject> readCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCity = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<DBObject> cityModalArrayList = new ArrayList<>();
        if (cursorCity.moveToFirst()) {
            do {
                cityModalArrayList.add(new DBObject(
                        cursorCity.getString(0),
                        cursorCity.getString(1),
                        cursorCity.getString(2),
                        cursorCity.getString(3),
                        cursorCity.getString(4),
                        cursorCity.getString(5),
                        cursorCity.getString(6),
                        cursorCity.getString(7)));
            } while (cursorCity.moveToNext());
        }
        cursorCity.close();
        return cityModalArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
