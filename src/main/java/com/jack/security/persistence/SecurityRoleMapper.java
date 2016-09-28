package com.jack.security.persistence;

import com.jack.security.pojo.SecurityRole;
import com.jack.utils.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wajiangk on 9/5/2016.
 */
@Repository
public interface SecurityRoleMapper extends BaseMapper<SecurityRole,Serializable> {

    public List<SecurityRole> findRolePage(@Param("pojo")SecurityRole role, @Param("pager")Pager<SecurityRole> page);
    public int findRoleCount(@Param("pojo")SecurityRole role, @Param("pager")Pager<SecurityRole> page);



}
