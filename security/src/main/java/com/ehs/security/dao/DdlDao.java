/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.dao 
 * @author: chentm   
 * @date: 2019年6月6日 下午3:06:25 
 */
package com.ehs.security.dao;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DdlDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月6日 下午3:06:25 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月6日      chentm          v1.0.0               修改原因
*/
public interface DdlDao {

	public void executeDdl(String ddl);
}
