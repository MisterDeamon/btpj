package com.jack.security.persistence;

import com.jack.security.pojo.FriendGroup;
import com.jack.utils.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wajiangk on 10/11/2016.
 */
@Repository
public interface FriendGroupMapper extends BaseMapper<FriendGroup,Serializable> {

    public List<FriendGroup> findAllFriendsByGroup(@Param("userId") String userId);
    public List<FriendGroup> findGroup(@Param("userId") String userId);

    public List<FriendGroup> findPageGroup(@Param("pojo")FriendGroup group, @Param("pager")Pager<FriendGroup> pager);
    public List<FriendGroup> findGroup(@Param("pojo")FriendGroup group);
    public int findGroupCount(@Param("pojo")FriendGroup group, @Param("pager")Pager<FriendGroup> pager);

    public void remove(@Param("pojo")FriendGroup group);
}
