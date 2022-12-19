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
@Api("搜索功能")
public class SearchController {
    @Resource
    PostService postService;

    @ApiOperation("通过关键字找帖子")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult search(@RequestParam("content") String content,
                                 @RequestParam(value = "nowPage", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "7") Integer size){

        return postService.search(content,page,size);
    }
}
