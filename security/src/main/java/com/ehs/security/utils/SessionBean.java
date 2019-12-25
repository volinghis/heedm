/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.utils 
 * @author: chentm   
 * @date: 2019年7月22日 下午5:34:01 
 */
package com.ehs.security.utils;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: SessionBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月22日 下午5:34:01 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月22日      chentm          v1.0.0               修改原因
*/
@Configuration
public class SessionBean {

	@Resource
	private RedisTemplate  redisTemplate;
	
	/**
	 * 
	* @Function: SessionBean.java
	* @Description: 查询用户账号
	*
	* @param request
	* @return：用户账号
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午4:52:39 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public String getSession(HttpServletRequest request) {
		return (String)request.getSession().getAttribute(SessionConstants.SESSION_USER_ACCOUNT);
	}
	
	/**
	 * 
	* @Function: SessionBean.java
	* @Description: 退出登录
	*
	* @param request
	* @return：无
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午4:55:02 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public void logout(HttpServletRequest request) {
		String userAccount=getSession(request);
		request.getSession().removeAttribute(SessionConstants.SESSION_USER_ACCOUNT);
		redisTemplate.delete(userAccount);
	}
	
	/**
	 * 
	* @Function: SessionBean.java
	* @Description: 登录
	*
	* @param userAccount
	* @param request
	* @return：无
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午4:55:38 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public void login(String userAccount,HttpServletRequest request) {
		request.getSession().setAttribute(SessionConstants.SESSION_USER_ACCOUNT, userAccount);
		addLoginInfoToCache(userAccount, BaseUtils.getIpAddress(request));
	}
	
	/**
	 * 
	* @Function: SessionBean.java
	* @Description: 从session里删除
	*
	* @param request
	* @return：无
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午4:55:58 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public void removeSession(HttpServletRequest request) {
		request.getSession().removeAttribute(SessionConstants.SESSION_USER_ACCOUNT);
	}
	
	/**
	 * 
	* @Function: SessionBean.java
	* @Description: 把登录信息存入缓存
	*
	* @param userAccount：用户账号
	* @param remoteIp：远程IP
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午4:56:31 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	private void  addLoginInfoToCache(String userAccount,String remoteIp) {
		redisTemplate.opsForValue().set(userAccount, remoteIp,SessionConstants.MAX_TIME_OUT,TimeUnit.SECONDS);
	}
	
	/**
	 * 
	* @Function: SessionBean.java
	* @Description: 从缓存中查询登陆信息
	*
	* @param userAccount：用户账号
	* @return：该账号信息
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午4:57:02 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	private String getLoginInfoFromCache(String userAccount) {
		return (String)redisTemplate.opsForValue().get(userAccount);
	}
	
	/**
	 * 
	* @Function: SessionBean.java
	* @Description: 验证账号
	*
	* @param request
	* @return：返回session状态码
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午4:57:34 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public String valid(HttpServletRequest request) {
		try {
			String sessionAccount=(String)request.getSession().getAttribute(SessionConstants.SESSION_USER_ACCOUNT);
			if(StringUtils.isBlank(sessionAccount)||StringUtils.isBlank(getLoginInfoFromCache(sessionAccount))) {
				return SessionConstants.VALID_NO_USER;
			}else {
//				if(!StringUtils.equals(BaseUtils.getIpAddress(request), getLoginInfoFromCache(sessionAccount))) {
//					return SessionConstants.VALID_IP_ERROR;
//				}else {
					return SessionConstants.VALID_OK;	
//				}
			}
		}catch (Exception e) {
			return SessionConstants.VALID_SYSTEM_ERROR;
		}
	}
}
