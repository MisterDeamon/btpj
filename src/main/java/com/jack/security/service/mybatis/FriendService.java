package com.jack.security.service.mybatis;


import com.jack.security.persistence.FriendGroupMapper;
import com.jack.security.persistence.FriendMapper;
import com.jack.security.pojo.Friend;
import com.jack.security.pojo.FriendGroup;
import com.jack.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wajiangk on 10/11/2016.
 */
@Service
public class FriendService extends AbstractService<Friend,Serializable,FriendMapper> {

    @Autowired
    public void setMapper(FriendMapper mapper){
        super.setMapper(mapper);
    }



}
