package com.idk.eleco.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("post")
public class Post {

    //帖子ID
    private String postId;

    //帖子标题
    private String postTitle;

    //帖子所属子版块ID
    private String postTagId;

    //帖子所属子版块名
    private String postTagName;

    //帖子简介
    private String postBrief;

    //帖子内容
    private String postContentHtml;

    //帖子内容
    private String postContentMark;

    //帖子图像
    private String imgUrlArr;

    //帖子创建时间
    private Date postTime;

    //帖子收藏量
    private int postCollection;

    //帖子修改时间
    private Date postModifyTime;

    //帖子浏览量
    private int postView;

    //帖子是否加精华
    private Boolean isEssence;

    //帖子是否置顶
    private Boolean isTop;

    //帖子是否热门
    private Boolean isHot;

    //帖子作者ID
    private String postAuthorId;

    //帖子作者名
    private String postAuthorName;

}
