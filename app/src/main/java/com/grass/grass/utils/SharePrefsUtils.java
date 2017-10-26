package com.grass.grass.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.grass.grass.app.BaseApplication;

/**
 * @author huchao
 * @time 2017/7/3 17:35
 * @explain: SharedPreferences工具类
 */
public class SharePrefsUtils {

    private static SharedPreferences sp = null;
    private static SharePrefsUtils spManager = null;
    private static SharedPreferences.Editor editor = null;
    /**
     * Preference文件名
     */
    private static final String SHARE_PREFREENCE_NAME = "ytoStation.pre";

    /**
     * 初始化sp和edit
     */
    private SharePrefsUtils() {
        sp = BaseApplication.getInstance().getSharedPreferences(SHARE_PREFREENCE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }


    public static SharePrefsUtils getInstance() {
        if (spManager == null || sp == null || editor == null) {
            synchronized (SharePrefsUtils.class) {
                if (spManager == null || sp == null || editor == null) {
                    spManager = new SharePrefsUtils();
                }
            }
        }
        return spManager;
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void putLong(String key, Long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLong(String key, int defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public boolean isKeyExist(String key) {
        return sp.contains(key);
    }

    public float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }
}
