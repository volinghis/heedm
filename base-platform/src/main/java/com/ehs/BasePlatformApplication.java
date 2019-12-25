/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs 
 * @author: chentm   
 * @date: 2019年5月22日 下午2:23:47 
 */
package com.ehs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.ehs.security.utils.SessionConstants;
import com.ehs.security.utils.SpringUtils;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: BasePlatformApplication.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月22日 下午2:23:47 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月22日      chentm          v1.0.0               修改原因
*/
@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = SessionConstants.MAX_TIME_OUT)
public class BasePlatformApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(BasePlatformApplication.class, args);

	}
}