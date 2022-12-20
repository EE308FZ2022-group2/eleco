package com.idk.eleco.serivce;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idk.eleco.mapper.CommentMapper;
import com.idk.eleco.mapper.UserMapper;
import com.idk.eleco.model.entity.Comment;
import com.idk.eleco.model.entity.User;
import com.idk.eleco.util.ResponseResult;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Repository
public class CommentService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    UserMapper userMapper;

    public ResponseResult<?> comment(String postId, String userId, String postComment,
                                     int commentFlour, int quoteFlour, String quoteCommentId) {
        if (ObjectUtils.isEmpty(quoteCommentId)) {
            // 无父评论
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("user_id", userId);
            String username = userMapper.selectOne(userQueryWrapper).getUserName();
            Comment comment = Comment.builder()
                    .commentPostId(postId)
                    .commentUserId(userId)
                    .commentUserName(username)
                    .postComment(postComment)
                    .commentFlour(commentFlour)
                    .isQuote(false)
                    .commentTime(new Date())
                    .build();
            commentMapper.insert(comment);
            return new ResponseResult<>(200, "发表成功");
        } else {
            // 有父评论
            QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
            commentQueryWrapper.eq("commentId", quoteCommentId);
            Comment quoteComment = commentMapper.selectOne(commentQueryWrapper);
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("user_id", userId);
            String username = userMapper.selectOne(userQueryWrapper).getUserName();
            Comment comment = Comment.builder()
                    .commentPostId(postId)
                    .commentUserId(userId)
                    .commentUserName(username)
                    .postComment(postComment)
                    .isQuote(true)
                    .quoteFlour(quoteFlour)
                    .quoteUserName(quoteComment.getCommentUserName())
                    .quoteUserId(quoteComment.getCommentUserId())
                    .quoteComment(quoteComment.getPostComment())
                    .quoteCommentTime(quoteComment.getCommentTime())
                    .commentTime(new Date())
                    .build();
            commentMapper.insert(comment);
            return new ResponseResult<>(200, "发表成功");
        }
    }
}
