package com.grass.grass.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.grass.grass.R;
import com.grass.grass.app.BaseApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huchao on 2017/10/16.
 * 基类,无MVP形式
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnBinder;

    //初始化控件
    Toolbar mToolbar;
    TextView mTvToolbarTitle;
    FrameLayout mFlContainer;
//    FrameLayout mFlError;
//    FrameLayout mFlLoading;
//    Button mBtnRetry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_view);
        BaseApplication.getInstance().addActivity(this);

        initCommentView();

        //加载内容体
        View view = LayoutInflater.from(this).inflate(getLayoutID(), mFlContainer, false);
        mFlContainer.addView(view);
        mUnBinder = ButterKnife.bind(this);

        if(isShowTitle()){
            mTvToolbarTitle.setText(getThisPageTitle() == null ? "" : getThisPageTitle());
            if(isCanBack()){
                mToolbar.setNavigationIcon(R.mipmap.base_view_back);
                mToolbar.setNavigationOnClickListener(view1 -> onBackPressed());
            }
        }else {
            mToolbar.setVisibility(View.GONE);
        }



        onViewCreated();
        onActivityStart();
    }

    private void initCommentView() {
        mToolbar = (Toolbar) findViewById(R.id.base_toolbar);
        mTvToolbarTitle = (TextView) findViewById(R.id.base_toolbar_title);
        mFlContainer = (FrameLayout) findViewById(R.id.layout_container);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        BaseApplication.getInstance().removeActivity(this);
    }

    public void shouErrorView() {
    }

    public void showEmptyView() {
    }

    public void showLoading() {
    }

    public void hideLoading() {
    }

    //获取当前上下文对象
    public Context mContext() {
        return this;
    }

    //是否显示title
    public boolean isShowTitle() {
        return true;
    }

    //是否可以返回
    public boolean isCanBack() {
        return true;
    }

    public abstract String getThisPageTitle();

    public abstract int getLayoutID();

    public abstract void onViewCreated();

    public abstract void onActivityStart();
}
