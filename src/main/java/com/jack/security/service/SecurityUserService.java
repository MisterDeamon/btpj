package com.jack.security.service;

import java.util.ArrayList;
import java.util.List;

import com.jack.security.pojo.SecurityRole;
import com.jack.security.shiro.ShiroDbRealm;
import com.jack.utils.Pager;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.security.persistence.SecurityUserMapper;
import com.jack.security.pojo.SecurityUser;
import com.jack.security.service.mybatis.AbstractService;


@Service
public class SecurityUserService extends AbstractService<SecurityUser,String,SecurityUserMapper> {

	@Autowired
	private ShiroDbRealm shiroRealm;

	@Autowired
	public void setMapper(SecurityUserMapper mapper){
		super.setMapper(mapper);
	}

	public SecurityUser findByName(String userName){
		List<SecurityUser> list = this.getMapper().findByNanme(userName);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void lockAccount(String id){
		this.getMapper().lockAccount(id);
	}

	public void relockAccount(String id){
		this.getMapper().relockAccount(id);
	}

	public void changeLoginState(int state,String id){ this.getMapper().changeLoginState(state,id);}

	public int saveOrUpdate(SecurityUser user){
		if(StringUtils.isNotBlank(user.getId())){
			if(!this.findById(user.getId()).getPlainPasswd().equals(user.getPlainPasswd())){
				hashPassword(user);
			}
			return this.update(user);
		}else{
			hashPassword(user);
			return this.save(user);
		}
	}

	public List<SecurityUser> findUserPage(SecurityUser user,Pager<SecurityUser> page){
		return this.getMapper().findUserPage(user,page);
	}

	public int findUserCount(SecurityUser user,Pager<SecurityUser> page){
		return this.getMapper().findUserCount(user,page);
	}
	/**
	 * 设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
	 */
	private void hashPassword(SecurityUser user){
		if (StringUtils.isNotBlank(user.getPlainPasswd()) && (this.shiroRealm != null)) {
			ShiroDbRealm.HashPassword hashPassword = this.shiroRealm.encrypt(user.getPlainPasswd());
			user.setSalt(hashPassword.salt);
			user.setPlainPasswd(hashPassword.password);
		}
	}

	public void  setRole(String userId, String[] roleIds, List<SecurityRole> roles){
		if(roleIds == null){
			roleIds = new String[0];
		}
		List<String> list = new ArrayList<String>();
		for(SecurityRole role : roles){
			list.add(role.getRoleId());
		}
		for(String roleId : roleIds) {
			if (!list.contains(roleId)) {
				this.getMapper().setUserRoles(userId, roleId);
			}
		}
		for(SecurityRole role : roles){
			if(!contains(role.getRoleId(),roleIds)){
				this.getMapper().cancleRole(userId,role.getRoleId());
			}
		}
	}

	private boolean contains(String str, String[] strs){
		for(int i=0;i<strs.length;i++){
			if(str.equals(strs[i])){
				return true;
			}else{
				if(i!=strs.length-1){
					continue;
				}else{
					return false;
				}
			}
		}
		return false;
	}

}
