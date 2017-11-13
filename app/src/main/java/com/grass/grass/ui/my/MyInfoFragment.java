package com.grass.grass.ui.my;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.grass.grass.R;
import com.grass.grass.app.Constants;
import com.grass.grass.base.BaseMVPFragment;
import com.grass.grass.contract.my.MyInfo;
import com.grass.grass.presenter.my.MyInfoPersenter;
import com.grass.grass.ui.login.LoginActivity;
import com.grass.grass.utils.AppUtils;
import com.grass.grass.utils.SharePrefsUtils;
import com.grass.grass.view.MyPressView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyInfoFragment extends BaseMVPFragment<MyInfoPersenter> implements MyInfo.MyInfoView {

    @BindView(R.id.userName)
    TextView mUserName;
    @BindView(R.id.exitLogin)
    MyPressView mExitLogin;
    @BindView(R.id.headPic)
    ImageView mHeadPic;

    public static MyInfoFragment getInstance() {
        return new MyInfoFragment();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public String getThisPageTitle() {
        return "我的";
    }

    @Override
    public boolean isShowTitle() {
        return true;
    }

    @Override
    public boolean isCanBack() {
        return false;
    }


    @Override
    public int getLayoutID() {
        return R.layout.fragment_my_info;
    }

    @Override
    public void onFragmentStart() {
        mUserName.setText(SharePrefsUtils.getInstance().getString(Constants.UserName,""));
        mExitLogin.setOnClickListener(view -> exit());

    }

    private void exit() {
        SharePrefsUtils.getInstance().remove(Constants.UserId);
        AppUtils.jump(mContext(), LoginActivity.class);
        ((Activity) mContext()).finish();
    }


}
