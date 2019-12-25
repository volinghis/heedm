/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service.impl 
 * @author: chentm   
 * @date: 2019年6月20日 上午11:07:50 
 */
package com.ehs.security.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.UserMenu;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.UserMenuService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserMenuServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月20日 上午11:07:50 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月20日      chentm          v1.0.0               修改原因
*/
@Service
public class UserMenuServiceImpl implements UserMenuService {

	
	@Resource
	private BaseCommonService baseCommonService;
	
	
	@Resource
	private RedisTemplate<String,String> redisTemplate;
	
	/**
	 * 
	* @see com.ehs.security.service.UserMenuService#findUserMenus(java.lang.String, java.lang.String, boolean, org.springframework.data.domain.Sort[])
	 */
	@Override
	public List<UserMenu> findUserMenus(String account, String parentCode, boolean hasChildren,Sort... sorts) {
		Assert.notNull(account, "userAccount is required");
		List<UserMenu> menus=new LinkedList<UserMenu>();
		findChild(menus, parentCode,hasChildren,account,sorts);
		return menus;
	}

	private void findChild(List<UserMenu> menus, String parentCode,boolean recursion,String account,Sort... sorts) {
		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> {
			if(StringUtils.isBlank(parentCode)) {
				return criteriaBuilder.and(criteriaBuilder.isNull(root.get(UserMenu.MENU_PARENT_CODE)),criteriaBuilder.equal(root.get(UserMenu.ACCOUNT),account ));
			}else {
				return criteriaBuilder.and(criteriaBuilder.equal(root.get(UserMenu.MENU_PARENT_CODE),parentCode),criteriaBuilder.equal(root.get(UserMenu.ACCOUNT),account ));
			}
		};
		List<UserMenu> menuList= baseCommonService.findAll(UserMenu.class, sf, sorts);
		
		if(menuList!=null&&menuList.size()>0) {
			menus.addAll(menuList);
			if(recursion) {
				menuList.forEach((s)->findChild(menus, s.getMenuCode(),recursion,account));
			}

	
		}
		
	}
	/**
	 * 
	* @see com.ehs.security.service.UserMenuService#findUserMenu(java.lang.String, java.lang.String, org.springframework.data.domain.Sort[])
	 */
	@Override
	public UserMenu findUserMenu(String account, String menuCode,Sort... sorts) {
		Assert.notNull(account, "userAccount is required");
		Assert.notNull(menuCode, "menuCode is required");
		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> criteriaBuilder.and(criteriaBuilder.equal(root.get(UserMenu.ACCOUNT), account),criteriaBuilder.equal(root.get(UserMenu.MENU_CODE), menuCode));
		return (UserMenu)baseCommonService.findOne(UserMenu.class, sf,sorts);
	}

	/**
	 * 
	* @see com.ehs.security.service.UserMenuService#setUserMenusToCache(java.lang.String)
	 */
	@Override
	public void setUserMenusToCache(String account) {
		Assert.notNull(account, "userAccount is required");
		Specification<UserMenu> sf=(Root<UserMenu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(UserMenu.ACCOUNT),account ));
		List<UserMenu> userMenus=  baseCommonService.findAll(UserMenu.class, sf);
		String data=JSON.toJSONString(userMenus);
		redisTemplate.opsForValue().set(account+"menus", data,7*24,TimeUnit.HOURS);
		
	}
	
	/**
	 * 
	* @see com.ehs.security.service.UserMenuService#getUserMenusForCache(java.lang.String)
	 */
	@Override
	public List<UserMenu> getUserMenusForCache(String account) {
		List<UserMenu> systemMenus= JSON.parseArray(redisTemplate.opsForValue().get(account+"menus"), UserMenu.class); 
		return systemMenus;
	}

}
