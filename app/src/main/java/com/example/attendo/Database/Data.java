package com.example.attendo.Database;

public class Data
{
    public static final String Table_Name="Attendance_Table";
    public static final String Coln_1="ID";
    public static final String Coln_2="Subname";
    public static final String Coln_3="Present";
    public static final String Coln_4="Absent";

    public static final String SQL_CREATE = "CREATE TABLE" + Table_Name + "("+
                                            Coln_1 + "TEXT PRIMARY KEY" +
                                            Coln_2 + "TEXT" +
                                            Coln_3 + "INTEGER" +
                                            Coln_4 + "INTEGER);";

    public static final String SQL_DELETE = "DROP TABLE IF EXISTS" + Table_Name;
}
