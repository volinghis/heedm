/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.login.controller 
 * @author: chentm   
 * @date: 2019年5月30日 下午3:29:17 
 */
package com.ehs.base.platform.login.controller;

import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ehs.base.platform.base.config.SystemConfig;
import com.ehs.base.platform.login.config.LoginConfig;
import com.ehs.security.config.PlatformConfig;
import com.ehs.security.entity.SysUser;
import com.ehs.security.service.LoginLogService;
import com.ehs.security.service.SysUserSearchService;
import com.ehs.security.service.UserMenuService;
import com.ehs.security.service.UserService;
import com.ehs.security.utils.BaseUtils;
import com.ehs.security.utils.MD5Util;
import com.ehs.security.utils.SessionBean;
import com.ehs.security.utils.SessionConstants;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: LoginController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月30日 下午3:29:17 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月30日      chentm          v1.0.0               修改原因
*/
@Controller
public class LoginController {

	@Resource
	private SessionBean  sessionBean;
	
	@Resource
	private LoginConfig loginConfig;
	
	@Resource
	private PlatformConfig platformConfig;
	
	@Resource
	private UserMenuService userMenuService;
	
	@Resource
	private SysUserSearchService sysUserSearchService;
	
	@Resource
	private RedisTemplate<String, String> redisTemplate; 
	
	@Resource
	private SystemConfig  systemConfig;
	
	@Resource
	private UserService userService;
	
	@Resource
	private LoginLogService loginLogService;
	
	/**
	 * 
	* @Function: LoginController.java
	* @Description: 跳转登录页面
	*
	* @param httpRequest
	* @param response
	* @return 登录页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午10:17:54 
	*
	* Modification History:
	* Date         Author          Version         Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@GetMapping(value = "/action/loginV3")
	public String loginV3(HttpServletRequest httpRequest,HttpServletResponse response) {
		return loginPage(httpRequest, response)+"V3";
	}
	
	/**
	 * 
	* @Function: LoginController.java
	* @Description: 登录页面session验证，跳转不同页面
	*
	* @param httpRequest
	* @param response
	* @return 登录页面或者首页
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午10:18:43 
	*
	* Modification History:
	* Date         Author          Version         Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@GetMapping(value = "/action/login")
	public String loginPage(HttpServletRequest httpRequest,HttpServletResponse response) {
		if (!StringUtils.equals(sessionBean.valid(httpRequest), SessionConstants.VALID_OK)) {
			return "/login";
		}else {
			return "redirect:"+loginConfig.getIndexURL();	
		}
	}

	/**
	 * 
	* @Function: LoginController.java
	* @Description: 跳转登录页面
	*
	* @param request
	* @param response
	* @return 登录页面
	* @throws Exception
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午10:21:09 
	*
	* Modification History:
	* Date         Author          Version         Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@PostMapping(value = "/action/loginV3")
	public String doLoginV3(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return doLogin(request, response)+"V3";
	}
	
	/**
	 * 
	* @Function: LoginController.java
	* @Description: 登录
	*
	* @param request
	* @param response
	* @return 登录页面
	* @throws Exception
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午10:22:10 
	*
	* Modification History:
	* Date         Author          Version         Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@PostMapping(value = "/action/login")
	public String doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1、检验验证码
		String inputCode = request.getParameter("captchaCode");
		String crsfToken=request.getParameter("crsfToken");
		if (StringUtils.isNotBlank(inputCode)) {
			String captchaSession = redisTemplate.opsForValue().get(crsfToken);
			if (!StringUtils.equals(inputCode, captchaSession)) {
				request.setAttribute("msg", "验证码不正确!");
				redisTemplate.delete(crsfToken);
				request.setAttribute("crsfToken",UUID.randomUUID().toString());
				return "login";
			}else {
				redisTemplate.delete(crsfToken);
			}
		}
		String userAccount = request.getParameter("username");
		String password = request.getParameter("password");
		SysUser sysUser =sysUserSearchService.findByUserAccount(userAccount);
		String newpassword = MD5Util.string2MD5(password);
		request.setAttribute("SYS_USER", sysUser);
		if (sysUser!=null&&StringUtils.equals(userAccount, sysUser.getAccount()) && StringUtils.equals(sysUser.getPassword(), newpassword)) {
			//判断当前登录账号状态
			if(sysUser.getState()==1) {
				request.setAttribute("msg", "您的账号已被锁定，请联系管理员");
				return "/login";
			}
			userService.setUserToCache(userAccount);
			userMenuService.setUserMenusToCache(userAccount);
			sessionBean.login(userAccount, request);
			//添加登录日志 
			loginLogService.addLoginLog(userAccount,BaseUtils.getIpAddress(request));
			return "redirect:"+loginConfig.getIndexURL();
		} else {
			if(StringUtils.isNotBlank(crsfToken)) {
				redisTemplate.delete(crsfToken);
			}
			request.setAttribute("crsfToken",UUID.randomUUID().toString());
			request.setAttribute("msg", "用户名或密码错误!");
			return "/login";
		}
	}

	/**
	 * 
	* @Function: LoginController.java
	* @Description: 帐号退出
	*
	* @param request
	* @param response
	* @return 登录页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午10:22:21 
	*
	* Modification History:
	* Date         Author          Version         Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@GetMapping(value = "/action/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		sessionBean.logout(request);
		return "redirect:"+loginConfig.getLoginURL();
	}
}
