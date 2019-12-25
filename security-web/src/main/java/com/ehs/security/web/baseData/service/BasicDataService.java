package com.ehs.security.web.baseData.service;

import java.util.List;

import com.ehs.security.entity.DataDictionary;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: BasicDataService.java
* @Description: 基础数据维护接口
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年7月22日 上午10:42:50 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月22日     qjj           v1.0.0               修改原因
*/
public interface BasicDataService {
	

	/**
	 * 
	* @Function:saveSortChangedEntity 
	* @Description: 记录上移、下移后数据的保存接口
	* @param orId String类型 ，当前选中记录id
	* @param orSort Integer类型， 当前选中记录的顺序
	* @param toId String类型，目标位置记录id
	* @param toSort Integer类型，目标位置记录顺序
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 下午4:56:19 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	public void saveSortChangedEntity(String orId,Integer orSort,String toId,Integer toSort);
	
    /**
     * 
    * @Function:saveBasicData 
    * @Description: 基础数据保存接口
    * @param flag 页面传递的状态（added 新增 ，modified 修改）
    * @param dataList 页面传递的数据集合
    * @throws：异常描述
    * @version: v1.0.0
    * @author: qjj
    * @date: 2019年8月16日 下午3:18:48 
    *
    * Modification History:
    * Date         Author          Version            Description
    *---------------------------------------------------------*
    * 2019年8月16日     qjj           v1.0.0               修改原因
     */
	public void saveBasicData(String flag, List<DataDictionary> dataList);
	
	/**
	 * 
	* @Function:deleteBasicData 
	* @Description: 基础数据删除接口
	* @param id String类型基础数据唯一标识
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 下午3:24:45 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	public void deleteBasicData(String id);
	
}
