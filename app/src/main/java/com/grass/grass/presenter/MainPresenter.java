package com.grass.grass.presenter;

import android.util.Log;


import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.MainContract;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.utils.RxUtil;

import javax.inject.Inject;

/**
 * Created by huchao on 2017/10/17.
 */
public class MainPresenter extends RxPresenter<MainContract.MainView> implements MainContract.Presenter {

    @Inject
    public MainPresenter(){

    }

    @Override
    public void getData(String name,String pwd) {

        addSubscribe(mHttpUrlManager.login(name,pwd)
                .compose(RxUtil.<UserEntity>rxSchedulerHelper())
                .compose(RxUtil.<UserEntity>handleMyResult())
                .subscribeWith(new CommonSubscriber<UserEntity>(mView){

                    @Override
                    public void onNext(UserEntity userEntity) {
                        Log.i("tag------------------>",userEntity.toString());
                        mView.showContent(userEntity.toString());
                    }
                }));
    }
}
