/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.scoket 
 * @author: chentm   
 * @date: 2019年5月23日 下午4:13:09 
 */
package com.ehs.security.web.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: SystemConfig.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年8月16日 下午5:15:50 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年8月16日     zhaol           v1.0.0               修改原因
 */
@Configuration
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
	
	/**
     * 从配置文件中读取的limitSizeMap开头的数据
     * 注意：名称必须与配置文件中保持一致
     */
    private Map<String, String> systemMap = new ConcurrentHashMap<String, String>();

	/**
	 * @return the systemMap
	 */
	public Map<String, String> getSystemMap() {
		return systemMap;
	}

	/**
	 * @param systemMap the systemMap to set
	 */
	public void setSystemMap(Map<String, String> systemMap) {
		this.systemMap = systemMap;
	}
}
