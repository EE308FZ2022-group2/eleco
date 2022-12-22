package com.idk.eleco.controller;


import com.idk.eleco.serivce.TagService;
import com.idk.eleco.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tag")
@Api("子版块功能")
public class TagController {

    @Resource
    TagService tagService;

    @ApiOperation("获取子版块")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<?> getTag(@RequestParam("tagId") String tagId) {
        return tagService.getTag(tagId);
    }

    @ApiOperation("获取子版块标签下的帖子")
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<?> getPost(@RequestParam("tagId") String tagId) {
        return tagService.getPost(tagId);
    }

    @ApiOperation("获取热门子版块")
    @RequestMapping(value = "/hotTag", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<?> getHotTag() {
        return tagService.getHotTag();
    }

}
