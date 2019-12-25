package com.ehs.edm.edmPartLibrary.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

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
* @author: zhaol
* @date: 2019年6月27日 下午3:43:48 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月27日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "EDM_PART_LIBRARY")
public class EdmPartLibrary extends BaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	public static final String NAME="name";
	public static final String PARENTCODE="parentCode";
	public static final String NODECODE="nodeCode";
	public static final String TYPE="type";
	public static final String NORM="norm";
	public static final String MANUFACTURER="manufacturer";
	public static final String SUPPLIER="supplier";
	public static final String AMOUNT="amount";
	public static final String PURCHASEAMOUNT="purchaseAmount";
	public static final String WARNINGVALUE="warningValue";
	public static final String LABELCODE="labelCode";
	public static final String PROFESSION="profession";
	public static final String MATERIAL_TYPE_NAME="materialTypeName";
	public static final String MATERIAL_TYPE_CODE="materialTypeCode";
	public static final String SORT="sort";

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 父编码
	 */
	private String parentCode;
	
	/**
	 * 节点code
	 */
	private String nodeCode;

	/**
	 * 类型（归属设备）
	 */
	private String type;
	
	/**
	 * 规格型号
	 */
	private String norm;
	
	/**
	 * 生产厂家
	 */
	private String manufacturer;
	
	/**
	 * 供应商
	 */
	private String supplier;
	
	/**
	 * 库存余量
	 */
	private String amount;
	
	/**
	 * 购置金额
	 */
	private BigDecimal purchaseAmount;

	/**
	 * 预警值
	 */
	private String warningValue;
	
	/**
	 * 标签码
	 */
	private String labelCode;
	
	/**
	 * 专业
	 */
	private String profession;
	
	/**
	 * 物资类型
	 */
	private String materialTypeCode;
	
	/**
	 * 物资类型
	 */
	private String materialTypeName;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getWarningValue() {
		return warningValue;
	}

	public void setWarningValue(String warningValue) {
		this.warningValue = warningValue;
	}

	public String getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getMaterialTypeCode() {
		return materialTypeCode;
	}

	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	@Override
	public String toString() {
		return "EdmPartLibrary [name=" + name + ", parentCode=" + parentCode + ", nodeCode=" + nodeCode + ", type="
				+ type + ", norm=" + norm + ", manufacturer=" + manufacturer + ", supplier=" + supplier + ", amount="
				+ amount + ", purchaseAmount=" + purchaseAmount + ", warningValue=" + warningValue + ", labelCode="
				+ labelCode + ", profession=" + profession + ", materialTypeCode=" + materialTypeCode
				+ ", materialTypeName=" + materialTypeName + ", sort=" + sort + "]";
	}

}
