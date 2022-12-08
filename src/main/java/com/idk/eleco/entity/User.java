package com.idk.eleco.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@TableName("users")
public class User {

    //用户ID,雪花算法，自增ID
    @TableId(value = "userId", type = IdType.ASSIGN_ID)
    private String UserId;

    //用户名
    @TableField("username")
    private String UserName;

    //密码
    @JsonIgnore()
    @TableField("password")
    private String Password;

    //头像
    @Builder.Default
    @TableField("avatar")
    private  String avatar= "https://s3.ax1x.com/2020/12/01/DfHNo4.jpg";


    @Builder.Default
    @TableField("following")
    private int following = 0;

    @Builder.Default
    @TableField("follower")
    private int follower = 0;

    //ip地址
    @TableField("ipLocation")
    private String IpLocation;

    //个人简介
    @Builder.Default
    @TableField("userBrief")
    private String UserBrief="这个人好懒，什么简介都没写";

    //性别
    @Builder.Default
    @TableField("userGender")
    private String UserGender="未知";

    //邮箱
    @TableField("userEmail")
    private String UserEmail;

    //github地址
    @TableField("userGitAddress")
    private String UserGitAddress;

    //创建时间
    @TableField("userJoinTime")
    private Date UserJoinTime;

    //浏览量
    @Builder.Default
    @TableField("viewNum")
    private int ViewNum=0;

}
