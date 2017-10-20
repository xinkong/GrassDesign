package com.grass.grass.ui;

import android.widget.Toast;

import com.grass.grass.R;
import com.grass.grass.base.BaseMvpActivity;
import com.grass.grass.contract.LoginContract;
import com.grass.grass.presenter.LoginPresenter;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.LoginView {


    @Override
    public int getLayoutID() {
        return  R.layout.activity_login;
    }

    @Override
    public void onActivityStart() {
        mPresenter.login("alsdjfla", "aljfda");
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(mContext(),"登录成功",Toast.LENGTH_SHORT).show();
    }
}

