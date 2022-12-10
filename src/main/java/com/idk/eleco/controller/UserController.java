package com.idk.eleco.controller;


import com.idk.eleco.entity.User;
import com.idk.eleco.serivce.UserService;
import com.idk.eleco.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
