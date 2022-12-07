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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("user")
public class User {

    //用户ID,雪花算法，自增ID
    @TableId(value = "userid", type = IdType.ASSIGN_ID)
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
    @TableField("iplocation")
    private String IpLocation;

    //个人简介
    @Builder.Default
    @TableField("userbrief")
    private String UserBrief="这个人好懒，什么都没有简介";

    @TableField("usergender")
    private String UserGender;

    //邮箱
    @TableField("useremail")
    private String UserEmail;

    //github地址
    @TableField("usergitaddress")
    private String UserGitAddress;

    //创建时间
    @TableField("userjointime")
    private String UserJoinTime;

}
