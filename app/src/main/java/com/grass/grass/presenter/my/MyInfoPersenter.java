package com.grass.grass.presenter.my;

import com.grass.grass.app.Constants;
import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.my.MyInfo;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.utils.RxUtil;
import com.grass.grass.utils.SharePrefsUtils;
import com.grass.grass.utils.http.HttpUrlManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by huchao on 2017/10/31.
 */

public class MyInfoPersenter extends RxPresenter<MyInfo.MyInfoView> implements MyInfo.MyInfoContract {
    @Inject
    public MyInfoPersenter() {
    }

    @Override
    public void updatUserHeadPic(String path) {

        List<MultipartBody.Part> parts = new ArrayList<>();
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        parts.add(body);

        addSubscribe(mHttpUrlManager.uploadFile(HttpUrlManager.BASEIMAGEUPLOADURL,parts )
                    .compose(RxUtil.<String>handleResult())
                    .flatMap(headUrl->{
                        Map<String, String> params = new HashMap<>();
                        params.put("userId", SharePrefsUtils.getInstance().getString(Constants.UserId, ""));
                        params.put("userHeadPic", headUrl);
                        return mHttpUrlManager.updateUserInfo(params);
                    })
                    .compose(RxUtil.<UserEntity>handleResult())
                    .compose(RxUtil.rxSchedulerHelper())
                    .subscribeWith(new CommonSubscriber<UserEntity>(mView,mContext,"请稍等..."){
                        @Override
                        public void onNext(UserEntity userEntity) {
                            super.onNext(userEntity);
                            SharePrefsUtils.getInstance().putString(Constants.UserHeadUrl,userEntity.userHeadPic);
                            mView.updateUserHeadOk();
                        }
                    }));
    }
}
