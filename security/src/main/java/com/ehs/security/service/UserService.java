/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service 
 * @author: chentm   
 * @date: 2019年6月10日 下午3:04:55 
 */
package com.ehs.security.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ehs.security.entity.User;
import com.ehs.security.query.Pagenate;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月10日 下午3:04:55 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月10日      chentm          v1.0.0               修改原因
*/
public interface UserService {
	
	/**
	 * 
	* @Function:findUser 
	* @Description: 根据code查找用户
	* @param code 用户唯一标识
	* @return 用户对象
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:25:21 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public User findUser(String code);
	
	/**
	 * 
	* @Function:findUsers 
	* @Description: 根据一组用户code获取用户集合
	* @param codes 
	* @return 用户集合
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:25:40 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public List<User> findUsers(String codes);
	
	/**
	 * 
	* @Function:findUserByAccount 
	* @Description: 根据账号获取用户
	* @param account 用户账号
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:41:04 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */

	public User findUserByAccount(String account);
	
	/**
	 * 
	* @Function:findUserByOrgCode 
	* @Description: 获取当前机构下的所有用户
	* @param orgCode 组织机构唯一标识
	* @return 用户集合
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:43:45 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public List<User> findUserByOrgCode(String orgCode);
	
	/**
	 * 
	* @Function:findUserListPagenate 
	* @Description: 分页查询用户列表
	* @param clazz
	* @param sf
	* @param pageNate
	* @param sorts
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:44:40 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public Pagenate findUserListPagenate(Class clazz,Specification sf, Pagenate pageNate,Sort... sorts);
	
	/**
	 * 
	* @Function:setUserToCache 
	* @Description: 将当前用户存入缓存中
	* @param account 用户账号
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:45:23 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public void setUserToCache(String account);
	
	/**
	 * 
	* @Function:getUserForCache 
	* @Description: 从缓存中获取用户
	* @param account 用户账号
	* @return 用户对象
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:46:15 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public User getUserForCache(String account);
}
