package com.grass.grass.entity;

/**
 * Created by huchao on 2017/11/6.
 */

public class SendMsgInfo {

    public String userId;
    public String msgContent;
    public String msgImages;

    public SendMsgInfo(String userId, String msgContent, String msgImages) {
        this.userId = userId;
        this.msgContent = msgContent;
        this.msgImages = msgImages;
    }
}
