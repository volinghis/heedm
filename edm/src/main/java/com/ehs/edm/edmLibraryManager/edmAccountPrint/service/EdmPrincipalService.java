package com.ehs.edm.edmLibraryManager.edmAccountPrint.service;

import java.util.List;

import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrintParameter;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmPrincipal;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmPrincipalService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年9月19日 上午10:20:31 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月19日     zhaol           v1.0.0               修改原因
*/
public interface EdmPrincipalService {

	/**
	 * 
	* @Function: EdmPrincipalService.java
	* @Description: 保存历任将检点员
	*
	* @param edmPrincipals ----> 检点员参数集合
	* @param parameterCode -----> 设备code
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年9月19日 上午10:20:42 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年9月19日     zhaol           v1.0.0               修改原因
	 */
	public void saveEdmPrincipal(List<EdmPrincipal> edmPrincipals, String parameterCode);

	
	/**
	 * 
	* @Function: EdmPrincipalService.java
	* @Description: 查询所有跟设备关联的检点员
	*
	* @param parameterCode ----> 设备code
	* @return 设备相关数据
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年9月19日 上午10:20:47 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年9月19日     zhaol           v1.0.0               修改原因
	 */
	public List<EdmPrincipal> getEdmpEdmPrincipals(String parameterCode);

}
