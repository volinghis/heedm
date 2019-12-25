/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service 
 * @author: chentm   
 * @date: 2019年6月26日 上午8:47:55 
 */
package com.ehs.security.service;

import java.util.List;

import com.ehs.security.entity.DataDictionary;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DataDictionaryService.java
* @Description: 数据字典业务处理
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月26日 上午8:47:55 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月26日      chentm          v1.0.0               修改原因
*/
public interface DataDictionarySearchService {

	/**
	 * 
	* @Function: DataDictionarySearchService.java
	* @Description: 根据parentCode查询数据字典所有数据
	*
	* @param parentCode
	* @return：list集合
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午3:30:54 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public List<DataDictionary> findDataDictionary(String parentCode);
	
	/**
	 * 
	* @Function: DataDictionarySearchService.java
	* @Description: 查询所有数据
	*
	* @param 无
	* @return：list集合
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午3:31:52 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public List<DataDictionary>  findAllDataDictionary();

	/**
	 * 
	* @Function: DataDictionarySearchService.java
	* @Description: 根据Code查询一条数据
	*
	* @param code
	* @return：数据字典一条数据
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午3:32:48 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public DataDictionary findOne(String code);
	
	/**
	 * 
	* @Function: DataDictionarySearchService.java
	* @Description: 刷新缓存
	*
	* @param:无
	* @return：无
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午3:33:53 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	public void reflushCache();
}
