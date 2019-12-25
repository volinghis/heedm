/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.controller 
 * @author: chentm   
 * @date: 2019年6月18日 上午11:40:08 
 */
package com.ehs.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: ErrorController.java
* @Description: 
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月18日 上午11:40:08 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月18日      chentm          v1.0.0               修改原因
*/
@Controller
public class ErrorController {

	private Configuration cfg;
	
	public ErrorController() {
		 cfg=new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		 cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates");
	}
	
	
	
	
	@RequestMapping(value = "/404")
	@ResponseBody
	public String error404(HttpServletResponse response) {
		Template porTemplate=null;
		try {
			 porTemplate=cfg.getTemplate("/error/404.ftl");
			 Map<String, String> map=new HashMap<String, String>();
			 return FreeMarkerTemplateUtils.processTemplateIntoString(porTemplate, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "系统错误";
	}
	@RequestMapping(value = "/500")
	@ResponseBody
	public String error500(HttpServletRequest request,HttpServletResponse response) {
		Template porTemplate=null;
		try {
			 porTemplate=cfg.getTemplate("/error/500.ftl");
			 Map<String, String> map=new HashMap<String, String>();
			 map.put("errorMsg",request.getParameter("errorMsg"));
			 return FreeMarkerTemplateUtils.processTemplateIntoString(porTemplate, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "系统错误";
	}
	
	@RequestMapping(value = "/userValidError")
	@ResponseBody
	public String userValidError(HttpServletRequest request,HttpServletResponse response) {
		Template porTemplate=null;
		try {
			 porTemplate=cfg.getTemplate("/error/userValidError.ftl");
			 Map<String, String> map=new HashMap<String, String>();
			 map.put("errorMsg",request.getParameter("errorMsg"));
			 return FreeMarkerTemplateUtils.processTemplateIntoString(porTemplate, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "系统错误";
	}
	
	
	@RequestMapping(value = "/503")
	@ResponseBody
	public String error503(HttpServletResponse response) {
		Template porTemplate=null;
		try {
			 porTemplate=cfg.getTemplate("/error/503.ftl");
			 Map<String, String> map=new HashMap<String, String>();
			 return FreeMarkerTemplateUtils.processTemplateIntoString(porTemplate, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "系统错误";
	}

	
}
