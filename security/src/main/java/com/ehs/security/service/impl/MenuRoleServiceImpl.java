package com.ehs.security.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ehs.security.entity.MenuRole;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.MenuRoleService;

@Service
public class MenuRoleServiceImpl implements MenuRoleService {
	
	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	* @see com.ehs.security.service.MenuRoleService#getRoleMenu(java.lang.String)
	 */
	@Override
	public List<MenuRole> getRoleMenu(String code) {
		// TODO Auto-generated method stub
	    Specification<MenuRole> codeSf=(Root<MenuRole> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(MenuRole.CODE), code));
	    return (List<MenuRole>)baseCommonService.findAll(MenuRole.class,codeSf);
	}

}
