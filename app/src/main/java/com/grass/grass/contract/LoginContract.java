package com.grass.grass.contract;


import com.grass.grass.base.BasePresenter;
import com.grass.grass.base.BaseView;

/**
 * Created by huchao on 2017/10/19.
 */

public interface LoginContract {

    interface LoginView extends BaseView {
        void loginSuccess();
        void sendMessageOk();
    }

    interface LoginConteact extends BasePresenter<LoginView> {
        void login(String name, String pwd);
        void sendMessage(String phone,String token,String user_id);
    }

}
