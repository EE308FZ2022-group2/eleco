package com.idk.eleco.serivce;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idk.eleco.mapper.PostMapper;
import com.idk.eleco.mapper.TagMapper;
import com.idk.eleco.model.Vo.PostTagVO;
import com.idk.eleco.model.Vo.TagVO;
import com.idk.eleco.model.entity.Tag;
import com.idk.eleco.util.ResponseResult;
import com.idk.eleco.model.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
@Repository
public class TagService {

    private static final int hotTagPageSize = 5;

    @Resource
    TagMapper tagMapper;

    @Resource
    PostMapper postMapper;

    public ResponseResult<?> getTag(String tagId) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("tagId", tagId);
        Tag tag = tagMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(tag)) {
            return new ResponseResult<>(200, "未搜索到对应帖子");
        } else {
            TagVO tagVO = TagVO.builder()
                    .tagId(tag.getTagId())
                    .tagHot(tag.getTagHot())
                    .tagImg(tag.getTagImg())
                    .tagTitle(tag.getTagTitle())
                    .briefContent(tag.getTagBrief())
                    .tagPostNum(tag.getTagPostNum())
                    .lastPostTime(tag.getLastPostTime())
                    .tagTitle(tag.getTagTitle())
                    .build();
            return new ResponseResult<>(200, "查询成功", tagVO);
        }
    }

    public ResponseResult<?> getPost(String tagId) {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("posttagId", tagId);
        List<Post> posts = postMapper.selectList(wrapper);
        if (posts.size() == 0) {
            return new ResponseResult<>(200, "该板块暂无帖子");
        } else {
            PostTagVO postTagVO = PostTagVO.builder()
                    .showNum(posts.size())
                    .resultArrList(posts)
                    .build();
            return new ResponseResult<>(200, "获取成功", postTagVO);
        }
    }

    public ResponseResult<?> getHotTag() {
        List<Tag> tags = tagMapper.findData(hotTagPageSize);
        return new ResponseResult<>(200, "获取成功", tags);
    }

}
