package com.grass.grass.contract.home;

import com.grass.grass.base.BasePresenter;
import com.grass.grass.base.BaseView;
import com.grass.grass.entity.MsgInfoEntity;

import java.util.List;

/**
 * Created by huchao on 2017/10/31.
 */

public interface Home {

    interface HomeView extends BaseView{
        void loadDataOk(List<MsgInfoEntity> msgInfoEntity);
    }

    interface HomeContract extends BasePresenter<HomeView>{
        void loadData(int pageIndex,int pageSize);
    }

}
