/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service.impl 
 * @author: chentm   
 * @date: 2019年6月10日 下午3:06:35 
 */
package com.ehs.security.service.impl;

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
import com.ehs.security.entity.User;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.UserService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月10日 下午3:06:35 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月10日      chentm          v1.0.0               修改原因
*/
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private RedisTemplate<String,String> redisTemplate;
	
	/**
	 * 
	* @see com.ehs.security.service.UserService#findUser(java.lang.String)
	 */
	@Override
	public User findUser(String code) {
		Assert.notNull(code, "code for user must be required");
		
	    Specification codeSf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(User.CODE), code));
		return (User)baseCommonService.findOne(User.class,codeSf);
	}
	


	/**
	 * 
	* @see com.ehs.security.service.UserService#findUserByAccount(java.lang.String)
	 */
	@Override
	public User findUserByAccount(String account) {
		Assert.notNull(account, "account for user must be required");
	    Specification accountSf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(User.ACCOUNT), account));
		return (User)baseCommonService.findOne(User.class,accountSf);
	}
	
	/**
	 * 
	* @see com.ehs.security.service.UserService#findUserListPagenate(java.lang.Class, org.springframework.data.jpa.domain.Specification, com.ehs.security.query.Pagenate, org.springframework.data.domain.Sort[])
	 */
	@Override
	public Pagenate findUserListPagenate(Class clazz, Specification sf, Pagenate pageNate,Sort... sorts) {
		// TODO Auto-generated method stub
		pageNate=baseCommonService.findPagenate(clazz, sf, pageNate,sorts);
//		List<User> userList=baseCommonService.findAll(clazz, sf, sorts);
//		pageNate.setData(userList);
		return pageNate;
	}
	
	/**
	 * 
	* @see com.ehs.security.service.UserService#setUserToCache(java.lang.String)
	 */
	@Override
	public void setUserToCache(String account) {
		User user= findUserByAccount(account);
		String data=JSON.toJSONString(user);
		redisTemplate.opsForValue().set(account+"infos", data,7*24,TimeUnit.HOURS);
	}
	
	/**
	 * 
	* @see com.ehs.security.service.UserService#getUserForCache(java.lang.String)
	 */
	@Override
	public User getUserForCache(String account) {
		String data=redisTemplate.opsForValue().get(account+"infos");
		return JSON.parseObject(data, User.class);
	}

	/**
	 * 
	* @see com.ehs.security.service.UserService#findUserByOrgCode(java.lang.String)
	 */
	@Override
	public List<User> findUserByOrgCode(String orgCode) {
		Assert.notNull(orgCode, "orgCode for user must be required");
	    Specification codeSf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(User.ORG_CODE), orgCode));
		return baseCommonService.findAll(User.class,codeSf);
	}

	/**
	 * 
	* @see com.ehs.security.service.UserService#findUsers(java.lang.String)
	 */
	@Override
	public List<User> findUsers(String codes) {
		Assert.notNull(codes, "code for user must be required");
		
	    Specification codeSf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->{
	    	CriteriaBuilder.In in=criteriaBuilder.in(root.get(User.CODE));
	    	String[] ss=StringUtils.split(codes,",");
			for(String s:ss) {
				in.value(s);
			}
	    	return criteriaBuilder.and(in);
	    };
		return baseCommonService.findAll(User.class, codeSf);
	}
}
