package com.example.customcalendarjava;

import static com.example.customcalendarjava.base.BaseApplication.getDpi;

public class Util {

    public static int dpToPx(int dp) {
        return (dp * (getDpi() / 160));
    }

    public static int pxToDp(int px) {
        return (px / (getDpi() / 160));
    }
}
