package com.ehs.security.web.account.service;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: AccountService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Mapleave
* @date: 2019年8月16日 下午3:49:58 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年8月16日     Mapleave           v1.0.0               修改原因
*/
public interface AccountService {
	
	/**
	 * 
	* @Function: AccountService.java
	* @Description:给帐号授权角色，添加至用户角色表
	*
	* @param account ---> 帐号
	* @param code ---> 角色code
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Mapleave
	* @date: 2019年8月16日 下午3:50:48 
	*
	* Modification History:
	* Date         Author          Version         Description
	*---------------------------------------------------------*
	* 2019年8月16日     Mapleave      v1.0.0                    修改原因
	 */
	public void saveAuthForCurUser(String account,String code);
	
	/**
	 * 
	* @Function: AccountService.java
	* @Description: 给帐号取消授权角色，删除用户角色表信息
	*
	* @param account
	* @param code
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Mapleave
	* @date: 2019年8月16日 下午4:51:37 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     Mapleave           v1.0.0               修改原因
	 */
	public void removeRoleForCurUser(String account,String code);
}
