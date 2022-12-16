package com.idk.eleco.model.entity;


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
public class UserFollow {

    //用户ID
    @TableField("user_id")
    private String userId;

    //粉丝ID
    @TableField("follower_id")
    private String followerId;

}
