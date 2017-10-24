package com.grass.grass.presenter;

import android.content.Context;
import android.util.Log;


import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.LoginContract;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.utils.RxUtil;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by huchao on 2017/10/19.
 */

public class LoginPresenter extends RxPresenter<LoginContract.LoginView> implements LoginContract.LoginConteact {

    @Inject
    public LoginPresenter() {
    }

    //http://www.jianshu.com/p/b1979c25634f 这个内容仔细阅读看看

    @Override
    public void login(String name, String pwd) {
        //链式调用测试
        addSubscribe(mHttpUrlManager.login("lisi", "lksdj")
                .compose(RxUtil.<UserEntity>handleResult())
                .flatMap(userEntity -> mHttpUrlManager.errorTest(userEntity.userManagerName))
                .compose(RxUtil.<UserEntity>rxSchedulerHelper())
                .compose(RxUtil.<UserEntity>handleResult())
                .subscribeWith(new CommonSubscriber<UserEntity>(mView, mContext, "正在登录...") {

                    @Override
                    public void onNext(UserEntity userEntity) {
                        super.onNext(userEntity);
                        Logger.i(userEntity.toString());
                        mView.loginSuccess();
                    }
                }));
    }

}
