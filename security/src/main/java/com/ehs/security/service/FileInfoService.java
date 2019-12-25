/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service 
 * @author: chentm   
 * @date: 2019年6月27日 上午9:32:47 
 */
package com.ehs.security.service;

import java.util.List;

import com.ehs.security.entity.FileInfo;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FileInfoService.java
* @Description: 文件业务处理
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月27日 上午9:32:47 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月27日      chentm          v1.0.0               修改原因
*/
public interface FileInfoService {

	/**
	 * 
	* @Function: FileInfoService.java
	* @Description: 保存文件
	*
	* @param fileInfos：文件集合
	* @param entityDataCode：实体dataCode
	* @return：无
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午3:40:12 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public void saveFiles( List<FileInfo> fileInfos, String entityDataCode);
	
	/**
	 * 
	* @Function: FileInfoService.java
	* @Description: 根据实体dataCode查询所有文件
	*
	* @param entityDataCode：实体类dataCode
	* @return：文件集合
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午3:41:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public List<FileInfo> getFiles(String entityDataCode);
}
