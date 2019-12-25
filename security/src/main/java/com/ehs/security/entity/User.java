/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年6月10日 上午10:53:54 
 */
package com.ehs.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: User.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月10日 上午10:53:54 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月10日      chentm          v1.0.0               修改原因
*/
@Entity
@Table(name="VIEW_USER")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String CODE="code";
	public static final String NAME="name";
	public static final String ACCOUNT="account";
	public static final String STATE="state";
	public static final String ORG_CODE="orgCode";
	public static final String ORG_NAME="orgName";
	public static final String ORG_DATA_CODE="orgDataCode";
	public static final String USER_DATA_CODE="userDataCode";
	public static final String SYS_USER_CODE="sysUserCode";
	public static final String POSITION="position";
	
	
	private String orgDataCode;
	private String userDataCode;
	private String sysUserCode;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Id
	@Column(name = "account")
	private String account;
	
	@Column(name = "state")
	private Integer state;

	@Column(name="org_code")
	private String orgCode;
	
	@Column(name="org_name")
	private String orgName;
	
	@Column(name="position")
	private String position;
	
	
	public String getOrgDataCode() {
		return orgDataCode;
	}

	public void setOrgDataCode(String orgDataCode) {
		this.orgDataCode = orgDataCode;
	}

	public String getUserDataCode() {
		return userDataCode;
	}

	public void setUserDataCode(String userDataCode) {
		this.userDataCode = userDataCode;
	}

	public String getSysUserCode() {
		return sysUserCode;
	}

	public void setSysUserCode(String sysUserCode) {
		this.sysUserCode = sysUserCode;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
