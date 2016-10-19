package com.jack.security.persistence;

import com.jack.security.pojo.SecurityMenu;
import com.jack.utils.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wajiangk on 9/6/2016.
 */
@Repository
public interface SecurityMenuMapper extends BaseMapper<SecurityMenu,Serializable> {

   public List<SecurityMenu> findChildrenMenus(@Param("parentId")String parentId);

   public List<SecurityMenu> findAllParentMenus();

   public List<SecurityMenu> findMenuRight();

   public List<SecurityMenu> findMenuPage(@Param("pojo") SecurityMenu menu, @Param("pager") Pager<SecurityMenu> page);
   public int findMenuCount(@Param("pojo") SecurityMenu menu, @Param("pager") Pager<SecurityMenu> page);

   public SecurityMenu findByName(@Param("name")String name);

}
