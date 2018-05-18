package com.grass.grass.utils;

import android.content.Context;

/**
 * Created by huchao on 2017/12/20.
 */

public class TestUtils {
    private Context mContext;
    private static TestUtils mInstence;

    private TestUtils(Context context){
        this.mContext = context;
    }

    public static TestUtils getInstence(Context context){
        if(mInstence== null){
            mInstence = new TestUtils(context.getApplicationContext());
        }
        return mInstence;
    }

    public String test(){
        return "我是测试字符串";
    }
}
