package com.idk.eleco.serivce;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idk.eleco.mapper.PostMapper;
import com.idk.eleco.mapper.TagMapper;
import com.idk.eleco.mapper.UserMapper;
import com.idk.eleco.model.Vo.GetTagPostVO;
import com.idk.eleco.model.Vo.PostTagVO;
import com.idk.eleco.model.Vo.TagVO;
import com.idk.eleco.model.entity.Tag;
import com.idk.eleco.model.entity.User;
import com.idk.eleco.util.ResponseResult;
import com.idk.eleco.model.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class TagService {

    private static final int hotTagPageSize = 5;

    @Resource
    TagMapper tagMapper;

    @Resource
    PostMapper postMapper;

    @Resource
    UserMapper userMapper;

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
            List<PostTagVO> tagPostVOS = new ArrayList<>();
            for (Post post : posts) {
                QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
                userQueryWrapper.eq("user_id", post.getPostAuthorId());
                User user = userMapper.selectOne(userQueryWrapper);
                if (ObjectUtils.isEmpty(user)) {
                    return new ResponseResult<>(409, "用户不存在");
                }
                PostTagVO postTagVO = PostTagVO.builder()
                        .postId(post.getPostId())
                        .postTitle(post.getPostTitle())
                        .postTagId(post.getPostTagId())
                        .postTagName(post.getPostTagName())
                        .postBrief(post.getPostBrief())
                        .postContentHtml(post.getPostContentHtml())
                        .postContentMark(post.getPostContentMark())
                        .imgUrlArr(post.getImgUrlArr())
                        .postTime(post.getPostTime())
                        .postCollection(post.getPostCollection())
                        .postModifyTime(post.getPostModifyTime())
                        .isEssence(post.getIsEssence())
                        .isHot(post.getIsHot())
                        .isTop(post.getIsTop())
                        .postAuthorId(post.getPostAuthorId())
                        .postAuthorName(post.getPostAuthorName())
                        .Avatar(user.getAvatar())
                        .build();
                tagPostVOS.add(postTagVO);
            }
            GetTagPostVO getTagPostVO = GetTagPostVO.builder()
                    .showNum(posts.size())
                    .resultArrList(tagPostVOS)
                    .build();
            return new ResponseResult<>(200, "获取成功", getTagPostVO);
        }
    }

    public ResponseResult<?> getHotTag() {
        List<Tag> tags = tagMapper.findData(hotTagPageSize);
        return new ResponseResult<>(200, "获取成功", tags);
    }

}
