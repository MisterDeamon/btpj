package com.jack.security.service;

import com.jack.security.persistence.SecurityPermissionMapper;
import com.jack.security.persistence.SecurityRoleMapper;
import com.jack.security.pojo.SecurityPermission;
import com.jack.security.pojo.SecurityRole;
import com.jack.security.service.mybatis.AbstractService;
import com.jack.utils.Pager;
import com.jack.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wajiangk on 8/22/2016.
 */
@Service
public class SecurityPermissionService extends AbstractService<SecurityPermission,String,SecurityPermissionMapper>{

    @Autowired
    public void setMapper(SecurityPermissionMapper mapper){
        super.setMapper(mapper);
    }

    public List<SecurityPermission> findPermissionPage(SecurityPermission permission, Pager<SecurityPermission> page){
        return this.getMapper().findPermissionPage(permission,page);

    }

    public int findPermissionCount(SecurityPermission permission, Pager<SecurityPermission> page){
        return this.getMapper().findPermissionCount(permission,page);
    }

    public List<SecurityPermission> findAll(){
        return this.getMapper().findAll();
    }

    public void saveOrUpDate(SecurityPermission permission){

        if(StringUtils.isEmpty(permission.getRightId())){
            this.getMapper().save(permission);
        }else{
            this.getMapper().update(permission);
        }

    }

}
