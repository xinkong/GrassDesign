package com.grass.grass.ui;

import android.widget.TextView;
import android.widget.Toast;

import com.grass.grass.R;
import com.grass.grass.base.BaseMvpActivity;
import com.grass.grass.contract.LoginContract;
import com.grass.grass.presenter.LoginPresenter;

import butterknife.BindView;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.LoginView {


    @BindView(R.id.error)
    TextView mError;

    @Override
    public String getThisPageTitle() {
        return "登录";
    }

    @Override
    public int getLayoutID() {
        return  R.layout.activity_login;
    }

    @Override
    public void onActivityStart() {
        mPresenter.login("alsdjfla", "aljfda");
        mError.setOnClickListener(view ->{
            String a = "aa";
            if(a.equals("aa")){
                //doSomeThing
                Toast.makeText(this,"我修复了",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void loginSuccess() {
        //测试空视图
        showEmptyView();
        Toast.makeText(mContext(),"登录成功",Toast.LENGTH_SHORT).show();
    }
}

