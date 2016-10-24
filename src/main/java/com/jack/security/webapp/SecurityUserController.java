package com.jack.security.webapp;

import com.jack.security.pojo.SecurityRole;
import com.jack.security.pojo.SecurityUser;
import com.jack.security.service.SecurityUserRoleService;
import com.jack.security.service.SecurityUserService;
import com.jack.security.shiro.ShiroDbRealm;
import com.jack.security.webapp.asyncDemo.LongTimeAsyncCallService;
import com.jack.utils.Pager;
import com.jack.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by wajiangk on 9/6/2016.
 */
@Controller
@RequestMapping(value = "/management/security/user")
public class SecurityUserController extends BaseController{

    @Autowired
    private SecurityUserService userService;
    @Autowired
    private SecurityUserRoleService userRoleService;
    @Autowired
    private LongTimeAsyncCallService longTimeAsyncCallService;

    private static final Logger log = Logger.getLogger(SecurityUserController.class);

    private static final String LIST = "management/security/user/list";
    private static final String CREATE = "management/security/user/create";
    private static final String MODIFY = "management/security/user/modify";
    private static final String ROLESET = "management/security/user/roleSet";


    /**/
    @RequestMapping(value={"/list",""},method= {RequestMethod.GET,RequestMethod.POST})
    @RequiresPermissions("User:view")
    public String LIST(Model model,SecurityUser user,SecurityRole searchRole,HttpServletRequest request){


        log.info(request.getLocalAddr());

        Pager<SecurityUser> pager = new Pager<SecurityUser>();
        pager.setPageSize(10);
        setPageInfo(request,pager);

        List<SecurityRole> searchRoles=null;
        if(null!=searchRole.getRoleName()&&!"".equals(searchRole.getRoleName())){
            searchRoles = new ArrayList<SecurityRole>();
            searchRoles.add(searchRole);
        }

        user.setSroles(searchRoles);
        List<SecurityUser> userList = userService.findUserPage(user,pager);
        int total = userService.findUserCount(user,pager);
        //select user roles
        for(int i=0;i<userList.size();i++){
            userList.get(i).setSroles(userService.findById(userList.get(i).getId()).getSroles());
        }
        pager.setTotalCount(total);
        pager.setResult(userList);
        pager.setFirstandLastPn();
        model.addAttribute("page", pager);
        model.addAttribute("userList", pager.getResult());
        model.addAttribute("searchUser", user);
        model.addAttribute("searchRole", searchRole);
        return LIST;
    }


    /*@RequestMapping(value={"/list",""},method= {RequestMethod.GET,RequestMethod.POST})
    @RequiresPermissions("User:view")
    public Callable<ModelAndView> LIST(SecurityUser user, SecurityRole searchRole, HttpServletRequest request){

    }*/

    /**
     * create User
     * @return
     */
    @RequestMapping(value="/create",method = RequestMethod.GET)
    @RequiresPermissions("User:create")
    public  String create(){
        return CREATE;
    }

    /**
     * save create user form
     * @param request
     * @param user
     * @param file
     * @return
     */
    @RequestMapping(value="/create",method = RequestMethod.POST,produces ="application/json;charset=UTF-8" )
    @RequiresPermissions("User:create")
    public @ResponseBody
    String create(HttpServletRequest request, SecurityUser user, @RequestParam("file")MultipartFile file){

        user.setUserName(StringUtils.encodeUTF8(user.getUserName()));

        Map<String,Object> map = new HashMap<String,Object>();
        Subject subject = SecurityUtils.getSubject();
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) subject.getPrincipal();
        try {
            user.setPlainPasswd(request.getParameter("password"));
            userService.preCreate(user,shiroUser);
            userService.preUpdate(user,shiroUser);
            String fileName = uploadHeadPic(request, file, "/file/upload/headpic");
            if (!fileName.equals("")) {
                user.setHeadPicPath(fileName);
            }

            userService.saveOrUpdate(user);
            map.put("success", true);
            map.put("msg", "用户创建成功");
        }catch (Exception e){
            map.put("success",false);
            e.printStackTrace();
        }

        return getJsonResult(map);
    }

    /**
     * modify user
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value="/modify",method = RequestMethod.GET)
    @RequiresPermissions("User:modify")
    public  String modify(@RequestParam("id") String userId,Model model) {
        model.addAttribute("user",userService.findById(userId));
        return MODIFY;
    }

    /**
     * save modify user form
     * @param request
     * @param user
     * @param file
     * @return
     */
    @RequestMapping(value="/modify",method = RequestMethod.POST,produces ="application/json;charset=UTF-8" )
    @RequiresPermissions("User:modify")
    public @ResponseBody
    String modify(HttpServletRequest request, SecurityUser user, @RequestParam("file")MultipartFile file){

        user.setUserName(StringUtils.encodeUTF8(user.getUserName()));

        Map<String,Object> map = new HashMap<String,Object>();
        Subject subject = SecurityUtils.getSubject();
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) subject.getPrincipal();
        try {
            user.setPlainPasswd(request.getParameter("password"));
            userService.preUpdate(user,shiroUser);
            String fileName = uploadHeadPic(request, file, "/file/upload/headpic");
            if (!fileName.equals("")) {
                user.setHeadPicPath(fileName);
            }

            userService.saveOrUpdate(user);
            map.put("success", true);
            map.put("msg", "用户修改成功");

        }catch (Exception e){
            map.put("success",false);
            e.printStackTrace();
        }

        return getJsonResult(map);
    }

    public String uploadHeadPic(HttpServletRequest request,MultipartFile file,String savePath){
        String fileName = "";
        try{
            if(file!=null&&!file.isEmpty()){

                ServletContext sc = request.getSession().getServletContext();
                String dir = sc.getRealPath(savePath); // 设定文件保存的目录
                String orignFileName = file.getOriginalFilename();
                fileName = StringUtils.getFormatDate_2() + "." + orignFileName.substring(orignFileName.lastIndexOf(".") + 1);

                FileUtils.writeByteArrayToFile(new File(dir,fileName),file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * delete user
     * @param userIds
     * @return
     */
    @RequestMapping(value="/delete",method = RequestMethod.POST,produces ="application/json;charset=UTF-8" )
    @RequiresPermissions("User:delete")
    public @ResponseBody String deleteUser(@RequestParam("ids")String[] userIds){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            for(String id:userIds){
                userService.remove(id);
            }
            map.put("success",true);
            map.put("msg","删除用户成功");
        }catch (Exception e){
            map.put("success",false);
            e.printStackTrace();
        }
        return getJsonResult(map);
    }

    /**
     * change user account status
     * @param userId
     * @param status
     * @return
     */
    @RequestMapping(value = "/changeAccountStatus",method=RequestMethod.GET,produces ="application/json;charset=UTF-8" )
    public @ResponseBody  String unlockAccount(@RequestParam("userId")String userId,@RequestParam("status") int status){
        Map<String,Object> map= new HashMap<String,Object>();
        try {

            map.put("success",true);
            if(status==0){
                userService.lockAccount(userId);
                map.put("msg","锁定成功");
            }else{
                userService.relockAccount(userId);
                map.put("msg","解锁成功");
            }
        }catch (Exception e){
            map.put("success",false);
            e.printStackTrace();
        }
        return getJsonResult(map);
    }

    /**
     * roleSet
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/roleSet",method=RequestMethod.GET,produces ="application/json;charset=UTF-8" )
    public String roleSet(@RequestParam("id")String userId,Model model){

        SecurityUser tempUser = userService.findById(userId);
        List<SecurityRole> roles = userRoleService.findAll();

        model.addAttribute("userRoleSet",tempUser);
        model.addAttribute("allRoles",roles);

        return ROLESET;
    }


    /**
     * roleSet
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/roleSet",method=RequestMethod.POST,produces ="application/json;charset=UTF-8" )
    public @ResponseBody String roleSet(HttpServletRequest request,@RequestParam("userId") String userId){
        String[] roleIds = request.getParameterValues("roleId");
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            SecurityUser temp = userService.findById(userId);

            List<SecurityRole> roles = temp.getSroles();
            userService.setRole(userId,roleIds,roles);

            map.put("msg","角色分配成功！");
            map.put("success",true);

        }catch (Exception e){
            map.put("msg","角色分配失败！");
            e.printStackTrace();
        }


        return getJsonResult(map);
    }


}
