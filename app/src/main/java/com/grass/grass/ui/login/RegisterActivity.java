package com.grass.grass.ui.login;

import android.text.TextUtils;
import android.widget.EditText;

import com.grass.grass.R;
import com.grass.grass.base.BaseMvpActivity;
import com.grass.grass.contract.login.Register;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.presenter.login.RegisterPresenter;
import com.grass.grass.ui.MainActivity;
import com.grass.grass.utils.AppUtils;
import com.grass.views.MyPressView;

import butterknife.BindView;

public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> implements Register.RegisterView {


    @BindView(R.id.userName)
    EditText mUserName;
    @BindView(R.id.userPwd)
    EditText mUserPwd;
    @BindView(R.id.commit)
    MyPressView mCommit;

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

        mCommit.setOnClickListener(view -> register());
    }

    private void register() {
        String username = mUserName.getText().toString();
        String userpwd = mUserPwd.getText().toString();
        if (TextUtils.isEmpty(username)) {
            AppUtils.toast(mContext(), "用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(userpwd)) {
            AppUtils.toast(mContext(), "密码不能为空");
            return;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.userName = username;
        userEntity.userPwd = userpwd;
        mPresenter.register(userEntity);
    }

    @Override
    public void registerSuccess() {
        AppUtils.jump(mContext(), MainActivity.class);
        finish();
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

}
