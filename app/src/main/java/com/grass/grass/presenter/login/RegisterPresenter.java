package com.grass.grass.presenter.login;

import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.login.Register;
import com.grass.grass.entity.UserEntity;

import javax.inject.Inject;

/**
 * Created by huchao on 2017/10/26.
 */

public class RegisterPresenter extends RxPresenter<Register.RegisterView> implements Register.RegisterConteact {

    @Inject
    public RegisterPresenter(){}

    @Override
    public void register(UserEntity userEntity) {

    }
}
