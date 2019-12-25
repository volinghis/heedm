package com.ehs.security.service;

import java.util.List;

import com.ehs.security.entity.MenuRole;

public interface MenuRoleService {
	
	/**
	 * 
	* @Function:getRoleMenu 
	* @Description: 获取菜单所拥有的角色
	* @param code 菜单code
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午3:37:35 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public List<MenuRole> getRoleMenu(String code);
	
}
