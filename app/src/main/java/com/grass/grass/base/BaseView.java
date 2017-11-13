package com.grass.grass.base;

/**
 * Created by huchao on 2017/9/5.
 * View基类
 */

public interface BaseView {

    void showErrorMsg(String msg);
    //=======  State  =======
    void showErrorView(String errorCode);

    void showEmptyView();

    void showLoadingView(String hideMsg);

    void showMainView();

}
