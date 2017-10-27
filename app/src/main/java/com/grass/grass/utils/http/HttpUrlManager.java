package com.grass.grass.utils.http;


import com.grass.grass.entity.BaseEntity;
import com.grass.grass.entity.TokenEntity;
import com.grass.grass.entity.UserEntity;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by huchao on 2017/10/17.
 * WEBAPI Service
 */

public interface HttpUrlManager {

    String BASEURL = "http://192.168.9.6:8080/";

    /**
     * 获取登录用户信息
     */
    @GET("/userInfo/login")
    Flowable<BaseEntity<UserEntity>> login(@Query("UserName") String username, @Query("pwd") String pwd);


    /**
     * 注册
     * @param userName
     * @param userPwd
     * @return
     */
    @POST("/AppClient/UserInfo/login")
    Flowable<BaseEntity<String>> register(@Query("UserName") String userName,@Query("userPwd") String userPwd);

}
