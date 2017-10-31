package com.grass.grass.ui;

import android.os.Environment;
import android.widget.TextView;

import com.grass.grass.R;
import com.grass.grass.base.BaseMvpActivity;
import com.grass.grass.contract.MainContract;
import com.grass.grass.presenter.MainPresenter;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.test)
    TextView mTextView;


    @Override
    public String getThisPageTitle() {
        return "首页";
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void onActivityStart() {
//        mPresenter.getData("zhangsan","pwd");
        mTextView.setOnClickListener(view -> uploadeImage());
    }

    private void uploadeImage() {
        List<String> paths = new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/hsc/app/img/temp/test_20170926161616.jpg";
        String path2 = Environment.getExternalStorageDirectory().getAbsolutePath()+"/hsc/app/img/temp/test_20170926161559.jpg";
        paths.add(path);
        paths.add(path2);
        mPresenter.uploadImages(paths);

    }

    @Override
    public void onRetry() {
        super.onRetry();
        mPresenter.getData("zhangsan","pwd");
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
        mTextView.setText(content);
    }
}
