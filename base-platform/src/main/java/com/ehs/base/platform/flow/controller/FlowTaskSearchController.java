/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.controller 
 * @author: chentm   
 * @date: 2019年7月11日 下午2:36:18 
 */
package com.ehs.base.platform.flow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ehs.base.platform.base.config.SystemConfig;
import com.ehs.base.platform.flow.bean.TaskBean;
import com.ehs.security.flow.service.FlowBaseService;
import com.ehs.security.flow.utils.FlowConstans;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.utils.SysAccessUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FlowTaskSearchController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月11日 下午2:36:18 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月11日      chentm          v1.0.0               修改原因
*/
@Controller
public class FlowTaskSearchController {
	
	private static Logger logger=LoggerFactory.getLogger(FlowTaskSearchController.class);
	
	@Resource
	private FlowBaseService flowBaseService;
	
	@Resource
	private TaskService taskService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private RuntimeService runtimeService;
	
	@Resource
	private HistoryService historyService;
	
	@Resource
	private SystemConfig systemConfig;
	
	/**
	 * 
	* @Function:task 
	* @Description: 个人事项页面跳转
	* @param mv
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: chentm
	* @author: qjj 添加注释
	* @date: 2019年8月19日 上午9:22:27 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     qjj           v1.0.0                          修改原因
	 */
	@RequestMapping(value = "/action/flow/task/task",method = RequestMethod.GET)
	public String task(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {

		return "/flow/task/taskPage";
	}
	/**
	 * 
	* @Function:todoListPage 
	* @Description: 个人事项待办页面跳转
	* @param mv
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: chentm
	* @author: qjj 添加注释
	* @date: 2019年8月19日 上午9:20:48 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/flow/task/todoListPage",method = RequestMethod.GET)
	public String todoListPage(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {

		return "/flow/task/todoList";
	}
	/**
	 * 
	* @Function:rodoListPage 
	* @Description:个人事项已办页面跳转 
	* @param mv
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: chentm
	* @author: qjj 添加注释
	* @date: 2019年8月19日 上午9:17:55 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/flow/task/redoListPage",method = RequestMethod.GET)
	public String redoListPage(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {

		return "/flow/task/redoList";
	}
	
	

	/**
	 * 
	* @Function:redoList 
	* @Description:个人事项已办列表数据
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: chentm
	* @author: qjj 添加注释
	* @date: 2019年8月19日 上午9:15:09 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/flow/task/redoList")
    @ResponseBody
    public String redoList(HttpServletRequest request,HttpServletResponse response) {
	    Integer pageSize=Integer.parseInt(request.getParameter("pageSize"));
	    Integer pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
	    if(StringUtils.isBlank(SysAccessUser.get().getCode())) {
	    	return "";
	    }
	    List<TaskBean> tt=new ArrayList<TaskBean>();
		long total=historyService.createHistoricTaskInstanceQuery().taskAssignee(SysAccessUser.get().getCode()).count();
		List<HistoricTaskInstance> tkList= historyService.createHistoricTaskInstanceQuery().taskAssignee(SysAccessUser.get().getCode()).includeProcessVariables().includeTaskLocalVariables().orderByTaskCreateTime().desc().listPage(pageIndex*pageSize, pageSize);
		if(tkList!=null) {
			tkList.forEach(s->{
				TaskBean t=new TaskBean();
	    		t.setTaskId(s.getId());
	    		t.setOperMethod("view");
	    		t.setEntityCode((String)s.getProcessVariables().get(FlowConstans.FLOW_VARS_DATA_KEY));
	    		String formUrl=(String)s.getProcessVariables().get(FlowConstans.FLOW_FORM_DEFAULT_URL);
	    		t.setFormUrl(formUrl);
	    		t.setName((String)s.getProcessVariables().get(FlowConstans.FLOW_PROCESS_NAME)+"["+s.getName()+"]");
	    		t.setOwner((String)s.getTaskLocalVariables().get(FlowConstans.FLOW_TASK_OWNER));
	    		t.setOwnerName((String)s.getTaskLocalVariables().get(FlowConstans.FLOW_TASK_OWNER_NAME));
	    		t.setCreateDate(s.getCreateTime());
	    		tt.add(t);
			});
		}
		
	    Pagenate pageNate=new Pagenate();
		pageNate.setiTotalDisplayRecords(total);
		pageNate.setiTotalRecords(total);
		pageNate.setTotal(total);
	    pageNate.setData(tt);
        return JSON.toJSONString(pageNate);
    }
	
	/**
	 * 
	* @Function:todoList 
	* @Description:个人事项待办列表数据
	* @param request
	* @param response
	* @return json格式分页数据
	* @throws：异常描述
	* @version: v1.0.0
	* @author: chentm
	* @author: qjj 添加注释
	* @date: 2019年8月19日 上午9:13:42 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/flow/task/todoList")
    @ResponseBody
    public String todoList(HttpServletRequest request,HttpServletResponse response) {
	    Integer pageSize=Integer.parseInt(request.getParameter("pageSize"));
	    Integer pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
	    if(StringUtils.isBlank(SysAccessUser.get().getCode())) {
	    	return "";
	    }
	    List<TaskBean> tt=new ArrayList<TaskBean>();
	    long total = taskService.createTaskQuery().taskAssignee(SysAccessUser.get().getCode()).count();
	    List<Task> tasks = taskService.createTaskQuery().taskAssignee(SysAccessUser.get().getCode()).includeProcessVariables().includeTaskLocalVariables().orderByTaskCreateTime().desc().listPage(pageIndex*pageSize, pageSize);
	    Pagenate pageNate=new Pagenate();
		pageNate.setiTotalDisplayRecords(total);
		pageNate.setiTotalRecords(total);
		pageNate.setTotal(total);
		
	    if(tasks!=null) {
	    	tasks.stream().forEach(s->{
	    		TaskBean t=new TaskBean();
	    		t.setTaskId(s.getId());
	    		if(StringUtils.equals(s.getTaskDefinitionKey(), (String)s.getProcessVariables().get(FlowConstans.FLOW_START_ACTIVITY_ID))) {
	    			t.setOperMethod("edit");
	    		}else {
	    			t.setOperMethod("view");
	    		}
	    		t.setEntityCode((String)s.getProcessVariables().get(FlowConstans.FLOW_VARS_DATA_KEY));

	    		String formUrl=(String)s.getProcessVariables().get(FlowConstans.FLOW_FORM_DEFAULT_URL);
	    		t.setFormUrl(formUrl);
	    		t.setName((String)s.getProcessVariables().get(FlowConstans.FLOW_PROCESS_NAME)+"["+s.getName()+"]");
	    		t.setOwner((String)s.getTaskLocalVariables().get(FlowConstans.FLOW_TASK_OWNER));
	    		t.setOwnerName((String)s.getTaskLocalVariables().get(FlowConstans.FLOW_TASK_OWNER_NAME));
	    		t.setCreateDate(s.getCreateTime());
	    		tt.add(t);
	    	});
	    }
	    pageNate.setData(tt);
        return JSON.toJSONString(pageNate);
    }
	
}
