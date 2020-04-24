package com.example.attendo.Util;

import com.example.attendo.Model.SubEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {

    private static final String TEXT_1="Maths";
    private static final String TEXT_2="Electrical";
    private static final String TEXT_3="Computer";

    private static Date getDate(int diffAmount)
    {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(Calendar.MILLISECOND,diffAmount);
        return gregorianCalendar.getTime();
    }

    public static List<SubEntity> getSampleData()
    {
        List<SubEntity> subList=new ArrayList<>();

        return subList;
    }
}
