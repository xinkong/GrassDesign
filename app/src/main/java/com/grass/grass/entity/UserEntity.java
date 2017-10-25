package com.grass.grass.entity;

/**
 * Created by huchao on 2017/10/17.
 */

public class UserEntity{

    public int id;
    public String userManagerName;
    public String userManagerPhoneNo;
    public String userManagerPwd;
    public String userRegisterDate;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userManagerName='" + userManagerName + '\'' +
                ", userManagerPhoneNo='" + userManagerPhoneNo + '\'' +
                ", userManagerPwd='" + userManagerPwd + '\'' +
                ", userRegisterDate='" + userRegisterDate + '\'' +
                '}';
    }
}
