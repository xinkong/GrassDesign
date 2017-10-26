package com.grass.grass.ui.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.grass.grass.R;
import com.grass.grass.base.BaseMvpActivity;
import com.grass.grass.contract.login.Register;
import com.grass.grass.presenter.login.RegisterPresenter;

public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> implements Register.RegisterView {


    @Override
    public String getThisPageTitle() {
        return "注册";
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    public void onActivityStart() {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }
}
