package com.grass.grass.base;


import android.content.Context;

import com.grass.grass.utils.http.HttpUrlManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 基于Rx的Presenter封装,控制订阅的生命周期
 * @param <T>
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    //注入全局HttpUrlManager
    @Inject
    public HttpUrlManager mHttpUrlManager;

    protected T mView;
    protected CompositeDisposable mCompositeDisposable;
    public Context mContext;

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }

    @Override
    public void injectContext(Context context) {
        mContext = context;
    }
}