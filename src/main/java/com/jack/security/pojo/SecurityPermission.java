package com.jack.security.pojo;

import org.apache.shiro.authz.Permission;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class SecurityPermission extends BaseEntity implements Serializable,Permission {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String rightId;
	private String rightName;
	private String rightUrl;
	private String rightIcon;
	private String rightSign;



	public SecurityPermission() {
		super();
	}

	public String getRightId() {
		return rightId;
	}

	public void setRightId(String rightId) {
		this.rightId = rightId;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}


	public String getRightUrl() {
		return rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}


	public String getRightIcon() {
		return rightIcon;
	}

	public void setRightIcon(String rightIcon) {
		this.rightIcon = rightIcon;
	}

	public String getRightSign() {
		return rightSign;
	}

	public void setRightSign(String rightSign) {
		this.rightSign = rightSign;
	}

	@Override
	public String toString() {
		return "SecurityPermission{" +
				"rightId=" + rightId +
				", rightName='" + rightName + '\'' +
				", rightUrl='" + rightUrl + '\'' +
				'}';
	}

	public boolean implies(Permission p) {
		return false;
	}
}
