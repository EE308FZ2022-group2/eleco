package com.idk.eleco.serivce;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idk.eleco.mapper.CollectionMapper;
import com.idk.eleco.mapper.PostMapper;
import com.idk.eleco.mapper.TagMapper;
import com.idk.eleco.mapper.UserMapper;
import com.idk.eleco.model.Vo.RecommendPostVO;
import com.idk.eleco.model.Vo.SearchPostVO;
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

    @Resource
    CollectionMapper collectionMapper;

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
        Integer div = totalNum / size;
        Integer totalPage = totalNum % size == 0 ? div : div + 1;

        map.put("totalNum", totalNum);
        map.put("totalPage", totalPage);
        map.put("nowPage", page);

        Integer pageBegin = (page - 1) * size;
        List<Post> post = postMapper.findData(pageBegin, size);

        map.put("showNum", post.size());

        SearchPostVO[] resultArrList = new SearchPostVO[post.size() > size ? size : post.size()];
        for (int i = 0; i < resultArrList.length; i++) {
            SearchPostVO searchPostVO = SearchPostVO.builder()
                    .postId(post.get(i).getPostId())
                    .isEssPost(post.get(i).getIsEssence())
                    .isHotPost(post.get(i).getIsHot())
                    .postBrief(post.get(i).getPostBrief())
                    .postTime(post.get(i).getPostTime())
                    .postTitle(post.get(i).getPostTitle())
                    .authorName(post.get(i).getPostAuthorName())
                    .lastModifyTime(post.get(i).getPostModifyTime())
                    .relatedTagName(post.get(i).getPostTagName())
                    .viewNum(post.get(i).getPostView())
                    .build();
            resultArrList[i] = searchPostVO;
        }


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


        RecommendPostVO[] resultArrList = new RecommendPostVO[showNum];
        for (int i = 0; i < showNum; i++) {
            RecommendPostVO recommendPostVO = RecommendPostVO.builder()
                    .postId(randomSeries.get(i).getPostId())
                    .isEssPost(randomSeries.get(i).getIsEssence())
                    .isHotPost(randomSeries.get(i).getIsHot())
                    .postBrief(randomSeries.get(i).getPostBrief())
                    .postTime(randomSeries.get(i).getPostTime())
                    .postTitle(randomSeries.get(i).getPostTitle())
                    .authorName(randomSeries.get(i).getPostAuthorName())
                    .lastModifyTime(randomSeries.get(i).getPostModifyTime())
                    .relatedTagName(randomSeries.get(i).getPostTagName())
                    .viewNum(randomSeries.get(i).getPostView())
                    .postImgUrl(randomSeries.get(i).getImgUrlArr().split(" "))
                    .relatedTagId(randomSeries.get(i).getPostTagId())
                    .build();
            resultArrList[i] = recommendPostVO;
        }

        map.put("resultArrList", randomSeries);

        return new ResponseResult(200, "查询成功", map);
    }
}
