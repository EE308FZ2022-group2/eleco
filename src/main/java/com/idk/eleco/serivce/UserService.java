package com.idk.eleco.serivce;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idk.eleco.entity.User;
import com.idk.eleco.mapper.UserMapper;
import com.idk.eleco.util.ResponseResult;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Repository
public class UserService {

    @Resource
    UserMapper userMapper;

    public ResponseResult modifyUser(User user){
        userMapper.updateById(user);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("userId",user.getUserId());
        User NewUser=userMapper.selectOne(wrapper);
        return new ResponseResult(200,"修改成功",NewUser);
    }
}
