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
    private int tagPostNum;

    //子版块热度
    @TableField("tagHot")
    private int tagHot;

    //子版块帖子最后提交时间
    @TableField("tagHot")
    private Date lastPostTime;

    //子版块简介
    @TableField("tagBrief")
    private String tagBrief;

}
