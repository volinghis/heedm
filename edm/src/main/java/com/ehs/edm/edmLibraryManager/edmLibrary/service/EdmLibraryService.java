/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmLibraryManager.edmLibrary 
 * @author: chentm   
 * @date: 2019年7月1日 下午5:36:01 
 */
package com.ehs.edm.edmLibraryManager.edmLibrary.service;

import java.util.List;

import com.ehs.edm.edmLibraryManager.edmLibrary.entity.EdmLibrary;
import com.ehs.edm.edmLibraryManager.edmLibrary.entity.EdmLibraryParameter;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.FileInfo;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmLibraryService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月1日 下午5:36:01 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月1日      chentm          v1.0.0               修改原因
*/
public interface EdmLibraryService {

	public BaseEntity saveOrUpdate(EdmLibrary edmLibrary,List<FileInfo> fileInfos,List<EdmLibraryParameter> edmLibraryParameters);
}
