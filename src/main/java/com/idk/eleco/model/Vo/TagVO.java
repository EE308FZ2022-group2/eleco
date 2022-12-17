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
public class TagVO {
    //子版块ID
    private String tagId;

    //子版块标题
    private String tagTitle;

    //子版块默认图片
    private String tagImg;

    //子版块简介
    private String briefContent;

    //子版块帖子数量
    private int tagPostNum;

    //子版块热度
    private int tagHot;

    //子版块帖子最后提交时间
    private Date lastPostTime;
}
