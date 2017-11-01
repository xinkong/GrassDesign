package com.grass.grass.utils.http;


import com.grass.grass.entity.BaseEntity;
import com.grass.grass.entity.UserEntity;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by huchao on 2017/10/17.
 * WEBAPI Service
 */

public interface HttpUrlManager {

    //图片上传地址
    String BASEIMAGEUPLOADURL = "http://192.168.9.6:8888/WebImageService/fileUpload/upload";
    //BaseUrl
    String BASEURL = "http://192.168.9.6:8080/";

    /**
     * 获取登录用户信息
     */
    @GET("/userInfo/login")
    Flowable<BaseEntity<UserEntity>> login(@Query("userName") String username, @Query("userPwd") String pwd);

    /**
     * 注册
     * @param userName
     * @param userPwd
     * @return
     */
    @GET("/userInfo/register")
    Flowable<BaseEntity<String>> register(@Query("userName") String userName, @Query("userPwd") String userPwd);

    @Multipart
    @POST()
    Flowable<BaseEntity<String>> uploadFile(@Url String url, @Part() List<MultipartBody.Part> parts);

}
