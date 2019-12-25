package com.ehs.security.web.userBaseInfoManager.service;


import com.ehs.security.entity.UserBaseInfo;
import com.ehs.security.query.Pagenate;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserBaseInfoManagerService.java
* @Description: 用户管理接口类
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年7月5日 下午2:52:15 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月5日     qjj           v1.0.0               修改原因
*/
public interface UserBaseInfoManagerService {

	/**
	 * 
	* @Function:findUserBaseInfoByOrgCode 
	* @Description: 查找组织机构下的所有用户
	* @param orgCode 组织机构code
	* @param pagenate 分页条件
	* @return  用户分页数据集合
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 上午11:45:22 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	public Pagenate findUserBaseInfoByOrgCode(String orgCode,Pagenate pagenate);
	
	/**
	 * 
	* @Function:saveUserInfoAndSysUser 
	* @Description: 保存用户信息到 USER_BASE_INFO和SYS_USER表中
	* @param userBaseInfo 用户实体
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 上午11:39:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	public void saveUserInfoAndSysUser(UserBaseInfo userBaseInfo);
}
