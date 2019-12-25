/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.execute 
 * @author: chentm   
 * @date: 2019年6月24日 上午10:14:23 
 */
package com.ehs.security.execute;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: ResultBean.java
* @Description: 结果集
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月24日 上午10:14:23 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月24日      chentm          v1.0.0               修改原因
*/
public class ResultBean {

	public static final String NO_DATA_RESPONSE_URL="/500?errorMsg=未找到数据，数据或已被更新";
	
	public static final String NO_DATA="未找到数据，数据或已被更新";
	
	/**
	 * 实体id
	 */
	private String entityId;
	
	/**
	 * 信息
	 */
	private String message;
	
	/**
	 * 结果类型
	 */
	private ResultType resultType;
	
	private ResultBean() {
	}
	
	public static ResultBean getBean(String  message,ResultType resultType,String entityId) {
		ResultBean rb=new ResultBean();
		rb.setEntityId(entityId);
		rb.setMessage(message);
		rb.setResultType(resultType);
		return rb;
	}
	
	/**
	 * @return the entityId
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return the resultType
	 */
	public ResultType getResultType() {
		return resultType;
	}

	/**
	 * @param resultType the resultType to set
	 */
	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
