package com.jack.security.pojo;

import java.io.Serializable;
import java.util.List;

public class SecurityRole extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String roleId;
	private String roleName;
	
	private List<SecurityPermission> rights;
	private List<SecurityUser> users;
	
	public SecurityRole() {
		super();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<SecurityPermission> getRights() {
		return rights;
	}

	public void setRights(List<SecurityPermission> rights) {
		this.rights = rights;
	}

	public List<SecurityUser> getUsers() {
		return users;
	}

	public void setUsers(List<SecurityUser> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "SecurityRole [roleId=" + roleId + ", roleName=" + roleName
				+ ", rights=" + rights + ", users=" + users + "]";
	}
	
	

}
