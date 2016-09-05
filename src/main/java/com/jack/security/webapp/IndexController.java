package com.jack.security.webapp;

import com.jack.security.pojo.SecurityUser;
import com.jack.security.service.SecurityUserService;
import com.jack.security.shiro.ShiroDbRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wajiangk on 9/2/2016.
 */

@Controller
@RequestMapping(value = "/management")
public class IndexController {

    private static final String INDEX="management/index/index";


    @Autowired
    private SecurityUserService userService;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();

        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) subject
                .getPrincipal();


        return INDEX;
    }
}
