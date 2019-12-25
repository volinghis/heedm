package com.ehs.security.web.baseData.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ehs.security.entity.DataDictionary;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;
import com.ehs.security.web.baseData.service.BasicDataService;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: BasicDataServiceImpl.java
* @Description: 数据维护实现类
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年7月22日 上午10:47:52 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月22日     qjj           v1.0.0               修改原因
*/
@Service
public class BasicDataServiceImpl implements BasicDataService {

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private DataDictionarySearchService dataDictionaryService;
	/**
	 * 
	* @see com.ehs.security.web.baseData.service.BasicDataService#saveSortChangedEntity(java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Transactional
	@Override
	public void saveSortChangedEntity(String orId, Integer orSort, String toId, Integer toSort) {
		DataDictionary orEntity=(DataDictionary)baseCommonService.findById(DataDictionary.class, orId);
		DataDictionary toEntity=(DataDictionary)baseCommonService.findById(DataDictionary.class, toId);
		orEntity.setSort(toSort);
		toEntity.setSort(orSort);
		baseCommonService.saveOrUpdate(orEntity);
		baseCommonService.saveOrUpdate(toEntity);
		dataDictionaryService.reflushCache();//刷新缓存
	}
	
	/**
	 * 
	* @see com.ehs.security.web.baseData.service.BasicDataService#saveBasicData(java.lang.String, java.util.List)
	 */
	@Transactional
	@Override
	public void saveBasicData(String flag, List<DataDictionary> dataList) {
	   for (DataDictionary dataDictionary : dataList) {
		   String uuid=UUID.randomUUID().toString();
			if(("added").equals(flag)) {
				dataDictionary.setCode(uuid);
				dataDictionary.setDataCode(uuid);
				dataDictionary.setSystemCode("edmGroupInfo");
			}
			baseCommonService.saveOrUpdate(dataDictionary);
		}
	   dataDictionaryService.reflushCache();//刷新缓存
	}

	/**
	 * 
	* @see com.ehs.security.web.baseData.service.BasicDataService#deleteBasicData(java.lang.String)
	 */
	@Transactional
	@Override
	public void deleteBasicData(String id) {
		baseCommonService.deleteById(DataDictionary.class,id);
		dataDictionaryService.reflushCache();//刷新缓存
	}

}
