package com.ehs.security.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ehs.security.entity.UserRole;
import com.ehs.security.query.Pagenate;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserRoleService.java
* @Description:用户角色接口
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年6月20日 上午9:40:08 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月20日     qjj           v1.0.0               修改原因
*/
public interface UserRoleService {
	
	/**
	 * 
	* @Function:findUserRoleByAccount 
	* @Description: 获取当前用户拥有的角色
	* @param userAccount 用户账号
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:17:13 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public List<UserRole>  findUserRoleByAccount(String userAccount);
	
	/**
	 * 
	* @Function:findRoleCodeByAccount 
	* @Description: 获取当前用户关联的所有角色code
	* @param userAccount 用户账号
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:17:55 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public List<String> findRoleCodeByAccount(String userAccount);
 	
	/**
	 * 
	* @Function:findPagenateByAccount 
	* @Description: 分页查询当前用户所拥有的角色
	* @param userAccount 用户账号
	* @param clazz
	* @param pageNate
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:22:07 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public Pagenate findPagenateByAccount(String userAccount,Class clazz,Pagenate pageNate);
	
}
