/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.filter 
 * @author: chentm   
 * @date: 2019年7月22日 下午3:25:41 
 */
package com.ehs.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: TokenAuthorFilter.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月22日 下午3:25:41 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月22日      chentm          v1.0.0               修改原因
*/
public class CrossBaseFilter implements Filter {

	/** 
	* @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)  
	* @Function: TokenAuthorFilter.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月22日 下午3:25:42 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月22日      chentm           v1.0.0               修改原因
	*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
//        HttpServletResponse res = (HttpServletResponse) response;
//        res.addHeader("Access-Control-Allow-Credentials", "true");
//        res.addHeader("Access-Control-Allow-Origin", "*");
//        res.addHeader("Access-Control-Allow-Methods", "*");
//        res.addHeader("Access-Control-Allow-Headers", "*");
//        res.addHeader("Access-Control-Max-Age", "3600");
        //LOG.info("Origin:{}", req.getHeader("Origin"));

        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
//        rep.addHeader("Access-Control-Allow-Origin", "*");
//        //rep.setHeader("Access-Control-Allow-Origin", "*");
//       rep.addHeader("Access-Control-Expose-Headers", "*");
//        // 允许的访问方法
//        rep.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
//        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
//        
//        //若要返回cookie、携带seesion等信息则将此项设置我true
//        rep.addHeader("Access-Control-Allow-Credentials", "true");
//        // 把获取的Session返回个前端Cookie
        //rep.addCookie(new Cookie("JSSESIONID", session.getId()));
        chain.doFilter(req, rep);

	}

}
