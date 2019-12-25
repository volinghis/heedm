/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.controller 
 * @author: qjj   
 * @date: 2019年9月12日 上午10:27:07 
 */
package com.ehs.security.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.DataDictionary;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.service.DataDictionarySearchService;
import com.ehs.security.utils.SysAccessUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: PermissionController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年9月12日 上午10:27:07 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月12日     qjj           v1.0.0               修改原因
*/

@RestController
public class PermissionController {
	
	@Resource
	private DataDictionarySearchService dictService;
	/**
	 * 
	* @Function:checkOperPermission 
	* @Description: 页面操作按钮权限验证
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月12日 上午10:30:10 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月12日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value="/action/checkOperPermission")
	public String checkOperPermission(HttpServletRequest request) {
		String code=request.getParameter("code");
		DataDictionary dict= dictService.findOne(code);
		String relatedDepart="";
		if(dict!=null) {
			relatedDepart=dict.getRelatedDepart();
		}
		//获取当前登录用户所属机构
		String orgCode=	SysAccessUser.get().getOrgCode();
		if(StringUtils.isNotBlank(relatedDepart)&&StringUtils.isNotBlank(orgCode)) {
			if(!relatedDepart.contains(orgCode)) {
				return JSON.toJSONString(ResultBean.getBean("您不属于本专业，不可操作",ResultType.ERROR,null));
			}
		}
		 return JSON.toJSONString(ResultBean.getBean("验证通过",ResultType.OK,null));
	}

}
