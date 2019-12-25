/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service.impl 
 * @author: chentm   
 * @date: 2019年6月27日 上午9:35:19 
 */
package com.ehs.security.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ehs.security.entity.FileInfo;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.FileInfoService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FileInfoServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月27日 上午9:35:19 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月27日      chentm          v1.0.0               修改原因
*/
@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Resource
	private BaseCommonService baseCommonService;
	
	/** 
	* @see com.ehs.security.service.FileInfoService#saveFiles(java.util.List, java.lang.String)  
	* @Function: FileInfoServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月27日 上午9:35:19 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月27日      chentm           v1.0.0               修改原因
	*/
	@Override
	@Transactional
	public void saveFiles( List<FileInfo> fileInfos, String entityDataCode) {
		List<FileInfo> listFileInfo=getFiles(entityDataCode);
		if(listFileInfo!=null&&listFileInfo.size()>0) {
			listFileInfo.forEach(s->baseCommonService.deleteById(FileInfo.class,s.getId() ));
		}
		if(fileInfos!=null&&fileInfos.size()>0) {
			for(FileInfo s:fileInfos) {
				s.setId(null);
				s.setEntityDataCode(entityDataCode);
				baseCommonService.saveOrUpdate(s);
			}
		}
	}

	/** 
	* @see com.ehs.security.service.FileInfoService#getFiles(java.lang.String)  
	* @Function: FileInfoServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月27日 上午9:48:55 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月27日      chentm           v1.0.0               修改原因
	*/
	@Override
	public List<FileInfo> getFiles(String entityDataCode) {
		 Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(FileInfo.ENTITY_DATA_CODE), entityDataCode));

			List<FileInfo> listFileInfo= baseCommonService.findAll(FileInfo.class, sf);
			return listFileInfo;
	}

}
