/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.flow.entity 
 * @author: chentm   
 * @date: 2019年7月8日 下午4:33:00 
 */
package com.ehs.security.flow.entity;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.ehs.security.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FlowBaseEntity.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月8日 下午4:33:00 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月8日      chentm          v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class FlowBaseEntity extends BaseEntity {
	
	public abstract String getFlowProcessId();
	public abstract String getDefaultFormUrl();
	
	private static final long serialVersionUID = 1L;
	
	public static final String FLOW_STATUS = "flowStatus" ;
	public static final String FLOW_STATUS_NAME = "flowStatusName" ;
	public static final String FLOW_CURRENT_STEP = "flowCurrentStep" ;
	public static final String FLOW_CURRENT_STEP_NAME = "flowCurrentStepName" ;
	public static final String FLOW_CURRENT_PERSON = "flowCurrentPerson" ;
	public static final String FLOW_CURRENT_PERSON_NAME = "flowCurrentPersonName" ;
	public static final String FLOW_PREV_PERSON = "flowPrevPerson" ;
	public static final String FLOW_PREV_PERSON_NAME = "flowPrevPersonName" ;
	public static final String FLOW_PREV_STEP = "flowPrevStep" ;
	public static final String FLOW_PREV_STEP_NAME = "flowPrevStepName" ;
	public static final String FLOW_APPROVE_PERSON_LIST = "flowApprovePersonList" ;
	public static final String FLOW_PERSONS_DATA = "flowPersonsData" ;
	public static final String FLOW_PROCESS_INSTANCE_ID = "flowProcessInstanceId" ;
	/**
	 * 流程实例ID
	 */
	private String flowProcessInstanceId;
	
	
	@Transient
	private String flowOper;
	
	
	
	public String getFlowOper() {
		return flowOper;
	}
	public void setFlowOper(String flowOper) {
		this.flowOper = flowOper;
	}

	/**
	 * 审批流程状态
	 */
	private String flowStatus;
	
	@Transient
	private String flowStatusName;
	
	/**
	 * 当前环节
	 */
	private String flowCurrentStep;
	
	private String flowCurrentStepName;
	
	/**
	 * 当前处理人
	 */
	@Column(length = 3000)
	private String flowCurrentPerson;
	
	private String flowCurrentPersonName;
	
	

	/**
	 * 上 一环节处理人
	 */
	@Column(length = 3000)
	private String flowPrevPerson;
	
	private String flowPrevPersonName;
	/**
	 * 上一环节代码
	 */
	private String flowPrevStep;
	
	/**
	 * 上一环节名称
	 */
	private String flowPrevStepName;
	


	/**
	 * 流程参与人集合，流程开始到流程结束不断累加
	 */
	@Column(length = 3000)
	private String flowPersonsData;

	/**
	 * @return the flowStatus
	 */
	public String getFlowStatus() {
		return flowStatus;
	}
	/**
	 * @param flowStatus the flowStatus to set
	 */
	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}
	/**
	 * @return the flowStatusName
	 */
	public String getFlowStatusName() {
		return flowStatusName;
	}
	/**
	 * @param flowStatusName the flowStatusName to set
	 */
	public void setFlowStatusName(String flowStatusName) {
		this.flowStatusName = flowStatusName;
	}
	/**
	 * @return the flowCurrentStep
	 */
	public String getFlowCurrentStep() {
		return flowCurrentStep;
	}
	/**
	 * @param flowCurrentStep the flowCurrentStep to set
	 */
	public void setFlowCurrentStep(String flowCurrentStep) {
		this.flowCurrentStep = flowCurrentStep;
	}
	/**
	 * @return the flowCurrentStepName
	 */
	public String getFlowCurrentStepName() {
		return flowCurrentStepName;
	}
	/**
	 * @param flowCurrentStepName the flowCurrentStepName to set
	 */
	public void setFlowCurrentStepName(String flowCurrentStepName) {
		this.flowCurrentStepName = flowCurrentStepName;
	}
	/**
	 * @return the flowCurrentPerson
	 */
	public String getFlowCurrentPerson() {
		return flowCurrentPerson;
	}
	/**
	 * @param flowCurrentPerson the flowCurrentPerson to set
	 */
	public void setFlowCurrentPerson(String flowCurrentPerson) {
		this.flowCurrentPerson = flowCurrentPerson;
	}
	/**
	 * @return the flowCurrentPersonName
	 */
	public String getFlowCurrentPersonName() {
		return flowCurrentPersonName;
	}
	/**
	 * @param flowCurrentPersonName the flowCurrentPersonName to set
	 */
	public void setFlowCurrentPersonName(String flowCurrentPersonName) {
		this.flowCurrentPersonName = flowCurrentPersonName;
	}
	/**
	 * @return the flowPrevPerson
	 */
	public String getFlowPrevPerson() {
		return flowPrevPerson;
	}
	/**
	 * @param flowPrevPerson the flowPrevPerson to set
	 */
	public void setFlowPrevPerson(String flowPrevPerson) {
		this.flowPrevPerson = flowPrevPerson;
	}
	/**
	 * @return the flowPrevPersonName
	 */
	public String getFlowPrevPersonName() {
		return flowPrevPersonName;
	}
	/**
	 * @param flowPrevPersonName the flowPrevPersonName to set
	 */
	public void setFlowPrevPersonName(String flowPrevPersonName) {
		this.flowPrevPersonName = flowPrevPersonName;
	}
	/**
	 * @return the flowPrevStep
	 */
	public String getFlowPrevStep() {
		return flowPrevStep;
	}
	/**
	 * @param flowPrevStep the flowPrevStep to set
	 */
	public void setFlowPrevStep(String flowPrevStep) {
		this.flowPrevStep = flowPrevStep;
	}
	/**
	 * @return the flowPrevStepName
	 */
	public String getFlowPrevStepName() {
		return flowPrevStepName;
	}
	/**
	 * @param flowPrevStepName the flowPrevStepName to set
	 */
	public void setFlowPrevStepName(String flowPrevStepName) {
		this.flowPrevStepName = flowPrevStepName;
	}

	/**
	 * @return the flowPersonsData
	 */
	public String getFlowPersonsData() {
		return flowPersonsData;
	}
	/**
	 * @param flowPersonsData the flowPersonsData to set
	 */
	public void setFlowPersonsData(String flowPersonsData) {
		this.flowPersonsData = flowPersonsData;
	}
	/**
	 * @return the flowProcessInstanceId
	 */
	public String getFlowProcessInstanceId() {
		return flowProcessInstanceId;
	}
	/**
	 * @param flowProcessInstanceId the flowProcessInstanceId to set
	 */
	public void setFlowProcessInstanceId(String flowProcessInstanceId) {
		this.flowProcessInstanceId = flowProcessInstanceId;
	}
	


	
	
	
}
