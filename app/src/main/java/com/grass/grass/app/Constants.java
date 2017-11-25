package com.grass.grass.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by codeest on 2016/8/3.
 */
public interface Constants {

    //================= TYPE ====================
    //请求成功
    String RequestSuccessCode = "0";
    //无网络情况的错误码
    String NoNetWorkCode = "EAPP_100";
    //未知异常
    String UnknownException = "EAPP_101";

    //================= KEY ====================

    String UserName = "UserName";
    String UserId = "UserId";
    String UserHeadUrl = "UserHeadUrl";


    //================= PATH ====================

    String PATH_DATA = BaseApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "Grass";

    String PATH_CACHE = PATH_DATA + "/GrassCache";

    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "codeest" + File.separator + "GeekNews";

    //================= PREFERENCE ====================

    //================= RxBus Key ==============
    interface RxBusKeyMap{
        String SendMsgOk = "SENDMSGOK";
    }
    //================= RxBus Key ==============

}
