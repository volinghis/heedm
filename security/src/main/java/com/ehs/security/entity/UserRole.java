package com.ehs.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserRole.java
* @Description: 用户角色关联视图
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年6月20日 上午9:32:48 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月20日     qjj           v1.0.0               修改原因
*/
@Entity
@Table(name="VIEW_USER_ROLE")
public class UserRole implements Serializable{
	
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	public static final String ACCOUNT="account";
	public static final String ROLE_CODE="roleCode";
	public static final String ROLE_NAME="roleName";
	public static final String ROLE_REMARK="roleRemark";
	
	@Id
	@Column(name="account")
	private String account;
	@Id
	@Column(name="role_code")
	private String roleCode;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="role_remark")
	private String roleRemark;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

}
