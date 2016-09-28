package com.jack.security.webapp;

import com.jack.security.pojo.SecurityPermission;
import com.jack.security.pojo.SecurityRole;
import com.jack.security.service.SecurityUserRoleService;
import com.jack.utils.PageUtils;
import com.jack.utils.Pager;
import com.jack.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wajiangk on 9/23/2016.
 */
@Controller
@RequestMapping(value = "/management/security/role")
public class SecurityRoleController extends BaseController {

    @Autowired
    private SecurityUserRoleService userRoleService;

    private static final String LIST = "management/security/role/list";

    @RequestMapping(value={"/list",""},method = {RequestMethod.GET,RequestMethod.POST})
//    @RequiresPermissions("Role:view")
    public String list(HttpServletRequest request, SecurityRole searchRole, SecurityPermission searchPermission,Model model){
        Pager<SecurityRole> pager = new Pager<SecurityRole>();
        pager.setPageSize(5);
        PageUtils.getPageNumber(request,pager);

        List<SecurityPermission> searchPermissions=null;
        if(StringUtils.isNotEmpty(searchPermission.getRightName())){
            searchPermissions = new ArrayList<SecurityPermission>();
            searchPermissions.add(searchPermission);
        }

        searchRole.setRights(searchPermissions);
        List<SecurityRole> roleList = userRoleService.findRolePage(searchRole,pager);
        int total = userRoleService.findRoleCount(searchRole,pager);
        //select user roles
        for(int i=0;i<roleList.size();i++){
            roleList.get(i).setRights(userRoleService.findById(roleList.get(i).getRoleId()).getRights());
        }
        pager.setTotalCount(total);
        pager.setResult(roleList);
        pager.setTotalCount(total);
        pager.setFirstandLastPn();
        model.addAttribute("page", pager);
        model.addAttribute("roleList", pager.getResult());
        model.addAttribute("searchRole", searchRole);
        model.addAttribute("searchPermission", searchPermission);
        return LIST;
    }

}
