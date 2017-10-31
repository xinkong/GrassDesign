package com.grass.grass.contract;


import com.grass.grass.base.BasePresenter;
import com.grass.grass.base.BaseView;

import java.io.File;
import java.util.List;

/**
 * Created by huchao on 2017/10/17.
 */

public interface MainContract {

    interface MainView extends BaseView {
        void showContent(String content);
    }

    interface Presenter extends BasePresenter<MainView> {
        void getData(String name, String pwd);

        void uploadImages(List<String> filePath);
    }
}
