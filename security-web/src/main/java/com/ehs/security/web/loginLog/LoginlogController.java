package com.ehs.security.web.loginLog;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.SysLoginLog;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.LoginLogService;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: LoginlogController.java
 * @Description: 登录日志控制层
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年6月19日 下午4:29:38
 *
 * Modification History: Date Author Version Description
 * ---------------------------------------------------------* 2019年6月19日
 * qjj v1.0.0 修改原因
 */
@Controller
public class LoginlogController {

	@Resource
	private LoginLogService loginLogService;
	/**
	 * 
	* @Function: LoginlogController.java
	* @Description: 登录日志页面跳转
	*
	* @param
	* @return：登录日志页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年6月19日 下午4:41:05 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月19日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/loginLog")
	public String loginLog() {
		return "/loginLog/loginLogManager";
	}
	/**
	 * 
	* @Function: LoginlogController.java
	* @Description: 登录日志列表数据
	* 				searchParam：查询条件
	*
	* @param response
	* @param request
	* @return：返回数据集合
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年6月19日 下午4:40:39 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月19日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/loginLog/loginLogList")
	@ResponseBody
	public String loginLogTableData(HttpServletResponse response,HttpServletRequest request) {
		String searchParam=request.getParameter("searchParam");
		Pagenate pageNate=new Pagenate();
		pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
    	pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
    	Specification<SysLoginLog> sf=null;
		if(StringUtils.isNotBlank(searchParam)) {
		    sf=(Root<SysLoginLog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)-> criteriaBuilder.or(criteriaBuilder.like(root.get(SysLoginLog.ACCOUNT),"%"+searchParam+"%" ), criteriaBuilder.like(root.get(SysLoginLog.CODE),"%"+searchParam+"%" ));
		}
		return JSON.toJSONString(loginLogService.findPagenate(SysLoginLog.class, sf, pageNate));
	}
}
