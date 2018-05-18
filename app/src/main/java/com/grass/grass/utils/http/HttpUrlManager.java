package com.grass.grass.utils.http;


import com.grass.grass.entity.BaseEntity;
import com.grass.grass.entity.MsgInfoEntity;
import com.grass.grass.entity.UserEntity;

import java.util.List;
import java.util.Map;
import java.util.jar.Manifest;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by huchao on 2017/10/17.
 * WEBAPI Service
 * 如果你在注解中提供的url是完整的url，则url将作为请求的url。
 * 如果你在注解中提供的url是不完整的url，且不以 / 开头，则请求的url为baseUrl+注解中提供的值
 * 如果你在注解中提供的url是不完整的url，且以 / 开头，则请求的url为baseUrl的主机部分+注解中提供的值
 */

public interface HttpUrlManager {

    //图片上传地址
    String BASEIMAGEUPLOADURL = "http://192.168.9.6:8888/WebImageService/fileUpload/upload";
    //BaseUrl
    String BASEURL = "http:/118.25.38.106:8080/AppClient/";


    /**
     * 文件上传
     *
     * @param url
     * @param parts
     * @return
     */
    @Multipart
    @POST()
    Flowable<BaseEntity<String>> uploadFile(@Url String url, @Part() List<MultipartBody.Part> parts);

    /**
     * 获取登录用户信息
     */
    @GET("userInfo/login")
    Flowable<BaseEntity<UserEntity>> login(@Query("userName") String username, @Query("userPwd") String pwd);

    /**
     * 注册
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @GET("userInfo/register")
    Flowable<BaseEntity<String>> register(@Query("userName") String userName, @Query("userPwd") String userPwd);

    /**
     * 保存消息
     *
     * @param params
     * @return
     */
    @POST("msgInfo/saveMsg")
    Flowable<BaseEntity<String>> sendMsg(@Body Map<String, String> params);

    /**
     * 获取所与消息
     *
     * @param parmsg
     * @return
     */
    @GET("msgInfo/findMsg")
    Flowable<BaseEntity<List<MsgInfoEntity>>> getMsg(@QueryMap Map<String, String> parmsg);

    /**
     * 更新用户信息
     *
     * @param params
     * @return
     */
    @GET("userInfo/updateUserInfo")
    Flowable<BaseEntity<UserEntity>> updateUserInfo(@QueryMap Map<String, String> params);

    /**
     * 短信发送
     *
     * @param params
     * @return
     */
    @POST()
    Flowable<Object> sendCode(@Url String url, @Body Map<String, String> params);
}
