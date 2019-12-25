package com.ehs.base.platform.base.util;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.ehs.security.config.PlatformConfig;


@Component
@Order(value=2) //定义组件加载顺序
public class PublicStaticFileLoaded  implements CommandLineRunner {

	@Resource
	private RedisTemplate<String, List<String>> redisTemplate; 
	
	@Resource
	private PlatformConfig platformConfig;

	@Override
	public void run(String... args) throws Exception {
		
	}

}
