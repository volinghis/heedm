/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmLibraryManager.edmRepairAccountPrint 
 * @author: chentm   
 * @date: 2019年7月19日 下午1:16:29 
 */
package com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.service;

import java.util.List;

import com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.entity.EdmRepairAccountPrint;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.FileInfo;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmRepairAccountPrintService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月19日 下午1:16:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月19日      chentm          v1.0.0               修改原因
*/
public interface EdmRepairAccountPrintService {

	/**
	 * 
	* @Function:saveOrUpdate 
	* @Description: 保存检修台账
	* @param edmRepairAccountPrint 保存的实体
	* @param list 上传文件信息列表
	* @return  保存成功返回实体
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月15日 下午3:06:00 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     qjj           v1.0.0               修改原因
	 */
	public BaseEntity saveOrUpdate(EdmRepairAccountPrint edmRepairAccountPrint, List<FileInfo> list);
	
	/**
	 * 
	* @Function:getCurrentOrgMembers 
	* @Description: 根据机构或组织的code 查找所有的用户
	* @param orgCode 组织code不能为空
	* @return 当前组织下所有用户，以“,”隔开
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月15日 下午3:11:34 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     qjj           v1.0.0               修改原因
	 */
	public String getCurrentOrgMembers(String orgCode);
}
