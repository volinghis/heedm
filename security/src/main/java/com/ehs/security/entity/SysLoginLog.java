package com.ehs.security.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.ehs.security.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: LoginLogEntity.java
* @Description: 登录日志实体
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年6月18日 下午4:24:42 
*
* Modification History:
* Date          Author          Version            Description
*---------------------------------------------------------*
* 2019年6月18日    qjj           v1.0.0               修改原因
*/
@Entity
@Table(name="SYS_LOGIN_LOG")
public class SysLoginLog extends BaseEntity  {
	
	private static final long serialVersionUID = 1L;
	
	public static final String CODE="name";
	public static final String ACCOUNT="account";
	public static final String TIME="time";
	public static final String IP="ip";
	
	/**
	 * 	用户名
	 */
	private String name;
	
	/**
	 * 	登录账号
	 */
	private String account ;
	
	/**
	 * 	登录时间
	 */
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Timestamp time;
	
	/**
	 * 	ip地址
	 */
	private String ip;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public List<String> getForeignClasses() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
