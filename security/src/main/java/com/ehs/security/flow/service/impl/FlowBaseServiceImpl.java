/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.flow.service.impl 
 * @author: chentm   
 * @date: 2019年7月17日 上午9:24:45 
 */
package com.ehs.security.flow.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.Activity;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.FlowableEngineAgenda;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.impl.util.ProcessDefinitionUtil;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.UserBaseInfo;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.flow.controller.FlowBaseController;
import com.ehs.security.flow.entity.FlowBaseEntity;
import com.ehs.security.flow.service.FlowBaseService;
import com.ehs.security.flow.utils.FlowConstans;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.UserService;
import com.ehs.security.utils.SysAccessUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FlowBaseServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月17日 上午9:24:45 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月17日      chentm          v1.0.0               修改原因
*/
@Service
public class FlowBaseServiceImpl implements FlowBaseService {

	private static final Logger logger=LoggerFactory.getLogger(FlowBaseServiceImpl.class);

	
	@Resource
	private TaskService taskService;

	@Resource
	private RuntimeService runtimeService;

	@Resource
	private ProcessEngine processEngine;

	@Resource
	private RepositoryService repositoryService;
	
	@Resource
	private ManagementService managementService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private BaseCommonService baseCommonService;
	

	@Transactional
	@Override
	public void processReject(String processInstanceId) {
		
		try {

			ProcessInstance pi = runtimeService.createProcessInstanceQuery().includeProcessVariables()
					.processInstanceId(processInstanceId).singleResult();
			FlowBaseEntity fbe = (FlowBaseEntity) baseCommonService.findByCode(
					Class.forName((String) pi.getProcessVariables().get(FlowConstans.FLOW_VARS_CLASS_KEY)),
					(String) pi.getProcessVariables().get(FlowConstans.FLOW_VARS_DATA_KEY));
			runtimeService.createChangeActivityStateBuilder()
            .processInstanceId(processInstanceId)
            .moveActivityIdTo(fbe.getFlowCurrentStep(), (String)pi.getProcessVariables().get(FlowConstans.FLOW_START_ACTIVITY_ID))
            .changeState();
			
		} catch (Exception e) {
			
			logger.error("执行流程驳回出错！", e.getMessage());
			throw new RuntimeException(e);
		}

	}


	/** 
	* @see com.ehs.security.flow.service.FlowBaseService#startProcess(java.lang.String, java.util.Map)  
	* @Function: FlowBaseServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月18日 下午4:13:33 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月18日      chentm           v1.0.0               修改原因
	*/


	/** 
	* @see com.ehs.security.flow.service.FlowBaseService#startProcess(com.ehs.security.flow.entity.FlowBaseEntity)  
	* @Function: FlowBaseServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月19日 上午11:30:49 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月19日      chentm           v1.0.0               修改原因
	*/
	@Transactional
	@Override
	public ProcessInstance startProcess(FlowBaseEntity flowBaseEntity,Map vars) {
		try {
			String processId=flowBaseEntity.getFlowProcessId();
			String assignee=flowBaseEntity.getFlowOper();
			vars.put(FlowConstans.FLOW_VARS_DATA_KEY, flowBaseEntity.getCode());
			vars.put(FlowConstans.FLOW_VARS_CLASS_KEY, flowBaseEntity.getClass().getName());
			String user=SysAccessUser.get().getCode();
			//部署流程
		   repositoryService.createDeployment().addClasspathResource("processes/"+processId+".bpmn").deploy();
		   //System.out.println(d);
		   ProcessDefinition pd= repositoryService.createProcessDefinitionQuery().processDefinitionKey(processId).latestVersion().singleResult();
			 BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());
			 FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(FlowConstans.FLOW_START);
			   //输出连线
			    List<SequenceFlow> outgoingFlows = flowNode.getOutgoingFlows();
			 String eventKey="";
			    //遍历返回下一个节点信息
			    for (SequenceFlow outgoingFlow : outgoingFlows) {
			        //类型自己判断
			        FlowElement targetFlowElement = outgoingFlow.getTargetFlowElement();
			        if(!(targetFlowElement instanceof EndEvent)) {
			        	eventKey=targetFlowElement.getId();
			        	vars.put(targetFlowElement.getId()+FlowConstans.FLOW_ASSIGNEE_USER_REGIX, user);
			        }
			    }
//			    vars.put(FlowConstans.FLOW_PROCESS_NAME, pd.getName());
			    vars.put(FlowConstans.FLOW_START_ACTIVITY_ID, eventKey);
			    Authentication.setAuthenticatedUserId(SysAccessUser.get().getCode());
			    ProcessInstance pi=   runtimeService.startProcessInstanceByKey(processId, vars);
			 Authentication.setAuthenticatedUserId(null);
				FlowBaseEntity fbe=(FlowBaseEntity) baseCommonService.findByCode(Class.forName(flowBaseEntity.getClass().getName()),flowBaseEntity.getCode());
				Task t=taskService.createTaskQuery().taskVariableValueEquals(FlowConstans.FLOW_TASK_ENTITY_ID, fbe.getId()).singleResult();
				
					 FlowNode node = (FlowNode) bpmnModel.getFlowElement(t.getTaskDefinitionKey());
					   //输出连线
					    List<SequenceFlow> sFlows = node.getOutgoingFlows();
					    //遍历返回下一个节点信息
					    for (SequenceFlow outgoingFlow : sFlows) {
					        //类型自己判断
					        FlowElement targetFlowElement = outgoingFlow.getTargetFlowElement();
					        if(!(targetFlowElement instanceof EndEvent)) {
					        	vars.put(targetFlowElement.getId()+FlowConstans.FLOW_ASSIGNEE_USER_REGIX, assignee);
					        }
					    }
					    taskService.complete(t.getId(),vars);
			 
			 return pi;
		}catch(Exception ex) {
			logger.error("启动流程出错",ex);
			throw new RuntimeException(ex);
		}

	}


	/** 
	* @see com.ehs.security.flow.service.FlowBaseService#processSend(java.lang.String, java.lang.String)  
	* @Function: FlowBaseServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年8月5日 上午9:21:00 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月5日      chentm           v1.0.0               修改原因
	*/
	@Transactional
	@Override
	public void processSend(String processInstanceId, String flowOper) {
		try {


			ProcessInstance pi = runtimeService.createProcessInstanceQuery().includeProcessVariables()
					.processInstanceId(processInstanceId).singleResult();

			FlowBaseEntity fbe = (FlowBaseEntity) baseCommonService.findByCode(
					Class.forName((String) pi.getProcessVariables().get(FlowConstans.FLOW_VARS_CLASS_KEY)),
					(String) pi.getProcessVariables().get(FlowConstans.FLOW_VARS_DATA_KEY));
			
			Task tt=taskService.createTaskQuery().taskAssignee(SysAccessUser.get().getCode()).taskDefinitionKey(fbe.getFlowCurrentStep()).processInstanceId(processInstanceId).singleResult();

			 BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
			 FlowNode node = (FlowNode) bpmnModel.getFlowElement(tt.getTaskDefinitionKey());
			 Map vars=new HashMap();
			   //输出连线
			    List<SequenceFlow> sFlows = node.getOutgoingFlows();
			    //遍历返回下一个节点信息
			    for (SequenceFlow outgoingFlow : sFlows) {
			        //类型自己判断
			        FlowElement targetFlowElement = outgoingFlow.getTargetFlowElement();
			        if(!(targetFlowElement instanceof EndEvent)) {
			        	vars.put(targetFlowElement.getId()+FlowConstans.FLOW_ASSIGNEE_USER_REGIX, flowOper);
			        }
			    }
			    taskService.complete(tt.getId(),vars);
	 
		} catch (Exception e) {
			
			logger.error("执行流程审批出错！", e.getMessage());
			throw new RuntimeException(e);
		}
		
	}


	/** 
	* @see com.ehs.security.flow.service.FlowBaseService#processEnd(java.lang.String)  
	* @Function: FlowBaseServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年8月5日 下午5:38:24 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月5日      chentm           v1.0.0               修改原因
	*/
	@Override
	public void processEnd(String processInstanceId) {
		try {

			ProcessInstance pi = runtimeService.createProcessInstanceQuery().includeProcessVariables()
					.processInstanceId(processInstanceId).singleResult();
			FlowBaseEntity fbe = (FlowBaseEntity) baseCommonService.findByCode(
					Class.forName((String) pi.getProcessVariables().get(FlowConstans.FLOW_VARS_CLASS_KEY)),
					(String) pi.getProcessVariables().get(FlowConstans.FLOW_VARS_DATA_KEY));

			Task t=taskService.createTaskQuery().taskVariableValueEquals(FlowConstans.FLOW_TASK_ENTITY_ID, fbe.getId()).singleResult();
			taskService.complete(t.getId());
		} catch (Exception e) {
			
			logger.error("执行流程审批出错！", e.getMessage());
			throw new RuntimeException(e);
		}
		
	}

}
