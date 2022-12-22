package com.idk.eleco.controller;

import com.idk.eleco.serivce.CommentService;
import com.idk.eleco.serivce.PostService;
import com.idk.eleco.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/post")
@Api("帖子功能")
public class PostController {
    static Integer PageSize = 7;
    @Resource
    PostService postService;

    @Resource
    CommentService commentService;

    @ApiOperation("首页获取推荐帖子")
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult recommend() {
        return postService.recommend();
    }

    @ApiOperation("其他地方获取推荐帖子")
    @RequestMapping(value = "/relate", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult relate() {
        return postService.relate();
    }

    @ApiOperation("发布帖子")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestParam("userId") String userId,
                                 @RequestParam("tags") String tags,
                                 @RequestParam("title") String title,
                                 @RequestParam("contentHtml") String contentHtml,
                                 @RequestParam("contentMark") String contentMark,
                                 @RequestParam("imgUrlArr") String[] imgUrlArr,
                                 @RequestParam("postBrief") String postBrief) {
        return postService.create(userId, tags, title, contentHtml, contentMark, imgUrlArr, postBrief);
    }

    @ApiOperation("通过id获得帖子")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult info(@RequestParam("postId") String postId) {
        return postService.info(postId);
    }

    @ApiOperation("通过id收藏帖子")
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<?> collect(@RequestParam("userId") String userId,
                                     @RequestParam("postId") String postId) {
        return postService.collect(userId, postId);
    }

    @ApiOperation("通过id取消收藏帖子")
    @RequestMapping(value = "/cancelcollect", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult<?> cancelcollect(@RequestParam("userId") String userId,
                                           @RequestParam("postId") String postId) {
        return postService.cancelcollect(userId, postId);
    }

    @ApiOperation("添加评论")
    @RequestMapping(value = "/addcomment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<?> comment(@RequestParam("postId") String postId,
                                     @RequestParam("userId") String userId,
                                     @RequestParam("postComment") String postComment,
                                     @RequestParam("commentFlour") int commentFlour,
                                     @RequestParam(value = "quoteFlour", defaultValue = "0") int quoteFlour,
                                     @RequestParam(value = "quoteCommentId", required = false) String quoteCommentId) {
        return commentService.comment(postId, userId, postComment, commentFlour, quoteFlour, quoteCommentId);
    }

    @ApiOperation("通过id获得帖子评论")
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult comment(@RequestParam("postId") String postId,
                                  @RequestParam("nowPage") Integer nowPage) {
        return postService.comment(postId, nowPage, PageSize);
    }

    @ApiOperation("更新帖子")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult update(@RequestParam("postId") String postId,
                                 @RequestParam("userId") String userId,
                                 @RequestParam("tags") String tags,
                                 @RequestParam("title") String title,
                                 @RequestParam("contentHtml") String contentHtml,
                                 @RequestParam("contentMark") String contentMark,
                                 @RequestParam("imgUrlArr") String[] imgUrlArr,
                                 @RequestParam("postBrief") String postBrief) {
        return postService.update(postId, userId, tags, title, contentHtml, contentMark, imgUrlArr, postBrief);
    }

    @ApiOperation("删除帖子")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult delete(@RequestParam("postId") String postId) {
        return postService.delete(postId);
    }

}
