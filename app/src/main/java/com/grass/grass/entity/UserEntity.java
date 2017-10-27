package com.grass.grass.entity;

/**
 * Created by huchao on 2017/10/17.
 */

public class UserEntity{

    public long userId;
    public String userName;
    public String userPwd;
    public String userHeadPic;

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", UserName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userHeadPic='" + userHeadPic + '\'' +
                '}';
    }
}
