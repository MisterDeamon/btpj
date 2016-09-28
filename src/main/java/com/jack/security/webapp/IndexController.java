package com.jack.security.webapp;

import com.jack.security.pojo.SecurityMenu;
import com.jack.security.pojo.SecurityPermission;
import com.jack.security.pojo.SecurityRole;
import com.jack.security.pojo.SecurityUser;
import com.jack.security.service.SecurityMenuService;
import com.jack.security.service.SecurityUserRoleService;
import com.jack.security.service.SecurityUserService;
import com.jack.security.shiro.ShiroDbRealm;
import com.jack.utils.SecurityConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wajiangk on 9/2/2016.
 */

@Controller
@RequestMapping(value = "/management")
public class IndexController {

    private static final String INDEX="management/index/index";


    @Autowired
    private SecurityUserService userService;
    @Autowired
    private SecurityMenuService sercurityMenuService;
    @Autowired
    private SecurityUserRoleService securityUserRoleService;

    @RequestMapping(value="/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(HttpServletRequest request,Model model){
        Subject subject = SecurityUtils.getSubject();

        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) subject
                .getPrincipal();

        SecurityUser user = shiroUser.getUser();
        List<SecurityRole> userRoles = new ArrayList<SecurityRole>();
        for(SecurityRole role:user.getSroles()){
            userRoles.add(securityUserRoleService.findById(role.getRoleId()));
        }
        Set<String> permissionList=new HashSet<String>();

        if (!userRoles.isEmpty()) {
            for (SecurityRole userRole : userRoles) {
                for(SecurityPermission permission:userRole.getRights() ){
                    permissionList.add(permission.getRightSign()+":"+permission.getRightName());
                }
            }
        }

        List<SecurityMenu> parentMenus =getSysMenu(sercurityMenuService.findAllParentMenus(),permissionList);

        request.getSession().setAttribute("menus",parentMenus);
        request.getSession().setAttribute("user",shiroUser.getUser());
        return INDEX;
    }

    /**
     * 鉴权一级菜单、二级菜单
     * @param parentMenus
     * @param permissionList
     * @return
     */
    private List<SecurityMenu> getSysMenu(List<SecurityMenu> parentMenus, Set<String> permissionList){
        List<SecurityMenu> parentMenusChecked = new ArrayList<SecurityMenu>();

        for(SecurityMenu menu:parentMenus){

            //鉴权一级菜单
            if(permissionList.contains(menu.getRightSn()+":" +SecurityConstants.OPERATION_VIEW)){

                List<SecurityMenu> childrenMenus = sercurityMenuService.findChildrenMenus(menu.getId());
                List<SecurityMenu> childrenMenusChecked = new ArrayList<SecurityMenu>();

                if(null!=childrenMenus&&childrenMenus.size()>0){
                    for(int i=0;i<childrenMenus.size();i++){
                        //鉴权一级菜单下的二级菜单
                        if(permissionList.contains(childrenMenus.get(i).getRightSn()+":" +SecurityConstants.OPERATION_VIEW)){
                            childrenMenusChecked.add(childrenMenus.get(i));
                        }
                    }
                }

                if(0<childrenMenusChecked.size()){
                    menu.setChildrenMenus(childrenMenusChecked);
                }
                parentMenusChecked.add(menu);
            }
        }

        return parentMenusChecked;
    }

}
