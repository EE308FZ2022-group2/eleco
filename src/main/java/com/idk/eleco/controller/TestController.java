package com.idk.eleco.controller;

import com.idk.eleco.entity.User;
import com.idk.eleco.util.IPUtil;
import com.idk.eleco.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@RequestMapping("/test")
@Api("测试功能")
public class TestController {

    @Resource
    Ip2regionSearcher ip2regionSearcher;

    @ApiOperation("获取IP")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult GetIp() throws IOException {
        IPUtil ipUtil=new IPUtil();
        String ip=ipUtil.getNowIP2();
        String res=ip2regionSearcher.getAddress(ip);

        return new ResponseResult(200,"获取成功",res);
    }


}
