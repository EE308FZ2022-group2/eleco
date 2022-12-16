package com.idk.eleco.serivce;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idk.eleco.mapper.PostMapper;
import com.idk.eleco.mapper.TagMapper;
import com.idk.eleco.mapper.UserMapper;
import com.idk.eleco.model.Vo.TagVO;
import com.idk.eleco.model.entity.Post;
import com.idk.eleco.model.entity.Tag;
import com.idk.eleco.util.ResponseResult;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class PostService {

    @Resource
    UserMapper userMapper;

    @Resource
    PostMapper postMapper;

    @Resource
    TagMapper tagMapper;

    //搜索
    public ResponseResult search(String content, Integer page, Integer size) {

        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("tagTitle", content);
        Tag tag = tagMapper.selectOne(wrapper);

        Map<String, Object> map = new HashMap<>();

        if (ObjectUtils.isEmpty(tag)) {
            map.put("isTag", false);
        } else {
            map.put("isTag", true);
            TagVO tagVO = TagVO.builder()
                    .tagId(tag.getTagId())
                    .tagImg(tag.getTagImg())
                    .tagPostNum(tag.getTagPostNum())
                    .tagHot(tag.getTagHot())
                    .lastPostTime(tag.getLastPostTime())
                    .tagTitle(tag.getTagTitle())
                    .build();
            map.put("tagsObj", tagVO);
        }
        //搜索结果总数
        int totalNum = postMapper.findSize();
        //搜索结果总页数
        Integer div = totalNum/size;
        Integer totalPage = totalNum % size == 0 ? div : div + 1;

        map.put("totalNum", totalNum);
        map.put("totalPage", totalPage);
        map.put("nowPage", page);
        map.put("showNum", size);

        Integer pageBegin = (page-1) * size;
        List<Post> resultArrList = postMapper.findData(pageBegin,size);
        map.put("resultArrList", resultArrList);

        return new ResponseResult(200, "查询成功", map);
    }

    public ResponseResult recommend() {

        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("isHot", true);
        List<Post> post = postMapper.selectList(wrapper);

        Integer showNum = 4;
        Collections.shuffle(post);
        List<Post> randomSeries = post.subList(0, showNum);

        Map<String, Object> map = new HashMap<>();
        map.put("showNum", showNum);
        map.put("resultArrList", randomSeries);

        return new ResponseResult(200, "查询成功", map);
    }
}
