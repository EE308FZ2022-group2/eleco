package com.idk.eleco.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "postId", type = IdType.ASSIGN_ID)
    private String postId;

    //帖子标题
    @TableField("postTitle")
    private String postTitle;

    //帖子所属子版块ID
    @TableField("postTagId")
    private String postTagId;

    //帖子所属子版块名
    @TableField("postTagName")
    private String postTagName;

    //帖子简介
    @TableField("postBrief")
    private String postBrief;

    //帖子内容
    @TableField("postContentHtml")
    private String postContentHtml;

    //帖子内容
    @TableField("postContentMark")
    private String postContentMark;

    //帖子图像
    @TableField("imgUrlArr")
    private String imgUrlArr;

    //帖子创建时间
    @TableField("postTime")
    private Date postTime;

    //帖子收藏量
    @TableField("postCollection")
    private int postCollection;

    //帖子修改时间
    @TableField("postModifyTime")
    private Date postModifyTime;

    //帖子浏览量
    @TableField("postView")
    private int postView;

    //帖子是否加精华
    @TableField("isEssence")
    private Boolean isEssence;

    //帖子是否置顶
    @TableField("isTop")
    private Boolean isTop;

    //帖子是否热门
    @TableField("isHot")
    private Boolean isHot;

    //帖子作者ID
    @TableField("postAuthorId")
    private String postAuthorId;

    //帖子作者名
    @TableField("postAuthorName")
    private String postAuthorName;

}
