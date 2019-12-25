package com.ehs.security.service;

import java.util.List;

import com.ehs.security.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: InitDataService.java
* @Description: 初始化实体
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年10月10日 下午3:42:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年10月10日     zhaol           v1.0.0               修改原因
*/
public interface InitDataService {
	
	/**
	 * 
	* @Function: InitDataService.java
	* @Description: 初始化所有实体
	*
	* @param baseEntities：所有实体
	* @return：无
	* @throws：Exception
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午3:43:00 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public void initDataEntity(List<BaseEntity> baseEntities) throws Exception;
}
