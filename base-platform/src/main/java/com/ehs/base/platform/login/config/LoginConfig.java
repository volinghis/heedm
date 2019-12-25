package com.ehs.base.platform.login.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:login.properties"})
public class LoginConfig {
	
	/**
 	* 注入loginURL配置外部文件属性
	 */
	@Value("${login.loginURL}")
	private String loginURL;
	
	/**
	 * 注入indexURL配置外部文件属性
	 */
	@Value("${login.indexURL}")
	private String indexURL;
	
	/**
	 * 
	* @Function: LoginConfig.java
	* @Description: 映射登录页面url
	*
	* @return 登录
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午10:09:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	public String getLoginURL() {
		return loginURL;
	}

	/**
	 * 
	* @Function: LoginConfig.java
	* @Description: 映射首页url
	*
	* @return 首页
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午10:09:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	public String getIndexURL() {
		return indexURL;
	}
	
}
