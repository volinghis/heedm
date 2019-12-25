/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.flow.config 
 * @author: chentm   
 * @date: 2019年7月16日 上午10:21:24 
 */
package com.ehs.security.flow.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.TransactionFactory;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FlowableConfig.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月16日 上午10:21:24 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月16日      chentm          v1.0.0               修改原因
*/
@Controller
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration>  {

	
	@Resource
	private DataSource dataSource;
	
	   @Autowired
	    PlatformTransactionManager transactionManager;
	


	/** 
	* @see org.flowable.spring.boot.EngineConfigurationConfigurer#configure(java.lang.Object)  
	* @Function: FlowableConfig.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月16日 上午10:46:20 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月16日      chentm           v1.0.0               修改原因
	*/
	@Override
	public void configure(SpringProcessEngineConfiguration engineConfiguration) {
		engineConfiguration.setActivityFontName("宋体");
        engineConfiguration.setLabelFontName("宋体");
        engineConfiguration.setAnnotationFontName("宋体");
	}

}
