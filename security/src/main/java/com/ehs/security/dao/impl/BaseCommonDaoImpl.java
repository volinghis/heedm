
package com.ehs.security.dao.impl;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.ehs.security.dao.BaseCommonDao;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.query.Pagenate;

@Repository
public class BaseCommonDaoImpl implements BaseCommonDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger=LoggerFactory.getLogger(BaseCommonDaoImpl.class);

	/**
	 * 
	* @see com.ehs.edm.base.dao.BaseCommonDao#saveOrUpdate(com.ehs.edm.base.bean.BaseEntity)  
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午1:40:25 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日      chentm           v1.0.0               修改原因
	 */
	@Override
	public BaseEntity saveOrUpdate(BaseEntity entity)  {
		String dataId = entity.getId();
		if (StringUtils.isBlank(dataId)) {
			entity.initCreate();
			entity.setDataModel("CREATE");
			entityManager.persist(entity);
			return entity;
		}
		return update(entity, "UPDATE");

	}

	private BaseEntity update(BaseEntity entity,String model) {
		BaseEntity newEntity=null;
		try {
			newEntity = entity.getClass().getConstructor().newInstance();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
		BeanUtils.copyProperties(entity, newEntity);
		
		String className = entity.getClass().getSimpleName();
		String updateHql = " update " + className + " set "+BaseEntity.VERSION_ID+"=1 where "+BaseEntity.ID+"=?1 and "+BaseEntity.VERSION_ID+"=?2";
		Query query = entityManager.createQuery(updateHql);
		query.setParameter(1, entity.getId());
		query.setParameter(2, 0l);
		int result = query.executeUpdate();
		if(result<1) {
			throw new RuntimeException("data has been updated");
		}
		newEntity.initUpdate();
		if(StringUtils.equals(model, "REMOVE")) {
			newEntity.setVersionId(1l);
		}
		newEntity.setDataModel(model);
		entityManager.persist(newEntity);
		return newEntity;
	}

	/**
	 * 
	* @see com.ehs.edm.base.dao.BaseCommonDao#deleteById(java.lang.String)  
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午1:40:22 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日      chentm           v1.0.0               修改原因
	 */
	@Override
	public void delete(BaseEntity entity) {
		update(entity, "REMOVE");
		
	}

	/**
	 * 
	* @see com.ehs.edm.base.dao.BaseCommonDao#findAll(java.lang.Class)  
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午1:40:19 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日      chentm           v1.0.0               修改原因
	 */
	@Override
	public List find(Class clazz,Specification sf,Sort...sorts ) {
		Query query=entityManager.createQuery(createQuery(clazz, sf,sorts));

		return query.getResultList();
	}

	/** 
	* @see com.ehs.edm.base.dao.BaseCommonDao#findOne(java.lang.Class, org.springframework.data.jpa.domain.Specification)  
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午4:25:36 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日      chentm           v1.0.0               修改原因
	*/
	@Override
	public Object findOne(Class clazz, Specification sf,Sort... sorts) {
		List list = (List) entityManager.createQuery(createQuery(clazz, sf,sorts)).getResultList();
		if (list.size() >0) {
			return list.get(0);
		}
		return null;
	}

	/** 
	* @see com.ehs.edm.base.dao.BaseCommonDao#createQuery(java.lang.Class, org.springframework.data.jpa.domain.Specification)  
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午4:37:17 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日      chentm           v1.0.0               修改原因
	*/
	private CriteriaQuery createQuery(Class clazz, Specification sf,Sort... sorts ) { 
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(clazz);
		Root root=applyCriteria(query, clazz, sf);
		query.select(root);
		if(sorts==null|sorts.length==0) {
			Class c=clazz;
			int k=0;
			while(k<100&&!StringUtils.equals(c.getSuperclass().getSimpleName(), Object.class.getSimpleName())) {
				c=c.getSuperclass();
				k++;
			}
			if(StringUtils.equals(c.getName(), BaseEntity.class.getName())) {
				Sort sort=new Sort(Direction.DESC,BaseEntity.CREATION_TIME);
				query.orderBy(toOrders(sort, root, builder));
			}

		}else {
			for(Sort s:sorts) {
				query.orderBy(toOrders(s, root, builder));
			}
		}
		
		return query;
	}
	/**
	 * 
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月11日 上午9:21:17 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月11日     chentm           v1.0.0               修改原因
	 */
	private CriteriaQuery createCountQuery(Class clazz,Specification sf) { 
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(Long.class);
		Root root=applyCriteria(query, clazz, sf);
		query.select(builder.count(root));
		return query;
	}
	/**
	 * 
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月11日 上午9:31:44 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月11日     chentm           v1.0.0               修改原因
	 */
	private Root applyCriteria(CriteriaQuery query,Class clazz, Specification sf) {
		Root root=query.from(clazz);
		if(sf!=null) {
			query.where(sf.toPredicate(root, query, entityManager.getCriteriaBuilder()));
		}
		return root;
	}

	/** 
	* @see com.ehs.security.dao.BaseCommonDao#find(java.lang.Class, org.springframework.data.jpa.domain.Specification, com.ehs.security.query.Pagenate, org.springframework.data.domain.Sort[])  
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月21日 下午1:25:18 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月21日      chentm           v1.0.0               修改原因
	*/
	@Override
	public Pagenate findPagenate(Class clazz, Specification sf, Pagenate pageNate, Sort... sorts) {
		if(pageNate==null) {
			return null;
		}else {
			Query query=entityManager.createQuery(createQuery(clazz, sf,sorts));
			Query countQuery=entityManager.createQuery(createCountQuery(clazz,sf));
			Long count=(Long)countQuery.getSingleResult();
			
			pageNate.setiTotalDisplayRecords(count);
			pageNate.setiTotalRecords(count);
			pageNate.setTotal(count);
			query.setFirstResult(pageNate.getStartNum()*pageNate.getPageSize());
			query.setMaxResults(pageNate.getPageSize());
			pageNate.setData(query.getResultList());
			return pageNate;
		}

	}

	/** 
	* @see com.ehs.security.dao.BaseCommonDao#getSession()  
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月19日 下午4:39:45 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月19日      chentm           v1.0.0               修改原因
	*/
	@Override
	public Session getSession() {
		return entityManager.unwrap(org.hibernate.Session.class);
	}





}
