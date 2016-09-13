package com.jack.security.persistence;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jack.security.pojo.SecurityUser;
import com.jack.utils.Page;

@Repository
public interface SecurityUserMapper extends BaseMapper<SecurityUser,Serializable>{

	public List<SecurityUser> findUserPage(@Param("pojo")SecurityUser user,@Param("page")Page<SecurityUser> page);
	
	public int findUserCount(@Param("pojo")SecurityUser user,@Param("page")Page<SecurityUser> page);
	
	public void changeLoginState(@Param("state")int state,@Param("id") String id);

	public void lockAccount(@Param("id")String id);

	public void relockAccount(@Param("id")String id);
	
}
