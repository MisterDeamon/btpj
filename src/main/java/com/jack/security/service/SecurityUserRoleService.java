package com.jack.security.service;

import com.jack.security.persistence.SecurityRoleMapper;
import com.jack.security.pojo.SecurityPermission;
import com.jack.security.pojo.SecurityRole;
import com.jack.security.service.mybatis.AbstractService;
import com.jack.utils.Pager;
import com.jack.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void saveOrUpDate(SecurityRole role){

        if(StringUtils.isEmpty(role.getRoleId())){
            this.getMapper().save(role);
        }else{
            this.getMapper().update(role);
        }

    }

    public void setRights(String[] rightIds,String roleId,List<SecurityPermission> permissions){
        if(rightIds==null){
            rightIds = new String[0];
        }

        List<String> ownedRightIds = new ArrayList<String>();
        for(SecurityPermission permission : permissions){
            ownedRightIds.add(permission.getRightId());
        }

        for(String rightId : rightIds){
            if(!ownedRightIds.contains(rightId)){
                this.getMapper().setRight(rightId,roleId);
            }
        }

        for(String rightId : ownedRightIds){
            if(!StringUtils.contains(rightId,rightIds)){
                this.getMapper().cancelRight(rightId,roleId);
            }
        }
    }

}
