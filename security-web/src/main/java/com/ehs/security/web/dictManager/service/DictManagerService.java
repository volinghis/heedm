/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.web.dictManager.service 
 * @author: qjj   
 * @date: 2019年9月19日 下午7:09:12 
 */
package com.ehs.security.web.dictManager.service;

import java.util.List;

import com.ehs.security.entity.DataDictionary;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DictManagerService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年9月19日 下午7:09:12 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月19日     qjj           v1.0.0               修改原因
*/
public interface DictManagerService {

	/**   
	* @Function:saveDictData 
	* @Description: 该函数的功能描述
	* @param flag
	* @param dataList
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月19日 下午7:10:17 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月19日     qjj        v1.0.0            修改原因
	*/
	public void saveDictData(String flag, List<DataDictionary> dataList);

	/**   
	* @Function:deleteDictData 
	* @Description: 该函数的功能描述
	* @param id
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月20日 上午11:26:00 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月20日     qjj        v1.0.0            修改原因
	*/
	public void deleteDictData(String id);
	

}
