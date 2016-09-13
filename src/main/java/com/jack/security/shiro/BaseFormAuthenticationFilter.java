package com.jack.security.shiro;

import com.jack.security.service.SecurityUserService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.util.SecurityConstants;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wajiangk on 8/23/2016.
 */
public class BaseFormAuthenticationFilter extends FormAuthenticationFilter {

    @Autowired
    protected SecurityUserService securityUserService;

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = request.getParameter("userName");
        String password = request.getParameter("passwd");
        return createToken(username, password, request, response);
    }

    @Override
    protected  boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                      ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser)subject.getPrincipal();

        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))
                || request.getParameter("ajax") == null) {// 不是ajax请求

            httpServletRequest.getSession().setAttribute("user", user.getUser());
            httpServletResponse.sendRedirect(this.getSuccessUrl());

            securityUserService.changeLoginState(1,user.getUser().getId());

        }else{
            httpServletRequest.getSession().setAttribute("error", "login failure");
            httpServletResponse.sendRedirect(this.getLoginUrl());
        }

        return false;

    }

    public void setSecurityUserService(SecurityUserService securityUserService) {
        this.securityUserService=securityUserService;
    }
}
