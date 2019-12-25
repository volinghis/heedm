/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service.impl 
 * @author: chentm   
 * @date: 2019年6月6日 下午3:16:33 
 */
package com.ehs.security.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.security.dao.DdlDao;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DDLservice.java
* @Description: 执行视图
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月6日 下午3:16:33 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月6日      chentm          v1.0.0               修改原因
*/
@Service
public class DdlserviceImpl implements com.ehs.security.service.Ddlservice {

	@Resource
	private DdlDao ddlDao;
	
	/** 
	* @see com.ehs.security.service.Ddlservice#executeDdl(java.lang.String)  
	* @Function: DDLservice.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月6日 下午3:16:33 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月6日      chentm           v1.0.0               修改原因
	*/
	@Override
	public void executeDdl(String ddl) {
		Assert.notNull(ddl, "ddl ddl is required");
		ddlDao.executeDdl(ddl);
	}

}
