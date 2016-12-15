package com.jack.security.webapp;

import com.jack.security.pojo.SecurityRole;
import com.jack.security.pojo.SecurityUser;
import com.jack.security.service.SecurityUserRoleService;
import com.jack.security.service.SecurityUserService;
import com.jack.security.shiro.ShiroDbRealm;
import com.jack.security.webapp.asyncDemo.LongTimeAsyncCallService;
import com.jack.utils.LinuxUtil;
import com.jack.utils.Pager;
import com.jack.utils.StringUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wajiangk on 9/6/2016.
 */
@Controller
@RequestMapping(value = "/management/security/linuxCommand")
public class LinuxCommandController extends BaseController{


    private static final Logger log = Logger.getLogger(LinuxCommandController.class);

    private static final String LIST = "management/security/linuxCommand/linuxCommand";

    @RequestMapping(value={"/list",""},method= {RequestMethod.GET,RequestMethod.POST})
    @RequiresPermissions("User:view")
    public String LIST(Model model,SecurityUser user,SecurityRole searchRole,HttpServletRequest request){

        log.info(request.getLocalAddr());
        return LIST;
    }

    @RequestMapping(value = "/excuteCmd",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody String excuteCmd(String cmd){
        Map<String,Object> map = new HashMap<String,Object>();
        String result = LinuxUtil.excuteCommand(cmd);
        map.put("result",result);
        return getJsonResult(map);
    }

}
