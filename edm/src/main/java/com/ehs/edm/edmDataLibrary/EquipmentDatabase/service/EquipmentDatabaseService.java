/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmDataLibrary.EquipmentDatabase.service 
 * @author: qjj   
 * @date: 2019年9月23日 下午5:16:47 
 */
package com.ehs.edm.edmDataLibrary.EquipmentDatabase.service;

import java.util.List;

import com.ehs.edm.edmDataLibrary.EquipmentDatabase.entity.EquipmentDatabase;
import com.ehs.security.entity.FileInfo;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EquipmentDatabaseService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年9月23日 下午5:16:47 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月23日     qjj           v1.0.0               修改原因
*/
public interface EquipmentDatabaseService {

	/**   
	* @Function:saveEquipmentDatabase 
	* @Description: 该函数的功能描述
	* @param parseArray
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月23日 下午5:18:53 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月23日     qjj        v1.0.0            修改原因
	*/
	public void saveEquipmentDatabase(List<FileInfo> fileInfos,FileInfo fileInfo);
	
	

}
