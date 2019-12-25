package com.ehs.security.web.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ehs.security.entity.SysUser;
import com.ehs.security.entity.SysUserRole;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.SysUserSearchService;
import com.ehs.security.service.UserRoleService;
import com.ehs.security.web.account.service.AccountService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: AccountServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Mapleave
* @date: 2019年8月16日 下午3:55:51 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年8月16日     Mapleave           v1.0.0               修改原因
*/
@Service
public class AccountServiceImpl implements AccountService {
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private SysUserSearchService sysUserService;
	
	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	 * @see com.ehs.security.web.account.service.AccountService#saveAuthForCurUser(java.lang.String, java.lang.String, boolean)  
	 */
	@Override
	@Transactional
	public void saveAuthForCurUser(String account, String code) {
		// TODO Auto-generated method stub
		try {
			SysUserRole sysUserRole=new SysUserRole();
			sysUserRole.setRoleCode(code);
			SysUser user=sysUserService.findByUserAccount(account);
			sysUserRole.setSysUserCode(user.getCode());
			baseCommonService.saveOrUpdate(sysUserRole);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see com.ehs.security.web.account.service.AccountService#removeRoleForCurUser(java.lang.String, java.lang.String)  
	 */
	@Override
	@Transactional
	public void removeRoleForCurUser(String account, String code) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new ArrayList<>();
		Specification<SysUserRole> sf=(Root<SysUserRole> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->{
			SysUser user=sysUserService.findByUserAccount(account);
			predicates.add(criteriaBuilder.equal(root.get(SysUserRole.SYS_USER_CODE), user.getCode()));
			predicates.add(criteriaBuilder.equal(root.get(SysUserRole.ROLE_CODE), code));
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
		baseCommonService.deleteByWhereCase(SysUserRole.class, sf);
	}

}
