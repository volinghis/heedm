/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.web.base.config 
 * @author: chentm   
 * @date: 2019年6月4日 下午1:17:35 
 */
package com.ehs.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: PlatformConfig.java
* @Description: 通过注解得到参数
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月4日 下午1:17:35 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月4日      chentm          v1.0.0               修改原因
*/
@Configuration
@PropertySource({"classpath:base-platform.properties"})
public class PlatformConfig {
	
	@Value("${base.server.domain}") 
	private String platformDomain;

	/**
	 * @return the platformDomain
	 */
	public String getPlatformDomain() {
		return platformDomain;
	}

	/**
	 * @param platformDomain the platformDomain to set
	 */
	public void setPlatformDomain(String platformDomain) {
		this.platformDomain = platformDomain;
	}

}
