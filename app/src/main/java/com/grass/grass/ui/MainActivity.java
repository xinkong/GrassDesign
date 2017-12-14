package com.grass.grass.ui;

import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.grass.grass.R;
import com.grass.grass.base.BaseMvpActivity;
import com.grass.grass.contract.MainContract;
import com.grass.grass.presenter.MainPresenter;
import com.grass.grass.ui.adapter.TabPagerAdapter;
import com.grass.grass.ui.home.HomeFragment;
import com.grass.grass.ui.my.MyInfoFragment;
import com.grass.grass.view.TabViewPager;
import com.orhanobut.logger.Logger;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.bottombar)
    BottomBar mBottomBar;
    @BindView(R.id.tabPage)
    TabViewPager mViewPager;
    List<Fragment> fragments;

    @Override
    public String getThisPageTitle() {
        return "";
    }

    @Override
    public boolean isShowTitle() {
        return false;
    }

    @Override
    public boolean isCanSwipeToDismiss() {
        return false;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    private MyInfoFragment mMyInfoFragment = MyInfoFragment.getInstance();

    @Override
    public void onActivityStart() {

//        mPresenter.getData("zhangsan","pwd");717129012037
//        mTextView.setOnClickListener(view -> uploadeImage());

        fragments = new ArrayList<>(4);
        fragments.add(HomeFragment.getInstance());
        fragments.add(mMyInfoFragment);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), fragments));

        mBottomBar.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
        mBottomBar.setActiveTabColor(getResources().getColor(R.color.colorAccent));
        mBottomBar.setOnTabSelectListener(resId -> {
            switch (resId) {
                case R.id.tab_home:
                    mViewPager.setCurrentItem(0, false);
                    break;
                case R.id.tab_my:
//                    设置和移除Badge 即小圆点
//                    mBottomBar.getTabAtPosition(1).setBadgeCount(new Random().nextInt(100));
//                    mBottomBar.getTabAtPosition(1).removeBadge();
                    mViewPager.setCurrentItem(1, false);
                    mMyInfoFragment.onPageSelInit();
                    break;
            }
        });


    }

    private void uploadeImage() {
        List<String> paths = new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hsc/app/img/temp/test_20170926161616.jpg";
        String path2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hsc/app/img/temp/test_20170926161559.jpg";
        paths.add(path);
        paths.add(path2);
        mPresenter.uploadImages(paths);

    }

    @Override
    public void onRetry() {
        super.onRetry();
        mPresenter.getData("zhangsan", "pwd");
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
    public void showContent(String content) {
//        mTextView.setText(content);
    }
}
