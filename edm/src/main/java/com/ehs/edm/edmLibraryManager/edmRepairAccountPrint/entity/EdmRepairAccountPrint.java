package com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.ehs.security.flow.entity.FlowBaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmRepairAccountPrint.java
* @Description: 检修台账
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年7月15日 上午9:18:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月15日     qjj           v1.0.0               修改原因
*/
@Entity
@Table(name="EDM_REPAIR_ACCOUNT_PRINT")
public class EdmRepairAccountPrint extends FlowBaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	public static final String DEVICE_CODE = "deviceCode" ;
	public static final String NODE_CODE = "nodeCode" ;
	public static final String REPAIR_NAME = "repairName" ;
	public static final String REPAIR_TEAM = "repairTeam" ;
	public static final String REPAIR_TEAM_CODE = "repairTeamCode" ;
	public static final String REPAIR_NATURE_NAME = "repairNatureName" ;
	public static final String REPAIR_NATURE_CODE = "repairNatureCode" ;
	public static final String GROUP_MEMBER = "groupMember" ;
	public static final String BEFORE_SATUATION = "beforeSatuation" ;
	public static final String DUTY_PERSON_CODE = "dutyPersonCode" ;
	public static final String FOUNDER = "founder" ;
	public static final String DUTY_PERSON_NAME = "dutyPersonName" ;
	public static final String REPAIR_START_TIME = "repairStartTime" ;
	public static final String REPAIR_END_TIME = "repairEndTime" ;
	public static final String REPAIR_CONTENT = "repairContent" ;
	public static final String FILE_ID = "fileId" ;
	public static final String REPAIR_RESULT = "repairResult" ;
	public static final String TESTER = "tester" ;
	public static final String TESTER_CODE = "testerCode" ;
	public static final String PROFESSION = "profession" ;
	public static final String Equipment_Rating = "equipmentRating" ;
	public static final String TEST_RESULTS = "testResults" ;
	public static final String CAUSE_ANALYSIS= "causeAnalysis" ;
	public static final String REPAIR_TEST = "repairTest" ;
	public static final String TRIAL_SITUATION = "trialSituation" ;
	public static final String REMARK = "remark" ;


	
	/**
	 * 设备code
	 */
	private String deviceCode;
	
	/**
	 * 设备所属系统
	 */
	private String nodeCode;

	/**
	 * 设备名称
	 */
	private String deviceName;
	
	/**
	 * 检修名称 
	 */
	private String repairName;
	
	/**
	 * 检修班组
	 */
	private String repairTeam;
	
	/**
	 * 检修班组编码
	 */
	private String repairTeamCode;
	/**
	 * 检修性质
	 */
	private String repairNatureName;
	
	/**
	 *  检修性质编码
	 */
	private String repairNatureCode;
	
	
	private String profession;
	/**
	   *  试验人code
	 */
	private String testerCode;
	
	/**
	   *试验人 
	 */
	private String tester;
	
	/**
	 * 工作组成员
	 */
	@Column(length = 3000)
	private String groupMember;
	
	/**
	 * 修前状况
	 */
	@Column(length = 3000)
	private String beforeSatuation;
	
	/**
	 * 负责人
	 */
	private String dutyPersonCode;
	
	/**
	 * 创建人
	 */
	private String founder;
	/**
	 * 负责人姓名
	 */
	private String dutyPersonName;

	/**
	 * 检修开始时间
	 */
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Timestamp repairStartTime;
	
	/**
	 * 检修结束时间
	 */
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Timestamp repairEndTime;
	
	/**
	 * 检修内容
	 */
//	@Lob
//	@Basic(fetch = FetchType.LAZY)
	@Column(length = 3000)
	private String repairContent;
	
	/**
	 * 检修记事文件标识
	 */
	private String fileId;
	
	/**
	 * 修后状况
	 */
	@Column(length = 3000)
	private String repairResult;
	
	/**
	 *  修后设备评级
	 */
	private String equipmentRating;
	
	/**
	 * 解体检查情况
	 */
	@Column(length = 3000)
	private String testResults;
	
	/**
	 * 更换备品备件及原因分析
	 */
	@Column(length = 3000)
	private String causeAnalysis;
	
	/**
	 * 修后试验
	 */
	@Column(length = 3000)
	private String repairTest;
	
	/**
	 *试运情况 
	 */
	@Column(length = 3000)
	private String trialSituation;
	
	/**
	 * 备注(你的建议及改进的内容)
	 */
	@Column(length = 3000)
	private String remark;
	
	
	
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
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

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getRepairName() {
		return repairName;
	}

	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}

	public String getRepairTeamCode() {
		return repairTeamCode;
	}

	public void setRepairTeamCode(String repairTeamCode) {
		this.repairTeamCode = repairTeamCode;
	}

	public String getRepairTeam() {
		return repairTeam;
	}

	public void setRepairTeam(String repairTeam) {
		this.repairTeam = repairTeam;
	}

	
	 
	public String getRepairNatureName() {
		return repairNatureName;
	}

	public void setRepairNatureName(String repairNatureName) {
		this.repairNatureName = repairNatureName;
	}

	public String getRepairNatureCode() {
		return repairNatureCode;
	}

	public void setRepairNatureCode(String repairNatureCode) {
		this.repairNatureCode = repairNatureCode;
	}
	
	/**
	 * @return the testerCode
	 */
	public String getTesterCode() {
		return testerCode;
	}

	/**
	 * @param testerCode the testerCode to set
	 */
	public void setTesterCode(String testerCode) {
		this.testerCode = testerCode;
	}
	
	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * @return the tester
	 */
	public String getTester() {
		return tester;
	}

	/**
	 * @param tester the tester to set
	 */
	public void setTester(String tester) {
		this.tester = tester;
	}

	public String getGroupMember() {
		return groupMember;
	}

	public void setGroupMember(String groupMember) {
		this.groupMember = groupMember;
	}

	public String getBeforeSatuation() {
		return beforeSatuation;
	}

	public void setBeforeSatuation(String beforeSatuation) {
		this.beforeSatuation = beforeSatuation;
	}

	public String getDutyPersonCode() {
		return dutyPersonCode;
	}
	

	public void setDutyPersonCode(String dutyPersonCode) {
		this.dutyPersonCode = dutyPersonCode;
	}

	public String getDutyPersonName() {
		return dutyPersonName;
	}

	public void setDutyPersonName(String dutyPersonName) {
		this.dutyPersonName = dutyPersonName;
	}
	
	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}

	public Timestamp getRepairStartTime() {
		return repairStartTime;
	}

	public void setRepairStartTime(Timestamp repairStartTime) {
		this.repairStartTime = repairStartTime;
	}

	public Timestamp getRepairEndTime() {
		return repairEndTime;
	}

	public void setRepairEndTime(Timestamp repairEndTime) {
		this.repairEndTime = repairEndTime;
	}

	public String getRepairContent() {
		return repairContent;
	}

	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	
	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getRepairResult() {
		return repairResult;
	}

	public void setRepairResult(String repairResult) {
		this.repairResult = repairResult;
	}
	
	/**
	 * @return the equipmentRating
	 */
	public String getEquipmentRating() {
		return equipmentRating;
	}

	/**
	 * @param equipmentRating the equipmentRating to set
	 */
	public void setEquipmentRating(String equipmentRating) {
		this.equipmentRating = equipmentRating;
	}
	
	/**
	 * @return the testResults
	 */
	public String getTestResults() {
		return testResults;
	}

	/**
	 * @param testResults the testResults to set
	 */
	public void setTestResults(String testResults) {
		this.testResults = testResults;
	}

	/**
	 * @return the causeAnalysis
	 */
	public String getCauseAnalysis() {
		return causeAnalysis;
	}

	/**
	 * @param causeAnalysis the causeAnalysis to set
	 */
	public void setCauseAnalysis(String causeAnalysis) {
		this.causeAnalysis = causeAnalysis;
	}

	/**
	 * @return the repairTest
	 */
	public String getRepairTest() {
		return repairTest;
	}

	/**
	 * @param repairTest the repairTest to set
	 */
	public void setRepairTest(String repairTest) {
		this.repairTest = repairTest;
	}

	/**
	 * @return the trialSituation
	 */
	public String getTrialSituation() {
		return trialSituation;
	}

	/**
	 * @param trialSituation the trialSituation to set
	 */
	public void setTrialSituation(String trialSituation) {
		this.trialSituation = trialSituation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 
	* @see com.ehs.security.entity.BaseEntity#getForeignClasses()
	 */
	@Override
	public List<String> getForeignClasses() {
		return null;
	}

	/**
	 * 
	 * @see com.ehs.security.flow.entity.FlowBaseEntity#getFlowProcessId()
	 */
	@Override
	public String getFlowProcessId() {
		return "EdmRepairAccountPrint";
	}

	/** 
	* @see com.ehs.security.flow.entity.FlowBaseEntity#getDefaultFormUrl()  
	*/
	@Override
	public String getDefaultFormUrl() {
		// TODO Auto-generated method stub
		return null;
	}

}
