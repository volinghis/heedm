/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.login.config 
 * @author: chentm   
 * @date: 2019年5月16日 上午9:53:26 
 */
package  com.ehs.security.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ehs.security.filter.LoginFilter;
import com.ehs.security.filter.PathFilter;
import com.ehs.security.filter.CrossBaseFilter;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FilterRegister.java
* @Description: 过滤注册
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月16日 上午9:53:26 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月16日      chentm          v1.0.0               修改原因
*/
@Configuration
public class FilterRegister {

	/**
	 * 
	* @Function: FilterRegister.java
	* @Description: 过滤登录
	*
	* @param 
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午2:50:22 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	@Bean
    public FilterRegistrationBean setFilter(){
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new LoginFilter());
        filterBean.setName("loginFilter");
        filterBean.addUrlPatterns("/*");
        filterBean.setOrder(1);
        return filterBean;
    }
	
	/**
	 * 
	* @Function: FilterRegister.java
	* @Description: 过滤url路径
	*
	* @param
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午2:50:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	@Bean
    public FilterRegistrationBean setPathFilter(){
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new PathFilter());
        filterBean.setName("pathFilter");
        filterBean.addUrlPatterns("/*");
        filterBean.setOrder(10);
        return filterBean;
    }
	
	/**
	 * 
	* @Function: FilterRegister.java
	* @Description: 过滤跨域访问
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年10月10日 下午2:52:05 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年10月10日     zhaol           v1.0.0               修改原因
	 */
	@Bean
    public FilterRegistrationBean setTokenAuthorFilter(){
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new CrossBaseFilter());
        filterBean.setName("crossBaseFilter");
        filterBean.addUrlPatterns("/*");
        filterBean.setOrder(20);
        return filterBean;
    }
	
}
