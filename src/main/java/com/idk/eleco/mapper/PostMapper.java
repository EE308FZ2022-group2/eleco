package com.idk.eleco.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idk.eleco.model.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostMapper extends BaseMapper<Post> {

        @Select("select * from post limit #{pageBegin}, #{pageSize}")
        List<Post> findData(@Param("pageBegin") Integer pageBegin, @Param("pageSize") Integer pageSize);

        @Select("select count(*) from post")
        int findSize();

}
