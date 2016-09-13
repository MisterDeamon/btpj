package com.jack.security.service;

import java.util.List;

import com.jack.security.shiro.ShiroDbRealm;
import org.apache.commons.lang3.StringUtils;
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

	@Override
	public int save(SecurityUser user){

// 设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		if (StringUtils.isNotBlank(user.getPlainPasswd()) && (this.shiroRealm != null)) {
			ShiroDbRealm.HashPassword hashPassword = this.shiroRealm.encrypt(user.getPlainPasswd());
			user.setSalt(hashPassword.salt);
			user.setPlainPasswd(hashPassword.password);
		}
		return this.getMapper().save(user);
	}


}
