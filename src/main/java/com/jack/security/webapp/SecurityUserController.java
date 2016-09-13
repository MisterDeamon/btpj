package com.jack.security.webapp;

import com.jack.security.pojo.SecurityUser;
import com.jack.security.service.SecurityUserService;
import com.jack.security.shiro.ShiroDbRealm;
import com.jack.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wajiangk on 9/6/2016.
 */
@Controller
@RequestMapping(value = "/management/security/user")
public class SecurityUserController {

    @Autowired
    private SecurityUserService userService;

    private static final String LIST = "management/security/user/list";
    private static final String CREATE = "management/security/user/create";


    @RequestMapping(value={"/list",""},method= RequestMethod.GET)
    @RequiresPermissions("User:view")
    public String LIST(Model model){

        List<SecurityUser> userList = userService.findAll();

        model.addAttribute("userList",userList);
        return LIST;
    }


    @RequestMapping(value="/create",method = RequestMethod.GET)
    @RequiresPermissions("User:create")
    public  String create(){
        return CREATE;
    }

    @RequestMapping(value="/create",method = RequestMethod.POST,produces ="application/json;charset=UTF-8" )
    @RequiresPermissions("User:create")
    public @ResponseBody
    String create(Model model, HttpServletRequest request, SecurityUser user, @RequestParam("file")MultipartFile file){

        Map<String,Object> map = new HashMap<String,Object>();


        Subject subject = SecurityUtils.getSubject();

        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) subject
                .getPrincipal();
        String result = "";
        try {
            user.setPlainPasswd(request.getParameter("password"));
            user.setCreatedDate(new Date());
            user.setCreatedBy(shiroUser.getUser().getUserName());
            user.setUserStatus(0);


            if (file != null) {
                String orignFileName = file.getOriginalFilename();
                String fileName = StringUtils.getFormatDate_2() + "." + orignFileName.substring(orignFileName.lastIndexOf(".") + 1);

                boolean headpicEnd = uploadHeadPic(request, file, "/file/upload/headpic", fileName);

                if (headpicEnd) {
                    user.setHeadPicPath(fileName);
                }
            }
            userService.save(user);
            map.put("success", true);

            map.put("msg", "用户创建成功");

        }catch (Exception e){
            map.put("success",false);
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(map);
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }

    public boolean uploadHeadPic(HttpServletRequest request,MultipartFile file,String savePath,String fileName){
        boolean flag=false;
        try{
            if(file!=null){

                ServletContext sc = request.getSession().getServletContext();
                String dir = sc.getRealPath(savePath); // 设定文件保存的目录

                FileUtils.writeByteArrayToFile(new File(dir,fileName),file.getBytes());
                flag=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return flag;
    }

}
