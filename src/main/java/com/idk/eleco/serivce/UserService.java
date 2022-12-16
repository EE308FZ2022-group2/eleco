package com.idk.eleco.serivce;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.idk.eleco.mapper.CollectionMapper;
import com.idk.eleco.mapper.PostMapper;
import com.idk.eleco.model.Vo.PostVO;
import com.idk.eleco.model.entity.Post;
import com.idk.eleco.model.entity.User;
import com.idk.eleco.model.Vo.FollowVO;
import com.idk.eleco.model.entity.UserCollection;
import com.idk.eleco.model.entity.UserFollow;
import com.idk.eleco.mapper.FollowMapper;
import com.idk.eleco.mapper.UserMapper;
import com.idk.eleco.util.ResponseResult;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    FollowMapper followMapper;

    @Resource
    PostMapper postMapper;

    @Resource
    CollectionMapper collectionMapper;

    public ResponseResult modifyUser(User user){
        userMapper.updateById(user);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",user.getUserId());
        User NewUser=userMapper.selectOne(wrapper);
        return new ResponseResult(200,"修改成功",NewUser);
    }

    public ResponseResult FindUserByUd(String userId){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        User user=userMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(user)){
            return new ResponseResult(200,"查无此人");
        }
        return new ResponseResult(200,"查询成功",user);
    }

    public ResponseResult addFollow(String userId,String parentId){

        QueryWrapper<UserFollow> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",parentId).eq("follower_id",userId);
        UserFollow userGet=followMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(userGet)){
            return new ResponseResult(409,"已关注该用户");
        }
        if (parentId.equals(userId)){
            return new ResponseResult(200,"你不可以关注自己");
        }
        UserFollow user= UserFollow.builder()
                .userId(parentId)
                .followerId(userId)
                .build();
        followMapper.insert(user);

        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq("user_id",userId);
        User user1=userMapper.selectOne(qw);
        user1.setFollowing(user1.getFollowing()+1);
        userMapper.updateById(user1);

        User user2=userMapper.selectById(parentId);
        user2.setFollower(user2.getFollower()+1);
        userMapper.updateById(user2);
        return new ResponseResult(200,"关注成功");
    }

    public ResponseResult DelFollow(String userId,String parentId){
        QueryWrapper<UserFollow> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",parentId).eq("follower_id",userId);
        UserFollow userGet=followMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(userGet)){
            return new ResponseResult(409,"未关注");
        }

        QueryWrapper<UserFollow> qwrapper=new QueryWrapper<>();
        qwrapper.eq("user_id",parentId).eq("follower_id",userId);
        followMapper.delete(qwrapper);

        User user=userMapper.selectById(userId);
        user.setFollowing(user.getFollowing()-1 < 0 ? 0 : user.getFollowing()-1);
        userMapper.updateById(user);

        User user1=userMapper.selectById(parentId);
        user1.setFollower(user1.getFollower()-1 <0?0:user1.getFollower()-1);
        userMapper.updateById(user1);
        return new ResponseResult(200,"删除成功");
    }

    //查找粉丝
    public ResponseResult UserFollower(String userId,Integer page,Integer size){
        QueryWrapper<UserFollow> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<UserFollow> userGet=followMapper.selectList(wrapper);
        if (ObjectUtils.isEmpty(userGet)){
            return new ResponseResult(409,"你没有粉丝");
        }

        Page<UserFollow> FollowPage=new Page<>(page,size);
        QueryWrapper<UserFollow> GetWrapper=new QueryWrapper<>();
        GetWrapper.eq("user_id",userId);

        Page<UserFollow> followPage=followMapper.selectPage(FollowPage,GetWrapper);
        Map<String, Object> map = new HashMap<>(16);

        List<UserFollow> list=followPage.getRecords();
        FollowVO followVO[] =new FollowVO[list.size()];
        for (int i = 0; i < list.size(); i++) {
            User u=userMapper.selectById(list.get(i).getFollowerId());
            FollowVO followVO1=FollowVO.builder()
                    .followAvatar(u.getAvatar())
                    .followBrief(u.getUserBrief())
                    .followName(u.getUserName())
                    .build();
            followVO[i]=followVO1;
        }

        map.put("followerObjArr",followVO);
        map.put("nowPage",followPage.getCurrent());
        map.put("showNum",followPage.getSize());
        map.put("totalNum",followPage.getTotal());
        map.put("totalPage",followPage.getPages());
        return new ResponseResult(200,"查询成功",map);
    }

    //查找关注
    public ResponseResult UserFollow(String userId,Integer page,Integer size){
        QueryWrapper<UserFollow> wrapper=new QueryWrapper<>();
        wrapper.eq("follower_id",userId);
        List<UserFollow> userGet=followMapper.selectList(wrapper);
        if (ObjectUtils.isEmpty(userGet)){
            return new ResponseResult(409,"你没有关注");
        }

        Page<UserFollow> FollowPage=new Page<>(page,size);
        QueryWrapper<UserFollow> GetWrapper=new QueryWrapper<>();
        GetWrapper.eq("follower_id",userId);
        Page<UserFollow> followPage=followMapper.selectPage(FollowPage,GetWrapper);
        Map<String, Object> map = new HashMap<>(16);

        List<UserFollow> list =followPage.getRecords();
        FollowVO userArr[] =new FollowVO[list.size()];
        for (int i = 0; i < list.size(); i++) {
            User u=userMapper.selectById(list.get(i).getUserId());
            FollowVO followVo= FollowVO.builder()
                    .followName(u.getUserName())
                    .followBrief(u.getUserBrief())
                    .followAvatar(u.getAvatar())
                    .build();
            userArr[i]=followVo;
            //map.put(u.getUserId(),followVo);
        }
        map.put("followingObjArr",userArr);
        map.put("nowPage",followPage.getCurrent());
        map.put("showNum",followPage.getSize());
        map.put("totalNum",followPage.getTotal());
        map.put("totalPage",followPage.getPages());

        return new ResponseResult(200,"查询成功",map);
    }

    public ResponseResult<?> collect(String userId, String postId) {
        QueryWrapper<UserCollection> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId).eq("postId", postId);
        UserCollection userCollection = collectionMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(userCollection)) {
            collectionMapper.delete(wrapper);
            return new ResponseResult<>(200, "删除成功！");
        } else {
            UserCollection newUserCollection = UserCollection.builder()
                    .userId(userId)
                    .postId(postId)
                    .build();
            collectionMapper.insert(newUserCollection);
            return new ResponseResult<>(200, "收藏成功！");
        }
    }

    public ResponseResult<?> getCollection(String userId, Integer nowPage) {
        QueryWrapper<UserCollection> userCollectionQueryWrapper = new QueryWrapper<>();
        userCollectionQueryWrapper.eq("userId", userId);
        List<UserCollection> userCollections = collectionMapper.selectList(userCollectionQueryWrapper);
        List<PostVO> postVOS = new ArrayList<>();
        for (UserCollection userCollection : userCollections) {
            QueryWrapper<Post> postQueryWrapper = new QueryWrapper<>();
            postQueryWrapper.eq("postId", userCollection.getPostId());
            Post post = postMapper.selectOne(postQueryWrapper);
            PostVO postVO = PostVO.builder()
                    .postId(post.getPostId())
                    .postTitle(post.getPostTitle())
                    .postAuthorName(post.getPostAuthorName())
                    .postTime(post.getPostTime())
                    .postModifyTime(post.getPostModifyTime())
                    .postView(post.getPostView())
                    .isEssence(post.getIsEssence())
                    .isHot(post.getIsHot())
                    .postTagName(post.getPostTagName())
                    .postBrief(post.getPostBrief())
                    .build();
            postVOS.add(postVO);
        }
        return new ResponseResult<>(200, postVOS);
    }
}
