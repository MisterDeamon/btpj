package com.jack.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.security.persistence.SecurityUserMapper;
import com.jack.security.pojo.SecurityUser;
import com.jack.security.service.mybatis.AbstractService;


@Service
public class SecurityUserService extends AbstractService<SecurityUser,Integer,SecurityUserMapper> {
	
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
}
