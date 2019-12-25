package com.ehs.security.web.menuManager.service;

import com.ehs.security.entity.SysRoleMenu;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: MenuManagerService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年8月16日 上午11:04:55 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年8月16日     zhaol           v1.0.0           修改原因
*/
public interface MenuManagerService {
	
	/**
	 * 
	* @Function: MenuManagerService.java
	* @Description: 该函数的功能描述
	*
	* @param sysRoleMenu ---> 需要保存的角色菜单实体
	* @param roleCode ---> 通过roleCode去查询数据库，看是否已经拥有该角色
	* @return 返回成功或者失败信息
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月16日 上午11:05:01 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     zhaol           v1.0.0               修改原因
	 */
	public String saveRoleMenu(String menuCode,String roleCode);
	
	/**
	 * 
	* @Function: MenuManagerService.java
	* @Description: 通过得到菜单code和角色code,删除菜单角色表对应的数据
	*
	* @param menuCode --->菜单code
	* @param roleCode --->角色code
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月16日 上午11:08:57 
	*
	* Modification History:
	* Date         Author          Version         Description
	*---------------------------------------------------------*
	* 2019年8月16日     zhaol           v1.0.0               修改原因
	 */
	public void deleteRoleMenu(String menuCode,String roleCode);
	
	
}
