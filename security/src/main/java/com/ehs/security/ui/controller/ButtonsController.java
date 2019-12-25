/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.ui.controller 
 * @author: chentm   
 * @date: 2019年7月15日 下午2:34:29 
 */
package com.ehs.security.ui.controller;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehs.security.flow.entity.FlowBaseEntity;
import com.ehs.security.flow.utils.FlowConstans;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.utils.SpringUtils;
import com.ehs.security.utils.SysAccessUser;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: ButtonsController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月15日 下午2:34:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月15日      chentm          v1.0.0               修改原因
*/
@Controller
public class ButtonsController {

	private static final Logger logger=LoggerFactory.getLogger(ButtonsController.class);
	
	@Resource
	private RuntimeService runtimeService;
	 
	@Resource
	private RepositoryService repositoryService;
	 
	@Resource
	private TaskService taskService;
	 
	private Configuration cfg;
	
	public ButtonsController() {
		 cfg=new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		 cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates");
	}

	@RequestMapping(value = "/action/ui/buttons")
	@ResponseBody
	public String buttons(HttpServletRequest request,HttpServletResponse response) {
		 Map<String,Object> stackMap = new HashMap<String,Object>();  
         Enumeration paramNames = request.getParameterNames();  
         while (paramNames.hasMoreElements()) {  
             String paramName = (String) paramNames.nextElement();  
             String[] paramValues = request.getParameterValues(paramName);  
             if (paramValues.length >0) {  
                 String paramValue = paramValues[0];  
                 if (paramValue.length() != 0) {  
                	 stackMap.put(paramName, paramValue);  
                 }  
             }  
         } 
		 //存放栈值
		 Enumeration attributeNames = request.getAttributeNames();  
         while (attributeNames.hasMoreElements()) {  
             String attributeName = (String) attributeNames.nextElement();  
             Object attributeValue = request.getAttribute(attributeName);
             if (attributeValue!=null&&attributeValue.getClass().toString().equals(String.class.toString())) {  
                	 stackMap.put(attributeName, attributeValue);  
             }  
         } 
		String uiType=request.getParameter("type");
		String processInstanceId=request.getParameter("processInstanceId");
		try {
			if(StringUtils.equals(uiType, "flow")) {
				boolean tracProcess=false;
				boolean rejectProcess=false;
				boolean nextStep=false;
				boolean endProcess=false;
				boolean stopProcess=false;
				boolean startProcess=false;
				if(StringUtils.isBlank(processInstanceId)) {
					startProcess=true;
				}else {
					BaseCommonService baseCommonService=SpringUtils.getBean(BaseCommonService.class);
				  ProcessInstance pi=	runtimeService.createProcessInstanceQuery().includeProcessVariables().processInstanceId(processInstanceId).singleResult();
				  if(pi!=null) {
					  
					  //如果流程存在显示流程跟踪按钮
					  tracProcess=true;
						  FlowBaseEntity fbe=(FlowBaseEntity) baseCommonService.findByCode(Class.forName((String)pi.getProcessVariables().get(FlowConstans.FLOW_VARS_CLASS_KEY)),(String)pi.getProcessVariables().get(FlowConstans.FLOW_VARS_DATA_KEY));
							Task tt=taskService.createTaskQuery().taskAssignee(SysAccessUser.get().getCode()).taskDefinitionKey(fbe.getFlowCurrentStep()).processInstanceId(processInstanceId).singleResult();
							if(tt!=null) {
								//如果是开始环节，并且当前处理人是当前用户
								if(StringUtils.equals(tt.getTaskDefinitionKey(), (String)pi.getProcessVariables().get(FlowConstans.FLOW_START_ACTIVITY_ID))&&StringUtils.equals(tt.getAssignee(), SysAccessUser.get().getCode())) {
									nextStep=true;
								}else {
									if(StringUtils.equals(tt.getAssignee(), SysAccessUser.get().getCode())) {
										rejectProcess=true;
										 BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
										 FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(tt.getTaskDefinitionKey());
										   //输出连线
										    List<SequenceFlow> outgoingFlows = flowNode.getOutgoingFlows();
										 
										    //遍历返回下一个节点信息
										    for (SequenceFlow outgoingFlow : outgoingFlows) {
										        //类型自己判断
										        FlowElement targetFlowElement = outgoingFlow.getTargetFlowElement();
										        //用户任务
										        if (targetFlowElement instanceof EndEvent) {
										        	if(StringUtils.isBlank(outgoingFlow.getConditionExpression())) {
											        	endProcess=true;	
										        	}
										        }else {
										        	if(StringUtils.isBlank(outgoingFlow.getConditionExpression())) {
										        		nextStep=true;
										        	}
										        }
										    }
										
									}else {
										if(StringUtils.equals(pi.getStartUserId(), SysAccessUser.get().getCode())) {
											stopProcess=true;
										}
									}
								}

							}
					}
				}
				
				stackMap.put("startProcess", startProcess);
				  stackMap.put("tracProcess", tracProcess);
				  stackMap.put("rejectProcess", rejectProcess);
				  stackMap.put("endProcess", endProcess);
				  stackMap.put("nextStep", nextStep);
				  stackMap.put("stopProcess", stopProcess);
				stackMap.put("processInstanceId", processInstanceId);
			    Template porTemplate=cfg.getTemplate("/buttons/button-flow.ftl");
				return FreeMarkerTemplateUtils.processTemplateIntoString(porTemplate, stackMap);
			}else {
				Template porTemplate=cfg.getTemplate("/buttons/button-normal.ftl");
				return FreeMarkerTemplateUtils.processTemplateIntoString(porTemplate, stackMap);
			}
		} catch (Exception e) {
			logger.error("构建button失败！");
			e.printStackTrace();
		}

		return "系统错误";
	}
}
