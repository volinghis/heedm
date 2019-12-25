/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.base.dao 
 * @author: chentm   
 * @date: 2019年5月7日 上午9:23:06 
 */
package com.ehs.security.dao;



import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ehs.security.entity.BaseEntity;
import com.ehs.security.query.Pagenate;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: BaseCommonDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月7日 上午9:23:06 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月7日     chentm           v1.0.0               修改原因
*/
public interface BaseCommonDao {

	public Session getSession();
	
	/**
	 * 
	* @Function: BaseCommonDao.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月7日 下午3:57:27 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月7日     chentm           v1.0.0               修改原因
	 */
	public BaseEntity saveOrUpdate(BaseEntity baseEntity) ;
	
	/**
	 * 
	* @Function: BaseCommonDao.java
	* @Description: 根据ID
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月7日 下午3:57:30 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月7日     chentm           v1.0.0               修改原因
	 */
	public void delete(BaseEntity baseEntity) ;
	/**
	 * 
	* @Function: BaseCommonDao.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
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
	public List find(Class clazz,Specification sf,Sort... sorts);
	
	/**
	 * 
	* @Function: BaseCommonDao.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午4:25:28 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日     chentm           v1.0.0               修改原因
	 */
	public Object findOne(Class clazz,Specification sf,Sort... sorts);

	/**
	 * 
	* @Function: BaseCommonDao.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
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
	
}
