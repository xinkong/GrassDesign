package com.grass.grass.contract.login;

import com.grass.grass.base.BasePresenter;
import com.grass.grass.base.BaseView;

import java.util.List;

/**
 * Created by huchao on 2017/11/2.
 */

public interface AddMsg {

    interface AddMsgView extends BaseView{
        void sendSuccess();
    }

    interface AddMsgContract extends BasePresenter<AddMsgView>{
        void sendMsg(String content, List<String> imagePath);
    }
}
