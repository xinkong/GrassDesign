package com.grass.grass.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.grass.grass.app.BaseApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huchao on 2017/10/16.
 * 基类,无MVP形式
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        mUnBinder = ButterKnife.bind(this);
        BaseApplication.getInstance().addActivity(this);
        onViewCreated();
        onActivityStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        BaseApplication.getInstance().removeActivity(this);
    }

    //获取当前上下文对象
    public Context mContext(){
        return this;
    }

    public abstract int getLayoutID();
    public abstract void onViewCreated();
    public abstract void onActivityStart();
}
