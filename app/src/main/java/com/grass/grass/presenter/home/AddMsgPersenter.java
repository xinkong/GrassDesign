package com.grass.grass.presenter.home;

import com.grass.grass.app.Constants;
import com.grass.grass.base.CommonSubscriber;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.login.AddMsg;
import com.grass.grass.entity.BaseEntity;
import com.grass.grass.entity.SendMsgInfo;
import com.grass.grass.utils.RxUtil;
import com.grass.grass.utils.SharePrefsUtils;
import com.grass.grass.utils.http.HttpUrlManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by huchao on 2017/11/2.
 */

public class AddMsgPersenter extends RxPresenter<AddMsg.AddMsgView> implements AddMsg.AddMsgContract {

    @Inject
    public AddMsgPersenter() {
    }

    @Override
    public void sendMsg(String content, List<String> imagePath) {
        addSubscribe(Flowable
                        .just(imagePath)
                        .flatMap(paths -> {
                            if (paths.size() > 0) {
                                List<MultipartBody.Part> parts = new ArrayList<>();
                                for (int i = 0; i < paths.size(); i++) {
                                    File file = new File(paths.get(i));
                                    if(!file.exists()){
                                        continue;
                                    }
                                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                                    MultipartBody.Part body = MultipartBody.Part.createFormData("file" + i, file.getName(), requestFile);
                                    parts.add(body);
                                }
                                return mHttpUrlManager.uploadFile(HttpUrlManager.BASEIMAGEUPLOADURL, parts);
                            } else {
                                BaseEntity<String> baseEntity = new BaseEntity<String>();
                                baseEntity.returnCode = "0";
                                baseEntity.data = "";
                                return Flowable.just(baseEntity);
                            }
                        })
                        .compose(RxUtil.<String>handleResult())
                        .flatMap(results -> {
//                            SendMsgInfo params = new SendMsgInfo(SharePrefsUtils.getInstance().getString(Constants.UserId,"1001"),content,results);
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("userId", SharePrefsUtils.getInstance().getString(Constants.UserId,"1001"));
                            params.put("msgContent", content);
                            params.put("msgImages", results);
                            return mHttpUrlManager.sendMsg(params);
                        })
                        .compose(RxUtil.<String>handleResult())
                        .compose(RxUtil.rxSchedulerHelper())
                        .subscribeWith(new CommonSubscriber<String>(mView, mContext, "正在上传...") {
                            @Override
                            public void onNext(String s) {
                                super.onNext(s);
                                mView.sendSuccess();
                            }
                        })
        );
    }
}
