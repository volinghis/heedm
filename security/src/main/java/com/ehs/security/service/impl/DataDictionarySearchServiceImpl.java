/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.service.impl 
 * @author: chentm   
 * @date: 2019年6月26日 上午8:48:08 
 */
package com.ehs.security.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.security.entity.DataDictionary;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DataDictionaryServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月26日 上午8:48:08 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月26日      chentm          v1.0.0               修改原因
*/
@Service
public class DataDictionarySearchServiceImpl implements DataDictionarySearchService{

	private static final  Logger logger=LoggerFactory.getLogger(DataDictionarySearchServiceImpl.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private RedisTemplate redisTemplate;
	
	/**
	 * 
	* @see com.ehs.security.service.DataDictionarySearchService#findDataDictionary(java.lang.String)  
	* @Function: DataDictionarySearchServiceImpl.java
	* @Description: 该函数的功能描述
	* 
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午3:34:54 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public List<DataDictionary> findDataDictionary(String parentCode){
//		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> {
//			if(StringUtils.isBlank(parentCode)) {
//				return criteriaBuilder.and(criteriaBuilder.isNull(root.get(DataDictionary.PARENT_CODE)),StringUtils.isBlank(systemCode)?criteriaBuilder.isNull(root.get(DataDictionary.SYSTEM_CODE)):criteriaBuilder.equal(root.get(DataDictionary.SYSTEM_CODE),systemCode ));
//			}else {
//				return criteriaBuilder.and(criteriaBuilder.equal(root.get(DataDictionary.PARENT_CODE),parentCode),StringUtils.isBlank(systemCode)?criteriaBuilder.isNull(root.get(DataDictionary.SYSTEM_CODE)):criteriaBuilder.equal(root.get(DataDictionary.SYSTEM_CODE),systemCode ));
//			}
//		};
//		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> {
//		if(StringUtils.isBlank(parentCode)) {
//			return criteriaBuilder.and(criteriaBuilder.isNull(root.get(DataDictionary.PARENT_CODE)));
//		}else {
//			return criteriaBuilder.and(criteriaBuilder.equal(root.get(DataDictionary.PARENT_CODE),parentCode));
//		}
//		};
//	    return (List<DataDictionary>)baseCommonService.findAll(DataDictionary.class, sf, new Sort(Direction.ASC,DataDictionary.SORT));
		 List<DataDictionary> datas= redisTemplate.opsForHash().values(DataDictionary.class.getSimpleName());
		 List<DataDictionary> lists=new ArrayList<DataDictionary>();
		 if(datas!=null) {
			 datas.forEach(s->{
				 if(StringUtils.equals(s.getParentCode(), parentCode)) {
					 lists.add(s);
				 }
			 });
		 }
		 lists.sort((a, b) -> {
			 if (a.getSort()==null||b.getSort()==null) {
				 return 0;
			 }
			 return a.getSort() - b.getSort();
		 });
		 return lists;
	}

	/** 
	* @see com.ehs.security.service.DataDictionarySearchService#findAddDataDictionary(java.lang.String)  
	* @Function: DataDictionaryServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月26日 下午3:33:09 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月26日      chentm           v1.0.0               修改原因
	*/
	@Override
	public List<DataDictionary> findAllDataDictionary() {
//		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> {
//			return criteriaBuilder.and( StringUtils.isBlank(systemCode)?criteriaBuilder.isNull(root.get(DataDictionary.SYSTEM_CODE)):criteriaBuilder.equal(root.get(DataDictionary.SYSTEM_CODE),systemCode ));
//		};
//	    return (List<DataDictionary>)baseCommonService.findAll(DataDictionary.class, null, new Sort(Direction.ASC,DataDictionary.SORT));
		List<DataDictionary> datas=redisTemplate.opsForHash().values(DataDictionary.class.getSimpleName());

		 return datas;
	}

	/** 
	* @see com.ehs.security.service.DataDictionarySearchService#findOne(java.lang.String, java.lang.String)  
	* @Function: DataDictionaryServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月2日 下午3:57:23 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月2日      chentm           v1.0.0               修改原因
	*/
	@Override
	public DataDictionary findOne(String code) {
		Assert.notNull(code, "code must be not null");
//		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> {
//			return criteriaBuilder.and(criteriaBuilder.equal(root.get(DataDictionary.CODE),code),StringUtils.isBlank(systemCode)?criteriaBuilder.isNull(root.get(DataDictionary.SYSTEM_CODE)):criteriaBuilder.equal(root.get(DataDictionary.SYSTEM_CODE),systemCode ));
//			
//		};
		
//		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> {
//		return criteriaBuilder.and(criteriaBuilder.equal(root.get(DataDictionary.CODE),code));
//		
//		};
//		DataDictionary dataDictionary=(DataDictionary)baseCommonService.findOne(DataDictionary.class, sf);

		return (DataDictionary)redisTemplate.opsForHash().get(DataDictionary.class.getSimpleName(), code);
	}
	
	/** 
	* @see com.ehs.security.service.DataDictionarySearchService#reflushCache()  
	* @Function: DataDictionarySearchServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年8月9日 下午2:53:18 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月9日      chentm           v1.0.0               修改原因
	*/
	@Override
	public void reflushCache() {
		if(redisTemplate.hasKey(DataDictionary.class.getSimpleName())) {
			redisTemplate.delete(DataDictionary.class.getSimpleName());
		}
		List<DataDictionary> list=(List<DataDictionary>)baseCommonService.findAll(DataDictionary.class, null, new Sort(Direction.ASC,DataDictionary.SORT));
		logger.info("开始刷新缓存===>");
		if(list!=null) {
			list.forEach(s->{
				redisTemplate.opsForHash().put(DataDictionary.class.getSimpleName(), s.getCode(), s);
			});
		}
	}
}
