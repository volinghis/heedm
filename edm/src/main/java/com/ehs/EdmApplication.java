
package com.ehs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.ehs.security.utils.SessionConstants;
/**
 * 
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmApplication.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月30日 上午10:01:02 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月30日      chentm          v1.0.0               修改原因
 */
@SpringBootApplication
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = SessionConstants.MAX_TIME_OUT)
public class EdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdmApplication.class, args);
	}

}
