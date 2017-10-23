package com.grass.grass.base;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.grass.grass.app.Constants;
import com.grass.grass.utils.NetWorkConnInfo;
import com.grass.grass.utils.ProgressDialogManager;
import com.grass.grass.utils.http.ApiException;
import com.orhanobut.logger.Logger;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowErrorState = true;


    protected CommonSubscriber(BaseView view) {
        this.mView = view;
    }

    protected CommonSubscriber(BaseView view, String hideMsg) {
//        this.mView = view;
//        mView.stateLoading();
        this(view, null, hideMsg);
    }

    protected CommonSubscriber(BaseView view, Context context, String hideMsg) {
        this.mView = view;
        if (!NetWorkConnInfo.isNetworkConnected()) {//提示网络不可用
            onError(new ApiException("无网络", Constants.NONETWORKCODE));
            onComplete();
            return;
        }
        if (context != null) {
            ProgressDialogManager.getInstance().showWait(context, hideMsg);
        }
    }


    @Override
    public void onComplete() {
        ProgressDialogManager.getInstance().dissmiss();
    }

    @Override
    public void onNext(T t) {
//        ProgressDialogManager.getInstance().dissmiss();
    }

    @Override
    public void onError(Throwable e) {

        ProgressDialogManager.getInstance().dissmiss();

        if (mView == null) {
            return;
        }

        if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            int code = exception.getCode();
            if (code == Constants.NONETWORKCODE) {
                Logger.e("当前无网络连接");
            } else {
                mView.showErrorMsg(e.toString());
            }
        } else {
            mView.showErrorMsg("未知错误ヽ(≧Д≦)ノ");
            Log.e("error", e.toString());
        }

    }
}