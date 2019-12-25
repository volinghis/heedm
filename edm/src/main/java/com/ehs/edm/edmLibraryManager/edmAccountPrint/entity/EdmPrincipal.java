package com.ehs.edm.edmLibraryManager.edmAccountPrint.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.ehs.security.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmPrincipal.java
* @Description: 历任点检员（专责人）
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年9月19日 上午8:55:44 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月19日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name="EDM_PRINCIPAL")
public class EdmPrincipal extends BaseEntity {
	
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	public static final String PRINCIPAL_CODE = "principalCode" ;
	public static final String PRINCIPAL = "principal" ;
	public static final String SERVE_TIME = "serveTime" ;
	public static final String LEAVE_TIME = "leaveTime" ;

	/**
	 * 和设备相关联Code
	 */
	private String principalCode;
	
	/**
	 * 专责人
	 */
	private String principal;
	
	/**
	 * 担任时间
	 */
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Timestamp serveTime;
	
	/**
	 * 离任时间
	 */
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Timestamp leaveTime;
	
	public String getPrincipalCode() {
		return principalCode;
	}

	public void setPrincipalCode(String principalCode) {
		this.principalCode = principalCode;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public Timestamp getServeTime() {
		return serveTime;
	}

	public void setServeTime(Timestamp serveTime) {
		this.serveTime = serveTime;
	}

	public Timestamp getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Timestamp leaveTime) {
		this.leaveTime = leaveTime;
	}

	@Override
	public String toString() {
		return "EdmPrincipal [principalCode=" + principalCode + ", principal=" + principal + ", serveTime=" + serveTime
				+ ", leaveTime=" + leaveTime + "]";
	}

	@Override
	public List<String> getForeignClasses() {
		// TODO Auto-generated method stub
		return null;
	}
}
