package com.idk.eleco.serivce;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idk.eleco.model.entity.User;
import com.idk.eleco.mapper.UserMapper;
import com.idk.eleco.util.IPUtil;
import com.idk.eleco.util.ResponseResult;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

@Service
@Repository
public class LoginService {

    @Resource
    UserMapper userMapper;

    @Resource
    Ip2regionSearcher ip2regionSearcher;

    public ResponseResult Login(String UserEmail ,String password){

    QueryWrapper<User> wrapper=new QueryWrapper<>();
    wrapper.eq("useremail",UserEmail);
    User user=userMapper.selectOne(wrapper);
    if (!ObjectUtils.isEmpty(user)){
        if (user.getPassword().equals(password)){
            return new ResponseResult(200,"登陆成功",user);
        }
        else return new ResponseResult(200,"邮箱或密码错误");
    }
    else return new ResponseResult(200,"邮箱不存在");
    }

    public ResponseResult Register(String useremail ,String username,String password) throws IOException {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("useremail",useremail);
        User user=userMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(user)){
            return new ResponseResult(409,"用户已存在");
        }

        IPUtil ipUtil=new IPUtil();
        String ip=ipUtil.getNowIP2();
        String res=ip2regionSearcher.getAddress(ip);

        User addUser =User.builder()
                .UserName(username)
                .UserEmail(useremail)
                .Password(password)
                .IpLocation(res)
                .UserJoinTime(new Date())
                .build();
        userMapper.insert(addUser);
        return new ResponseResult(200,"注册成功",addUser);
    }



}
