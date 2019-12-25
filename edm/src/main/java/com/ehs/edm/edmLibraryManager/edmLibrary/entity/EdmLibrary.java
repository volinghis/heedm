/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmLibraryManager.edmLibrary.entity 
 * @author: chentm   
 * @date: 2019年7月1日 上午10:50:15 
 */
package com.ehs.edm.edmLibraryManager.edmLibrary.entity;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmLibrary.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月1日 上午10:50:15 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月1日      chentm          v1.0.0               修改原因
*/
@Entity
@Table(name="EDM_LIBRARY")
public class EdmLibrary extends BaseEntity{

	private static final long serialVersionUID = 1L;

	public static final String KEEP_REPAIR_TIME = "keepRepairTime" ;
	public static final String DEPRECATE_TIME = "deprecateTime" ;
	public static final String PRICE = "price" ;
	public static final String BRAND = "brand" ;
	public static final String DEVICE_SHORT_NAME = "deviceShortName" ;
	public static final String DEVICE_TYPE = "deviceType" ;
	public static final String DEVICE_TYPE_NAME = "deviceTypeName" ;
	public static final String DEVICE_MODEL = "deviceModel" ;
	public static final String PRODUCT_FACTORY_NUMBER = "productFactoryNumber" ;
	public static final String PRODUCT_FACTORY_NAME = "productFactoryName" ;
	public static final String SUPPLY_FACTORY_NUMBER = "supplyFactoryNumber" ;
	public static final String SUPPLY_FACTORY_NAME = "supplyFactoryName" ;
	public static final String DECRIPTION = "decription" ;
	public static final String CHECK_RATE = "checkRate" ;
	

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
	 * 设备简称
	 */
	private String deviceShortName;
	
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
	 * 描述
	 */
	@Column(length = 3000)
	private String decription;
	
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
	 * @return the deviceShortName
	 */
	public String getDeviceShortName() {
		return deviceShortName;
	}

	/**
	 * @param deviceShortName the deviceShortName to set
	 */
	public void setDeviceShortName(String deviceShortName) {
		this.deviceShortName = deviceShortName;
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
	 * @return the decription
	 */
	public String getDecription() {
		return decription;
	}

	/**
	 * @param decription the decription to set
	 */
	public void setDecription(String decription) {
		this.decription = decription;
	}

	public static List<String> foreignClassesList=null;

	/** 
	* @see com.ehs.security.entity.BaseEntity#getForeignClasses()  
	* @Function: EdmLibrary.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月1日 上午10:50:49 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月1日      chentm           v1.0.0               修改原因
	*/
	@Override
	public List<String> getForeignClasses() {
		return foreignClassesList;
	}
	
//	static {
//		foreignClassesList=new ArrayList<String>();
//		foreignClassesList.add(BaseEntity.CODE+","+EdmAccountPrint.class.getName()+","+EdmAccountPrint.REF_EDM_LIBRARY);
//	}
	
}
