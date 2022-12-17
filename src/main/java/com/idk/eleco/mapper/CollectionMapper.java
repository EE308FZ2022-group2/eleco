package com.idk.eleco.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idk.eleco.model.entity.User;
import com.idk.eleco.model.entity.UserCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectionMapper extends BaseMapper<UserCollection> {

    @Select("select * from user_collection limit #{collectionBegin}, #{colectionSize}")
    List<UserCollection> findData(@Param("collectionBegin") Integer collectionBegin, @Param("collectionSize") Integer collectionSize);

    @Select("select count(*) from post")
    int findSize();

}
