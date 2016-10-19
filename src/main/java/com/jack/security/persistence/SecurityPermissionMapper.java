package com.jack.security.persistence;

import com.jack.security.pojo.SecurityPermission;
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
public interface SecurityPermissionMapper extends BaseMapper<SecurityPermission,Serializable> {

    public List<SecurityPermission> findPermissionPage(@Param("pojo") SecurityPermission role, @Param("pager") Pager<SecurityPermission> page);
    public int findPermissionCount(@Param("pojo") SecurityPermission role, @Param("pager") Pager<SecurityPermission> page);



}
