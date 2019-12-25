package com.ehs.edm.edmDataLibrary.RulesDatabase.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.ehs.security.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: Accessory.java
* @Description: 备件实体类
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年6月25日 上午11:40:56 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月25日     qjj           v1.0.0               修改原因
*/
/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: Accessory.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Mapleave
* @date: 2019年6月27日 下午3:43:48 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月27日     zhaol           v1.0.0               修改原因
*/
/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmDataLibrary.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Mapleave
* @date: 2019年7月5日 上午10:08:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月5日     Mapleave           v1.0.0               修改原因
*/
@Entity
@Table(name = "EDM_RULES_DATABASE")
public class RulesDatabase extends BaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	public static final String NAME="name";
	public static final String PARENTCODE="parentCode";
	public static final String UPLOADPERSON="uploadPerson";
	public static final String UPLOADDATE="uploadDate";
	public static final String SORT="sort";

	/**
	 * 资料名称
	 */
	private String name;
	
	/**
	 * 父编码
	 */
	private String parentCode;
	
	/**
	 * 资料大小
	 */
	private String uploadPerson;
	
	/**
	 * 上传日期
	 */
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Timestamp uploadDate;
	
	/**
	 * 排序
	 */
	private Integer sort; 
	
	@Override
	public List<String> getForeignClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Timestamp getUploadDate() {
		return uploadDate;
	}
	
	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "EdmDataLibrary [name=" + name + ", parentCode=" + parentCode + ", uploadPerson=" + uploadPerson + ", uploadDate=" + uploadDate + ", sort=" + sort + "]";
	}

}
