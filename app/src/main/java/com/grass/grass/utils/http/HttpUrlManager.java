package com.grass.grass.utils.http;


import com.grass.grass.entity.BaseEntity;
import com.grass.grass.entity.TokenEntity;
import com.grass.grass.entity.UserEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by huchao on 2017/10/17.
 * WEBAPI Service
 */

public interface HttpUrlManager {

    String BASEIMAGEURL = "http://192.168.9.6:8080/";

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
    Flowable<BaseEntity<String>> register(@Query("userName") String userName,@Query("userPwd") String userPwd);

    @Multipart
    @POST("/uploadImage/uploadImages/upload")
    Flowable<BaseEntity<List<String>>> uploadFile(@Part() List<MultipartBody.Part> parts);

}
