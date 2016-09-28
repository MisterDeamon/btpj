package com.jack.security.pojo;

import java.io.Serializable;
import java.util.List;

public class SecurityUser extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String userName;
	private String plainPasswd;
	private String salt;
	private String phone;
	private String idCard;
	private String email;
	private String tencentNo;
	private Integer isDeleted;
	private Integer loginState;
	private String headPicPath;
	private Integer userStatus;
	
	private List<SecurityRole> sroles;

	public SecurityUser() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlainPasswd() {
		return plainPasswd;
	}

	public void setPlainPasswd(String plainPasswd) {
		this.plainPasswd = plainPasswd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTencentNo() {
		return tencentNo;
	}

	public void setTencentNo(String tencentNo) {
		this.tencentNo = tencentNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getLoginState() {
		return loginState;
	}

	public void setLoginState(Integer loginState) {
		this.loginState = loginState;
	}

	public List<SecurityRole> getSroles() {
		return sroles;
	}

	public void setSroles(List<SecurityRole> sroles) {
		this.sroles = sroles;
	}

	public String getHeadPicPath() {
		return headPicPath;
	}

	public void setHeadPicPath(String headPicPath) {
		this.headPicPath = headPicPath;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

}
