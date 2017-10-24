package com.grass.grass.base;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.grass.grass.R;
import com.grass.grass.app.BaseApplication;
import com.grass.grass.app.Constants;

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

    FrameLayout mFlError;
    TextView mTvErrorMsg;
    Button mBtnRetry;

    FrameLayout mFlLoading;
    ImageView mIvLoadAnim;
    TextView mTvLoadMsg;

    RelativeLayout mEmptyView;
    TextView mTvEmptyMsg;

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

        if (isShowTitle()) {
            mTvToolbarTitle.setText(getThisPageTitle() == null ? "" : getThisPageTitle());
            if (isCanBack()) {
                mToolbar.setNavigationIcon(R.mipmap.base_view_back);
                mToolbar.setNavigationOnClickListener(view1 -> onBackPressed());
            }
        } else {
            mToolbar.setVisibility(View.GONE);
        }


        onViewCreated();
        onActivityStart();
    }

    private void initCommentView() {
        mToolbar = (Toolbar) findViewById(R.id.base_toolbar);
        mTvToolbarTitle = (TextView) findViewById(R.id.base_toolbar_title);
        mFlContainer = (FrameLayout) findViewById(R.id.layout_container);

        mFlError = (FrameLayout) findViewById(R.id.layout_error);
        mTvErrorMsg = (TextView) findViewById(R.id.error_tv_msg);
        mBtnRetry = (Button) findViewById(R.id.error_btn_retry);
        //重试
        mBtnRetry.setOnClickListener(view -> onRetry());

        mFlLoading = (FrameLayout) findViewById(R.id.layout_loding);
        mIvLoadAnim = (ImageView) findViewById(R.id.loading_view_anim);
        mTvLoadMsg = (TextView) findViewById(R.id.loading_view_msg);

        mEmptyView = (RelativeLayout) findViewById(R.id.layout_empty);
        mTvEmptyMsg = (TextView) findViewById(R.id.emptyView_tv_hintMsg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        BaseApplication.getInstance().removeActivity(this);
    }

    public void errorView(int  msg) {
        mFlError.setVisibility(View.VISIBLE);
        mFlLoading.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        if(msg == Constants.NoNetWorkCode){
            mTvErrorMsg.setText("当前网络不可用,请检查网络连接");
        }else {
            mTvErrorMsg.setText("访问服务端异常,请重试");
        }
    }

    public void emptyView() {
        mFlError.setVisibility(View.GONE);
        mFlLoading.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    public void loading(String msg) {
        mFlError.setVisibility(View.GONE);
        mFlLoading.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mTvLoadMsg.setText(TextUtils.isEmpty(msg)?"正在加载":msg);
        //启动动画
        AnimationDrawable animationDrawable= (AnimationDrawable) mIvLoadAnim.getDrawable();
        if(animationDrawable!=null && !animationDrawable.isRunning()){
            animationDrawable.start();
        }
    }

    public void loadSuccess() {
        mFlError.setVisibility(View.GONE);
        mFlLoading.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        AnimationDrawable animationDrawable= (AnimationDrawable) mIvLoadAnim.getDrawable();
        if(animationDrawable!=null && animationDrawable.isRunning()){
            animationDrawable.stop();
        }
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

    //发生错误是重试
    public void onRetry() {
    }

    public abstract String getThisPageTitle();

    public abstract int getLayoutID();

    public abstract void onViewCreated();

    public abstract void onActivityStart();
}
