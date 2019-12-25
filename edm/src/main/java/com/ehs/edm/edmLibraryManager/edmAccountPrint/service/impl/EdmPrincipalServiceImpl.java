package com.ehs.edm.edmLibraryManager.edmAccountPrint.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrintParameter;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmPrincipal;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmPrincipalService;
import com.ehs.security.service.BaseCommonService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmPrincipalServiceImpl.java
* @Description: 
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年9月19日 上午10:24:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月19日     zhaol           v1.0.0               修改原因
*/
@Service
public class EdmPrincipalServiceImpl implements EdmPrincipalService {
	
	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	* @see com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmPrincipalService#saveEdmPrincipal(java.util.List, java.lang.String)  
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年9月19日 上午10:25:01 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年9月19日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void saveEdmPrincipal(List<EdmPrincipal> edmPrincipals, String parameterCode) {
		// TODO Auto-generated method stub
		List<EdmPrincipal> listPrincipals=getEdmpEdmPrincipals(parameterCode);
		if(listPrincipals != null && listPrincipals.size() > 0) {
			listPrincipals.forEach(s->baseCommonService.deleteById(EdmPrincipal.class,s.getId() ));
		}
		if(edmPrincipals !=null && edmPrincipals.size() > 0) {
			for(EdmPrincipal s:edmPrincipals) {
				s.setId(null);
				s.setPrincipalCode(parameterCode);
				baseCommonService.saveOrUpdate(s);
			}
		}
	}

	/**
	 * 
	* @see com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmPrincipalService#getEdmpEdmPrincipals(java.lang.String)  
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年9月19日 上午10:25:22 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年9月19日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public List<EdmPrincipal> getEdmpEdmPrincipals(String parameterCode) {
		// TODO Auto-generated method stub
		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(EdmPrincipal.PRINCIPAL_CODE), parameterCode));
		List<EdmPrincipal> principals= baseCommonService.findAll(EdmPrincipal.class, sf);
		for (EdmPrincipal edmPrincipal : principals) {
			if (StringUtils.isEmpty(edmPrincipal.getPrincipal())  || StringUtils.isEmpty(edmPrincipal.getServeTime())  || StringUtils.isEmpty(edmPrincipal.getLeaveTime())) {
				System.out.println("edmPrincipal.getId()================"+edmPrincipal.getId());
				baseCommonService.deleteById(EdmPrincipal.class, edmPrincipal.getId());
			}
		}
		return principals;
	}

}
