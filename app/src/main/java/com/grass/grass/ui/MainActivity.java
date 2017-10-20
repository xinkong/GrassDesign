package com.grass.grass.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;


import com.grass.grass.R;
import com.grass.grass.base.BaseMvpActivity;
import com.grass.grass.contract.MainContract;
import com.grass.grass.presenter.MainPresenter;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.test)
    TextView mTextView;


    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void onActivityStart() {
        mPresenter.getData("zhangsan","pwd");
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext(),LoginActivity.class));
            }
        });
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
