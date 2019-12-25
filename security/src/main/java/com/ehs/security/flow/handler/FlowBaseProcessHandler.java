/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.flow.handler 
 * @author: chentm   
 * @date: 2019年7月18日 下午1:27:27 
 */
package com.ehs.security.flow.handler;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.ehs.security.entity.UserBaseInfo;
import com.ehs.security.flow.entity.FlowBaseEntity;
import com.ehs.security.flow.utils.FlowConstans;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;
import com.ehs.security.utils.SpringUtils;
import com.ehs.security.utils.SysAccessUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FlowBaseProcessHandler.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月18日 下午1:27:27 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月18日      chentm          v1.0.0               修改原因
*/
public class FlowBaseProcessHandler implements ExecutionListener {

	private static final long serialVersionUID = 1L;

	private static final Logger logger=LoggerFactory.getLogger(FlowBaseProcessHandler.class);
	
	private BaseCommonService baseCommonService;
	
	private DataDictionarySearchService dataDictionarySearchService;

	public FlowBaseProcessHandler() {
		baseCommonService=SpringUtils.getBean(BaseCommonService.class);
		dataDictionarySearchService=SpringUtils.getBean(DataDictionarySearchService.class);
	}
	
	/** 
	* @see org.flowable.engine.delegate.ExecutionListener#notify(org.flowable.engine.delegate.DelegateExecution)  
	* @Function: EdmLibraryProcessStartHandler.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月16日 上午10:01:47 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月16日      chentm           v1.0.0               修改原因
	*/
	@Transactional
	@Override
	public void notify(DelegateExecution execution) {
			try {
				if(StringUtils.equals(execution.getEventName(), FlowConstans.FLOW_ACTIVITY_LISTENER_START)) {
					FlowBaseEntity fbe=(FlowBaseEntity) baseCommonService.findByCode(Class.forName((String)execution.getVariable(FlowConstans.FLOW_VARS_CLASS_KEY)),(String)execution.getVariable(FlowConstans.FLOW_VARS_DATA_KEY));
					fbe.setFlowPrevStep(fbe.getFlowCurrentStep());
					fbe.setFlowPrevStepName(fbe.getFlowCurrentStepName());
					fbe.setFlowCurrentStep(execution.getCurrentFlowElement().getId());
					fbe.setFlowCurrentStepName(execution.getCurrentFlowElement().getName());
					fbe.setFlowPrevPerson(fbe.getFlowCurrentPerson());
					fbe.setFlowPrevPersonName(fbe.getFlowCurrentPersonName());
					fbe.setFlowCurrentPerson("");
					fbe.setFlowCurrentPersonName("");
					if(StringUtils.equals(FlowConstans.FLOW_START, execution.getCurrentActivityId())) {
						fbe.setFlowProcessInstanceId(execution.getProcessInstanceId());
						fbe.setFlowStatus(FlowConstans.FLOW_STATUS_APPROVE);
						fbe.setFlowStatusName(dataDictionarySearchService.findOne(FlowConstans.FLOW_STATUS_APPROVE).getText());						
					}else if(StringUtils.equals(FlowConstans.FLOW_END, execution.getCurrentActivityId())) {

						fbe.setFlowStatus(FlowConstans.FLOW_STATUS_END);
						fbe.setFlowStatusName(dataDictionarySearchService.findOne(FlowConstans.FLOW_STATUS_END).getText());
					}
					baseCommonService.saveOrUpdate(fbe);
				}

			} catch (Exception e) {
				logger.error("执行流程回调，"+execution.getEventName(), e);
				throw new RuntimeException(e);
			}

	}
}
