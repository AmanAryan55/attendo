package com.example.attendo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDB extends SQLiteOpenHelper
{
    public static final String Database_Name="Attendance.db";
    public static final int Database_Version=1;

    public MyDB(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Data.SQL_CREATE);
        Log.d("myTAG","Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(Data.SQL_DELETE);
        onCreate(db);
        Log.d("myTAG","Database Updated");
    }
}
