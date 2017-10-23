package com.grass.grass.base;

import android.content.Context;

/**
 * Created by codeest on 2016/8/2.
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();

    void injectContext(Context context);
}
