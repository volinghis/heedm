/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service 
 * @author: chentm   
 * @date: 2019年6月20日 上午10:55:50 
 */
package com.ehs.security.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.ehs.security.entity.UserMenu;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserMenuService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月20日 上午10:55:50 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月20日      chentm          v1.0.0               修改原因
*/
public interface UserMenuService {

	/**
	 * 
	* @Function:findUserMenus 
	* @Description: 查找当前用户账号和菜单所拥有的子菜单
	* @param account 用户账号
	* @param parentCode 父节点code
	* @param hasChildren 是否有子节点（布尔）
	* @param sorts 排序
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午3:48:19 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public List<UserMenu> findUserMenus(String account,String parentCode,boolean hasChildren,Sort... sorts);
	
	/**
	 * 
	* @Function:findUserMenu 
	* @Description:根据用户账号和菜单code查找
	* @param account 用户账号
	* @param menuCode 菜单唯一标识
	* @param sorts
	* @return 用户菜单
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午3:50:51 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public UserMenu findUserMenu(String account,String menuCode,Sort... sorts);
	
	/**
	 * 
	* @Function:setUserMenusToCache 
	* @Description: 将用户关联菜单存入缓存中
	* @param account 用户账号
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午3:50:55 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public void setUserMenusToCache(String account);
	
	/**
	 * 
	* @Function:getUserMenusForCache 
	* @Description: 从缓存中取出当前用户所关联菜单
	* @param account 用户账号
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午3:50:59 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public List<UserMenu> getUserMenusForCache(String account);
	
	
}
