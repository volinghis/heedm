package com.ehs.security.web.organizationManager.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ehs.security.entity.DataDictionary;
import com.ehs.security.entity.OrganizationInfo;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.web.organizationManager.service.OrganizationManagerService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: edmOrgServiceImpl.java
* @Description: 组织管理实现类
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年7月5日 上午10:16:16 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月5日     qjj           v1.0.0               修改原因
*/
@Service
public class OrganizationManagerServiceImpl implements OrganizationManagerService {

	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * @see com.ehs.security.web.organizationManager.service.OrganizationManagerService#findOrgsByCode(java.lang.String)  
	 */
	@Override
	public List<OrganizationInfo> findOrgsByCode(String parentCode) {
		Specification<OrganizationInfo> sf=(Root<OrganizationInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)-> {
			return criteriaBuilder.and( StringUtils.isBlank(parentCode)?criteriaBuilder.isNull(root.get(OrganizationInfo.PARENTCODE)):criteriaBuilder.equal(root.get(OrganizationInfo.PARENTCODE),parentCode ));
		};
	    return (List<OrganizationInfo>)baseCommonService.findAll(OrganizationInfo.class, sf, new Sort(Direction.ASC,OrganizationInfo.SORT));
	}
	
	

	/** 
	* @see com.ehs.security.web.organizationManager.service.OrganizationManagerService#saveSortChangedEntity(java.lang.String, int, java.lang.String, int)  
	*/
	@Override
	public void saveSortChangedEntity(String orId, int orSort, String toId, int toSort) {
		// TODO Auto-generated method stub
		OrganizationInfo orEntity=(OrganizationInfo)baseCommonService.findById(OrganizationInfo.class, orId);
		OrganizationInfo toEntity=(OrganizationInfo)baseCommonService.findById(OrganizationInfo.class, toId);
		orEntity.setSort(toSort);
		toEntity.setSort(orSort);
		baseCommonService.saveOrUpdate(orEntity);
		baseCommonService.saveOrUpdate(toEntity);
	}

}
