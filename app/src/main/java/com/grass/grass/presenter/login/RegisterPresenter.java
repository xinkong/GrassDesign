package com.grass.grass.presenter.login;

import com.grass.grass.app.Constants;
import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.login.Register;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.utils.RxUtil;
import com.grass.grass.utils.SharePrefsUtils;

import javax.inject.Inject;

/**
 * Created by huchao on 2017/10/26.
 */

public class RegisterPresenter extends RxPresenter<Register.RegisterView> implements Register.RegisterConteact {

    @Inject
    public RegisterPresenter() {}

    @Override
    public void register(UserEntity userEntity) {
        addSubscribe(mHttpUrlManager.register(userEntity.userName, userEntity.userPwd)
                .compose(RxUtil.<String>handleResult())
                .flatMap(result -> mHttpUrlManager.login(userEntity.userName, userEntity.userPwd))
                .compose(RxUtil.<UserEntity>handleResult())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserEntity>(mView, mContext, "注册中...") {
                    @Override
                    public void onNext(UserEntity user) {
                        super.onNext(user);
                        //保存用户信息
                        SharePrefsUtils.getInstance().putString(Constants.UserName,user.userName);
                        mView.registerSuccess();
                    }
                }));
    }
}
