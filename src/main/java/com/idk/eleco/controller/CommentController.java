package com.idk.eleco.controller;

import com.idk.eleco.serivce.CommentService;
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
@RequestMapping("/comment")
@Api("评论功能")
public class CommentController {

    @Resource
    CommentService commentService;

    @ApiOperation("添加评论")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<?> comment(@RequestParam("postId") String postId,
                                     @RequestParam("userId") String userId,
                                     @RequestParam("postComment") String postComment,
                                     @RequestParam("commentFlour") int commentFlour,
                                     @RequestParam(value = "quoteFlour", defaultValue = "0") int quoteFlour,
                                     @RequestParam(value = "quoteCommentId", required = false) String quoteCommentId) {
        return commentService.comment(postId, userId, postComment, commentFlour, quoteFlour, quoteCommentId);
    }

}
