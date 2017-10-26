package com.grass.grass.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.grass.grass.R;
import com.grass.grass.app.Constants;
import com.grass.grass.base.BaseActivity;
import com.grass.grass.ui.MainActivity;
import com.grass.grass.ui.login.LoginActivity;
import com.grass.grass.utils.SharePrefsUtils;

public class StartActivity extends BaseActivity {

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(TextUtils.isEmpty(SharePrefsUtils.getInstance().getString(Constants.userName,""))){
                startActivity(new Intent(mContext(),LoginActivity.class));
            }else {
                startActivity(new Intent(mContext(),MainActivity.class));
            }
            finish();
        }
    };

    @Override
    public boolean isShowTitle() {
        return false;
    }

    @Override
    public String getThisPageTitle() {
        return null;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_start;
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onActivityStart() {
        mHandler.sendEmptyMessageDelayed(1,2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(1);
    }
}
