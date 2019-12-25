/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.flow.controller 
 * @author: chentm   
 * @date: 2019年7月16日 下午4:36:39 
 */
package com.ehs.security.flow.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.flow.service.FlowBaseService;
import com.ehs.security.flow.utils.FlowConstans;
import com.ehs.security.query.Pagenate;
import com.ehs.security.utils.SysAccessUser;

import freemarker.template.Template;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: FlowBaseController.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: chentm
 * @date: 2019年7月16日 下午4:36:39
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年7月16日
 *        chentm v1.0.0 修改原因
 */
@Controller
public class FlowBaseController {

	private static final Logger logger=LoggerFactory.getLogger(FlowBaseController.class);
	
	@Resource
	private TaskService taskService;

	@Resource
	private RuntimeService runtimeService;

	@Resource
	private ProcessEngine processEngine;

	@Resource
	private RepositoryService repositoryService;
	
	@Resource
	private FlowBaseService flowBaseService;

	
	/**
	 * 
	* @Function: FlowBaseController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月16日 下午5:13:25 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月16日     chentm           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/flow/base/processReject")
	@ResponseBody
	public String processReject(HttpServletRequest request, HttpServletResponse httpServletResponse)
			throws Exception {
		try {
			String processId = request.getParameter("processInstanceId");
			flowBaseService.processReject(processId);
			 return JSON.toJSONString(ResultBean.getBean("驳回成功！", ResultType.OK, ""));
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("驳回流程失败！",e.getMessage());
			return JSON.toJSONString(ResultBean.getBean("驳回失败！", ResultType.ERROR, ""));
		}

	}
	
	/**
	 * 
	* @Function: FlowBaseController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年8月5日 上午9:23:53 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月5日     chentm           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/flow/base/donextstep")
	@ResponseBody
	public String donextstep(HttpServletRequest request, HttpServletResponse httpServletResponse)
			throws Exception {
		try {
			String processInstanceId = request.getParameter("processInstanceId");
			String flowOper=request.getParameter("flowOper");
			flowBaseService.processSend(processInstanceId, flowOper);
			 return JSON.toJSONString(ResultBean.getBean("提交成功！", ResultType.OK, ""));
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("驳回流程失败！",e.getMessage());
			return JSON.toJSONString(ResultBean.getBean("提交失败！", ResultType.ERROR, ""));
		}

	}
	
	@RequestMapping(value = "/action/flow/base/doend")
	@ResponseBody
	public String doend(HttpServletRequest request, HttpServletResponse httpServletResponse)
			throws Exception {
		try {
			String processInstanceId = request.getParameter("processInstanceId");
			flowBaseService.processEnd(processInstanceId);
			 return JSON.toJSONString(ResultBean.getBean("处理成功！", ResultType.OK, ""));
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("驳回流程失败！",e.getMessage());
			return JSON.toJSONString(ResultBean.getBean("处理失败！", ResultType.ERROR, ""));
		}

	}

	
	@RequestMapping(value = "/action/flow/base/processDiagram")
	public void genProcessDiagram(HttpServletRequest request, HttpServletResponse httpServletResponse)
			throws Exception {
		String processId = request.getParameter("processInstanceId");
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
		// 流程走完的不显示图
		if (pi == null) {
			return;
		}

		// 使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		String InstanceId = processId;
		List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(InstanceId).list();

		// 得到正在执行的Activity的Id
		List<String> activityIds = new ArrayList<>();
		List<String> flows = new ArrayList<>();
		for (Execution exe : executions) {
			List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
			activityIds.addAll(ids);
		}

		// 获取流程图
		BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
		ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
		ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
		InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows,
				engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(),
				engconf.getClassLoader(), 1.0,true);
		OutputStream out = null;
		byte[] buf = new byte[1024];
		int legth = 0;
		try {
			out = httpServletResponse.getOutputStream();
			while ((legth = in.read(buf)) != -1) {
				out.write(buf, 0, legth);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
