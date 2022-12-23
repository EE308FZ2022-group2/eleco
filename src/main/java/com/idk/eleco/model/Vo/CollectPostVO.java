package com.idk.eleco.model.Vo;

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
public class CollectPostVO {

    //帖子ID
    private String postId;

    //帖子标题
    private String postTitle;

    //帖子作者名
    private String authorName;

    //帖子作者名
    private String authorId;

    //作者头像
    private String avatar;

    //帖子创建时间
    private Date postTime;

    //帖子修改时间
    private Date lastModifyTime;

    //帖子浏览量
    private int viewNum;

    //帖子是否加精华
    private Boolean isEssPost;

    //帖子是否热门
    private Boolean isHotPost;

    //帖子所属子版块名
    private String relatedTagName;

    //帖子简介
    private String postContent;

}