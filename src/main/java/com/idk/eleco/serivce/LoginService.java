package com.idk.eleco.serivce;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idk.eleco.entity.User;
import com.idk.eleco.mapper.UserMapper;
import com.idk.eleco.util.ResponseResult;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Repository
public class LoginService {

    @Resource
    UserMapper userMapper;

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

    public ResponseResult Register(String useremail ,String username,String password){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("useremail",useremail);
        User user=userMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(user)){
            return new ResponseResult(409,"用户已存在");
        }
        User addUser =User.builder()
                .UserName(username)
                .UserEmail(useremail)
                .Password(password)
                .UserJoinTime(new Date())
                .build();
        userMapper.insert(addUser);
        return new ResponseResult(200,"注册成功",addUser);
    }



}
