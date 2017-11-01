package com.grass.grass.ui.home;

import com.grass.grass.R;
import com.grass.grass.base.BaseMVPFragment;
import com.grass.grass.contract.home.Home;
import com.grass.grass.presenter.home.HomePersenter;


public class HomeFragment extends BaseMVPFragment<HomePersenter> implements Home.HomeView {

    public static HomeFragment getInstance(){
        return new HomeFragment();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public String getThisPageTitle() {
        return "首页";
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
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityStart() {

    }
}
