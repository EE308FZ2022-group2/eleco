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
public class PostVO {

    String postId;

    String postTitle;

    String authorName;

    String avatar;

    Date postTime;

    Date lastModifyTime;

    int viewNum;

    boolean isEssPost;

    boolean isHotPost;

    String relatedTagName;

    String postBrief;
}
