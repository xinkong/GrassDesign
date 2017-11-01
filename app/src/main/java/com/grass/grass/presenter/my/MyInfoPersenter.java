package com.grass.grass.presenter.my;

import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.my.MyInfo;

import javax.inject.Inject;

/**
 * Created by huchao on 2017/10/31.
 */

public class MyInfoPersenter extends RxPresenter<MyInfo.MyInfoView> implements MyInfo.MyInfoContract {
    @Inject
    public MyInfoPersenter() {
    }

    @Override
    public void loadData() {

    }
}
