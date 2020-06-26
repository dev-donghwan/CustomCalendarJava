package com.example.customcalendarjava.base;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.customcalendarjava.base.BaseApplication.getMetricsY;
import static com.example.customcalendarjava.base.BaseApplication.getNavigationBarHeight;
import static com.example.customcalendarjava.base.BaseApplication.getStatusBarHeight;
import static com.example.customcalendarjava.base.BaseApplication.setActualDeviceHeight;
import static com.example.customcalendarjava.base.BaseApplication.setDpi;
import static com.example.customcalendarjava.base.BaseApplication.setMetricsX;
import static com.example.customcalendarjava.base.BaseApplication.setMetricsY;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        setMetricsX(displayMetrics.widthPixels);
        setMetricsY(displayMetrics.heightPixels);
        setDpi(displayMetrics.densityDpi);
        setActualDeviceHeight(getMetricsY() - getNavigationBarHeight() - getStatusBarHeight());

    }
}
