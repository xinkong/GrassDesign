package com.grass.grass.base;

import android.widget.Toast;

import com.grass.grass.app.BaseApplication;
import com.grass.grass.di.component.DaggerFragmentComponent;
import com.grass.grass.di.component.FragmentComponent;
import com.grass.grass.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Created by huchao on 2017/10/26.
 */

public abstract class BaseMVPFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated() {
        initInject();

        if (mPresenter != null) {
            mPresenter.injectContext(mContext());
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
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
