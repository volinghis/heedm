package com.ehs.edm.edmDataLibrary.RulesDatabase.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ehs.edm.edmDataLibrary.RulesDatabase.entity.RulesDatabase;
import com.ehs.security.entity.FileInfo;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmDataLibraryService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年8月16日 上午9:42:49 
*
* Modification History:
* Date         Author          Version          Description
*---------------------------------------------------------*
* 2019年8月16日     zhaol           v1.0.0         修改原因
*/
public interface RulesDatabaseService {
	
	/**
	 * 
	* @Function: EdmDataLibraryService.java
	* @Description: 资料保存功能，并且保存资料内上传的文件
	*
	* @param request
	* @param edmDataLibrary ---> 资料数据
	* @param list ---> 文件数据集合
	* @return 保存的EdmDataLibrary实体
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月16日 上午9:42:59 
	*
	* Modification History:
	* Date         Author          Version         Description
	*---------------------------------------------------------*
	* 2019年8月16日     zhaol           v1.0.0               修改原因
	 */
	public RulesDatabase saveDataLibrary(RulesDatabase edmDataLibrary, List<FileInfo> list);

}
