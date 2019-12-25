/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmDataLibrary.EquipmentDatabase.entity 
 * @author: qjj   
 * @date: 2019年9月24日 下午5:59:46 
 */
package com.ehs.edm.edmDataLibrary.EquipmentDatabase.entity;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.ehs.security.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EquipmentDatabase.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年9月24日 下午5:59:46 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月24日     qjj           v1.0.0               修改原因
*/
@Entity
@Table(name ="EDM_EQUIPMENT_DATABASE")
public class EquipmentDatabase  extends BaseEntity{

	public static final String DEV_CODE = "devCode" ;
	public static final String DEV_NAME = "devName" ;
	public static final String CATEGORY = "category" ;
	public static final String NODE_CODE = "nodeCode" ;

	
	
	
	/**
	 * 设备code
	 */
	private String devCode;
	
	/**
	 * 设备名称
	 */
	private String devName;
	/**
	 * 资料类别
	 */
	private String category;
	
	
	private String nodeCode;
	
	

	/**
	 * @return the devCode
	 */
	public String getDevCode() {
		return devCode;
	}

	/**
	 * @param devCode the devCode to set
	 */
	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	

	/**
	 * @return the devName
	 */
	public String getDevName() {
		return devName;
	}

	/**
	 * @param devName the devName to set
	 */
	public void setDevName(String devName) {
		this.devName = devName;
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
	*/
	@Override
	public List<String> getForeignClasses() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
