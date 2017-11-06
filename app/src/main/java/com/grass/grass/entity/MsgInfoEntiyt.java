package com.grass.grass.entity;


import java.util.Date;
import java.util.List;

public class MsgInfoEntiyt {

  private long id;
  private String msgContent;
  private String msgImages;
  private String createTime;
  private UserEntity userInfo;
  private List<CommentEntity> comments;

}
