package com.grass.grass.contract.home;

import com.grass.grass.base.BasePresenter;
import com.grass.grass.base.BaseView;

/**
 * Created by huchao on 2017/10/31.
 */

public interface Home {

    interface HomeView extends BaseView{
        void loadSuccess();
    }

    interface HomeContract extends BasePresenter<HomeView>{
        void loadData();
    }

}
