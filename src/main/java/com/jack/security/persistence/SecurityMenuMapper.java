package com.jack.security.persistence;

import com.jack.security.pojo.SecurityMenu;
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

}
