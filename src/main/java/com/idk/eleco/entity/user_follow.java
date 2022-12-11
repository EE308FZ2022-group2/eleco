package com.idk.eleco.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user_follow")
public class user_follow {

    //用户ID
    @TableField("userId")
    private String userId;

    //粉丝ID
    @TableField("followerId")
    private String followerId;

}
