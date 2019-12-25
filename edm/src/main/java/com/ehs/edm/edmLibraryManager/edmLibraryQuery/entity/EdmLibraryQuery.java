/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmLibraryManager.edmLibraryQuery.entity 
 * @author: chentm   
 * @date: 2019年7月4日 下午5:27:02 
 */
package com.ehs.edm.edmLibraryManager.edmLibraryQuery.entity;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmLibraryQuery.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月4日 下午5:27:02 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月4日      chentm          v1.0.0               修改原因
*/
@Entity
@Table(name="view_edm_library_query")
public class EdmLibraryQuery {

	public static final String ID = "id" ;
	public static final String DEVICE_NUM = "deviceNum" ;
	public static final String DEVICE_NAME = "deviceName" ;
	public static final String KSS_NUM = "kssNum" ;
	public static final String INSTALL_ADDRESS_CODE = "installAddressCode" ;
	public static final String INSTALL_ADDRESS_NAME = "installAddressName" ;
	public static final String INSTALL_ADDRESS_FULL_NAME = "installAddressFullName" ;
	public static final String STATUS_CODE = "statusCode" ;
	public static final String STATUS_NAME = "statusName" ;
	public static final String DUTY_PERSON_CODE = "dutyPersonCode" ;
	public static final String DUTY_PERSON_NAME = "dutyPersonName" ;
	public static final String DUTY_ORG_CODE = "dutyOrgCode" ;
	public static final String DUTY_ORG_NAME = "dutyOrgName" ;
	public static final String REF_EDM_LIBRARY = "refEdmLibrary" ;
	public static final String INSTALL_TIME = "installTime" ;
	public static final String KEEP_REPAIR_TIME = "keepRepairTime" ;
	public static final String DEPRECATE_TIME = "deprecateTime" ;
	public static final String CHECK_RATE = "checkRate" ;
	public static final String PRICE = "price" ;
	public static final String BRAND = "brand" ;
	public static final String DEVICE_TYPE = "deviceType" ;
	public static final String DEVICE_TYPE_NAME = "deviceTypeName" ;
	public static final String DEVICE_MODEL = "deviceModel" ;
	public static final String PRODUCT_FACTORY_NUMBER = "productFactoryNumber" ;
	public static final String PRODUCT_FACTORY_NAME = "productFactoryName" ;
	public static final String SUPPLY_FACTORY_NUMBER = "supplyFactoryNumber" ;
	public static final String SUPPLY_FACTORY_NAME = "supplyFactoryName" ;
	
	
	
	@Id
	private String id;
	
	/**
	 * 设备编码
	 */
	private String deviceNum;
	
	/**
	 * 设备名称
	 */
	private String deviceName;
	/**
	 *KSS编码
	 */
	private String kssNum;
	
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
	/**
	 * 设备状态
	 */
	private String statusCode;
	/**
	 * 设备状态
	 */
	private String statusName;
	
	/**
	 * 责任人
	 */
	private String dutyPersonCode;
	
	/**
	 * 责任人姓名
	 */
	private String dutyPersonName;
	
	/**
	 * 责任部门
	 */
	private String dutyOrgCode;
	
	/**
	 * 责任部门
	 */
	private String dutyOrgName;
	
	/**
	 * 所属设备
	 */
	public String refEdmLibrary;
	/**
	 * 安装日期
	 */
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Timestamp installTime;
	
	
	
	/**
	 * 保修期限(月)
	 */
	private Integer keepRepairTime;
	
	/**
	 * 报废期限(月)
	 */
	private Integer  deprecateTime;
	
	/**
	 * 检修频率(月)
	 */
	private Integer checkRate;
	
	/**
	 * 产品价格(万元)
	 */
	private BigDecimal price;
	/**
	 * 品牌
	 */
	private String brand;
	
	

	/**
	 * 设备类型
	 */
	private String deviceType;
	/**
	 * 设备类型名称
	 */
	private String deviceTypeName;
	/**
	 * 设备型号
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
	 * 供应商编码
	 */
	private String supplyFactoryNumber;
	/**
	 * 供应商名称
	 */
	private String supplyFactoryName;
	/**
	 * @return the deviceNum
	 */
	public String getDeviceNum() {
		return deviceNum;
	}
	/**
	 * @param deviceNum the deviceNum to set
	 */
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * @return the kssNum
	 */
	public String getKssNum() {
		return kssNum;
	}
	/**
	 * @param kssNum the kssNum to set
	 */
	public void setKssNum(String kssNum) {
		this.kssNum = kssNum;
	}
	/**
	 * @return the installAddressCode
	 */
	public String getInstallAddressCode() {
		return installAddressCode;
	}
	/**
	 * @param installAddressCode the installAddressCode to set
	 */
	public void setInstallAddressCode(String installAddressCode) {
		this.installAddressCode = installAddressCode;
	}
	/**
	 * @return the installAddressName
	 */
	public String getInstallAddressName() {
		return installAddressName;
	}
	/**
	 * @param installAddressName the installAddressName to set
	 */
	public void setInstallAddressName(String installAddressName) {
		this.installAddressName = installAddressName;
	}
	/**
	 * @return the installAddressFullName
	 */
	public String getInstallAddressFullName() {
		return installAddressFullName;
	}
	/**
	 * @param installAddressFullName the installAddressFullName to set
	 */
	public void setInstallAddressFullName(String installAddressFullName) {
		this.installAddressFullName = installAddressFullName;
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * @return the dutyPersonCode
	 */
	public String getDutyPersonCode() {
		return dutyPersonCode;
	}
	/**
	 * @param dutyPersonCode the dutyPersonCode to set
	 */
	public void setDutyPersonCode(String dutyPersonCode) {
		this.dutyPersonCode = dutyPersonCode;
	}
	/**
	 * @return the dutyPersonName
	 */
	public String getDutyPersonName() {
		return dutyPersonName;
	}
	/**
	 * @param dutyPersonName the dutyPersonName to set
	 */
	public void setDutyPersonName(String dutyPersonName) {
		this.dutyPersonName = dutyPersonName;
	}
	/**
	 * @return the dutyOrgCode
	 */
	public String getDutyOrgCode() {
		return dutyOrgCode;
	}
	/**
	 * @param dutyOrgCode the dutyOrgCode to set
	 */
	public void setDutyOrgCode(String dutyOrgCode) {
		this.dutyOrgCode = dutyOrgCode;
	}
	/**
	 * @return the dutyOrgName
	 */
	public String getDutyOrgName() {
		return dutyOrgName;
	}
	/**
	 * @param dutyOrgName the dutyOrgName to set
	 */
	public void setDutyOrgName(String dutyOrgName) {
		this.dutyOrgName = dutyOrgName;
	}
	/**
	 * @return the refEdmLibrary
	 */
	public String getRefEdmLibrary() {
		return refEdmLibrary;
	}
	/**
	 * @param refEdmLibrary the refEdmLibrary to set
	 */
	public void setRefEdmLibrary(String refEdmLibrary) {
		this.refEdmLibrary = refEdmLibrary;
	}
	/**
	 * @return the installTime
	 */
	public Timestamp getInstallTime() {
		return installTime;
	}
	/**
	 * @param installTime the installTime to set
	 */
	public void setInstallTime(Timestamp installTime) {
		this.installTime = installTime;
	}
	/**
	 * @return the keepRepairTime
	 */
	public Integer getKeepRepairTime() {
		return keepRepairTime;
	}
	/**
	 * @param keepRepairTime the keepRepairTime to set
	 */
	public void setKeepRepairTime(Integer keepRepairTime) {
		this.keepRepairTime = keepRepairTime;
	}
	/**
	 * @return the deprecateTime
	 */
	public Integer getDeprecateTime() {
		return deprecateTime;
	}
	/**
	 * @param deprecateTime the deprecateTime to set
	 */
	public void setDeprecateTime(Integer deprecateTime) {
		this.deprecateTime = deprecateTime;
	}
	/**
	 * @return the checkRate
	 */
	public Integer getCheckRate() {
		return checkRate;
	}
	/**
	 * @param checkRate the checkRate to set
	 */
	public void setCheckRate(Integer checkRate) {
		this.checkRate = checkRate;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}
	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * @return the deviceTypeName
	 */
	public String getDeviceTypeName() {
		return deviceTypeName;
	}
	/**
	 * @param deviceTypeName the deviceTypeName to set
	 */
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}
	/**
	 * @return the deviceModel
	 */
	public String getDeviceModel() {
		return deviceModel;
	}
	/**
	 * @param deviceModel the deviceModel to set
	 */
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	/**
	 * @return the productFactoryNumber
	 */
	public String getProductFactoryNumber() {
		return productFactoryNumber;
	}
	/**
	 * @param productFactoryNumber the productFactoryNumber to set
	 */
	public void setProductFactoryNumber(String productFactoryNumber) {
		this.productFactoryNumber = productFactoryNumber;
	}
	/**
	 * @return the productFactoryName
	 */
	public String getProductFactoryName() {
		return productFactoryName;
	}
	/**
	 * @param productFactoryName the productFactoryName to set
	 */
	public void setProductFactoryName(String productFactoryName) {
		this.productFactoryName = productFactoryName;
	}
	/**
	 * @return the supplyFactoryNumber
	 */
	public String getSupplyFactoryNumber() {
		return supplyFactoryNumber;
	}
	/**
	 * @param supplyFactoryNumber the supplyFactoryNumber to set
	 */
	public void setSupplyFactoryNumber(String supplyFactoryNumber) {
		this.supplyFactoryNumber = supplyFactoryNumber;
	}
	/**
	 * @return the supplyFactoryName
	 */
	public String getSupplyFactoryName() {
		return supplyFactoryName;
	}
	/**
	 * @param supplyFactoryName the supplyFactoryName to set
	 */
	public void setSupplyFactoryName(String supplyFactoryName) {
		this.supplyFactoryName = supplyFactoryName;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	


	
}
