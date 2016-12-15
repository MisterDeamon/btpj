package com.jack.security.webapp;

import com.jack.security.pojo.SecurityMenu;
import com.jack.security.service.SecurityMenuService;
import com.jack.security.shiro.ShiroDbRealm;
import com.jack.utils.Pager;
import com.jack.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping(value = "/management/security/menu")
public class SecurityMenuController extends BaseController {

    @Autowired
    private SecurityMenuService menuService;

    private static final String LIST = "management/security/menu/list";
    private static final String CREATE = "management/security/menu/create";
    private static final String MODIFY = "management/security/menu/modify";

    @RequestMapping(value = {"/list", ""}, method = {RequestMethod.GET, RequestMethod.POST})
    @RequiresPermissions("Menu:view")
    public String list(HttpServletRequest request, SecurityMenu searchMenu, Model model) {
        Pager<SecurityMenu> pager = new Pager<SecurityMenu>();
        pager.setPageSize(10);
        setPageInfo(request,pager);

        String parentName = request.getParameter("parentName");
        if(StringUtils.isNotEmpty(parentName)){
            SecurityMenu parentMenu = menuService.findByName(parentName.trim());
            if(parentMenu != null){
                searchMenu.setParentId(parentMenu.getId());
                model.addAttribute("parentName",parentName.trim());
            }
        }

        List<SecurityMenu> roleList = menuService.findMenuPage(searchMenu, pager);
        int total = menuService.findMenuCount(searchMenu, pager);

        pager.setTotalCount(total);
        pager.setResult(roleList);
        pager.setFirstandLastPn();
        model.addAttribute("page", pager);
        model.addAttribute("menuList", pager.getResult());
        model.addAttribute("searchMenu", searchMenu);
        return LIST;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("parentMenus",menuService.findAllParentMenus());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String create(SecurityMenu menu) {

        menu.setName(StringUtils.encodeUTF8(menu.getName()));
        menu.setDescription(StringUtils.encodeUTF8(menu.getDescription()));
        Map<String, Object> map = new HashMap<String, Object>();
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        menuService.preCreate(menu, shiroUser);
        menuService.preUpdate(menu, shiroUser);

        try {
            menuService.saveOrUpDate(menu);
            map.put("msg", "菜单添加成功");
            map.put("success", true);
        } catch (Exception e) {
            map.put("msg", "菜单添加失败");
            e.printStackTrace();
        }

        return getJsonResult(map);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(@RequestParam("id") String id, Model model) {
        model.addAttribute("menu", menuService.findById(id));
        model.addAttribute("parentMenus",menuService.findAllParentMenus());
        return MODIFY;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String modify(SecurityMenu menu) {

        menu.setName(StringUtils.encodeUTF8(menu.getName()));
        menu.setDescription(StringUtils.encodeUTF8(menu.getDescription()));
        Map<String, Object> map = new HashMap<String, Object>();
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        menuService.preUpdate(menu, shiroUser);

        try {
            menuService.saveOrUpDate(menu);
            map.put("msg", "修改菜单成功");
            map.put("success", true);
        } catch (Exception e) {
            map.put("msg", "修改菜单失败");
            e.printStackTrace();
        }

        return getJsonResult(map);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    @RequiresPermissions("Menu:delete")
    public
    @ResponseBody
    String deleteRight(@RequestParam("ids") String[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            for (String id : ids) {
                menuService.remove(id);
            }
            map.put("success", true);
            map.put("msg", "删除菜单成功");
        } catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        }
        return getJsonResult(map);
    }

}
