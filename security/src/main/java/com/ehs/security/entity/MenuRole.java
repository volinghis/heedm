package com.ehs.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: MenuRole.java
* @Description: 菜单角色实体视图
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年10月10日 下午3:02:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年10月10日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "VIEW_MENU_ROLE")
public class MenuRole implements Serializable {

private static final long serialVersionUID = 1L;
	
	public static final String CODE="code";
	public static final String NAME="name";
	public static final String SORT="sort";
	public static final String PARENT_CODE="parentCode";
	public static final String URL="url";
	public static final String ROLE_CODE="roleCode";
	public static final String ROLE_NAME="roleName";
	public static final String ROLE_REMARK="roleRemark";
	
	/**
	 * 编码
	 */
	@Id
	@Column(name = "CODE")
	private String code;
	/**
	 * 名称
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * 排序
	 */
	@Column(name = "SORT")
	private String sort;
	/**
	 * 父编码
	 */
	@Column(name = "PARENT_CODE")
	private String parentCode;
	/**
	 * url
	 */
	@Column(name = "URL")
	private String url;
	
	/**
	 * 角色编码
	 */
	@Id
	@Column(name = "ROLE_CODE")
	private String roleCode;
	
	/**
	 * 菜单编码
	 */
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	/**
	 * 角色描述
	 */
	@Column(name = "ROLE_REMARK")
	private String roleRemark;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
