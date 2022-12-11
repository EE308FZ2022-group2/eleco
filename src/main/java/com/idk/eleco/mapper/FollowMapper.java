package com.idk.eleco.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idk.eleco.entity.user_follow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface FollowMapper extends BaseMapper<user_follow> {
}
