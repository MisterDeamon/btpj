package com.jack.security.service;

import com.jack.security.persistence.SecurityMenuMapper;
import com.jack.security.pojo.SecurityMenu;
import com.jack.security.service.mybatis.AbstractService;
import com.jack.utils.Pager;
import com.jack.utils.StringUtils;
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

    public List<SecurityMenu> findMenuRight(){
        return this.getMapper().findMenuRight();
    }

    public List<SecurityMenu> findMenuPage(SecurityMenu menu, Pager<SecurityMenu> pager){
        return this.getMapper().findMenuPage(menu,pager);
    }

    public int findMenuCount(SecurityMenu menu,Pager<SecurityMenu> pager){
        return this.getMapper().findMenuCount(menu,pager);
    }

    public SecurityMenu findByName(String name){
        return this.getMapper().findByName(name);
    }

    public void saveOrUpDate(SecurityMenu menu){

        if(StringUtils.isEmpty(menu.getId())){
            this.getMapper().save(menu);
        }else{
            this.getMapper().update(menu);
        }

    }

}
