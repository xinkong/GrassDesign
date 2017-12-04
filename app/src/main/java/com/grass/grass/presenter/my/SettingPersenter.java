package com.grass.grass.presenter.my;

import com.grass.grass.app.Constants;
import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.my.Setting;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.utils.AppUtils;
import com.grass.grass.utils.RxUtil;
import com.grass.grass.utils.SharePrefsUtils;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

/**
 * Created by huchao on 2017/12/1.
 */

public class SettingPersenter extends RxPresenter<Setting.SettintView> implements Setting.SettContract {
    @Inject
    public SettingPersenter(){}

    @Override
    public void loadData() {
        addSubscribe(mHttpUrlManager.login("qwer","123")
        .compose(RxUtil.<UserEntity>handleResult())
        .compose(RxUtil.rxSchedulerHelper())
        .subscribeWith(new CommonSubscriber<UserEntity>(mView,mContext,"正在登录..."){
            @Override
            public void onNext(UserEntity userEntity) {
                super.onNext(userEntity);
                Logger.i(userEntity.toString());
            }
        }));
    }
}
