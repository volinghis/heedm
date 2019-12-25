package com.ehs.security.web.menuManager.service.impl;

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

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.SysRoleMenu;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.web.menuManager.service.MenuManagerService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: MenuManageServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Mapleave
* @date: 2019年8月16日 上午11:04:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年8月16日     Mapleave           v1.0.0               修改原因
*/
@Service
public class MenuManageServiceImpl implements MenuManagerService {
	
	@Resource
	public BaseCommonService baseCommonService;
	
	/**
	 * @see com.ehs.security.web.menuManager.service.MenuManagerService#saveRoleMenu(com.ehs.security.entity.SysRoleMenu, java.lang.String)  
	 */
	@Override
	@Transactional
	public String saveRoleMenu(String menuCode, String roleCode) {
		// TODO Auto-generated method stub
		try {
			SysRoleMenu sysRoleMenu= new SysRoleMenu();
			sysRoleMenu.setRoleCode(roleCode);
			sysRoleMenu.setMenuCode(menuCode);
//			List<Predicate> predicates = new ArrayList<>();
//			Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->{
//				predicates.add(criteriaBuilder.equal(root.get(SysRoleMenu.MENU_CODE), sysRoleMenu.getMenuCode()));
//				predicates.add(criteriaBuilder.equal(root.get(SysRoleMenu.ROLE_CODE), sysRoleMenu.getRoleCode()));
//				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//			};
//			SysRoleMenu roleMenu =(SysRoleMenu) baseCommonService.findOne(SysRoleMenu.class, sf);
//			if (roleMenu != null) {
//				return JSON.toJSONString(ResultBean.getBean("该角色已经存在，请勿重复添加！",ResultType.ERROR,null));
//			}
			 baseCommonService.saveOrUpdate(sysRoleMenu);
			 return JSON.toJSONString(ResultBean.getBean("添加成功！",ResultType.OK,null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON.toJSONString(ResultBean.getBean("添加失败！",ResultType.OK,null));
	}

	/**
	 * @see com.ehs.security.web.menuManager.service.MenuManagerService#deleteRoleMenu(java.lang.String, java.lang.String)  
	 */
	@Override
	@Transactional
	public void deleteRoleMenu(String menuCode, String roleCode) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new ArrayList<>();
		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->{
			predicates.add(criteriaBuilder.equal(root.get(SysRoleMenu.MENU_CODE), menuCode));
			predicates.add(criteriaBuilder.equal(root.get(SysRoleMenu.ROLE_CODE), roleCode));
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
		baseCommonService.deleteByWhereCase(SysRoleMenu.class, sf);;
	}
}
