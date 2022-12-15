package com.idk.eleco.controller;


import com.idk.eleco.model.entity.User;
import com.idk.eleco.serivce.UserService;
import com.idk.eleco.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
@Api("用户功能")
public class UserController {

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
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "7") Integer size){

        return userService.UserFollower(userId,page,size);
    }

    @ApiOperation("查看关注")
    @RequestMapping(value = "/GetFollow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult FindFollow(@RequestParam("userId") String userId,
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "7") Integer size){

        return userService.UserFollow(userId,page,size);
    }


}
