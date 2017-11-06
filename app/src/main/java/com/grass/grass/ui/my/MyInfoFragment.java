package com.grass.grass.ui.my;


import android.support.v4.app.Fragment;

import com.grass.grass.R;
import com.grass.grass.base.BaseMVPFragment;
import com.grass.grass.contract.my.MyInfo;
import com.grass.grass.presenter.my.MyInfoPersenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyInfoFragment extends BaseMVPFragment<MyInfoPersenter> implements MyInfo.MyInfoView {

    public static MyInfoFragment getInstance(){
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

    }
}
