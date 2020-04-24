package com.example.attendo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.attendo.Model.SubEntity;

import java.util.List;

import static com.example.attendo.Database.Data.Coln_2;
import static com.example.attendo.Database.Data.Coln_3;
import static com.example.attendo.Database.Data.Coln_4;
import static com.example.attendo.Database.Data.Table_Name;

public class DataSource
{
    private Context mContext;
    private MyDB myDB;
    private SQLiteDatabase sqLiteDatabase;

    public DataSource(Context mContext)
    {
        this.mContext=mContext;
        this.myDB=new MyDB(mContext);
        sqLiteDatabase=myDB.getWritableDatabase();
    }

    public void open()
    {
        sqLiteDatabase=myDB.getWritableDatabase();
    }

    public void close()
    {
        sqLiteDatabase.close();
    }

    public void insertItem(SubEntity item)
    {
        ContentValues values=item.getValues();
        long insert = sqLiteDatabase.insert(Table_Name,null,values);
    }

    public boolean insertSubname(String Subname)
    {
        SQLiteDatabase sqLiteDatabase=myDB.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Coln_2,Subname);
        long result= sqLiteDatabase.insert(Table_Name,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public void updatePresent(String subname,int present)
    {
        ContentValues values=new ContentValues(1);
        values.put(Coln_3,present);

        String where= Coln_3 + " LIKE ?";
        String whereArgs[]={subname};

        sqLiteDatabase.update(Table_Name,values,where,whereArgs);
    }

    public void updateAbsent(String subname,int absent)
    {
        ContentValues values=new ContentValues(1);
        values.put(Coln_4,absent);
        String where = Coln_4 + "LIKE ?" ;
        String whereArgs[] = {subname};

        sqLiteDatabase.update(Table_Name,values,where,whereArgs);
    }

}
