package com.grass.grass.contract.my;

import com.grass.grass.base.BasePresenter;
import com.grass.grass.base.BaseView;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.utils.SharePrefsUtils;

/**
 * Created by huchao on 2017/10/31.
 */

public interface MyInfo {

    interface MyInfoView extends BaseView {
        void updateUserHeadOk();
    }

    interface MyInfoContract extends BasePresenter<MyInfoView> {

        void updatUserHeadPic(String path);
    }

}
