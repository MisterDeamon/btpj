package com.jack.security.webapp;

import com.jack.security.pojo.SecurityUser;
import com.jack.security.service.SecurityUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by wajiangk on 9/6/2016.
 */
@Controller
@RequestMapping(value = "/management/security/user")
public class SecurityUserController {

    @Autowired
    private SecurityUserService userService;

    private static final String LIST = "management/security/user/list";

    @RequestMapping(value={"/list",""},method= RequestMethod.GET)
    @RequiresPermissions("User:view")
    public String LIST(Model model){

        List<SecurityUser> userList = userService.findAll();

        model.addAttribute("userList",userList);
        return LIST;
    }

}
