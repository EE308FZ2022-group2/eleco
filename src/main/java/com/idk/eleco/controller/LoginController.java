package com.idk.eleco.controller;


import com.idk.eleco.serivce.LoginService;
import com.idk.eleco.util.HttpUtils;
import com.idk.eleco.util.IPUtil;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
@Api("登陆注册功能")
public class LoginController {

    @Resource
    LoginService loginService;



    @ApiOperation("登录方法")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@RequestParam("useremail") String useremail,
                                @RequestParam("password") String password,
                                HttpServletRequest request,
                                HttpSession session) {
//        IPUtil ipUtil=new IPUtil();
//        String ip= ipUtil.getPublicIp();
//        System.out.println(ip);
        return loginService.Login(useremail,password);

    }

    @ApiOperation("注册方法")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult Register(@RequestParam("useremail") String email,
                                @RequestParam("password") String password,
                                @RequestParam("username") String username,
                                   HttpServletRequest request,
                                HttpSession session) throws IOException {

//        HttpUtils httpUtils=new HttpUtils();
//        String ip = httpUtils.getIp(request);
        return loginService.Register(email,username,password);
    }
}
