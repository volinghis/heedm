/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.base.service 
 * @author: chentm   
 * @date: 2019年5月7日 上午9:20:39 
 */
package com.ehs.security.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ehs.security.entity.BaseEntity;
import com.ehs.security.query.Pagenate;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: BaseCommonService.java
* @Description: baseService
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月7日 上午9:20:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月7日     chentm           v1.0.0               修改原因
*/
public interface BaseCommonService {
	
	public Session getSession();
	/**
	 * 
	* @Function: BaseCommonService.java
	* @Description: 保存或更新实体
	*
	* @param baseEntity 实体类
	* @return：BaseEntity
	* @throws：无
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月7日 上午9:21:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月7日     chentm           v1.0.0               修改原因
	 */
	public BaseEntity saveOrUpdate(BaseEntity baseEntity) ;
	/**
	 * 
	* @Function: BaseCommonService.java
	* @Description: 根据Id删除实体
	*
	* @param id
	* @return：无
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年5月29日 上午8:53:54 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月29日     zhaol           v1.0.0               修改原因
	 */
	public void deleteById(Class clazz,String id);
	
	/**
	 * 
	* @Function: BaseCommonService.java
	* @Description: 根据不同条件删除实体
	*
	* @param sf 动态查询条件
	* @return：无
	* @throws：无
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月20日 上午9:41:37 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月20日     chentm           v1.0.0               修改原因
	 */
	public void deleteByWhereCase(Class clazz,Specification sf);
	/**
	 * 
	* @Function: BaseCommonService.java
	* @Description: 查询所有，并进行排序
	*
	* @param clazz 实体类
	* @param sf 实动态查询条件
	* @param sorts 排序方式
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月7日 下午1:44:25 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月7日     chentm           v1.0.0               修改原因
	 */
	public List findAll(Class clazz, Specification sf,Sort... sorts);
	
	/**
	 * 
	* @Function: BaseCommonService.java
	* @Description: 查找一个
	*
	* @param clazz 实体类
	* @param sf 实动态查询条件
	* @param sorts 排序方式
	* @return：Object对象
	* @throws：无
	*
	* @version: v1.0.0
	* @author: Mapleave
	* @date: 2019年5月15日 下午6:02:33 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月15日     Mapleave           v1.0.0               修改原因
	 */
	public Object findOne(Class clazz, Specification sf,Sort... sorts);
	

	/**
	 * 
	* @Function: BaseCommonDao.java
	* @Description: 根据动态条件查找并进行分页
	*
	* @param clazz 实体类
	* @param sf 实动态查询条件
	* @param pageNate 分页
	* @param sorts 排序方式
	* @return：分页
	* @throws：无
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午3:00:02 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日     chentm           v1.0.0               修改原因
	 */
	public Pagenate findPagenate(Class clazz,Specification sf,Pagenate pageNate,Sort... sorts);
	/**
	 * 
	* @Function: BaseCommonService.java
	* @Description: 通过ID查找
	*
	* @param clazz 实体类
	* @param id 实体类id
	* @return：BaseEntity实体
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月1日 上午11:17:27 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月1日     chentm           v1.0.0               修改原因
	 */
	public BaseEntity findById(Class clazz,String id);

	/**
	 * 
	* @Function: BaseCommonService.java
	* @Description: 通过Code查找
	*
	* @param clazz 实体类
	* @param code 实体类code
	* @return：BaseEntity 实体类
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月3日 上午11:30:43 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月3日     chentm           v1.0.0               修改原因
	 */
	public BaseEntity findByCode(Class clazz,String code);
}
