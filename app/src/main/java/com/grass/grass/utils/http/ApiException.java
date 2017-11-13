package com.grass.grass.utils.http;

public class ApiException extends Exception{

    private String code;

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, String code) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}