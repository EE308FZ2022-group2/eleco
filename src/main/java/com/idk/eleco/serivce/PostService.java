package com.idk.eleco.serivce;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idk.eleco.mapper.*;
import com.idk.eleco.model.Vo.*;
import com.idk.eleco.model.entity.*;
import com.idk.eleco.util.ResponseResult;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class PostService {

    @Resource
    UserMapper userMapper;

    @Resource
    PostMapper postMapper;

    @Resource
    TagMapper tagMapper;

    @Resource
    CommentMapper commentMapper;

    @Resource
    CollectionMapper collectionMapper;

    //搜索
    public ResponseResult search(String content, Integer page, Integer size) {

        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("tagTitle", content);
        Tag tag = tagMapper.selectOne(wrapper);

        Map<String, Object> map = new HashMap<>();

        if (ObjectUtils.isEmpty(tag)) {
            map.put("isTag", false);
        } else {
            map.put("isTag", true);
            TagVO tagVO = TagVO.builder()
                    .tagId(tag.getTagId())
                    .tagImg(tag.getTagImg())
                    .tagPostNum(tag.getTagPostNum())
                    .tagHot(tag.getTagHot())
                    .lastPostTime(tag.getLastPostTime())
                    .tagTitle(tag.getTagTitle())
                    .build();
            map.put("tagsObj", tagVO);
        }
        //搜索结果总数
        int totalNum = postMapper.findSize(content);
        //搜索结果总页数
        Integer div = totalNum / size;
        Integer totalPage = totalNum % size == 0 ? div : div + 1;

        map.put("totalNum", totalNum);
        map.put("totalPage", totalPage);
        map.put("nowPage", page);

        Integer pageBegin = (page - 1) * size;
        List<Post> post = postMapper.findData(pageBegin, size, content);

        map.put("showNum", post.size());

        SearchPostVO[] resultArrList = new SearchPostVO[post.size() > size ? size : post.size()];
        for (int i = 0; i < resultArrList.length; i++) {
            SearchPostVO searchPostVO = SearchPostVO.builder()
                    .postId(post.get(i).getPostId())
                    .isEssPost(post.get(i).getIsEssence())
                    .isHotPost(post.get(i).getIsHot())
                    .postBrief(post.get(i).getPostBrief())
                    .postTime(post.get(i).getPostTime())
                    .postTitle(post.get(i).getPostTitle())
                    .authorName(post.get(i).getPostAuthorName())
                    .lastModifyTime(post.get(i).getPostModifyTime())
                    .relatedTagName(post.get(i).getPostTagName())
                    .viewNum(post.get(i).getPostView())
                    .build();
            resultArrList[i] = searchPostVO;
        }


        map.put("resultArrList", resultArrList);

        return new ResponseResult(200, "查询成功", map);
    }

    public ResponseResult recommend() {

        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("isHot", true);
        List<Post> post = postMapper.selectList(wrapper);

        Integer showNum = 4;
        Collections.shuffle(post);
        List<Post> randomSeries = post.subList(0, showNum);

        Map<String, Object> map = new HashMap<>();
        map.put("showNum", showNum);


        RecommendPostVO[] resultArrList = new RecommendPostVO[showNum];
        for (int i = 0; i < showNum; i++) {
            RecommendPostVO recommendPostVO = RecommendPostVO.builder()
                    .postId(randomSeries.get(i).getPostId())
                    .isEssPost(randomSeries.get(i).getIsEssence())
                    .isHotPost(randomSeries.get(i).getIsHot())
                    .postBrief(randomSeries.get(i).getPostBrief())
                    .postTime(randomSeries.get(i).getPostTime())
                    .postTitle(randomSeries.get(i).getPostTitle())
                    .authorName(randomSeries.get(i).getPostAuthorName())
                    .lastModifyTime(randomSeries.get(i).getPostModifyTime())
                    .relatedTagName(randomSeries.get(i).getPostTagName())
                    .viewNum(randomSeries.get(i).getPostView())
                    .postImgUrl(randomSeries.get(i).getImgUrlArr().split(" "))
                    .relatedTagId(randomSeries.get(i).getPostTagId())
                    .build();
            resultArrList[i] = recommendPostVO;
        }

        map.put("resultArrList", randomSeries);

        return new ResponseResult(200, "查询成功", map);
    }

    public ResponseResult relate() {

        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        List<Post> post = postMapper.selectList(wrapper);

        Integer showNum = 7;
        Collections.shuffle(post);
        List<Post> randomSeries = post.subList(0, showNum);

        Map<String, Object> map = new HashMap<>();
        map.put("showNum", showNum);

        RecommendPostVO[] resultArrList = new RecommendPostVO[showNum];
        for (int i = 0; i < showNum; i++) {
            RecommendPostVO recommendPostVO = RecommendPostVO.builder()
                    .postId(randomSeries.get(i).getPostId())
                    .isEssPost(randomSeries.get(i).getIsEssence())
                    .isHotPost(randomSeries.get(i).getIsHot())
                    .postBrief(randomSeries.get(i).getPostBrief())
                    .postTime(randomSeries.get(i).getPostTime())
                    .postTitle(randomSeries.get(i).getPostTitle())
                    .authorName(randomSeries.get(i).getPostAuthorName())
                    .lastModifyTime(randomSeries.get(i).getPostModifyTime())
                    .relatedTagName(randomSeries.get(i).getPostTagName())
                    .viewNum(randomSeries.get(i).getPostView())
                    .postImgUrl(randomSeries.get(i).getImgUrlArr().split(" "))
                    .relatedTagId(randomSeries.get(i).getPostTagId())
                    .build();
            resultArrList[i] = recommendPostVO;
        }

        map.put("resultArrList", randomSeries);

        return new ResponseResult(200, "查询成功", map);
    }

    public ResponseResult create(String userId, String tags, String title, String contentHtml,
                                 String contentMark, String[] imgUrlArr, String postBrief) {

        String imgUrl = "";
        for (int i = 0; i < imgUrlArr.length - 1; i++) {
            imgUrl = imgUrl + imgUrlArr[i] + " ";
        }
        imgUrl = imgUrl + imgUrlArr[imgUrlArr.length - 1];

        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        User user = userMapper.selectOne(qw);

        //没有标题会创建标题
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("tagTitle", tags);
        Tag Tag = tagMapper.selectOne(wrapper);
        String tagId = Tag.getTagId();
        if (ObjectUtils.isEmpty(Tag)) {
            Tag tag = Tag.builder()
                    .tagTitle(title)
                    .build();
            tagMapper.insert(tag);
            tagId = tag.getTagId();
        }

        Post post = Post.builder()
                .postAuthorId(userId)
                .postTagName(tags)
                .postTitle(title)
                .postTagId(tagId)
                .postContentHtml(contentHtml)
                .postContentMark(contentMark)
                .imgUrlArr(imgUrl)
                .postBrief(postBrief)
                .postAuthorName(user.getUserName())
                .build();
        postMapper.insert(post);

        return new ResponseResult(200, "发布帖子成功");
    }


    public ResponseResult info(String PostId) {

        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("postId", PostId);
        Post post = postMapper.selectOne(wrapper);
        post.setPostView(post.getPostView() + 1);
        postMapper.update(post,wrapper);

        User user = userMapper.selectById(post.getPostAuthorId());

        CollectPostVO postObj = CollectPostVO.builder()
                .postId(post.getPostId())
                .postTitle(post.getPostTitle())
                .authorName(post.getPostAuthorName())
                .avatar(user.getAvatar())
                .postTime(post.getPostTime())
                .lastModifyTime(post.getPostModifyTime())
                .viewNum(post.getPostView())
                .isEssPost(post.getIsEssence())
                .isHotPost(post.getIsHot())
                .relatedTagName(post.getPostTagName())
                .postContent(post.getPostContentHtml())
                .build();

        return new ResponseResult(200, "查询成功", postObj);
    }

    public ResponseResult<?> collect(String userId, String postId) {
        QueryWrapper<UserCollection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).eq("post_id", postId);
        UserCollection userCollection = collectionMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(userCollection)) {
            return new ResponseResult<>(500, "收藏已存在");
        } else {
            UserCollection newUserCollection = UserCollection.builder()
                    .userId(userId)
                    .postId(postId)
                    .build();
            collectionMapper.insert(newUserCollection);

            QueryWrapper<Post> wrapper1 = new QueryWrapper<>();
            wrapper.eq("postId", postId);
            Post post = postMapper.selectOne(wrapper1);
            QueryWrapper<User> wrapper2 = new QueryWrapper<>();
            wrapper.eq("user_Id", userId);
            User user = userMapper.selectOne(wrapper2);


            CollectPostVO collectPostVO = CollectPostVO.builder()
                    .postId(post.getPostId())
                    .isEssPost(post.getIsEssence())
                    .isHotPost(post.getIsHot())
                    .postTime(post.getPostTime())
                    .postTitle(post.getPostTitle())
                    .authorName(post.getPostAuthorName())
                    .lastModifyTime(post.getPostModifyTime())
                    .relatedTagName(post.getPostTagName())
                    .viewNum(post.getPostView())
                    .postContent(post.getPostContentHtml())
                    .authorId(user.getUserId())
                    .avatar(user.getAvatar())
                    .build();

            return new ResponseResult<>(200, "收藏成功！", collectPostVO);
        }
    }

    public ResponseResult<?> cancelcollect(String userId, String postId) {
        QueryWrapper<UserCollection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).eq("post_id", postId);
        UserCollection userCollection = collectionMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(userCollection)) {
            collectionMapper.delete(wrapper);
            return new ResponseResult<>(200, "删除成功！");
        } else {
            return new ResponseResult<>(500, "不存在此收藏");
        }
    }

    public ResponseResult comment(String commentPostId, Integer page, Integer size) {

        Map<String, Object> map = new HashMap<>();

        //搜索结果总数
        int totalNum = commentMapper.findSize(commentPostId);
        //搜索结果总页数
        Integer div = totalNum / size;
        Integer totalPage = totalNum % size == 0 ? div : div + 1;

        map.put("totalNum", totalNum);
        map.put("totalPage", totalPage);
        map.put("nowPage", page);

        Integer pageBegin = (page - 1) * size;
        List<Comment> comment = commentMapper.findData(pageBegin, size, commentPostId);

        map.put("showNum", comment.size());

        CommentVO[] resultArrList = new CommentVO[comment.size() > size ? size : comment.size()];
        for (int i = 0; i < resultArrList.length; i++) {

            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", comment.get(i).getCommentUserName());
            User user = userMapper.selectOne(wrapper);

            CommentVO commentVO = CommentVO.builder()
                    .commentFlour(comment.get(i).getCommentFlour())
                    .commentId(comment.get(i).getCommentId())
                    .commentTime(comment.get(i).getCommentTime())
                    .commentUserName(comment.get(i).getCommentUserName())
                    .postComment(comment.get(i).getPostComment())
                    .quoteComment(comment.get(i).getQuoteComment())
                    .quoteCommentTime(comment.get(i).getQuoteCommentTime())
                    .avatar(user.getAvatar())
                    .isQuote(comment.get(i).getIsQuote())
                    .quoteUserName(comment.get(i).getQuoteUserName())
                    .quoteFlour(comment.get(i).getQuoteFlour())
                    .quoteUserId(comment.get(i).getQuoteUserId())
                    .build();
            resultArrList[i] = commentVO;
        }


        map.put("resultArrList", resultArrList);

        return new ResponseResult(200, "查询成功", map);

    }


    public ResponseResult update(String postId, String userId, String tags, String title,
                                 String contentHtml, String contentMark, String[] imgUrlArr, String postBrief) {

        String imgUrl = "";
        for (int i = 0; i < imgUrlArr.length - 1; i++) {
            imgUrl = imgUrl + imgUrlArr[i] + " ";
        }
        imgUrl = imgUrl + imgUrlArr[imgUrlArr.length - 1];

        QueryWrapper<Post> qw = new QueryWrapper<>();
        qw.eq("postId", postId);
        Post post = postMapper.selectOne(qw);

        post.setPostAuthorId(userId);
        post.setPostTagName(tags);
        post.setPostTitle(title);
        post.setPostContentHtml(contentHtml);
        post.setPostContentMark(contentMark);
        post.setImgUrlArr(imgUrl);
        post.setPostBrief(postBrief);

        postMapper.update(post, qw);

        return new ResponseResult(200, "更新帖子成功");
    }

    public ResponseResult delete(String postId) {

        QueryWrapper<Post> qw = new QueryWrapper<>();
        qw.eq("postId", postId);

        postMapper.delete(qw);

        return new ResponseResult(200, "删除帖子成功");
    }

}
