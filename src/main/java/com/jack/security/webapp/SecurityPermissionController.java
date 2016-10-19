package com.jack.security.webapp;

import com.jack.security.pojo.SecurityPermission;
import com.jack.security.service.SecurityPermissionService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wajiangk on 9/23/2016.
 */
@Controller
@RequestMapping(value = "/management/security/right")
public class SecurityPermissionController extends BaseController {

    @Autowired
    private SecurityPermissionService permissionService;

    private static final String LIST = "management/security/right/list";
    private static final String CREATE = "management/security/right/create";
    private static final String MODIFY = "management/security/right/modify";

    @RequestMapping(value = {"/list", ""}, method = {RequestMethod.GET, RequestMethod.POST})
//    @RequiresPermissions("Role:view")
    public String list(HttpServletRequest request, SecurityPermission searchRight, Model model) {
        Pager<SecurityPermission> pager = new Pager<SecurityPermission>();
        pager.setPageSize(5);
        setPageInfo(request,pager);

        List<SecurityPermission> roleList = permissionService.findPermissionPage(searchRight, pager);
        int total = permissionService.findPermissionCount(searchRight, pager);

        pager.setTotalCount(total);
        pager.setResult(roleList);
        pager.setFirstandLastPn();
        model.addAttribute("page", pager);
        model.addAttribute("rightList", pager.getResult());
        model.addAttribute("searchPermission", searchRight);
        return LIST;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String create(SecurityPermission right) {

        Map<String, Object> map = new HashMap<String, Object>();

        right.setRightName(StringUtils.encodeUTF8(right.getRightName()));
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        permissionService.preCreate(right, shiroUser);
        permissionService.preUpdate(right, shiroUser);

        try {
            permissionService.saveOrUpDate(right);
            map.put("msg", "权限添加成功");
            map.put("success", true);
        } catch (Exception e) {
            map.put("msg", "权限添加失败");
            e.printStackTrace();
        }

        return getJsonResult(map);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(@RequestParam("id") String roleId, Model model) {
        model.addAttribute("right", permissionService.findById(roleId));
        return MODIFY;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String modify(SecurityPermission right) {

        Map<String, Object> map = new HashMap<String, Object>();
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        permissionService.preUpdate(right, shiroUser);

        try {
            permissionService.saveOrUpDate(right);
            map.put("msg", "修改权限成功");
            map.put("success", true);
        } catch (Exception e) {
            map.put("msg", "修改权限失败");
            e.printStackTrace();
        }

        return getJsonResult(map);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    @RequiresPermissions("Role:delete")
    public
    @ResponseBody
    String deleteRight(@RequestParam("ids") String[] roleIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            for (String id : roleIds) {
                permissionService.remove(id);
            }
            map.put("success", true);
            map.put("msg", "删除权限成功");
        } catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        }

        return getJsonResult(map);
    }



}
