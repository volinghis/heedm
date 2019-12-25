/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年6月26日 下午4:22:40 
 */
package com.ehs.security.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FileInfo.java
* @Description: 文件实体类
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月26日 下午4:22:40 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月26日      chentm          v1.0.0               修改原因
*/
@Entity
@Table(name="FILE_INFO")
public class FileInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	public static final String NAME="name";
	public static final String TYPE="type";
	public static final String SIZE="size";
	public static final String ENTITY_DATA_CODE="entityDataCode";
	public static final String ENTITY_NAME="entityName";
	public static final String FILE_ID="fileId";
	public static final String CATEGORY="category";
	public static final String NODE_CODE="nodeCode";
	public static final String SYS_CODE="sysCode";
	
	/**
	 * 文件名称
	 */
	private String name;
	
	/**
	 * 文件类型
	 */
	private String type;
	
	/**
	 * 文件大小
	 */
	private String fileSize;
	
	/**
	 * 关联实体dateCode
	 */
	private String entityDataCode;
	
	/**
	 * 文件Id
	 */
	private String fileId;
	
	/**
	 * 关联实体的名称
	 */
	private String entityName;
	/**
	 * 文件类别(操作手册，质保卡，说明书。。。)
	 */
	private String category;
	
	/**
	 * 所属资料（设备资料，规章制度）
	 */
	private String sysCode;
	
	/**
	 * 专业或设备系统
	 */
	private String nodeCode;
	
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the entityDataCode
	 */
	public String getEntityDataCode() {
		return entityDataCode;
	}

	/**
	 * @param entityDataCode the entityDataCode to set
	 */
	public void setEntityDataCode(String entityDataCode) {
		this.entityDataCode = entityDataCode;
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the sysCode
	 */
	public String getSysCode() {
		return sysCode;
	}

	/**
	 * @param sysCode the sysCode to set
	 */
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	/**
	 * @return the nodeCode
	 */
	public String getNodeCode() {
		return nodeCode;
	}

	/**
	 * @param nodeCode the nodeCode to set
	 */
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	/** 
	* @see com.ehs.security.entity.BaseEntity#getForeignClasses()  
	* @Function: FileInfo.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月26日 下午4:23:47 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月26日      chentm           v1.0.0               修改原因
	*/
	@Override
	public List<String> getForeignClasses() {
		return null;
	}
	
}
