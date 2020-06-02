package com.example.codeexp.util;

import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DensityUtils {
    public DensityUtils() {
    }

    public static float getScreenDensity() {
        return ContextUtils.getContext().getResources().getDisplayMetrics().density;
    }

    public static float getScreenScaleDensity() {
        return ContextUtils.getContext().getResources().getDisplayMetrics().scaledDensity;
    }

    public static int getScreenWidth() {
        WindowManager wm = (WindowManager)ContextUtils.getContext().getSystemService("window");
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int getScreenHeight() {
        WindowManager wm = (WindowManager)ContextUtils.getContext().getSystemService("window");
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int dip2px(float dpValue) {
        float scale = getScreenDensity();
        return (int)(dpValue * scale + 0.5F);
    }

    public static int px2dip(float pxValue) {
        float scale = getScreenDensity();
        return (int)(pxValue / scale + 0.5F);
    }

    public static int px2sp(float pxValue) {
        float fontScale = getScreenScaleDensity();
        return (int)(pxValue / fontScale + 0.5F);
    }

    public static int sp2px(float spValue) {
        float fontScale = getScreenScaleDensity();
        return (int)(spValue * fontScale + 0.5F);
    }
}
