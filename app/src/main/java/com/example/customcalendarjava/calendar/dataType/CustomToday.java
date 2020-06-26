package com.example.customcalendarjava.calendar.dataType;

import java.text.SimpleDateFormat;

public class CustomToday {

    private java.util.Date today;
    private String year, month, day;

    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

    public CustomToday() {
        this.today = new java.util.Date();
        year = yearFormat.format(today);
        month = monthFormat.format(today);
        day = dayFormat.format(today);
    }

    public int getYear() {
        return Integer.parseInt(year);
    }

    public int getMonth() {
        return Integer.parseInt(month);
    }

    public int getDay() {
        return Integer.parseInt(day);
    }

}
