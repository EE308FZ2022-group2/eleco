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
@TableName("user_collection")
public class UserCollection {

    //用户ID
    @TableField("user_id")
    private String userId;

    //帖子ID
    @TableField("post_id")
    private String postId;

}
