/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.login.util 
 * @author: chentm   
 * @date: 2019年5月28日 下午5:02:04 
 */
package com.ehs.security.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.ehs.security.entity.User;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: SysAccessUser.java
* @Description: 线程本地化对象
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月28日 下午5:02:04 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月28日      chentm          v1.0.0               修改原因
*/
public class SysAccessUser {
	private static final  ThreadLocal<User> threadlocal=new TransmittableThreadLocal<User>();
	
	/**
	 * 
	* @Function:set 
	* @Description: 赋值
	* @param user
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:56:12 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public static void set(User user) {
		threadlocal.set(user);
	}

	/**
	 * 
	* @Function:get 
	* @Description: 取值
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:56:30 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public static User get() {
		return (User) threadlocal.get();
	}
	
	/**
	 * 
	* @Function:remove 
	* @Description: 移除
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午4:56:51 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	public static void remove() {
		threadlocal.remove();
	}
}
