package com.grass.grass.contract.login;

import com.grass.grass.base.BasePresenter;
import com.grass.grass.base.BaseView;
import com.grass.grass.entity.UserEntity;

/**
 * Created by huchao on 2017/10/26.
 */

public interface Register {

    interface RegisterView extends BaseView{
        void registerSuccess();
    }

    interface RegisterConteact extends BasePresenter<RegisterView>{
        void register(UserEntity userEntity);
    }

}
