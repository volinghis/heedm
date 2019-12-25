package com.ehs.edm.edmLibraryManager.edmAccountPrint.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrintParameter;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmPrincipal;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmAccountPrintParameterService;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmAccountPrintService;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmPrincipalService;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.FileInfoService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmAccountPrintServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年8月15日 下午3:45:07 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年8月15日     zhaol           v1.0.0               修改原因
*/
@Service
public class EdmAccountPrintServiceImpl implements EdmAccountPrintService {
	
	private Logger logger = LoggerFactory.getLogger(EdmAccountPrintServiceImpl.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FileInfoService fileInfoService;
	
	@Resource
	private EdmAccountPrintParameterService accountPrintParameterService;
	
	@Resource
	private EdmPrincipalService principalService;

	/**
	 * @see com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmAccountPrintService#saveOrUpdate(com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint, java.util.List)  
	 */
	@Override
	@Transactional
	public BaseEntity saveOrUpdate(EdmAccountPrint edmAccountPrint, List<EdmAccountPrintParameter> acountParameters,
			List<FileInfo> fileInfos,List<EdmPrincipal> principals) {
		logger.info("========准备保存EdmAccountPrint dataCode============");
		EdmAccountPrint edap = (EdmAccountPrint) baseCommonService.saveOrUpdate(edmAccountPrint);
		logger.info("saveOrUpdate	fileInfos==+++++++++++++++==="+fileInfos.size());
		logger.info("saveOrUpdate	acountParameters===+++++++++++++++==="+acountParameters.size());
		for (FileInfo fl : fileInfos) {
			fl.setSysCode("equipmentData");
			fl.setEntityName(edap.getDeviceName());
			fl.setNodeCode(edap.getInstallAddressCode());
		}
		fileInfoService.saveFiles(fileInfos, edap.getCode());
		accountPrintParameterService.saveEdmAccountPrintParameter(acountParameters, edap.getCode());
		principalService.saveEdmPrincipal(principals, edap.getCode());
		logger.info("========保存完成EdmAccountPrint dataCode=======");
		return edap;
	}

}
