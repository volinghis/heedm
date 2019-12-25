/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.flow.handler 
 * @author: chentm   
 * @date: 2019年7月19日 上午10:30:08 
 */
package com.ehs.security.flow.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Attachment;
import org.flowable.task.service.delegate.DelegateTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.UserBaseInfo;
import com.ehs.security.flow.entity.FlowBaseEntity;
import com.ehs.security.flow.utils.FlowConstans;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;
import com.ehs.security.service.UserService;
import com.ehs.security.utils.SpringUtils;
import com.ehs.security.utils.SysAccessUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FlowBaseTaskHandler.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月19日 上午10:30:08 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月19日      chentm          v1.0.0               修改原因
*/
public class FlowBaseTaskHandler implements TaskListener {

	private static final long serialVersionUID = 1L;

	private static final Logger logger=LoggerFactory.getLogger(FlowBaseTaskHandler.class);
	
	private BaseCommonService baseCommonService;
	
	private DataDictionarySearchService dataDictionarySearchService;

	private ProcessEngine processEngine;
	
	public FlowBaseTaskHandler() {
		processEngine=SpringUtils.getBean(ProcessEngine.class);
		baseCommonService=SpringUtils.getBean(BaseCommonService.class);
		dataDictionarySearchService=SpringUtils.getBean(DataDictionarySearchService.class);
	}
	
	/** 
	* @see org.flowable.task.service.delegate.TaskListener#notify(org.flowable.task.service.delegate.DelegateTask)  
	* @Function: FlowBaseTaskHandler.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月19日 上午10:30:08 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月19日      chentm           v1.0.0               修改原因
	*/
	@Transactional
	@Override
	public void notify(DelegateTask delegateTask) {
		
		try {
			if(StringUtils.equals(delegateTask.getEventName(), FlowConstans.FLOW_TASK_LISTENER_CREATE)) {
				UserService userService=SpringUtils.getBean(UserService.class);
				FlowBaseEntity fbe=(FlowBaseEntity) baseCommonService.findByCode(Class.forName((String)delegateTask.getVariable(FlowConstans.FLOW_VARS_CLASS_KEY)),(String)delegateTask.getVariable(FlowConstans.FLOW_VARS_DATA_KEY));
				if(StringUtils.isBlank(fbe.getFlowCurrentPerson())) {
					fbe.setFlowCurrentPerson(delegateTask.getAssignee());
					fbe.setFlowCurrentPersonName(userService.findUser(delegateTask.getAssignee()).getName());
				}else {
					fbe.setFlowCurrentPerson(fbe.getFlowCurrentPerson()+","+delegateTask.getAssignee());
					fbe.setFlowCurrentPersonName(fbe.getFlowCurrentPersonName()+","+userService.findUser(delegateTask.getAssignee()).getName());
				}
				String fs=fbe.getFlowPersonsData();
				if(StringUtils.isNotBlank(fs)) {
					String[] fss=fs.split(",");
					List<String> stringB = Arrays.asList(fss);
					
					List arrList = new ArrayList(stringB);
					if(!arrList.contains(delegateTask.getAssignee())) {
						arrList.add(delegateTask.getAssignee());
					}
					fbe.setFlowPersonsData(StringUtils.join(arrList,","));
				}else {
					fbe.setFlowPersonsData(delegateTask.getAssignee());
				}
				BaseEntity baseEntity=baseCommonService.saveOrUpdate(fbe);
				TaskService tt=SpringUtils.getBean(TaskService.class);
				RuntimeService rr=SpringUtils.getBean(RuntimeService.class);
				TaskService taskService=processEngine.getTaskService();
				taskService.setVariableLocal(delegateTask.getId(), FlowConstans.FLOW_TASK_ENTITY_ID, baseEntity.getId());
				taskService.setVariableLocal(delegateTask.getId(), FlowConstans.FLOW_TASK_OWNER, SysAccessUser.get().getCode());
				taskService.setVariableLocal(delegateTask.getId(), FlowConstans.FLOW_TASK_OWNER_NAME, SysAccessUser.get().getName());
				
			}
		} catch (Exception e) {
			logger.error("执行流程回调失败！"+delegateTask.getEventName(),e.getMessage());
			throw new RuntimeException(e);
		}

		

	}

}
