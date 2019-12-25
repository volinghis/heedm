/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.portal.controller 
 * @author: chentm   
 * @date: 2019年6月12日 下午3:17:17 
 */
package com.ehs.edm.portal.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.UserMenu;
import com.ehs.security.utils.MD5Util;
import com.ehs.security.utils.SysAccessUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: PortalController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月12日 下午3:17:17 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月12日      chentm          v1.0.0               修改原因
*/
@Controller
public class PortalController {

	@RequestMapping(value = "/action/chart")
	public ModelAndView pointer(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		mv.setViewName("/chart/index");
		return mv;
	}
	
	@RequestMapping(value = "/action/portal",method = RequestMethod.GET)
	public ModelAndView portal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {

		mv.setViewName("/portal/portalV2");
		return mv;
	}

	
}
