/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.flow.service 
 * @author: chentm   
 * @date: 2019年7月17日 上午9:24:08 
 */
package com.ehs.security.flow.service;

import java.util.Map;

import org.flowable.engine.runtime.ProcessInstance;

import com.ehs.security.entity.BaseEntity;
import com.ehs.security.flow.entity.FlowBaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FlowBaseService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月17日 上午9:24:08 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月17日      chentm          v1.0.0               修改原因
*/
public interface FlowBaseService {

	public void processReject(String processInstanceId);
	
	public void processSend(String processInstanceId,String flowOper);
	
	public void processEnd(String processInstanceId);
	
	public ProcessInstance startProcess(FlowBaseEntity flowBaseEntity,Map vars);
}
