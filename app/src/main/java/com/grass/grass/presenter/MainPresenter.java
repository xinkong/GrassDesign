package com.grass.grass.presenter;

import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.MainContract;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.utils.RxUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by huchao on 2017/10/17.
 */
public class MainPresenter extends RxPresenter<MainContract.MainView> implements MainContract.Presenter {

    @Inject
    public MainPresenter(){

    }

    @Override
    public void getData(String name,String pwd) {

        addSubscribe(mHttpUrlManager.login(name,pwd)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.<UserEntity>handleResult())
                .subscribeWith(new CommonSubscriber<UserEntity>(mView,"获取数据中..."){

                    @Override
                    public void onNext(UserEntity userEntity) {
                        super.onNext(userEntity);
                        Logger.i(userEntity.toString());
                        mView.showContent(userEntity.toString());
                    }
                }));
    }

    @Override
    public void uploadImages(File file) {
//        File file = new File(filePath);
        Map<String, RequestBody> files = new HashMap<>();
        files.put(file.getName(),RequestBody.create(MediaType.parse("image/jpg"),file));
        addSubscribe(mHttpUrlManager.uploadFile(files)
                    .compose(RxUtil.rxSchedulerHelper())
                    .compose(RxUtil.<List<String>>handleResult())
                    .subscribeWith(new CommonSubscriber<List<String>>(mView,mContext,"正在提交数据"){
                        @Override
                        public void onNext(List<String> url) {
                            super.onNext(url);
                            for (String s : url){
                                Logger.i(s);
                            }
                            mView.showContent(url.toString());
                        }
                    }));
    }
}
