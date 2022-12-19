package com.idk.eleco.model.entity;


import com.baomidou.mybatisplus.annotation.*;
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
    @TableField(value = "postTime", fill = FieldFill.INSERT)
    private Date postTime;

    //帖子收藏量
    @TableField("postCollection")
    @Builder.Default
    private int postCollection = 0;

    //帖子修改时间
    @TableField(value = "postModifyTime", fill = FieldFill.UPDATE)
    private Date postModifyTime;

    //帖子浏览量
    @TableField("postView")
    @Builder.Default
    private int postView = 0;

    //帖子是否加精华
    @TableField("isEssence")
    @Builder.Default
    private Boolean isEssence= false;

    //帖子是否置顶
    @TableField("isTop")
    @Builder.Default
    private Boolean isTop = false;

    //帖子是否热门
    @TableField("isHot")
    @Builder.Default
    private Boolean isHot = false;

    //帖子作者ID
    @TableField("postAuthorId")
    private String postAuthorId;

    //帖子作者名
    @TableField("postAuthorName")
    private String postAuthorName;

}
