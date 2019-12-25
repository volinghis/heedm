/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.login.filter 
 * @author: chentm   
 * @date: 2019年5月27日 下午4:38:28 
 */
package com.ehs.security.filter;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ehs.security.config.PlatformConfig;
import com.ehs.security.utils.SpringUtils;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: PathFilter.java
* @Description: 对请求路径进行过滤
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月27日 下午4:38:28 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月27日      chentm          v1.0.0               修改原因
*/
public class PathFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(PathFilter.class);

	/** 
	* @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)  
	* @Function: PathFilter.java 
	* @Description: 过滤路径
	*
	* @param request
	* @param response
	* @param chain
	* @return：返回结果描述
	* @throws：IOException ServletException
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月27日 下午4:38:28 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月27日      chentm           v1.0.0               修改原因
	*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		String globalPath=new StringBuilder().append(httpRequest.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort()).append(httpRequest.getContextPath()).toString();
		if(StringUtils.endsWith(globalPath, "/")) {
			globalPath=StringUtils.substringBeforeLast(globalPath, "/");
		}
		String addr = InetAddress.getLocalHost().getHostAddress();//获得本机IP 
		logger.info("本机IP是："+addr);
		request.setAttribute("globalPath", request.getServerName());
		request.setAttribute("globalContextPath", httpRequest.getContextPath());
		request.setAttribute("domain", SpringUtils.getBean(PlatformConfig.class).getPlatformDomain());
		chain.doFilter(httpRequest, response);
	}

}
