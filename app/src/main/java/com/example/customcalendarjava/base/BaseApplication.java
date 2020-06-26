package com.example.customcalendarjava.base;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    private static Context mContext;

    private static int dpi;
    private static int metricsX;
    private static int metricsY;
    private static int actualDeviceHeight;

    public static Context getContext() {
        return mContext;
    }

    public static void setDpi(int dpi) {
        BaseApplication.dpi = dpi;
    }

    public static int getDpi() {
        return dpi;
    }

    public static void setMetricsX(int metricsX) {
        BaseApplication.metricsX = metricsX;
    }

    public static int getMetricsX() {
        return metricsX;
    }

    public static void setMetricsY(int metricsY) {
        BaseApplication.metricsY = metricsY;
    }

    public static int getMetricsY() {
        return metricsY;
    }

    public static void setActualDeviceHeight(int actualDeviceHeight) {
        BaseApplication.actualDeviceHeight = actualDeviceHeight;
    }

    public static int getActualDeviceHeight() {
        return actualDeviceHeight;
    }

    public static int getStatusBarHeight() {
        int statusBarHeight;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getContext().getResources().getDimensionPixelSize(resourceId);
        } else {
            statusBarHeight = 0;
        }
        return statusBarHeight;
    }

    public static int getNavigationBarHeight() {
        int navigationBarHeight;
        int resourceId = getContext().getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = getContext().getResources().getDimensionPixelSize(resourceId);
        } else {
            navigationBarHeight = 0;
        }
        return navigationBarHeight;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }
}
