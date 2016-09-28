package com.jack.security.service;

import com.jack.security.persistence.SecurityRoleMapper;
import com.jack.security.pojo.SecurityRole;
import com.jack.security.service.mybatis.AbstractService;
import com.jack.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wajiangk on 8/22/2016.
 */
@Service
public class SecurityUserRoleService extends AbstractService<SecurityRole,String,SecurityRoleMapper>{

    @Autowired
    public void setMapper(SecurityRoleMapper mapper){
        super.setMapper(mapper);
    }

    public List<SecurityRole> findRolePage(SecurityRole role, Pager<SecurityRole> page){
        return this.getMapper().findRolePage(role,page);

    }

    public int findRoleCount(SecurityRole role, Pager<SecurityRole> page){
        return this.getMapper().findRoleCount(role,page);
    }

    public List<SecurityRole> findAll(){
        return this.getMapper().findAll();
    }


}
