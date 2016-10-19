package com.jack.security.persistence;

import com.jack.security.pojo.Friend;
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
public interface FriendMapper extends BaseMapper<Friend,Serializable> {

}
