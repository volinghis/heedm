/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.flow.bean 
 * @author: chentm   
 * @date: 2019年7月30日 上午10:34:35 
 */
package com.ehs.base.platform.flow.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: TaskBean.java
* @Description: 个人事项实体类
*
* @version: v1.0.0
* @author: chentm
* @author: qjj 添加注释
* @date: 2019年7月30日 上午10:34:35 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月30日      chentm          v1.0.0               修改原因
*/
public class TaskBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	
	private String owner;

	private String formUrl;
	
	private String ownerName;

	private String taskId;
	
	
	private String operMethod;
	
	private String processInstanceId;
	
	
	private String entityCode;
	
	
	
	
	/**
	 * @return the entityCode
	 */
	public String getEntityCode() {
		return entityCode;
	}

	/**
	 * @param entityCode the entityCode to set
	 */
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	/**
	 * @return the processInstanceId
	 */
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	/**
	 * @param processInstanceId the processInstanceId to set
	 */
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getOperMethod() {
		return operMethod;
	}

	public void setOperMethod(String operMethod) {
		this.operMethod = operMethod;
	}

	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Date createDate;
	
	
	

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the formUrl
	 */
	public String getFormUrl() {
		return formUrl;
	}

	/**
	 * @param formUrl the formUrl to set
	 */
	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}

	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	
	
	
}
