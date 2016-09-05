package com.jack.security.service.mybatis;

import java.io.Serializable;
import java.util.List;

import com.jack.security.persistence.BaseMapper;

public abstract class AbstractService<T,PK extends Serializable, Mapper extends BaseMapper<T,Serializable>> {
	
	private Mapper mapper;
	
	public int save(T t){
		return mapper.save(t);
	};
	
	public T findById(PK pk){
		return mapper.findById(pk);};

	public void remove(PK pk) {
		mapper.delete(pk);
	}

	public void update(T t) {
		mapper.update(t);
	}

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
