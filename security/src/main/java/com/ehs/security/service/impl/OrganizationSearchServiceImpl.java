/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service.impl 
 * @author: chentm   
 * @date: 2019年7月3日 下午4:51:02 
 */
package com.ehs.security.service.impl;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ehs.security.entity.OrganizationInfo;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.OrganizationSearchService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationSearchServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月3日 下午4:51:02 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月3日      chentm          v1.0.0               修改原因
*/
@Service
public class OrganizationSearchServiceImpl implements OrganizationSearchService{

	
	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * 
	* @see com.ehs.security.service.OrganizationSearchService#findByCode(java.lang.String)
	 */
	@Override
	public OrganizationInfo findByCode(String orgCode) {
		
	    Specification<OrganizationInfo> sf=(Root<OrganizationInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(OrganizationInfo.CODE), orgCode));
		return (OrganizationInfo)baseCommonService.findOne(OrganizationInfo.class, sf);
	}

}
