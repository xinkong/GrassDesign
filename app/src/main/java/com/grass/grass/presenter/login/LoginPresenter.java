package com.grass.grass.presenter.login;


import com.grass.grass.app.Constants;
import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.LoginContract;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.utils.RxUtil;
import com.grass.grass.utils.SharePrefsUtils;
import com.grass.grass.utils.Tools;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

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
        addSubscribe(mHttpUrlManager.login(name, pwd)
                .compose(RxUtil.<UserEntity>handleResult())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserEntity>(mView,mContext,"正在登录...") {
                    @Override
                    public void onNext(UserEntity userEntity) {
                        super.onNext(userEntity);
                        //保存用户信息
                        SharePrefsUtils.getInstance().putString(Constants.UserId,userEntity.userId+"");
                        SharePrefsUtils.getInstance().putString(Constants.UserName,userEntity.userName);
                        SharePrefsUtils.getInstance().putString(Constants.UserHeadUrl,userEntity.userHeadPic);
                        mView.loginSuccess();
                    }
                }));

    }

    @Override
    public void sendMessage(String phone, String token, String user_id) {

        String url = "http://dev.xiaodongxing.com/api/v3/base/sendCode/?F=android&V=3.5.0&timestamp=1519796193742&sign=50a1341047a7703b80af418a317ab4a4";
        Map<String,String> params = new HashMap<>();
        params.put("token",token);
        params.put("user_id",user_id);
        addSubscribe(mHttpUrlManager.sendCode(url,params)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView,mContext,"正在发送...") {
                    @Override
                    public void onNext(Object userEntity) {
                        super.onNext(userEntity);
                        mView.sendMessageOk();
                    }
                }));


    }
}



