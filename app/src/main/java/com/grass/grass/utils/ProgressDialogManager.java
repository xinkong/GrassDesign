package com.grass.grass.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class ProgressDialogManager {

    private static ProgressDialogManager messageDialog;
    private CustomProgressDialog mDialog;
    private TextView mTvContent;

    private ProgressDialogManager() {
    }

    public static ProgressDialogManager getInstance() {
        if (messageDialog == null) {
            synchronized (ProgressDialogManager.class) {
                if (messageDialog == null) {
                    messageDialog = new ProgressDialogManager();
                }
            }
        }
        return messageDialog;
    }

    public CustomProgressDialog getCustionDialog() {
        return mDialog;
    }

    public void dissmiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public void showWait(Context context, String content) {
        if (context != null && !((Activity) context).isDestroyed()) {
//            if (mDialog == null) {
                mDialog = new CustomProgressDialog(context, content);
//            }
            if (!mDialog.isShowing()){
                mDialog.show();
            }
        }

    }


}