package com.ehs.security.web.organizationManager.service;

import java.util.List;

import com.ehs.security.entity.OrganizationInfo;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: edmOrgService.java
* @Description: 组织管理接口
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年7月5日 上午10:14:18 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月5日     qjj           v1.0.0               修改原因
*/
public interface OrganizationManagerService {
	
	/**
	 * 
	* @Function: OrganizationManagerService.java
	* @Description: 根据该点code查找下面所有子节点
	*
	* @param parentCode ---> 节点code
	* @return 该节点下所有子节点集合
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月16日 下午1:51:01 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     zhaol           v1.0.0               修改原因
	 */
	public List<OrganizationInfo> findOrgsByCode(String parentCode);

	/**   
	* @Function:saveSortChangedEntity 
	* @Description: 部门管理上移或下移
	* @param orId
	* @param orSort
	* @param toId
	* @param toSort
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月27日 下午3:44:18 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月27日     qjj        v1.0.0            修改原因
	*/
	public void saveSortChangedEntity(String orId, int orSort, String toId, int toSort);
}
