/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.utils 
 * @author: chentm   
 * @date: 2019年5月28日 下午2:46:58 
 */
package com.ehs.security.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: RoleConfig.java
* @Description: 通过注解拿到角色
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月28日 下午2:46:58 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月28日      chentm          v1.0.0               修改原因
*/
@Configuration
@PropertySource("classpath:roleConfig.propreties")
public class RoleConfig {
	
	@Value("${normalRoleCode}")
	private String normalRoleCode;
	
	
	@Value("${adminRoleCode}")
	private String adminRoleCode;
	/**
	 * @return the normalRoleCode
	 */
	public String getNormalRoleCode() {
		return normalRoleCode;
	}
	/**
	 * @param normalRoleCode the normalRoleCode to set
	 */
	public void setNormalRoleCode(String normalRoleCode) {
		this.normalRoleCode = normalRoleCode;
	}
	/**
	 * @return the adminRoleCode
	 */
	public String getAdminRoleCode() {
		return adminRoleCode;
	}
	/**
	 * @param adminRoleCode the adminRoleCode to set
	 */
	public void setAdminRoleCode(String adminRoleCode) {
		this.adminRoleCode = adminRoleCode;
	}
	
}
