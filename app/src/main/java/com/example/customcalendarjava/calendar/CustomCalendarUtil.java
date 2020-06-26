package com.example.customcalendarjava.calendar;

import android.util.Log;

import java.util.Calendar;

import static java.util.Calendar.getInstance;

public class CustomCalendarUtil {

    public static int monthStartCheck(int year, int month) {
        //Month의 1일이 무슨 요일인지 Day of Week로 판별한다
        Calendar cal = getInstance();
        cal.set(year, month - 1, 1);
        Log.d("동환", "Calendar Day Of Week : " + cal.get(Calendar.DAY_OF_WEEK));
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static int MonthMax(int year, int month) {
        //Month의 끝이 몇인지 판별 2월(28 29) 1,3,5,7,8,10,12월 (30) 4,6,9,11월 (31)
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
