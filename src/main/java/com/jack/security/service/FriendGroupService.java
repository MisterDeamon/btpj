package com.jack.security.service;


import com.jack.security.persistence.FriendGroupMapper;
import com.jack.security.pojo.Friend;
import com.jack.security.pojo.FriendGroup;
import com.jack.security.service.mybatis.AbstractService;
import com.jack.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wajiangk on 10/11/2016.
 */
@Service
public class FriendGroupService extends AbstractService<FriendGroup,Serializable,FriendGroupMapper> {

    @Autowired
    public void setMapper(FriendGroupMapper mapper){
        super.setMapper(mapper);
    }


    public List<FriendGroup> findGroupPage(FriendGroup friendGroup, Pager<FriendGroup> pager){
        return this.getMapper().findPageGroup(friendGroup,pager);
    }
    public int findGroupCount(FriendGroup friendGroup, Pager<FriendGroup> pager){
        return this.getMapper().findGroupCount(friendGroup,pager);
    }

    public List<FriendGroup> findAllFriendsByGroup(String userId){
        return this.getMapper().findAllFriendsByGroup(userId);
    }

    public List<FriendGroup> FriendGroup(String userId){
        return this.getMapper().findGroup(userId);
    }

    public void delete(FriendGroup group){
        this.getMapper().remove(group);
    }

    public List<FriendGroup> findGroup(FriendGroup group){
        return this.getMapper().findGroup(group);
    }

}
