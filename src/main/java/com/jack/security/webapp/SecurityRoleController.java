package com.jack.security.webapp;

import com.jack.security.pojo.SecurityMenu;
import com.jack.security.pojo.SecurityPermission;
import com.jack.security.pojo.SecurityRole;
import com.jack.security.service.SecurityMenuService;
import com.jack.security.service.SecurityPermissionService;
import com.jack.security.service.SecurityUserRoleService;
import com.jack.security.shiro.ShiroDbRealm;
import com.jack.utils.Pager;
import com.jack.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wajiangk on 9/23/2016.
 */
@Controller
@RequestMapping(value = "/management/security/role")
public class SecurityRoleController extends BaseController {

    @Autowired
    private SecurityUserRoleService userRoleService;
    @Autowired
    private SecurityMenuService menuService;
    @Autowired
    private SecurityPermissionService permissionService;

    private static final String LIST = "management/security/role/list";
    private static final String CREATE = "management/security/role/create";
    private static final String MODIFY = "management/security/role/modify";
    private static final String RIGHTSET = "management/security/role/rightSet";

    @RequestMapping(value = {"/list", ""}, method = {RequestMethod.GET, RequestMethod.POST})
//    @RequiresPermissions("Role:view")
    public String list(HttpServletRequest request, SecurityRole searchRole, SecurityPermission searchPermission, Model model) {
        Pager<SecurityRole> pager = new Pager<SecurityRole>();
        pager.setPageSize(5);
        setPageInfo(request, pager);

        List<SecurityPermission> searchPermissions = null;
        if (StringUtils.isNotEmpty(searchPermission.getRightName())) {
            searchPermissions = new ArrayList<SecurityPermission>();
            searchPermissions.add(searchPermission);
        }

        searchRole.setRights(searchPermissions);
        List<SecurityRole> roleList = userRoleService.findRolePage(searchRole, pager);
        int total = userRoleService.findRoleCount(searchRole, pager);
        //select user roles
        for (int i = 0; i < roleList.size(); i++) {
            roleList.get(i).setRights(userRoleService.findById(roleList.get(i).getRoleId()).getRights());
        }
        pager.setTotalCount(total);
        pager.setResult(roleList);
        pager.setFirstandLastPn();
        model.addAttribute("page", pager);
        model.addAttribute("roleList", pager.getResult());
        model.addAttribute("searchRole", searchRole);
        model.addAttribute("searchPermission", searchPermission);
        return LIST;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String create(SecurityRole role, HttpServletRequest request) {
        role.setRoleName(StringUtils.encodeUTF8(role.getRoleName()));
        Map<String, Object> map = new HashMap<String, Object>();
        role.setUpdatedBy("");
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        userRoleService.preCreate(role, shiroUser);
        userRoleService.preUpdate(role, shiroUser);

        try {
            userRoleService.saveOrUpDate(role);
            map.put("msg", "角色添加成功");
            map.put("success", true);
        } catch (Exception e) {
            map.put("msg", "角色添加失败");
            e.printStackTrace();
        }

        return getJsonResult(map);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(@RequestParam("id") String roleId, Model model) {
        model.addAttribute("role", userRoleService.findById(roleId));
        return MODIFY;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String modify(SecurityRole role, HttpServletRequest request) {


        role.setRoleName(StringUtils.encodeUTF8(role.getRoleName()));
        Map<String, Object> map = new HashMap<String, Object>();
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        userRoleService.preUpdate(role, shiroUser);

        try {
            userRoleService.saveOrUpDate(role);
            map.put("msg", "修改角色成功");
            map.put("success", true);
        } catch (Exception e) {
            map.put("msg", "修改角色失败");
            e.printStackTrace();
        }

        return getJsonResult(map);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    @RequiresPermissions("Role:delete")
    public
    @ResponseBody
    String deleteRole(@RequestParam("ids") String[] roleIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            for (String id : roleIds) {
                userRoleService.remove(id);
            }
            map.put("success", true);
            map.put("msg", "删除角色成功");
        } catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        }

        return getJsonResult(map);
    }

    @RequestMapping(value= "/rightSet",method = RequestMethod.GET)
    public String rightSet( @RequestParam("id") String roleId,Model model) {

        SecurityRole roleSet = userRoleService.findById(roleId);
        List<SecurityMenu> menus = menuService.findMenuRight();
        for (int i = 0; i < menus.size(); i++) {
            menus.get(i).setChildrenMenus(menuService.findChildrenMenus(menus.get(i).getId()));
        }
        List<SecurityPermission> permissions = permissionService.findAll();

        model.addAttribute("roleSet",roleSet);
        model.addAttribute("menus",menus);
        model.addAttribute("permissions",permissions);
        return RIGHTSET;
    }

    @RequestMapping(value="/rightSet",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody  String rightSet(@RequestParam("roleId")String roleId,HttpServletRequest request){

        String[] rightIds = request.getParameterValues("rightId");
        Map<String,Object> map = new HashMap<String, Object>();

        try{
            userRoleService.setRights(rightIds,roleId,userRoleService.findById(roleId).getRights());
            map.put("msg","权限分配成功");
            map.put("success",true);

        }catch (Exception e){
            map.put("msg","权限设置失败，系统错误");
            e.printStackTrace();
        }

        return getJsonResult(map);

    }

}
