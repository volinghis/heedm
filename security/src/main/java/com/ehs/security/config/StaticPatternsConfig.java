/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.config 
 * @author: chentm   
 * @date: 2019年6月14日 上午11:36:34 
 */
package com.ehs.security.config;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: StaticPatternsConfig.java
* @Description: 通过注解得到所有静态文件路径
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月14日 上午11:36:34 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月14日      chentm          v1.0.0               修改原因
*/
@Configuration
@ConfigurationProperties(prefix = "patterns")
@PropertySource("classpath:static-patterns.properties")
public class StaticPatternsConfig {

	private List<String> list = new ArrayList<String>();

	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<String> list) {
		this.list = list;
	}
	/**
	 * 
	* @Function: StaticPatternsConfig.java
	* @Description: 判断路径是否匹配
	*
	* @param url
	* @return：返回成功或者失败
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午2:35:49 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public boolean matchs(String url) {
		if(StringUtils.isBlank(url)) {
			return false;
		}
		for(String s:list) {
			if(Pattern.matches(s, url)) {
				return true;
			}
		}
		return false;
	}
	 
}
