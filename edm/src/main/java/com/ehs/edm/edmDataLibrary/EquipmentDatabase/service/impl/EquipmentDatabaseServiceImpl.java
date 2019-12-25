/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmDataLibrary.EquipmentDatabase.service.impl 
 * @author: qjj   
 * @date: 2019年9月23日 下午5:19:50 
 */
package com.ehs.edm.edmDataLibrary.EquipmentDatabase.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ehs.edm.edmDataLibrary.EquipmentDatabase.service.EquipmentDatabaseService;
import com.ehs.security.entity.DataDictionary;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;
import com.ehs.security.service.FileInfoService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EquipmentDatabaseServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年9月23日 下午5:19:50 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月23日     qjj           v1.0.0               修改原因
*/
@Service
public class EquipmentDatabaseServiceImpl implements EquipmentDatabaseService {

	@Resource
	private FileInfoService fileInfoService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private DataDictionarySearchService dictService;
	
	
	/** 
	 * 
	* @see com.ehs.edm.edmDataLibrary.EquipmentDatabase.service.EquipmentDatabaseService#saveEquipmentDatabase(java.util.List)  
	*/
	@Transactional
	@Override
	public void saveEquipmentDatabase(List<FileInfo> fileInfos,FileInfo fileInfo) {
		// TODO Auto-generated method stub
		if(fileInfos!=null&&fileInfos.size()>0) {
			for(FileInfo s:fileInfos) {
				if(StringUtils.isNotBlank(fileInfo.getCategory())) {
					DataDictionary dd=	dictService.findOne(fileInfo.getCategory());
					if(dd!=null) {
						s.setCategory(dd.getText());
					}
				}
				s.setId(null);
				s.setSysCode("equipmentData");
				s.setNodeCode(fileInfo.getNodeCode());
				s.setEntityName(fileInfo.getEntityName());
				s.setEntityDataCode(fileInfo.getEntityDataCode());
				baseCommonService.saveOrUpdate(s);
			}
		}
	}

}
