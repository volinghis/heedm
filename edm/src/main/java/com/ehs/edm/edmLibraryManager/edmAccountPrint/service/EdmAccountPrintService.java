package com.ehs.edm.edmLibraryManager.edmAccountPrint.service;

import java.util.List;

import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrintParameter;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmPrincipal;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.FileInfo;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmAccountPrintService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年8月15日 下午3:41:26 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年8月15日     zhaol           v1.0.0               修改原因
*/
public interface EdmAccountPrintService {
	
	/**
	 * 
	* @Function: EdmAccountPrintService.java
	* @Description: 保存设备台账,同时保存和设备相关的文件信息和参数信息
	*
	* @param edmAccountPrint --->设备台账信息
	* @param acountParameters ---> 设备参数信息
	* @param fileInfos ---> 文件信息
	* @return 设备台账
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午3:41:39 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	public BaseEntity saveOrUpdate(EdmAccountPrint edmAccountPrint,List<EdmAccountPrintParameter> acountParameters,List<FileInfo> fileInfos,List<EdmPrincipal> principals);
}
