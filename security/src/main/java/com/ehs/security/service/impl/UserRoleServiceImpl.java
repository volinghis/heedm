package com.ehs.security.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.security.entity.SysRole;
import com.ehs.security.entity.UserRole;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.UserRoleService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserRoleServiceImpl.java
* @Description: 用户角色实现类
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年6月20日 上午9:44:35 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月20日     qjj           v1.0.0               修改原因
*/
@Service
public class UserRoleServiceImpl implements UserRoleService {
	
	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * 
	* @see com.ehs.security.service.UserRoleService#findUserRoleByAccount(java.lang.String)
	 */
	@Override
	public List<UserRole> findUserRoleByAccount(String userAccount) {
		Assert.notNull(userAccount, "userAccount is required");
	    Specification<UserRole> sf=(Root<UserRole> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(UserRole.ACCOUNT), userAccount));
	    List<UserRole> userRoleList=baseCommonService.findAll(UserRole.class, sf);
		return userRoleList;
	}
	
	/**
	 * 
	* @see com.ehs.security.service.UserRoleService#findRoleCodeByAccount(java.lang.String)
	 */
	@Override
	public List<String> findRoleCodeByAccount(String userAccount) {
		Assert.notNull(userAccount, "userAccount is required");
		List<UserRole> userRoleList=findUserRoleByAccount(userAccount);
		List<String> roleCodes=new ArrayList<String>();
		if(userRoleList!=null&&userRoleList.size()>0) {
			userRoleList.forEach((s)->roleCodes.add(s.getRoleCode()));
		}
		return roleCodes;
	}
	
	
	/**
	 * 
	* @see com.ehs.security.service.UserRoleService#findPagenateByAccount(java.lang.String, java.lang.Class, com.ehs.security.query.Pagenate)
	 */
	@Override
	public Pagenate findPagenateByAccount(String userAccount,Class clazz, Pagenate pageNate) {
		Assert.notNull(userAccount, "userAccount is required");
		Specification<UserRole> sf=(Root<UserRole> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(UserRole.ACCOUNT), userAccount));
	    List<UserRole> userRoleList=(List<UserRole>) baseCommonService.findAll(UserRole.class, sf).stream().filter(s->!StringUtils.equals(((UserRole) s).getRoleCode(), "normal")).collect(Collectors.toList());
	    for (UserRole userRole : userRoleList) {
			SysRole sysRole = (SysRole) baseCommonService.findByCode(SysRole.class, userRole.getRoleCode());
			userRole.setRoleCode(sysRole.getDataCode());
		}
	    pageNate=baseCommonService.findPagenate(clazz, sf, pageNate);
	    pageNate.setData(userRoleList);
		return pageNate;
	}

}
