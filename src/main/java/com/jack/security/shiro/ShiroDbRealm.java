package com.jack.security.shiro;

import com.jack.security.pojo.SecurityPermission;
import com.jack.security.pojo.SecurityRole;
import com.jack.security.pojo.SecurityUser;
import com.jack.security.service.SecurityUserRoleService;
import com.jack.security.service.SecurityUserService;
import com.jack.utils.Digests;
import com.jack.utils.Encodes;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wajiangk on 8/22/2016.
 */
public class ShiroDbRealm extends AuthorizingRealm {


    private static final int INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;
    private static final String ALGORITHM = "SHA-1";

    @Autowired
    protected SecurityUserService securityUserService;

    @Autowired
    protected SecurityUserRoleService securityUserRoleService;


    /**
     * 给ShiroDbRealm提供编码信息，用于密码密码比对 描述
     */
    public ShiroDbRealm() {
        super();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
                ShiroDbRealm.ALGORITHM);
        matcher.setHashIterations(ShiroDbRealm.INTERATIONS);

        this.setCredentialsMatcher(matcher);
    }

    /**
     * 用户认证
     * @param auctoken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auctoken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken)auctoken;
        SecurityUser user = null;
        try{
            System.out.println(token.getUsername());
            user= securityUserService.findByName(token.getUsername());
            }catch (Exception e){
            e.printStackTrace();
        }

        if(null!=user){

            if(0==user.getUserStatus()){
                throw new LockedAccountException();
            }

            //账户禁用
            //throw new DisabledAccountException()

            byte[] salt = Encodes.decodeHex(user.getSalt());
            ShiroUser shiroUser = new ShiroUser(user.getId(),
                    user.getUserName(), user);
            return new SimpleAuthenticationInfo(shiroUser, user.getPlainPasswd(),
                    ByteSource.Util.bytes(salt), getName());

        }else{
            return null;
        }

    }



    /**
     * 用户鉴权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.fromRealm(this.getName())
                .iterator().next();
        List<SecurityRole> userRoles =new ArrayList<SecurityRole>();

        SecurityUser user = shiroUser.getUser();
        for(SecurityRole role:user.getSroles()){
            userRoles.add(securityUserRoleService.findById(role.getRoleId()));
        }

        if (!userRoles.isEmpty()) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for (SecurityRole userRole : userRoles) {
                // 基于Permission的权限信息
                Set<String> permissionList = new HashSet<String>();
                for(SecurityPermission permission:userRole.getRights()){
                    permissionList.add(permission.getRightSign()+":"+permission.getRightName());
//                    info.addObjectPermission(permission);
                }
                info.addStringPermissions(permissionList);
            }
            return info;
        } else {
            return null;
        }

    }


    public static class HashPassword {
        public String salt;
        public String password;
    }

    public HashPassword encrypt(String plainText) {
        HashPassword result = new HashPassword();
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        result.salt = Encodes.encodeHex(salt);

        byte[] hashPassword = Digests.sha1(plainText.getBytes(), salt,
                INTERATIONS);
        result.password = Encodes.encodeHex(hashPassword);
        return result;

    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principal, this.getName());
        this.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = this.getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    public static class ShiroUser implements Serializable {

        private static final long serialVersionUID = -1748602382963711884L;


        private String id;
        private String loginName;
        private SecurityUser user;

        public ShiroUser() {

        }

        public ShiroUser(String id, String loginName, SecurityUser user) {
            this.id = id;
            this.loginName = loginName;
            this.user = user;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public SecurityUser getUser() {
            return user;
        }

        public void setUser(SecurityUser user) {
            this.user = user;
        }
    }


    public SecurityUserRoleService getSecurityUserRoleService() {
        return securityUserRoleService;
    }


    //getters and setters

    public void setSecurityUserRoleService(SecurityUserRoleService securityUserRoleService) {
        this.securityUserRoleService = securityUserRoleService;
    }

    public SecurityUserService getSecurityUserService() {
        return securityUserService;
    }

    public void setSecurityUserService(SecurityUserService securityUserService) {
        this.securityUserService = securityUserService;
    }
}
