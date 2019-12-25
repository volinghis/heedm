/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.login.config 
 * @author: chentm   
 * @date: 2019年5月17日 上午10:32:55 
 */
package com.ehs.security.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: ErrorPageRegistrar.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月17日 上午10:32:55 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月17日      chentm          v1.0.0               修改原因
*/

@Configuration
public class ErrorConfigurar implements org.springframework.boot.web.server.ErrorPageRegistrar {
	/**
	 * 
	* @see org.springframework.boot.web.server.ErrorPageRegistrar#registerErrorPages(org.springframework.boot.web.server.ErrorPageRegistry)  
	* @Function: ErrorConfigurar.java
	* @Description: 错误页面描述</br>
	*
	* @param registry 
	* @return：返回错误页面
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午2:13:02 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	 @Override
	 public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[3];
        errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
        errorPages[1] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
        errorPages[2] = new ErrorPage(HttpStatus.SERVICE_UNAVAILABLE, "/503");
        registry.addErrorPages(errorPages);
	}
	
	
}
