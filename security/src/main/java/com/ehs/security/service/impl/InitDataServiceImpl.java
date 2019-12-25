
package com.ehs.security.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.security.dao.BaseCommonDao;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.InitDataService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: InitDataServiceImpl.java
* @Description: 初始化实体实现类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年10月10日 下午3:44:04 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年10月10日     zhaol           v1.0.0               修改原因
*/
@Service
public class InitDataServiceImpl implements InitDataService {

	@Resource
	private BaseCommonService baseCommonService ;
	
	@Resource
	private BaseCommonDao baseCommonDao;

	/**
	 * 
	* @see com.ehs.security.service.InitDataService#initDataEntity(java.util.List)  
	* @Function: InitDataServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午3:44:37 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	@Transactional
	public void initDataEntity(List<BaseEntity> baseEntities)  {
		Assert.notNull(baseEntities, "list for baseEntities is required");
		for (BaseEntity baseEntity : baseEntities) {
			String dataCode =baseEntity.getCode();
//			System.out.println("............................dataCode=================="+dataCode);
			Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) ->criteriaBuilder.and(criteriaBuilder.equal(root.get(BaseEntity.CODE), dataCode));
			BaseEntity entity = (BaseEntity)baseCommonService.findOne(baseEntity.getClass(), sf);
			if(entity == null) {
				baseCommonService.saveOrUpdate(baseEntity);
			}
		}
	}
}
