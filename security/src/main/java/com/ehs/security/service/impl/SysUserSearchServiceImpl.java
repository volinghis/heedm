package com.ehs.security.service.impl;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.security.entity.SysUser;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.SysUserSearchService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: SysUserServiceImpl.java
* @Description: 根据用户编号查询用户信息
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年5月15日 下午6:39:32 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月15日     zhaol           v1.0.0               修改原因
*/
@Service
public class SysUserSearchServiceImpl implements SysUserSearchService {
	

	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * 
	* @see com.ehs.security.service.SysUserSearchService#findByUserAccount(java.lang.String)
	 */
	@Override
	public SysUser findByUserAccount(String userAccount) {
		Assert.notNull(userAccount, "userAccount is required");
		Specification<SysUser> sf=(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(SysUser.ACCOUNT), userAccount));
		return (SysUser) baseCommonService.findOne(SysUser.class, sf);
	}

}
