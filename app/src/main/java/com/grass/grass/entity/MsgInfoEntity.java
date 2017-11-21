package com.grass.grass.entity;


import java.util.List;

public class MsgInfoEntity {

  public int id;
  public String msgContent;
  public String msgImages;
  public String createTime;
  public UserEntity userInfo;
  public List<Comments> comments;
  public List<ImagesInfo> imagesInfo;

  public class Comments {
    public int id;
    public int msgId;
    public UserEntity commentUser;
    public UserEntity replyUser;
    public String content;
    public String createTime;
  }

  public  class ImagesInfo {
    public String imageUrl;
    public String imagemThumbnailUrl;
  }


}
