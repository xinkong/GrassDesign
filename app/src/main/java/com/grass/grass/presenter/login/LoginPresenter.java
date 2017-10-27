package com.grass.grass.presenter.login;


import com.grass.grass.app.Constants;
import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.LoginContract;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.utils.RxUtil;
import com.grass.grass.utils.SharePrefsUtils;

import javax.inject.Inject;

/**
 * Created by huchao on 2017/10/19.
 */

public class LoginPresenter extends RxPresenter<LoginContract.LoginView> implements LoginContract.LoginConteact {

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void login(String name, String pwd) {
        //链式调用测试
//        addSubscribe(mHttpUrlManager.login("lisi", "lksdj")
//                .compose(RxUtil.<UserEntity>handleResult())
//                .flatMap(userEntity -> mHttpUrlManager.errorTest(userEntity.userManagerName))
//                .compose(RxUtil.<List<TokenEntity>>handleResult())
//                .compose(RxUtil.rxSchedulerHelper())
//                .subscribeWith(new CommonSubscriber<List<TokenEntity>>(mView, mContext, "正在登录...") {
//
//                    @Override
//                    public void onNext(List<TokenEntity> userEntity) {
//                        super.onNext(userEntity);
//                        Logger.i(userEntity.toString());
//                        mView.loginSuccess();
//                    }
//                }));

        addSubscribe(mHttpUrlManager.login(name, pwd)
                .compose(RxUtil.<UserEntity>handleResult())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserEntity>(mView,mContext,"正在登录...") {
                    @Override
                    public void onNext(UserEntity userEntity) {
                        super.onNext(userEntity);
                        //保存用户信息
                        SharePrefsUtils.getInstance().putString(Constants.UserName,userEntity.userName);
                        mView.loginSuccess();
                    }
                }));

    }
}
