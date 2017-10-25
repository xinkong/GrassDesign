package com.grass.grass.entity;

import java.util.List;

/**
 * Created by huchao on 2017/10/24.
 */

public class TokenEntity {

    public long id;
    public String token;
    public long userId;
    public String operationTime;

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", userId=" + userId +
                ", operationTime='" + operationTime + '\'' +
                '}';
    }
}
