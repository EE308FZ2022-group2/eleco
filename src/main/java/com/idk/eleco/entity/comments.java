package com.idk.eleco.entity;


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
@TableName("comments")
public class comments {

    //评论ID
    private String commentId;

    //评论对应帖子ID
    private String commentPostId;

    //评论用户ID
    private String commentUserId;

    //评论用户名
    private String commentUserName;

    //评论时间
    private Date commentTime;

    //评论内容
    private String postComment;

    //评论楼层
    private int commentFlour;

    //是否为回复
    private Boolean isQuote;

    //回复楼层
    private int quoteFlour;

    //回复楼层用户名
    private String quoteUserName;

    //回复楼层ID
    private String quoteUserId;

    //回复时间
    private Date quoteCommentTime;

    //回复内容
    private String quoteComment;
}
