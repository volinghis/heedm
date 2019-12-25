/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.base.service 
 * @author: chentm   
 * @date: 2019年5月7日 上午9:21:47 
 */
package com.ehs.security.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ehs.security.dao.BaseCommonDao;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: BaseCommonServiceImpl.java
* @Description: BaseCommonService实现类
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月7日 上午9:21:47 
*
* Modification History:
* Date         Author          Version      Description
*---------------------------------------------------------*
* 2019年5月7日     Administrator     v1.0.0       修改原因
*/
@Service
public class BaseCommonServiceImpl implements BaseCommonService {
	
	private static Logger logger=LoggerFactory.getLogger(BaseCommonService.class);

	@Resource
	private BaseCommonDao baseCommonDao; 
	
	/** 
	* @see com.ehs.edm.base.service.BaseCommonService#saveOrUpdate(com.ehs.edm.base.bean.BaseEntity)  
	* @Function: BaseCommonServiceImpl.java
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月7日 上午9:21:47 
	*
	* Modification History:
	* Date         Author          Version    Description
	*---------------------------------------------------------*
	* 2019年5月7日      chentm           v1.0.0               修改原因
	*/
	@Override
	@Transactional
	public BaseEntity saveOrUpdate(BaseEntity baseEntity) {
		try {
			return baseCommonDao.saveOrUpdate(baseEntity);
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	

	/** 
	* @see com.ehs.edm.base.service.BaseCommonService#findAll(java.lang.Class, org.springframework.data.jpa.domain.Specification)  
	* @Function: BaseCommonServiceImpl.java
	* 
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午2:08:37 
	*
	* Modification History:
	* Date         Author          Version    Description
	*---------------------------------------------------------*
	* 2019年5月8日      chentm           v1.0.0               修改原因
	*/
	@Override
	public List findAll(Class clazz, Specification sf,Sort... sorts) {
		Specification  sp=null;
		Class c=clazz;
		int k=0;
		while(k<100&&!StringUtils.equals(c.getSuperclass().getSimpleName(), Object.class.getSimpleName())) {
			c=c.getSuperclass();
			k++;
		}
		if(StringUtils.equals(c.getName(),BaseEntity.class.getName())) {
			sp=getExtendSpecification(sf);
		}else {
			sp=sf;
		}
		List objects=baseCommonDao.find(clazz, sp, sorts);
		if(objects!=null) {
			objects.stream().forEach(s->getSession().evict(s));
		}
		return objects;
	}


	/** 
	* @see com.ehs.edm.base.service.BaseCommonService#findOne(java.lang.Class, org.springframework.data.jpa.domain.Specification)  
	* @Function: BaseCommonServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午4:41:58 
	*
	* Modification History:
	* Date         Author          Version    Description
	*---------------------------------------------------------*
	* 2019年5月8日      chentm           v1.0.0               修改原因
	*/
	@Override
	public Object findOne(Class clazz, Specification sf,Sort... sorts) {
		Specification  sp=null;
		Class c=clazz;
		int k=0;
		while(k<100&&!StringUtils.equals(c.getSuperclass().getSimpleName(), Object.class.getSimpleName())) {
			c=c.getSuperclass();
			k++;
		}
		if(StringUtils.equals(c.getName(),BaseEntity.class.getName())) {
			sp=getExtendSpecification(sf);
		}else {
			sp=sf;
		}
		Object object=baseCommonDao.findOne(clazz, sp,sorts);
		if(object!=null) {
			getSession().evict(object);
		}
		return object;
	}

	/** 
	* @see com.ehs.edm.base.service.BaseCommonService#getExtendSpecification(org.springframework.data.jpa.domain.Specification)  
	* @Function: BaseCommonServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午4:44:35 
	*
	* Modification History:
	* Date         Author          Version    Description
	*---------------------------------------------------------*
	* 2019年5月8日      chentm           v1.0.0               修改原因
	*/
	private Specification getExtendSpecification(Specification sf) {
	    Specification versionSf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(BaseEntity.VERSION_ID), 0));
		//如果有查询条件
		if(sf!=null) {
			final Specification sfff=sf;
			Specification withVersion=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->{
				return criteriaBuilder.and(sfff.toPredicate(root, query, criteriaBuilder),versionSf.toPredicate(root, query, criteriaBuilder));
			};
			return withVersion;
		}else {
			//如果没有指定查询条件
			sf=versionSf;
		}
		return sf;
	}
	
	/**
	 * 
	* @see com.ehs.security.service.BaseCommonService#deleteById(java.lang.Class, java.lang.String)  
	* @Function: BaseCommonServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年5月29日 上午8:54:32 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月29日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void deleteById(Class clazz, String id) {
		doDelete(findById(clazz, id));
	}
	private void doDelete(BaseEntity baseEntity) {
		List<String> foreignClasses= baseEntity.getForeignClasses();
		if(foreignClasses!=null&&foreignClasses.size()>0){
			foreignClasses.stream().forEach(f->{
				String[] ss=StringUtils.split(f,",");
				String foreignKey=ss[0];
				String className=ss[1];
				String refKey=ss[2];
				String v="";
				try {
					v=(String)BeanUtils.findDeclaredMethod(baseEntity.getClass(),(new StringBuilder()).append("get").append(Character.toUpperCase(foreignKey.charAt(0))).append(foreignKey.substring(1)).toString()).invoke(baseEntity);
				
					if(StringUtils.isBlank(v)) {
						StringBuilder builder=(new StringBuilder()).append("无法获取到数据!实体:").append(baseEntity.getClass()).append(",id=").append(baseEntity.getId());
						logger.error(builder.toString());
						throw new RuntimeException();
					}
					final String fi = v;
				    List<BaseEntity> baseList=findAll(Class.forName(className), (Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(refKey), fi)));
				    if(baseList!=null&&baseList.size()>0) {
				    	baseList.forEach(s->doDelete(s));
				    }
				}  catch (Exception e) {
					logger.error(e.getMessage());
					throw new RuntimeException(e);
				}
	
			});
		}
		baseCommonDao.delete(baseEntity);
	}

	/** 
	* @see com.ehs.security.service.BaseCommonService#deleteByWhereCase(java.lang.Class, org.springframework.data.jpa.domain.Specification)  
	* @Function: BaseCommonServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月20日 上午9:41:55 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月20日      chentm           v1.0.0               修改原因
	*/
	@Override
	@Transactional
	public void deleteByWhereCase(Class clazz, Specification sf) {
		BaseEntity baseEntity= (BaseEntity)baseCommonDao.findOne(clazz, getExtendSpecification(sf));
		if(baseEntity!=null) {
			doDelete(baseEntity);
		}
	}

	/** 
	* @see com.ehs.security.service.BaseCommonService#find(java.lang.Class, org.springframework.data.jpa.domain.Specification, com.ehs.security.query.Pagenate, org.springframework.data.domain.Sort[])  
	* @Function: BaseCommonServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月21日 下午2:33:14 
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
			Specification  sp=null;
			Class c=clazz;
			int k=0;
			while(k<100&&!StringUtils.equals(c.getSuperclass().getSimpleName(), Object.class.getSimpleName())) {
				c=c.getSuperclass();
				k++;
			}
			if(StringUtils.equals(c.getName(),BaseEntity.class.getName())) {
				sp=getExtendSpecification(sf);
			}else {
				sp=sf;
			}
			Pagenate pg=baseCommonDao.findPagenate(clazz, sp, pageNate, sorts);
			if(pg.getData()!=null) {
				pg.getData().stream().forEach(s->getSession().evict(s));
			}
			return pg;
		}
		
	}

	/** 
	* @see com.ehs.security.service.BaseCommonService#findById(java.lang.Class, java.lang.String)  
	* @Function: BaseCommonServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月1日 上午11:17:39 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月1日      chentm           v1.0.0               修改原因
	*/
	@Override
	public BaseEntity findById(Class clazz, String id) {
		BaseEntity baseEntity= (BaseEntity)findOne(clazz,(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(BaseEntity.ID), id)));
		if(baseEntity!=null) {
			getSession().evict(baseEntity);
		}
		return baseEntity;
	}

	/** 
	* @see com.ehs.security.service.BaseCommonService#findByCode(java.lang.Class, java.lang.String)  
	* @Function: BaseCommonServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月3日 上午11:31:01 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月3日      chentm           v1.0.0               修改原因
	*/
	@Override
	public BaseEntity findByCode(Class clazz, String code) {
		BaseEntity baseEntity= (BaseEntity)findOne(clazz,(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(BaseEntity.CODE), code)));
		if(baseEntity!=null) {
			getSession().evict(baseEntity);
		}
		return baseEntity;
	}

	/** 
	* @see com.ehs.security.service.BaseCommonService#getSession()  
	* @Function: BaseCommonServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月19日 下午4:39:07 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月19日      chentm           v1.0.0               修改原因
	*/
	@Override
	public Session getSession() {
		return baseCommonDao.getSession();
	}

}
