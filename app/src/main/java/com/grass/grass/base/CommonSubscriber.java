package com.grass.grass.base;

import android.content.Context;

import com.grass.grass.app.Constants;
import com.grass.grass.utils.NetWorkConnInfo;
import com.grass.grass.utils.ProgressDialogManager;
import com.grass.grass.utils.http.ApiException;
import com.orhanobut.logger.Logger;

import io.reactivex.subscribers.ResourceSubscriber;

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private Context mContext;

    //不带效果的view
    protected CommonSubscriber(BaseView view) {
        this.mView = view;
    }

    //初始化嵌套页面内加载方式的等待进度提醒
    protected CommonSubscriber(BaseView view, String hideMsg) {
        this(view, null, hideMsg);
    }

    //初始化Dialog方式的等待提醒
    protected CommonSubscriber(BaseView view, Context context, String hideMsg) {
        this.mView = view;
        this.mContext = context;
        if (context != null) {
            ProgressDialogManager.getInstance().showWait(context, hideMsg);
        } else {
            mView.showLoadingView(hideMsg);
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(T t) {
        ProgressDialogManager.getInstance().dissmiss();
        mView.showMainView();
    }

    @Override
    public void onError(Throwable e) {

        ProgressDialogManager.getInstance().dissmiss();

        if (mView == null) {
            return;
        }

        if (!NetWorkConnInfo.isNetworkConnected()) {
            mView.showErrorView(Constants.NoNetWorkCode);
            return;
        }

        //显示错误消息和视图
        if (e instanceof ApiException) {
            mView.showErrorMsg(e.getMessage());
            Logger.e("错误内容,code:" + ((ApiException) e).getCode() + ",message:" + e.getMessage());
        } else {
            mView.showErrorMsg("未知错误,请重试");
            Logger.e("错误内容,error:" + e.toString());
        }

        if (mContext == null){
            if(e instanceof ApiException){
                mView.showErrorView(((ApiException) e).getCode());
            }else {
                mView.showErrorView(Constants.UnknownException);
            }
        }

    }
}