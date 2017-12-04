package com.grass.grass.ui.my;

import android.widget.TextView;

import com.grass.grass.R;
import com.grass.grass.base.BaseMvpActivity;
import com.grass.grass.contract.my.Setting;
import com.grass.grass.presenter.my.SettingPersenter;

import butterknife.BindView;

public class SettingActivity extends BaseMvpActivity<SettingPersenter> implements Setting.SettintView {


    @BindView(R.id.test)
    TextView mTestView;

    @Override
    public String getThisPageTitle() {
        return "设置";
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_setting;
    }


    @Override
    public void onActivityStart() {
        mTestView.setText("测试bindview是否泄漏---->没有泄漏");
        mPresenter.loadData();
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }
}
