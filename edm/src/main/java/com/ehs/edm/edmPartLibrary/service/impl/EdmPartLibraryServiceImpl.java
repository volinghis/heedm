package com.ehs.edm.edmPartLibrary.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.ehs.edm.edmPartLibrary.entity.EdmPartLibrary;
import com.ehs.edm.edmPartLibrary.service.EdmPartLibraryService;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.FileInfoService;

@Service
public class EdmPartLibraryServiceImpl implements EdmPartLibraryService {
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FileInfoService fileInfoService;

	/**
	 * 
	* @see com.ehs.edm.edmPartLibrary.service.EdmPartLibraryService#savePartLibrary(com.ehs.edm.edmPartLibrary.entity.EdmPartLibrary, java.util.List)
	 */
	@Override
	@Transactional
	public EdmPartLibrary savePartLibrary(EdmPartLibrary edmPartLibrary, List<FileInfo> fileInfos) {
		// TODO Auto-generated method stub
		EdmPartLibrary partLibrary=(EdmPartLibrary)baseCommonService.saveOrUpdate(edmPartLibrary);
		fileInfoService.saveFiles(fileInfos, partLibrary.getCode());
		return partLibrary;
	}

}
