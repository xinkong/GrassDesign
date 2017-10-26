package com.grass.grass.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.grass.grass.app.BaseApplication;

/**
 * Created by baozhiyuan on 16/6/24.
 */
public class AppUtils {

    public static void jump(Context context, Class<? extends Activity> targetActivity){
        jump(context,targetActivity,null);
    }

    public static void jump(Context context, Class<? extends Activity> targetActivity, Bundle bundle){
        Intent intent = new Intent(context,targetActivity);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void toast(Context context , String msg){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int messageId){
        Toast.makeText(context,messageId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 隐藏键盘
     *
     * @param
     */
    public static void hideSoftInput(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * 显示键盘
     */
    public static void showSoftInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }

    /**
     * 判断网络是否连接
     * @return
     */
    public static boolean isNetConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) (BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivity != null) {
            NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }
}
