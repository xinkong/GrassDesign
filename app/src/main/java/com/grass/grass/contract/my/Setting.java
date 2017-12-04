package com.grass.grass.contract.my;

import com.grass.grass.base.BasePresenter;
import com.grass.grass.base.BaseView;

/**
 * Created by huchao on 2017/12/1.
 */

public interface Setting {

    interface SettintView extends BaseView{

    }

    interface SettContract extends BasePresenter<SettintView>{

        void loadData();

    }

}
