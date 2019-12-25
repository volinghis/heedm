/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.flow.utils 
 * @author: chentm   
 * @date: 2019年7月18日 下午4:28:31 
 */
package com.ehs.security.flow.utils;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FlowConstans.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月18日 下午4:28:31 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月18日      chentm          v1.0.0               修改原因
*/
public class FlowConstans {

	public static final String FLOW_START_ACTIVITY_ID="startActivityId";
	
	/**
	 * 开始环节ID
	 */
	public static final String FLOW_START="startevent1";
	public static final String FLOW_END="endevent1";
	
	public static final String FLOW_PROCESS_NAME="processName";
	/**
	 * 任务处理人ID后缀
	 */
	public static final String FLOW_ASSIGNEE_USER_REGIX="User";
	
	public static final String FLOW_TASK_OWNER="owner";
	public static final String FLOW_TASK_OWNER_NAME="ownerName";
	public static final String FLOW_TASK_ENTITY_ID="entityId";
	/**
	 * 节点创建事件
	 */
	public static final String FLOW_TASK_LISTENER_CREATE="create";
	
	/**
	 * 节点处理事件
	 */
	public static final String FLOW_TASK_LISTENER_COMPLETE="complete";
	
	public static final String FLOW_ACTIVITY_LISTENER_START="start";

	public static final String FLOW_STATUS_DISCARD="flowStatus_discard";
	public static final String FLOW_STATUS_END="flowStatus_end";
	public static final String FLOW_STATUS_APPROVE="flowStatus_approve";
	public static final String FLOW_STATUS_DRAFT="flowStatus_draft";
	
	public static final String FLOW_FORM_DEFAULT_URL="formDefaultUrl";
	public static final String FLOW_SERVER_CONTEXT_PATH="server.servlet.context-path";
	
	/**
	 * 流程关联数据
	 */
	public static final String FLOW_VARS_DATA_KEY="code";
	
	/**
	 * 流程关联实例
	 */
	public static final String FLOW_VARS_CLASS_KEY="dataClass";
	
	
}
