/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmLibraryManager.edmAccountPrint.entity 
 * @author: chentm   
 * @date: 2019年7月3日 上午9:55:51 
 */
package com.ehs.edm.edmLibraryManager.edmAccountPrint.entity;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.ehs.security.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmAccountPrint.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月3日 上午9:55:51 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月3日      chentm          v1.0.0               修改原因
*/
@Entity
@Table(name="EDM_ACCOUNT_PRINT")
public class EdmAccountPrint extends BaseEntity{

//	public static final String DEVICE_ACCOUNT_CODE = "deviceAccountCode" ;
//	public static final String DEVICE_ACCOUNT_NAME = "deviceAccountName" ;
	public static final String DEVICE_NUM = "deviceNum" ;
	public static final String DEVICE_NAME="deviceName";
	public static final String DEVICE_MODEL="deviceModel";
	public static final String PRODUCT_FACTORY_NUMBER="productFactoryNumber";
	public static final String PRODUCT_FACTORY_NAME="productFactoryName";
	public static final String LEAVE_FACTORY_CODE="leaveFactoryCode";
	public static final String LEAVE_FACTORY_TIME="leaveFactoryTime";
	public static final String RUN_TIME="runTime";
	public static final String ASSOCIATION_DEFECT="associationDefect";
	public static final String CHECKRREPAIR_TEAM_CODE="checkRrepairTeamCode";
	public static final String CHECKRREPAIR_TEAM_NAME="checkRrepairTeamName";
	public static final String FOUNDER="founder";
	public static final String CHECK_RREPAIR_NORM="checkRrepairNorm";
	public static final String REGULAR_WORK_NORM="regularWorkNorm";
	public static final String PAST_EQUIP_INSPECTORS="pastEquipInspectors";
	public static final String STATUS_CODE="statusCode";
	public static final String STATUS_NAME="statusName";
	public static final String INSTALL_ADDRESS_CODE = "installAddressCode" ;
	public static final String INSTALL_ADDRESS_NAME = "installAddressName" ;
	public static final String INSTALL_ADDRESS_FULL_NAME = "installAddressFullName" ;
	
//	public static final String KSS_NUM = "kssNum" ;
//	public static final String INSTALL_ADDRESS_CODE = "installAddressCode" ;
//	public static final String INSTALL_ADDRESS_NAME = "installAddressName" ;
//	public static final String INSTALL_ADDRESS_FULL_NAME = "installAddressFullName" ;
//	public static final String STATUS_CODE = "statusCode" ;
//	public static final String STATUS_NAME = "statusName" ;
//	public static final String DUTY_PERSON_CODE = "dutyPersonCode" ;
//	public static final String DUTY_PERSON_NAME = "dutyPersonName" ;
//	public static final String DUTY_ORG_CODE = "dutyOrgCode" ;
//	public static final String DUTY_ORG_NAME = "dutyOrgName" ;
//	public static final String REF_EDM_LIBRARY="refEdmLibrary";
	
	private static final long serialVersionUID = 1L;

	/**
	 * 设备编码
	 */
	private String deviceNum;
	
	/**
	 * 设备名称
	 */
	private String deviceName;
	
	/**
	 * 规格型号
	 */
	private String deviceModel;
	
	/**
	 * 生产商编码
	 */
	private String productFactoryNumber;
	
	/**
	 * 生产商名称
	 */
	private String productFactoryName;
	
	/**
	 * 出厂编号
	 */
	private String leaveFactoryCode;
	
	/**
	 * 出厂日期
	 */
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Timestamp leaveFactoryTime;
	
	/**
	 * 投运日期
	 */
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Timestamp runTime;
	
	/**
	 * 关联缺陷
	 */
	private String associationDefect;
	
	/**
	 * 检修班组code
	 */
	private String checkRrepairTeamCode;
	
	/**
	 * 检修班组名称
	 */
	private String checkRrepairTeamName;
	
	/**
	 * 创建人
	 */
	private String founder;
	
	/**
	 * 检修质量标准
	 */
	@Column(length = 3000)
	private String checkRrepairNorm;
	
	/**
	 * 定期工作标准
	 */
	@Column(length = 3000)
	private String regularWorkNorm;
	
	/**
	 * 历任设备点检员（专责人）
	 */
	@Column(length = 3000)
	private String pastEquipInspectors;
	
	/**
	 * 设备状态
	 */
	private String statusCode;
	
	/**
	 * 设备状态名称
	 */
	@Transient
	private String statusName;
	
	/**
	 * 安装位置编码
	 */
	private String installAddressCode;
	
	/**
	 * 安装位置名称
	 */
	private String installAddressName;
	
	/**
	 * 安装位置全路径名
	 */
	private String installAddressFullName;
	
//	/**
//	 *KSS编码
//	 */
//	private String kssNum;
//	
//	
//	/**
//	 * 责任人
//	 */
//	private String dutyPersonCode;
//	
//	/**
//	 * 责任人姓名
//	 */
//	private String dutyPersonName;
//	
//	/**
//	 * 责任部门
//	 */
//	private String dutyOrgCode;
//	
//	/**
//	 * 责任部门
//	 */
//	private String dutyOrgName;
//	
//	/**
//	 * 所属设备
//	 */
//	public String refEdmLibrary;
//	/**
//	 * 安装日期
//	 */
//	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
//	private Timestamp installTime;
	
	/** 
	* @see com.ehs.security.entity.BaseEntity#getForeignClasses()  
	* @Function: EdmAccountPrint.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月3日 上午9:57:06 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月3日      chentm           v1.0.0               修改原因
	*/
	@Override
	public List<String> getForeignClasses() {
		return null;
	}
	
	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getProductFactoryNumber() {
		return productFactoryNumber;
	}

	public void setProductFactoryNumber(String productFactoryNumber) {
		this.productFactoryNumber = productFactoryNumber;
	}

	public String getProductFactoryName() {
		return productFactoryName;
	}

	public void setProductFactoryName(String productFactoryName) {
		this.productFactoryName = productFactoryName;
	}

	public String getLeaveFactoryCode() {
		return leaveFactoryCode;
	}

	public void setLeaveFactoryCode(String leaveFactoryCode) {
		this.leaveFactoryCode = leaveFactoryCode;
	}

	public Timestamp getLeaveFactoryTime() {
		return leaveFactoryTime;
	}

	public void setLeaveFactoryTime(Timestamp leaveFactoryTime) {
		this.leaveFactoryTime = leaveFactoryTime;
	}

	public Timestamp getRunTime() {
		return runTime;
	}

	public void setRunTime(Timestamp runTime) {
		this.runTime = runTime;
	}

	public String getAssociationDefect() {
		return associationDefect;
	}

	public void setAssociationDefect(String associationDefect) {
		this.associationDefect = associationDefect;
	}

	public String getCheckRrepairTeamCode() {
		return checkRrepairTeamCode;
	}

	public void setCheckRrepairTeamCode(String checkRrepairTeamCode) {
		this.checkRrepairTeamCode = checkRrepairTeamCode;
	}

	public String getCheckRrepairTeamName() {
		return checkRrepairTeamName;
	}

	public void setCheckRrepairTeamName(String checkRrepairTeamName) {
		this.checkRrepairTeamName = checkRrepairTeamName;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	public String getCheckRrepairNorm() {
		return checkRrepairNorm;
	}

	public void setCheckRrepairNorm(String checkRrepairNorm) {
		this.checkRrepairNorm = checkRrepairNorm;
	}

	public String getRegularWorkNorm() {
		return regularWorkNorm;
	}

	public void setRegularWorkNorm(String regularWorkNorm) {
		this.regularWorkNorm = regularWorkNorm;
	}

	public String getPastEquipInspectors() {
		return pastEquipInspectors;
	}

	public void setPastEquipInspectors(String pastEquipInspectors) {
		this.pastEquipInspectors = pastEquipInspectors;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getInstallAddressCode() {
		return installAddressCode;
	}

	public void setInstallAddressCode(String installAddressCode) {
		this.installAddressCode = installAddressCode;
	}

	public String getInstallAddressName() {
		return installAddressName;
	}

	public void setInstallAddressName(String installAddressName) {
		this.installAddressName = installAddressName;
	}

	public String getInstallAddressFullName() {
		return installAddressFullName;
	}

	public void setInstallAddressFullName(String installAddressFullName) {
		this.installAddressFullName = installAddressFullName;
	}
	
}
