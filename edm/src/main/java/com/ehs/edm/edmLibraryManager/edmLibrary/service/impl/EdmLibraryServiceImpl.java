/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmLibraryManager.edmLibrary.service.impl 
 * @author: chentm   
 * @date: 2019年7月1日 下午5:36:34 
 */
package com.ehs.edm.edmLibraryManager.edmLibrary.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ehs.edm.edmLibraryManager.edmLibrary.entity.EdmLibrary;
import com.ehs.edm.edmLibraryManager.edmLibrary.entity.EdmLibraryParameter;
import com.ehs.edm.edmLibraryManager.edmLibrary.service.EdmLibraryParameterService;
import com.ehs.edm.edmLibraryManager.edmLibrary.service.EdmLibraryService;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.FileInfoService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmLibraryServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月1日 下午5:36:34 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月1日      chentm          v1.0.0               修改原因
*/
@Service
public class EdmLibraryServiceImpl implements EdmLibraryService{

	@Resource
	private FileInfoService fileInfoService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private EdmLibraryParameterService libraryParameterService;
	
	/** 
	* @see com.ehs.edm.edmLibraryManager.edmLibrary.service.EdmLibraryService#saveOrUpdate(com.ehs.edm.edmLibraryManager.edmLibrary.entity.EdmLibrary, com.ehs.security.entity.FileInfo[])  
	* @Function: EdmLibraryServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月1日 下午5:37:24 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月1日      chentm           v1.0.0               修改原因
	*/
	@Override
	@Transactional
	public BaseEntity saveOrUpdate(EdmLibrary edmLibrary, List<FileInfo> fileInfos,List<EdmLibraryParameter> elParameters) {
		EdmLibrary el=(EdmLibrary)baseCommonService.saveOrUpdate(edmLibrary);
		fileInfoService.saveFiles(fileInfos, el.getCode());
//		edmAssemblyService.saveAssembly(edmAssemblies, el.getDataCode());
		libraryParameterService.saveEdmLibraryParameter(elParameters, el.getCode());
		return el;
	}
}
