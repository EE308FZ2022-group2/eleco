package com.idk.eleco.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idk.eleco.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {


    @Select("select * from comment where commentPostId = commentPostId limit #{pageBegin}, #{pageSize}")
    List<Comment> findData(@Param("pageBegin") Integer pageBegin, @Param("pageSize") Integer pageSize ,@Param("commentPostId") String commentPostId);

    @Select("select count(*) from comment where commentPostId = commentPostId")
    int findSize(String postTitle);


}