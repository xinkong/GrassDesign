package com.grass.grass.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.grass.grass.R;
import com.grass.grass.base.BaseMvpActivity;
import com.grass.grass.contract.LoginContract;
import com.grass.grass.presenter.login.LoginPresenter;
import com.grass.grass.ui.MainActivity;
import com.grass.grass.utils.AppUtils;
import com.grass.views.MyPressView;

import butterknife.BindView;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.LoginView {

    @BindView(R.id.userName)
    EditText mUserName;
    @BindView(R.id.userPwd)
    EditText mUserPwd;
    @BindView(R.id.commit)
    MyPressView mCommit;
    @BindView(R.id.register)
    TextView mRegister;

    @Override
    public String getThisPageTitle() {
        return "登录";
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }


    @Override
    public void onActivityStart() {
        mCommit.setOnClickListener(view -> commit());
        mRegister.setOnClickListener(view -> AppUtils.jump(mContext(), RegisterActivity.class));
    }

    private void commit() {
        String userName = mUserName.getText().toString();
        String userPwd = mUserPwd.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            AppUtils.toast(mContext(), "用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(userPwd)) {
            AppUtils.toast(mContext(), "密码不能为空");
            return;
        }

        mPresenter.login(userName, userPwd);

    }

    @Override
    public boolean isCanBack() {
        return false;
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(mContext(), MainActivity.class));
        finish();
    }
}

