package com.example.customcalendarjava.calendar.dataType;

public class CustomDate {
    private String state;
    private int year, month, day;

    public CustomDate(String state, int year, int month, int day) {
        this.state = state;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
