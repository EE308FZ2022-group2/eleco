package com.idk.eleco.controller;

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
    @Resource
    PostService postService;
    @ApiOperation("首页获取推荐帖子")
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult recommend(){
        return postService.recommend();
    }
}
