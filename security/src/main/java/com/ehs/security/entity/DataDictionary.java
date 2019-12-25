/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年6月26日 上午8:43:14 
 */
package com.ehs.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DataDictionary.java
* @Description: 数据字典实体类
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月26日 上午8:43:14 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月26日      chentm          v1.0.0               修改原因
*/
@Entity
@Table(name="DATA_DICTIONARY")
public class DataDictionary extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	public static final String DATA_CODE="dataCode";
	public static final String PARENT_CODE="parentCode";
	public static final String TEXT="text";
	public static final String SORT="sort";
	public static final String SYSTEM_CODE="systemCode";
	public static final String RELATED_DEPART="relatedDepart";
	public static final String RELATED_DEPART_NAMES="relatedDepartNames";

	/**
	 * 父菜单
	 */
	private String parentCode;
	
	/**
	 * 名称
	 */
	private String text;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 系统标识
	 */
	private String systemCode;
	
	/**
	 * 关联部门
	 */
	private String relatedDepart; 
	
	/**
	 * 关联部门名称
	 */
	private String relatedDepartNames; 
	
    /**
     * 数据字典编码
     */
	@Column(nullable = false)
	private String dataCode;



	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	/**
	 * @return the systemCode
	 */
	public String getSystemCode() {
		return systemCode;
	}

	/**
	 * @param systemCode the systemCode to set
	 */
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * @return the relatedDepartment
	 */
	public String getRelatedDepart() {
		return relatedDepart;
	}

	/**
	 * @param relatedDepartment the relatedDepartment to set
	 */
	public void setRelatedDepart(String relatedDepart) {
		this.relatedDepart = relatedDepart;
	}

	/**
	 * @return the relatedDepartNames
	 */
	public String getRelatedDepartNames() {
		return relatedDepartNames;
	}

	/**
	 * @param relatedDepartNames the relatedDepartNames to set
	 */
	public void setRelatedDepartNames(String relatedDepartNames) {
		this.relatedDepartNames = relatedDepartNames;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}


	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	private static List<String> foreignClassesList=null;

	/** 
	* @see com.ehs.security.entity.BaseEntity#getForeignClasses()  
	* @Function: DataDictionary.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月26日 上午8:44:09 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月26日      chentm           v1.0.0               修改原因
	*/
	@Override
	public List<String> getForeignClasses() {
		return foreignClassesList;
	}
	static {
		foreignClassesList=new ArrayList<String>();
		foreignClassesList.add(CODE+","+DataDictionary.class.getName()+","+DataDictionary.PARENT_CODE);
	}

}
