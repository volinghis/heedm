/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.dao.impl 
 * @author: chentm   
 * @date: 2019年6月6日 下午3:06:47 
 */
package com.ehs.security.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ehs.security.dao.DdlDao;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DdlDaoImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月6日 下午3:06:47 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月6日      chentm          v1.0.0               修改原因
*/
@Repository
public class DdlDaoImpl implements DdlDao{

	private static Logger logger=LoggerFactory.getLogger(DdlDaoImpl.class);
	
	
	@Autowired
	private javax.sql.DataSource dataSource;

	/** 
	* @see com.ehs.security.dao.DdlDao#executeDdl(java.lang.String)  
	* @Function: DdlDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月6日 下午3:07:48 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月6日      chentm           v1.0.0               修改原因
	*/
	@Override
	public void executeDdl(String ddl) {
		logger.debug(ddl);
		PreparedStatement stmt=null;
		try {
			Connection connection=dataSource.getConnection();
			stmt=connection.prepareStatement(ddl);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				if(stmt!=null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
