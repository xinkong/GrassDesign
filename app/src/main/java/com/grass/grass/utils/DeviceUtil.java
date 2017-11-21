package com.grass.grass.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import com.grass.grass.app.BaseApplication;

public class DeviceUtil {
    public static Display getDefaultDisplay() {
        return ((WindowManager) BaseApplication.getInstance().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, BaseApplication.getInstance().getResources().getDisplayMetrics());
    }

    public static int sp2px(float sp) {
        final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    public static boolean isTablet(Context c) {
        return (c.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static int[] getScreen() {
        return new int[]{getDefaultDisplay().getWidth(), getDefaultDisplay().getHeight()};
    }

    public static int getActionBarSize() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (BaseApplication.getInstance().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, BaseApplication.getInstance().getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    public static int getVersionCode(Context context)//获取版本号(内部识别号)
    {
        try {
            PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

}
