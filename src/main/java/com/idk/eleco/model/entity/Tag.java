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
@TableName("tag")
public class Tag {

    //子版块ID
    @TableId(value = "tagId", type = IdType.ASSIGN_ID)
    private String tagId;

    //子版块标题
    @TableField("tagTitle")
    private String tagTitle;

    //子版块默认图片
    @TableField("tagImg")
    private String tagImg;

    //子版块帖子数量
    @TableField("tagPostNum")
    @Builder.Default
    private int tagPostNum =0;

    //子版块热度
    @TableField("tagHot")
    @Builder.Default
    private int tagHot =0;

    //子版块帖子最后提交时间
    @TableField(value = "lastPostTime", fill = FieldFill.UPDATE)
    private Date lastPostTime;

    //子版块简介
    @TableField("tagBrief")
    private String tagBrief;

}
