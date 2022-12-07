package com.idk.eleco.controller;


import com.idk.eleco.serivce.LoginService;
import com.idk.eleco.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@Api("用户功能")
public class LoginController {

    @Resource
    LoginService loginService;

    @ApiOperation("登录方法")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@RequestParam("useremail") String useremail,
                                @RequestParam("password") String password,
                                HttpServletResponse response,
                                HttpSession session) {

        return loginService.Login(useremail,password);

    }

    @ApiOperation("注册方法")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult Register(@RequestParam("useremail") String email,
                                @RequestParam("password") String password,
                                @RequestParam("username") String username,
                                HttpServletResponse response,
                                HttpSession session) {

        return loginService.Register(email,username,password);
    }
}
