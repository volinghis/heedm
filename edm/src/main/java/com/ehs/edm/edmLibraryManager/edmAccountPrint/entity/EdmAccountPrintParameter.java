package com.ehs.edm.edmLibraryManager.edmAccountPrint.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.security.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmAccountPrintParameter.java
* @Description: 设备台账的设备参数实体
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年7月19日 上午10:40:37 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月19日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "EDM_ACCOUNT_PRINT_PARAMETER")
public class EdmAccountPrintParameter extends BaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	public static final String A_PARAMETER_CODE = "parameterCode";
	public static final String A_PARAMETER_NAME = "parameterName";
	public static final String A_PARAMETER_VALUE = "parameterValue";
	
	private String parameterCode;
	
	private String parameterName;
	
	private String parameterValue;
	
	public String getParameterCode() {
		return parameterCode;
	}

	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	@Override
	public List<String> getForeignClasses() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
