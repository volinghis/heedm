package com.ehs.security.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ehs.security.query.Pagenate;

public interface LoginLogService{
	/**
	 * 
	* @Function: LoginLogService.java
	* @Description: 记录系统登录日志
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	 * @param userAccount 
	* @date: 2019年6月18日 下午5:10:27 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月18日     qjj           v1.0.0               修改原因
	 */
	public 	void addLoginLog(String userAccount, String ip);
	
	
	/**
	 * 
	* @Function:findPagenate 
	* @Description: 查询所有登录日志并分页
	* @param clazz
	* @param sf
	* @param pageNate
	* @param sorts
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午3:35:00 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public  Pagenate findPagenate(Class clazz, Specification sf, Pagenate pageNate, Sort... sorts);
}
