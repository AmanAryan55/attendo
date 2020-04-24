package com.example.attendo.Model;

import android.content.ContentValues;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.attendo.Database.Data;

import java.util.UUID;

public class SubEntity {


    private String ID;

    private String subName;
    public int present;
    public int absent;

    public SubEntity() {
    }

    public SubEntity(String ID, String subname, int present, int absent) {

        if(ID==null)
        {
            ID= UUID.randomUUID().toString();
        }

        this.ID=ID;
        this.subName = subname;
        this.present = present;
        this.absent = absent;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getSubname() {
        return subName;
    }

    public void setSubname(String subname) {
        this.subName = subname;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public ContentValues getValues()
    {
        ContentValues values = new ContentValues(4);
        values.put(Data.Coln_1,this.ID);
        values.put(Data.Coln_2,this.subName);
        values.put(Data.Coln_3,this.present);
        values.put(Data.Coln_4,this.absent);

        return values;
    }

    @Override
    public String toString() {
        return "SubDataItem{" + "ID='" + ID + '\'' +
                            ", Subname='"+ subName + '\'' +
                            ", Present='"+ present +
                            ", Absent='"+ absent + '}';
    }
}
