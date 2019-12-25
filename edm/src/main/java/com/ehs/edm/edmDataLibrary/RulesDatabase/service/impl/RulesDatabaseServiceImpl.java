package com.ehs.edm.edmDataLibrary.RulesDatabase.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ehs.edm.edmDataLibrary.RulesDatabase.entity.RulesDatabase;
import com.ehs.edm.edmDataLibrary.RulesDatabase.service.RulesDatabaseService;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.FileInfoService;
import com.ehs.security.utils.BaseUtils;
import com.ehs.security.utils.SysAccessUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmDataLibraryServiceImpl.java
* @Description: 资料保存实现类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年8月16日 上午9:41:07 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年8月16日     zhaol           v1.0.0               修改原因
*/
@Service
public class RulesDatabaseServiceImpl implements RulesDatabaseService {
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FileInfoService fileInfoService;

	/**
	 * @see com.ehs.edm.edmDataLibrary.RulesDatabaseService.EdmDataLibraryService#saveDataLibrary(javax.servlet.http.HttpServletRequest, com.ehs.edm.edmDataLibrary.RulesDatabase.EdmDataLibrary, java.util.List)  
	 */
	@Override
	@Transactional
	public RulesDatabase saveDataLibrary(RulesDatabase rulesDatabase, List<FileInfo> fileInfos) {
		// TODO Auto-generated method stub
		String curUser = SysAccessUser.get().getAccount();
		rulesDatabase.setUploadDate(BaseUtils.getNow());
		rulesDatabase.setUploadPerson(curUser);
		RulesDatabase rulesEntity=(RulesDatabase)baseCommonService.saveOrUpdate(rulesDatabase);
		fileInfoService.saveFiles(fileInfos, rulesEntity.getCode());
		return rulesEntity;
	}

}
