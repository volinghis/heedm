/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.base 
 * @author: chentm   
 * @date: 2019年5月20日 下午3:08:58 
 */
package com.ehs.security.filter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.ehs.security.config.PlatformConfig;
import com.ehs.security.config.StaticPatternsConfig;
import com.ehs.security.service.SysUserSearchService;
import com.ehs.security.service.UserService;
import com.ehs.security.utils.BaseUtils;
import com.ehs.security.utils.SessionBean;
import com.ehs.security.utils.SessionConstants;
import com.ehs.security.utils.SpringUtils;
import com.ehs.security.utils.SysAccessUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: LoginFilter.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月20日 下午3:08:58 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月20日      chentm          v1.0.0               修改原因
*/

public class LoginFilter implements Filter{
	
	private static Logger logger=LoggerFactory.getLogger(LoginFilter.class);




	/** 
	* @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)  
	* @Function: LoginFilter.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月20日 下午3:09:43 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月20日      chentm           v1.0.0               修改原因
	*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		String url=httpRequest.getRequestURI();
		if(StringUtils.equals(url, "/")) {
			chain.doFilter(httpRequest, response);
			return ;
		}
		if(StringUtils.isNotBlank( httpRequest.getContextPath())) {
			url=StringUtils.substringAfter(url, httpRequest.getContextPath());
		}
		if(SpringUtils.getBean(StaticPatternsConfig.class).matchs(url)) {
			chain.doFilter(httpRequest, response);
			return ;
		}
		SessionBean sessionBean=SpringUtils.getBean(SessionBean.class);
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		logger.debug(httpRequest.getContextPath()+"进入项目校验登录");
		String result=sessionBean.valid(httpRequest);
		if(!StringUtils.equals(result, SessionConstants.VALID_OK)) {
			String errorMsg="";
			if(StringUtils.equals(result, SessionConstants.VALID_NO_USER)){
				errorMsg=URLEncoder.encode("会话已失效，请重新登录！","utf-8");
			}else if(StringUtils.equals(result, SessionConstants.VALID_IP_ERROR)) {
				errorMsg=URLEncoder.encode("你的账号已在其他地点登录！","utf-8");
			}else {
				errorMsg=URLEncoder.encode("系统异常！","utf-8");
			}
			httpResponse.sendRedirect(SessionConstants.VALID_ERROR_URL+ "?errorMsg="+errorMsg);
		}else {
			sessionBean.login(sessionBean.getSession(httpRequest), httpRequest);
			SysAccessUser.set(SpringUtils.getBean(UserService.class).getUserForCache(sessionBean.getSession(httpRequest)));
			request.setAttribute(SessionConstants.SESSION_USER_ACCOUNT,sessionBean.getSession(httpRequest));
			chain.doFilter(httpRequest, httpResponse);
		}
//		Cookie cookie=SpringUtils.getBean(CookieBean.class).findCookie(httpRequest);
//		if(cookie==null||StringUtils.isBlank(cookie.getValue())) {
//			logger.error(httpRequest.getContextPath()+"cookie为null转到登录页面");
//			httpResponse.sendRedirect(SpringUtils.getBean(PlatformConfig.class).getPlatformURL());
//			
//		}else {
//			String result=SpringUtils.getBean(CookieBean.class).checkLogin(cookie.getValue(), httpRequest.getRemoteAddr());
//			if(StringUtils.equals(result, "ok")) {
//				logger.debug(httpRequest.getContextPath()+"认证成功！");
//				SysAccessUser.set(SpringUtils.getBean(UserService.class).getUserForCache(cookie.getValue()));
//				httpRequest.setAttribute("SESSION_USER_ACCOUNT", cookie.getValue());
//				SpringUtils.getBean(CookieBean.class).reflushCookie(cookie.getValue(), httpRequest.getRemoteAddr());
//				chain.doFilter(httpRequest, httpResponse);
//			}else {
//				logger.error(httpRequest.getContextPath()+"用户已在其他地方进行登录，转到登录页面");
//				httpResponse.sendRedirect(SpringUtils.getBean(PlatformConfig.class).getPlatformURL());
//			}
//		}
		
		
		
		
	}

}
