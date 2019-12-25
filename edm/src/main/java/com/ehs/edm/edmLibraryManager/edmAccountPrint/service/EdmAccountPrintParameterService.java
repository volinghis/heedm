package com.ehs.edm.edmLibraryManager.edmAccountPrint.service;

import java.util.List;

import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrintParameter;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmAccountPrintParameterService.java
* @Description: 设备台账参数
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年7月19日 上午11:16:45 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月19日     zhaol           v1.0.0               修改原因
*/
public interface EdmAccountPrintParameterService {
	
	/**
	 * 
	* @Function: EdmAccountPrintParameterService.java
	* @Description: 保存设备相关联参数的接口
	*
	* @param edmAccountPrintParameters ----> 设备参数集合
	* @param parameterCode -----> 设备code
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午2:30:35 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	public void saveEdmAccountPrintParameter(List<EdmAccountPrintParameter> edmAccountPrintParameters, String parameterCode);
	
	/**
	 * 
	* @Function: EdmAccountPrintParameterService.java
	* @Description: 查询所有和设备关联的参数
	*
	* @param parameterCode ----> 设备code
	* @return 设备相关数据
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午2:31:08 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	public List<EdmAccountPrintParameter> getEdmAccountPrintParameter(String parameterCode);
}
