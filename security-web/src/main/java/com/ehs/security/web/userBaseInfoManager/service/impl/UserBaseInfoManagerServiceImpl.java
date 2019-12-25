package com.ehs.security.web.userBaseInfoManager.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ehs.security.entity.SysUser;
import com.ehs.security.entity.UserBaseInfo;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.utils.MD5Util;
import com.ehs.security.web.userBaseInfoManager.service.UserBaseInfoManagerService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserBaseInfoManagerServiceImpl.java
* @Description: 用户管理实现类
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年7月5日 下午2:55:01 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月5日     qjj           v1.0.0               修改原因
*/
@Service
public class UserBaseInfoManagerServiceImpl implements UserBaseInfoManagerService {

	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * @see com.ehs.security.web.userBaseInfo.userBaseInfoManager.service.UserBaseInfoManagerService#findUserBaseInfoByOrgCode(java.lang.String)  
	 */
	@Override
	public Pagenate findUserBaseInfoByOrgCode(String orgCode,Pagenate pagenate) {
		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> {
			return criteriaBuilder.and( StringUtils.isBlank(orgCode)?criteriaBuilder.isNull(root.get(UserBaseInfo.ORG_CODE)):criteriaBuilder.equal(root.get(UserBaseInfo.ORG_CODE),orgCode ));
		};
	    return baseCommonService.findPagenate(UserBaseInfo.class, sf,pagenate, new Sort(Direction.ASC,UserBaseInfo.CREATION_TIME));
	}
	
	
	/**
	 * @see com.ehs.security.web.userBaseInfo.userBaseInfoManager.service.UserBaseInfoManagerService#saveUserInfoAndSysUser(com.ehs.security.entity.UserBaseInfo)  
	 */
	@Transactional
	@Override
	public void saveUserInfoAndSysUser(UserBaseInfo userBaseInfo) {
		String dataCode = userBaseInfo.getDataCode();
		SysUser sysUser=new SysUser();
		sysUser.setAccount(dataCode);
		sysUser.setState(0);
		sysUser.setVersionId(0l);
		sysUser.setCode(UUID.randomUUID().toString());
		sysUser.setPassword(MD5Util.string2MD5("123456"));
		baseCommonService.saveOrUpdate(sysUser);
		
		userBaseInfo.setRefAccount(sysUser.getCode());
		baseCommonService.saveOrUpdate(userBaseInfo);
	}
}
