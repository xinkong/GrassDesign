package com.grass.grass.base;

import android.widget.Toast;

import com.grass.grass.app.BaseApplication;
import com.grass.grass.di.component.ActivityComponent;
import com.grass.grass.di.component.DaggerActivityComponent;
import com.grass.grass.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by huchao on 2017/10/17.
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    public T mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public void onViewCreated() {
        initInject();

        if (mPresenter != null) {
            mPresenter.injectContext(this);
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }


    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(mContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorView(int errorCode) {
        super.errorView(errorCode);
    }

    @Override
    public void showEmptyView() {
        super.emptyView();
    }

    @Override
    public void showLoadingView(String msg) {
        super.loading(msg);
    }

    @Override
    public void showMainView() {
        super.loadSuccess();
    }

    public abstract void initInject();

}
