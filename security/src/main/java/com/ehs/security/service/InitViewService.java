/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service 
 * @author: chentm   
 * @date: 2019年6月10日 下午5:35:41 
 */
package com.ehs.security.service;

import java.util.List;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: InitViewService.java
* @Description: 初始化视图
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月10日 下午5:35:41 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月10日      chentm          v1.0.0               修改原因
*/
public interface InitViewService {

	/**
	 * 
	* @Function: InitViewService.java
	* @Description: 执行所有视图
	*
	* @param ddl
	* @return：无
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午3:45:35 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public void executeDdl(List<String> ddl);
}
