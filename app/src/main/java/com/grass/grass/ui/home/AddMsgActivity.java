package com.grass.grass.ui.home;

import android.os.Environment;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;

import com.grass.grass.R;
import com.grass.grass.base.BaseMvpActivity;
import com.grass.grass.contract.login.AddMsg;
import com.grass.grass.presenter.home.AddMsgPersenter;
import com.grass.grass.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddMsgActivity extends BaseMvpActivity<AddMsgPersenter> implements AddMsg.AddMsgView {

    @BindView(R.id.sendContent)
    EditText mSendContent;

    List<String> imagePaths;

    @Override
    public String getThisPageTitle() {
        return "发布消息";
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_add_msg;
    }

    @Override
    public void onActivityStart() {
        imagePaths = new ArrayList<>();
        getToolbar().inflateMenu(R.menu.menu_send);
    }

    @Override
    public void onItemMenuClick(MenuItem item) {
        super.onItemMenuClick(item);
        String content = mSendContent.getText().toString();
        if(TextUtils.isEmpty(content) && imagePaths.size() == 0){
            AppUtils.toast(mContext(),"不能发行空内容");
            return;
        }
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hsc/app/img/temp/test_20170926161616.jpg";
        String path2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hsc/app/img/temp/test_20170926161559.jpg";
        imagePaths.add(path);
        imagePaths.add(path2);
        mPresenter.sendMsg(content,imagePaths);
    }

    @Override
    public void sendSuccess() {
        AppUtils.toast(mContext(),"发送成功");
        finish();
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }
}
