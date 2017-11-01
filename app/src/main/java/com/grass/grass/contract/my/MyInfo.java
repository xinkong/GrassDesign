package com.grass.grass.contract.my;

import com.grass.grass.base.BasePresenter;
import com.grass.grass.base.BaseView;

/**
 * Created by huchao on 2017/10/31.
 */

public interface MyInfo {

    interface MyInfoView extends BaseView {

    }

    interface MyInfoContract extends BasePresenter<MyInfoView> {

        void loadData();
    }

}
