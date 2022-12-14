package com.idk.eleco.controller;


import com.alibaba.fastjson.JSONObject;
import com.idk.eleco.model.entity.User;
import com.idk.eleco.serivce.FileService;
import com.idk.eleco.serivce.UserService;
import com.idk.eleco.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@Api("用户功能")
public class UserController {

    static Integer PageSize=7;
    @Resource
    UserService userService;

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult Modify(@RequestBody User user){
        return userService.modifyUser(user);
    }

    @ApiOperation("查找用户信息")
    @RequestMapping(value = "/userinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult FindUser(@RequestParam("userId") String userId){
        return userService.FindUserByUd(userId);
    }

    @ApiOperation("添加关注")
    @RequestMapping(value = "/addFollow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult AddFollow(@RequestParam("userId") String userId,
                                    @RequestParam("parentId") String parentId ){
        return userService.addFollow(userId,parentId);
    }

    @ApiOperation("取消关注")
    @RequestMapping(value = "/delFollow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult DelFollow(@RequestParam("userId") String userId,
                                    @RequestParam("parentId") String parentId ){
        return userService.DelFollow(userId,parentId);
    }

    @ApiOperation("查看粉丝")
    @RequestMapping(value = "/GetFollower", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult FindFollower(@RequestParam("userId") String userId,
                                     @RequestParam(value = "needPage", defaultValue = "1") Integer page){

        return userService.UserFollower(userId,page,PageSize);
    }

    @ApiOperation("查看关注")
    @RequestMapping(value = "/GetFollow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult FindFollow(@RequestParam("userId") String userId,
                                     @RequestParam(value = "needPage", defaultValue = "1") Integer page){

        return userService.UserFollow(userId,page,PageSize);
    }
    @ApiOperation("查询用户所有帖子")
    @RequestMapping(value = "/GetAllPost", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult GetAllPost(@RequestParam("userId") String userId,
                                     @RequestParam(value = "needPage", defaultValue = "1") Integer page){

        return userService.getPost(userId,page,PageSize);
    }

    @ApiOperation("上传图片")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<?> UploadImg(@RequestParam("file") MultipartFile file)  {
//        String uid = "69725009da0618599d1292a14cc61198";
//        String token = "e0356b167e3a0088501158ecad2acd1c";
        String url="https://tuchuangs.com/api/v2/upload";
        String result= FileService.sendHttpDataFile(url,file);
        JSONObject object = JSONObject.parseObject(result);
        return new ResponseResult<>(200,"上传成功",object);
    }

    @ApiOperation("获取收藏的帖子")
    @RequestMapping(value = "/collection", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<?> getCollection(@RequestParam("userId") String userId,
                                        @RequestParam(value = "needPage", defaultValue = "1") int nowPage) {
        return userService.getCollection(userId, nowPage, PageSize);
    }







}
