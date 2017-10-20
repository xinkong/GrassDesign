package com.grass.grass.presenter;

import android.util.Log;


import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.LoginContract;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.utils.RxUtil;

import javax.inject.Inject;

/**
 * Created by huchao on 2017/10/19.
 */

public class LoginPresenter extends RxPresenter<LoginContract.LoginView> implements LoginContract.LoginConteact {

    @Inject
    public LoginPresenter (){}

    @Override
    public void login(String name, String pwd) {
        addSubscribe(mHttpUrlManager.login("lisi","lksdj")
        .compose(RxUtil.<UserEntity>rxSchedulerHelper())
        .compose(RxUtil.<UserEntity>handleResult())
        .subscribeWith(new CommonSubscriber<UserEntity>(mView){

            @Override
            public void onNext(UserEntity userEntity) {
                Log.i("tag",userEntity.toString());
                mView.loginSuccess();
            }
        }));
    }
}
