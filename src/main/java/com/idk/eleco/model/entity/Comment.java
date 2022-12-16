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
@TableName("comment")
public class Comment {

    //评论ID
    @TableId(value = "commentId", type = IdType.ASSIGN_ID)
    private String commentId;

    //评论对应帖子ID
    @TableField("commentPostId")
    private String commentPostId;

    //评论用户ID
    @TableField("commentUserId")
    private String commentUserId;

    //评论用户名
    @TableField("commentUserName")
    private String commentUserName;

    //评论时间
    @TableField("commentTime")
    private Date commentTime;

    //评论内容
    @TableField("postComment")
    private String postComment;

    //评论楼层
    @TableField("commentFlour")
    private int commentFlour;

    //是否为回复
    @TableField("isQuote")
    private Boolean isQuote;

    //回复楼层
    @TableField("quoteFlour")
    private int quoteFlour;

    //回复楼层用户名
    @TableField("quoteUserName")
    private String quoteUserName;

    //回复楼层ID
    @TableField("quoteUserId")
    private String quoteUserId;

    //回复时间
    @TableField("quoteCommentTime")
    private Date quoteCommentTime;

    //回复内容
    @TableField("quoteComment")
    private String quoteComment;
}
