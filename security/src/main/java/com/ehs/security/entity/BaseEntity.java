package com.ehs.security.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;
import com.ehs.security.utils.BaseUtils;
import com.ehs.security.utils.SysAccessUser;

/**
 * 
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: BaseEntity.java
 * @Description: 基本类，包含公用实体映射关系
 *
 * @version: v1.0.0
 * @author: chentm
 * @date: 2019年5月6日 上午10:52:37
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年5月6日
 *        chentm v1.0.0 无修改
 */
@MappedSuperclass
public abstract class BaseEntity  implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final String ID = "id" ;
	public static final String CODE = "code" ;
	public static final String VERSION_ID = "versionId" ;
	public static final String OWNER_CODE = "ownerCode" ;
	public static final String OWNER_NAME = "ownerName" ;
	public static final String OWNER_ORG_CODE = "ownerOrgCode" ;
	public static final String OWNER_ORG_NAME = "ownerOrgName" ;
	public static final String OWNER_CREATION_TIME = "ownerCreationTime" ;
	public static final String CREATION_CODE = "creationCode" ;
	public static final String CREATION_NAME = "creationName" ;
	public static final String CREATION_ORG_CODE = "creationOrgCode" ;
	public static final String CREATION_ORG_NAME = "creationOrgName" ;
	public static final String CREATION_TIME = "creationTime" ;
	public static final String DATA_MODEL = "dataModel" ;
	public static final String ATTRIBUTE1 = "attribute1" ;
	public static final String ATTRIBUTE2 = "attribute2" ;
	public static final String ATTRIBUTE3 = "attribute3" ;
	public static final String ATTRIBUTE4 = "attribute4" ;
	public static final String ATTRIBUTE5 = "attribute5" ;

	/**
	 * 引用的外键表，数值以,分割。[foreignKey,foreignClassName,refKey]数据模型:在SysRole实体中：code,com.ehs.security.entity.SysRoleMenu,roleCode
	 */
	public abstract List<String> getForeignClasses();

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 数据编号
	 */
	private String code;

	/**
	 * 数据版本号,0有效，1失效
	 */
	private Long versionId;

	/**
	 * 数据拥有者账号
	 */
	private String ownerCode;
	private String ownerName;
	private String ownerOrgCode;
	private String ownerOrgName;
	/**
	 * 数据拥有者创建时间
	 */
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Timestamp ownerCreationTime;

	private String creationCode;
	private String creationName;
	private String creationOrgCode;
	private String creationOrgName;
	
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Timestamp creationTime;
	
	/**
	 * CREATE,UPDATE,REMOVE
	 */
	private String dataModel;

	public String getDataModel() {
		return dataModel;
	}
	public void setDataModel(String dataModel) {
		this.dataModel = dataModel;
	}

	/**
	 * 扩展字段1
	 */
	private String attribute1;

	/**
	 * 扩展字段金2
	 */
	private String attribute2;

	/**
	 * 扩展字段3
	 */
	private String attribute3;
	/**
	 * 扩展字段4
	 */
	private String attribute4;
	/**
	 *  
	 * 扩展字段5
	 */
	private String attribute5;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		if(StringUtils.isNotBlank(this.code)&&!StringUtils.equals(code, this.code)) {
			throw new RuntimeException("数据唯一标识Code不可修改");
		}
		this.code = code;
	}
	public Long getVersionId() {
		return versionId;
	}
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}
	public String getOwnerCode() {
		return ownerCode;
	}
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}
	public Timestamp getOwnerCreationTime() {
		return ownerCreationTime;
	}
	public void setOwnerCreationTime(Timestamp ownerCreationTime) {
		this.ownerCreationTime = ownerCreationTime;
	}
	public String getCreationCode() {
		return creationCode;
	}
	public void setCreationCode(String creationCode) {
		this.creationCode = creationCode;
	}
	public Timestamp getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public String getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}
	public String getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
	public String getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	/**
	 * 
	* @Function: BaseEntity.java
	* @Description: 初始化数据
	*
	* @param 
	* @return：无
	* @throws：无
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 上午9:38:38 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日     chentm           v1.0.0               修改原因
	 */
	public void initCreate() {
		Timestamp _ts=BaseUtils.getNow();
		
		this.setCreationTime(_ts);
		this.setOwnerCreationTime(_ts);
		this.setVersionId(0l);
		if(StringUtils.isBlank(this.getCode())){
			this.setCode(UUID.randomUUID().toString());
		}
		if(SysAccessUser.get()!=null) {
			this.setCreationCode(SysAccessUser.get().getCode());
			this.setCreationName(SysAccessUser.get().getName());
			this.setCreationOrgCode(SysAccessUser.get().getOrgCode());
			this.setCreationOrgName(SysAccessUser.get().getOrgName());
			this.setOwnerCode(SysAccessUser.get().getCode());
			this.setOwnerName(SysAccessUser.get().getName());
			this.setOwnerOrgCode(SysAccessUser.get().getOrgCode());
			this.setOwnerOrgName(SysAccessUser.get().getOrgName());
		}
	}
	/**
	 * 
	* @Function: BaseEntity.java
	* @Description: 修改
	*
	* @param
	* @return：无
	* @throws：无
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 上午9:39:56 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日     chentm           v1.0.0               修改原因
	 */
	public void initUpdate() {
		this.setCreationTime(BaseUtils.getNow());
		if(SysAccessUser.get()!=null) {
			this.setCreationCode(SysAccessUser.get().getCode());
			this.setCreationName(SysAccessUser.get().getName());
			this.setCreationOrgCode(SysAccessUser.get().getOrgCode());
			this.setCreationOrgName(SysAccessUser.get().getOrgName());
		}
		this.setId(null);
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
	 * @return the creationName
	 */
	public String getCreationName() {
		return creationName;
	}
	/**
	 * @param creationName the creationName to set
	 */
	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}
	/**
	 * @return the ownerOrgCode
	 */
	public String getOwnerOrgCode() {
		return ownerOrgCode;
	}
	/**
	 * @param ownerOrgCode the ownerOrgCode to set
	 */
	public void setOwnerOrgCode(String ownerOrgCode) {
		this.ownerOrgCode = ownerOrgCode;
	}
	/**
	 * @return the ownerOrgName
	 */
	public String getOwnerOrgName() {
		return ownerOrgName;
	}
	/**
	 * @param ownerOrgName the ownerOrgName to set
	 */
	public void setOwnerOrgName(String ownerOrgName) {
		this.ownerOrgName = ownerOrgName;
	}
	/**
	 * @return the creationOrgCode
	 */
	public String getCreationOrgCode() {
		return creationOrgCode;
	}
	/**
	 * @param creationOrgCode the creationOrgCode to set
	 */
	public void setCreationOrgCode(String creationOrgCode) {
		this.creationOrgCode = creationOrgCode;
	}
	/**
	 * @return the creationOrgName
	 */
	public String getCreationOrgName() {
		return creationOrgName;
	}
	/**
	 * @param creationOrgName the creationOrgName to set
	 */
	public void setCreationOrgName(String creationOrgName) {
		this.creationOrgName = creationOrgName;
	}
	
}
