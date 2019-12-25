package com.ehs.security.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: SysRoleMenu.java
* @Description: 菜单角色实体
*
* @version: v1.0.0
* @author: Mapleave
* @date: 2019年5月23日 下午4:13:57 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月23日     Mapleave           v1.0.0               修改原因
*/
@Entity
@Table(name = "SYS_ROLE_MENU")
public class SysRoleMenu extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String ROLE_CODE="roleCode";
	public static final String MENU_CODE="menuCode";
	
	/**
	 * 角色编码
	 */
	private String roleCode;
	
	/**
	 * 菜单编码
	 */
	private String menuCode;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	/** 
	* @see com.ehs.security.entity.BaseEntity#getForeignClasses()  
	* @Function: SysRoleMenu.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月4日 下午3:46:16 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月4日      chentm           v1.0.0               修改原因
	*/
	@Override
	public List<String> getForeignClasses() {
		return null;
	}

	
}
