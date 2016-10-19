package com.jack.security.persistence;

import java.io.Serializable;
import java.util.List;

import com.jack.utils.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jack.security.pojo.SecurityUser;

@Repository
public interface SecurityUserMapper extends BaseMapper<SecurityUser,Serializable> {

	public List<SecurityUser> findUserPage(@Param("pojo")SecurityUser user,@Param("pager")Pager<SecurityUser> page);
	
	public int findUserCount(@Param("pojo")SecurityUser user,@Param("pager")Pager<SecurityUser> page);
	
	public void changeLoginState(@Param("state")int state,@Param("id") String id);

	public void lockAccount(@Param("id")String id);

	public void relockAccount(@Param("id")String id);

	public void setUserRoles(@Param("userId")String userId,@Param("roleId")String roleId);

	public void cancleRole(@Param("userId")String userId,@Param("roleId")String roleId);

}
