package com.grass.grass.presenter.home;

import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.home.Home;
import com.grass.grass.presenter.MainPresenter;

import javax.inject.Inject;

/**
 * Created by huchao on 2017/10/31.
 */

public class HomePersenter extends RxPresenter<Home.HomeView> implements Home.HomeContract {

    @Inject
    public HomePersenter() {}

    @Override
    public void loadData() {

    }
}
