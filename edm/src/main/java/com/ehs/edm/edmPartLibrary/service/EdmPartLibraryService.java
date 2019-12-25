package com.ehs.edm.edmPartLibrary.service;

import java.util.List;

import com.ehs.edm.edmPartLibrary.entity.EdmPartLibrary;
import com.ehs.security.entity.FileInfo;

public interface EdmPartLibraryService {
	
	/**
	 * 
	* @Function:savePartLibrary 
	* @Description: 备品备件的保存
	* @param edmPartLibrary 备件实体
	* @param list 上传文件信息
	* @return 保存成功返回实体
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月15日 下午3:59:26 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     qjj           v1.0.0               修改原因
	 */
	public EdmPartLibrary savePartLibrary(EdmPartLibrary edmPartLibrary, List<FileInfo> list);

}
