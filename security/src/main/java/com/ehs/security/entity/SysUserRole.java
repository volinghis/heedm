package com.ehs.security.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserRole.java
* @Description: 用户角色实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年5月13日 下午5:23:08 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月13日     zhaol           v1.0.0               修改原因
*/
/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: SysUserRole.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Mapleave
* @date: 2019年5月23日 下午5:03:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月23日     Mapleave           v1.0.0               修改原因
*/
@Entity
@Table(name = "SYS_USER_ROLE")
public class SysUserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;
	public static final String SYS_USER_CODE="sysUserCode";
	public static final String ROLE_CODE="roleCode";
	
	
	private String roleCode;

	private String sysUserCode;
	



	public String getSysUserCode() {
		return sysUserCode;
	}



	public void setSysUserCode(String sysUserCode) {
		this.sysUserCode = sysUserCode;
	}



	public String getRoleCode() {
		return roleCode;
	}



	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}



	/** 
	* @see com.ehs.security.entity.BaseEntity#getForeignClasses()  
	* @Function: SysUserRole.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月4日 下午3:46:38 
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
