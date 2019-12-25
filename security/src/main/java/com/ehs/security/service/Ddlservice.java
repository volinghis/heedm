/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service 
 * @author: chentm   
 * @date: 2019年6月6日 下午3:15:50 
 */
package com.ehs.security.service;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DDLservice.java
* @Description: 执行视图
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月6日 下午3:15:50 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月6日      chentm          v1.0.0               修改原因
*/
public interface Ddlservice {

	/**
	 * 
	* @Function: Ddlservice.java
	* @Description: 执行视图
	*
	* @param ddl
	* @return：无
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午3:38:43 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public void executeDdl(String ddl);
}
