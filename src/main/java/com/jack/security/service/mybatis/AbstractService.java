package com.jack.security.service.mybatis;

import java.io.Serializable;
import java.util.List;

import com.jack.security.persistence.BaseMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractService<T,PK extends Serializable, Mapper extends BaseMapper<T,Serializable>> {
	
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

}
