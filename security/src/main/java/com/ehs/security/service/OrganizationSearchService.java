/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service 
 * @author: chentm   
 * @date: 2019年7月3日 下午4:50:51 
 */
package com.ehs.security.service;

import com.ehs.security.entity.OrganizationInfo;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationSearchService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月3日 下午4:50:51 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月3日      chentm          v1.0.0               修改原因
*/
public interface OrganizationSearchService {
	
	/**
	 * 
	* @Function:findByCode 
	* @Description: 获取组织信息
	* @param orgCode 组织机构的唯一标识
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午3:42:53 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public OrganizationInfo findByCode(String orgCode);
}
