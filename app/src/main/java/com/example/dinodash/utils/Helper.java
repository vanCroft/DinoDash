package com.example.dinodash.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Helper {
    public static int SCREEN_WIDTH, SCREEN_HEIGHT;

    /**
     *  Get the width and height of the device
     * @param context
     */
    public static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        Helper.SCREEN_WIDTH = dm.widthPixels;
        Helper.SCREEN_HEIGHT = dm.heightPixels;
    }
}
