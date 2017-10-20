package com.grass.grass.utils.http;


import com.grass.grass.entity.UserEntity;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by huchao on 2017/10/17.
 * WEBAPI Service
 */

public interface HttpUrlManager {

    String BASEURL = "http://192.168.9.156:8080/";

    /**
     * 获取登录用户信息
     */
    @GET("/AppClient/UserInfo/login")
    Flowable<UserEntity> login(@Query("userName") String username,
                               @Query("pwd") String pwd);
}
