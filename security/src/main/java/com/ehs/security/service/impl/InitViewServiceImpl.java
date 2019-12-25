/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service.impl 
 * @author: chentm   
 * @date: 2019年6月10日 下午5:37:36 
 */
package com.ehs.security.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.security.service.Ddlservice;
import com.ehs.security.service.InitViewService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: InitViewServiceImpl.java
* @Description: 初始化视图实现类
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月10日 下午5:37:36 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月10日      chentm          v1.0.0               修改原因
*/
@Service
public class InitViewServiceImpl implements InitViewService {

	@Resource
	private Ddlservice ddlservice;
	
	/** 
	* @see com.ehs.security.service.InitViewService#executeDdl(java.util.List)  
	* @Function: InitViewServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月10日 下午5:37:36 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月10日      chentm           v1.0.0               修改原因
	*/
	@Override
	public void executeDdl(List<String> ddl) {
		Assert.notNull(ddl, "list for ddl  is required");
		ddl.forEach(s->{
			String[] ss=s.split(":");
			try {ddlservice.executeDdl(ss[0]);}catch (Exception e) {};
			ddlservice.executeDdl(ss[1]);
		});
	}

}
