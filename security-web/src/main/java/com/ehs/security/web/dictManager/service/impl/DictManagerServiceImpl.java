/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.web.dictManager.service.impl 
 * @author: qjj   
 * @date: 2019年9月19日 下午7:11:46 
 */
package com.ehs.security.web.dictManager.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ehs.security.entity.DataDictionary;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;
import com.ehs.security.web.dictManager.service.DictManagerService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DictManagerServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年9月19日 下午7:11:46 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月19日     qjj           v1.0.0               修改原因
*/
@Service
public class DictManagerServiceImpl implements DictManagerService {

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private DataDictionarySearchService dataDictionaryService;
	
	
	
	/** 
	* @see com.ehs.security.web.dictManager.service.DictManagerService#saveDictData(java.lang.String, java.util.List)  
	*/
	@Transactional
	@Override
	public void saveDictData(String flag, List<DataDictionary> dataList) {
		// TODO Auto-generated method stub
		 for (DataDictionary dataDictionary : dataList) {
			   String uuid=UUID.randomUUID().toString();
				if(("added").equals(flag)) {
					dataDictionary.setCode(uuid);
				}
				dataDictionary.setSystemCode("dataDict");
				baseCommonService.saveOrUpdate(dataDictionary);
			}
		   dataDictionaryService.reflushCache();//刷新缓存
	}



	/** 
	* @see com.ehs.security.web.dictManager.service.DictManagerService#deleteDictData(java.lang.String)  
	*/
	@Transactional
	@Override
	public void deleteDictData(String id) {
		// TODO Auto-generated method stub
		baseCommonService.deleteById(DataDictionary.class,id);
		dataDictionaryService.reflushCache();//刷新缓存
	}

}
