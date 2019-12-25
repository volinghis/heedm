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
import com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmAccountPrintParameterService;
import com.ehs.security.service.BaseCommonService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmAccountPrintParameterServiceImpl.java
* @Description: 设备台账参数接口实现类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年7月19日 上午11:16:00 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月19日     zhaol           v1.0.0               修改原因
*/
@Service
public class EdmAccountPrintParameterServiceImpl implements EdmAccountPrintParameterService {
	
	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * @see com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmAccountPrintParameterService#saveEdmAccountPrintParameter(java.util.List, java.lang.String)  
	 */
	@Override
	@Transactional
	public void saveEdmAccountPrintParameter(List<EdmAccountPrintParameter> edmAccountPrintParameters, String parameterCode) {
		// TODO Auto-generated method stub
		List<EdmAccountPrintParameter> listAccountParam=getEdmAccountPrintParameter(parameterCode);
		if(listAccountParam != null && listAccountParam.size() > 0) {
			listAccountParam.forEach(s->baseCommonService.deleteById(EdmAccountPrintParameter.class,s.getId() ));
		}
		if(edmAccountPrintParameters !=null && edmAccountPrintParameters.size() > 0) {
			for(EdmAccountPrintParameter s:edmAccountPrintParameters) {
				s.setId(null);
				s.setParameterCode(parameterCode);
				baseCommonService.saveOrUpdate(s);
			}
		}
	}
	
	/**
	 * @see com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmAccountPrintParameterService#getEdmAccountPrintParameter(java.lang.String)  
	 */
	@Override
	public List<EdmAccountPrintParameter> getEdmAccountPrintParameter(String parameterCode) {
		// TODO Auto-generated method stub
		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(EdmAccountPrintParameter.A_PARAMETER_CODE), parameterCode));
		List<EdmAccountPrintParameter> accountParameters= baseCommonService.findAll(EdmAccountPrintParameter.class, sf);
		for (EdmAccountPrintParameter edmAccountPrintParameter : accountParameters) {
			if (StringUtils.isEmpty(edmAccountPrintParameter.getParameterName()) || StringUtils.isEmpty(edmAccountPrintParameter.getParameterValue())) {
				baseCommonService.deleteById(EdmAccountPrintParameter.class, edmAccountPrintParameter.getId());
			}
		}
		return accountParameters;
	}
}
