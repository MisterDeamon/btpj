package com.jack.security.service;

import com.jack.security.persistence.SecurityMenuMapper;
import com.jack.security.pojo.SecurityMenu;
import com.jack.security.service.mybatis.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wajiangk on 9/6/2016.
 */
@Service
public class SecurityMenuService extends AbstractService<SecurityMenu,String,SecurityMenuMapper> {

    @Autowired
    public void setMapper(SecurityMenuMapper mapper){
        super.setMapper(mapper);
    }

    public List<SecurityMenu> findChildrenMenus(String parentId){
        return this.getMapper().findChildrenMenus(parentId);
    }

    public List<SecurityMenu> findAllParentMenus(){
       return this.getMapper().findAllParentMenus();
    }


}
