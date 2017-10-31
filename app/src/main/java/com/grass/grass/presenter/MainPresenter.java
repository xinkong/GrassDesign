package com.grass.grass.presenter;

import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.MainContract;
import com.grass.grass.entity.BaseEntity;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.utils.RxUtil;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by huchao on 2017/10/17.
 */
public class MainPresenter extends RxPresenter<MainContract.MainView> implements MainContract.Presenter {

    @Inject
    public MainPresenter() {

    }

    @Override
    public void getData(String name, String pwd) {

        addSubscribe(mHttpUrlManager.login(name, pwd)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.<UserEntity>handleResult())
                .subscribeWith(new CommonSubscriber<UserEntity>(mView, "获取数据中...") {

                    @Override
                    public void onNext(UserEntity userEntity) {
                        super.onNext(userEntity);
                        Logger.i(userEntity.toString());
                        mView.showContent(userEntity.toString());
                    }
                }));
    }

    @Override
    public void uploadImages(List<String> paths) {
        addSubscribe(Flowable.just(paths)
                .flatMap(urls -> {
                    List<MultipartBody.Part> parts = new ArrayList<>();
                    for (int i = 0; i < urls.size(); i++) {
                        File file = new File(urls.get(i));
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("file" + i, file.getName(), requestFile);
                        parts.add(body);
                    }
                    return mHttpUrlManager.uploadFile(parts);
                })
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.<List<String>>handleResult())
                .subscribeWith(new CommonSubscriber<List<String>>(mView, mContext, "正在提交数据") {
                    @Override
                    public void onNext(List<String> url) {
                        super.onNext(url);
                        Logger.i(url.toString());
                        mView.showContent(url.toString());
                    }
                }));
    }


//    要是一次写不出来lambda表达式可先原始写法 在修改下
//    flatMap(new Function<List<String>, Publisher<BaseEntity<List<String>>>>() {
//        @Override
//        public Publisher<BaseEntity<List<String>>> apply(@NonNull List<String> strings) throws Exception {
//            List<MultipartBody.Part> parts = new ArrayList<>();
//            for (int i = 0; i < strings.size(); i++) {
//                File file = new File(strings.get(i));
//                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//                MultipartBody.Part body = MultipartBody.Part.createFormData("file"+i, file.getName(), requestFile);
//                parts.add(body);
//            }
//            return mHttpUrlManager.uploadFile(parts);
//        }
//    })
}
