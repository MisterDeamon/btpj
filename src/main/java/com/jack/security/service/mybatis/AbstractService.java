package com.jack.security.service.mybatis;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jack.security.persistence.BaseMapper;
import com.jack.security.pojo.BaseEntity;
import com.jack.security.shiro.ShiroDbRealm;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractService<T extends BaseEntity,PK extends Serializable, Mapper extends BaseMapper<T,Serializable>> {
	
	private Mapper mapper;
	
	public int save(T t){
		return mapper.save(t);
	};

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public T findById(PK pk){
		return mapper.findById(pk);}

	public void remove(PK pk) {
		mapper.delete(pk);
	}

	public int update(T t) {
		return mapper.update(t);
	}

	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> findAll() {
		return mapper.findAll();
	}

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	public void preUpdate(T t,ShiroDbRealm.ShiroUser shiroUser){
		t.setUpdatedDate(new Date());
		t.setUpdatedBy(shiroUser.getLoginName());
	}

	public void preCreate(T t,ShiroDbRealm.ShiroUser shiroUser){
		t.setCreatedDate(new Date());
		t.setCreatedBy(shiroUser.getLoginName());
	}

	public void preDelete(T t,ShiroDbRealm.ShiroUser shiroUser){
		t.setDeletedBy(shiroUser.getLoginName());
		t.setDeletedDate(new Date());
	}

}
