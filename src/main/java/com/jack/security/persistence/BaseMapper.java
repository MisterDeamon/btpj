package com.jack.security.persistence;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * BaseMapper
 * @author wajiangk
 *
 * @param <T>
 * @param <PK>
 */
public abstract interface BaseMapper<T,PK extends Serializable> {
	
	
	public T findById(@Param("id") PK id);
	
	public List<T> findByNanme(@Param("name") String name);
	
	public List<T> findByExemple(@Param("pojo") T exemple);
	
	public List<T> findAll();
	
	public void delete(@Param("id") PK id);
	
	public int update(@Param("pojo") T obj);
	
	public int save(@Param("pojo") T obj);
	
}
