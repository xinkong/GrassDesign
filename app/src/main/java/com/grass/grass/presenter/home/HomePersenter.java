package com.grass.grass.presenter.home;

import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.home.Home;
import com.grass.grass.entity.MsgInfoEntity;
import com.grass.grass.presenter.MainPresenter;
import com.grass.grass.utils.RxUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by huchao on 2017/10/31.
 */

public class HomePersenter extends RxPresenter<Home.HomeView> implements Home.HomeContract {

    @Inject
    public HomePersenter() {}


    @Override
    public void loadData(int pageIndex, int pageSize) {
        Map<String,String> params = new HashMap<>();
        params.put("pageIndex",pageIndex+"");
        params.put("pageSize",pageSize+"");
        addSubscribe(mHttpUrlManager.getMsg(params)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.<List<MsgInfoEntity>>handleResult())
                .subscribeWith(new CommonSubscriber<List<MsgInfoEntity>>(mView){
                    @Override
                    public void onNext(List<MsgInfoEntity> msgInfoEntities) {
                        super.onNext(msgInfoEntities);
                        mView.loadDataOk(msgInfoEntities);
                    }
                })

        );
    }
}
