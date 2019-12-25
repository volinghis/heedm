package com.ehs.security.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.utils.BaseUtils;
import com.ehs.security.utils.SysAccessUser;
import com.ehs.security.entity.SysLoginLog;
import com.ehs.security.entity.SysUser;
import com.ehs.security.entity.User;
import com.ehs.security.service.LoginLogService;
import com.ehs.security.service.UserService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: LoginLogServiceImpl.java
* @Description:登录日志实现类
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年6月21日 下午5:46:30 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月21日     qjj           v1.0.0               修改原因
*/
@Service
public class LoginLogServiceImpl implements LoginLogService {

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private UserService userService;
	
	
	/**
	 * 
	* @see com.ehs.security.service.LoginLogService#addLoginLog(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public void addLoginLog(String userAccount,String ip) {
       User curUser=userService.findUserByAccount(userAccount);
	    SysLoginLog logEntity=new SysLoginLog();
        logEntity.setAccount(curUser.getAccount());
        logEntity.setIp(ip);
        logEntity.setTime(BaseUtils.getNow());
        logEntity.setName(curUser.getName());
		baseCommonService.saveOrUpdate(logEntity);
	}
	/**
	 * 
	* @see com.ehs.security.service.LoginLogService#findPagenate(java.lang.Class, org.springframework.data.jpa.domain.Specification, com.ehs.security.query.Pagenate, org.springframework.data.domain.Sort[])
	 */
	@Override
	public Pagenate findPagenate(Class clazz, Specification sf, Pagenate pageNate, Sort... sorts) {
		// TODO Auto-generated method stub
		return baseCommonService.findPagenate(clazz, sf, pageNate, sorts);
	}

	

}
