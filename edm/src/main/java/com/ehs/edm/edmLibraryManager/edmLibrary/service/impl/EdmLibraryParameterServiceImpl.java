package com.ehs.edm.edmLibraryManager.edmLibrary.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ehs.edm.edmLibraryManager.edmLibrary.entity.EdmLibraryParameter;
import com.ehs.edm.edmLibraryManager.edmLibrary.service.EdmLibraryParameterService;
import com.ehs.edm.edmLibraryManager.edmLibrary.service.EdmLibraryService;
import com.ehs.security.service.BaseCommonService;

@Service
public class EdmLibraryParameterServiceImpl implements EdmLibraryParameterService {
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private EdmLibraryService edmLibraryService;

	@Override
	@Transactional
	public void saveEdmLibraryParameter(List<EdmLibraryParameter> edmLibraryParameters, String code) {
		// TODO Auto-generated method stub
		List<EdmLibraryParameter> listEdmLibraryParam=getEdmLibraryParameter(code);
		if(listEdmLibraryParam!=null&&listEdmLibraryParam.size()>0) {
			listEdmLibraryParam.forEach(s->baseCommonService.deleteById(EdmLibraryParameter.class,s.getId() ));
		}
		if(edmLibraryParameters!=null&&edmLibraryParameters.size()>0) {
			for(EdmLibraryParameter s:edmLibraryParameters) {
				s.setId(null);
				s.setParameterCode(code);
				baseCommonService.saveOrUpdate(s);
			}
		}
	}

	@Override
	public List<EdmLibraryParameter> getEdmLibraryParameter(String code) {
		// TODO Auto-generated method stub
		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(EdmLibraryParameter.PARAMETER_CODE), code));
		List<EdmLibraryParameter> libraryParameters= baseCommonService.findAll(EdmLibraryParameter.class, sf);
		return libraryParameters;
	}

}
