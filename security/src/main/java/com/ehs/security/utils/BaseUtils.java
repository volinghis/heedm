/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.base.utils 
 * @author: chentm   
 * @date: 2019年5月7日 下午2:46:08 
 */
package com.ehs.security.utils;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: Constans.java
* @Description: 工具类
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月7日 下午2:46:08 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月7日      chentm          v1.0.0               修改原因
*/
public class BaseUtils {


	/**
	 * 
	* @Function: Constans.java
	* @Description: 当前时间
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月7日 下午6:26:41 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月7日     chentm           v1.0.0               修改原因
	 */
	public static Timestamp getNow() {
		return new Timestamp(System.currentTimeMillis());
	}
	/**
	 * 
	* @Function: BaseUtils.java
	* @Description: 加密
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月22日 下午4:27:15 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月22日     chentm           v1.0.0               修改原因
	 */
	public static String encode(String v) {
		final BASE64Encoder encoder = new BASE64Encoder();
		final String text = v;
		byte[] textByte;
		try {
			textByte = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("加密错误");
		}
		final String encodedText = encoder.encode(textByte);
		return encodedText;
	}
	
	/**
	 * 
	* @Function: BaseUtils.java
	* @Description: 解密
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月22日 下午4:30:18 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月22日     chentm           v1.0.0               修改原因
	 */
	public static String decode(String v) {
		final BASE64Decoder  decoder = new BASE64Decoder();
		String str="";
		try {
			str = new String(decoder.decodeBuffer(v), "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("解密错误");
		} 
		return str;
	}
	/**
	 * 
	* @Function: BaseUtils.java
	* @Description: 获取IP
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月22日 下午4:49:58 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月22日     chentm           v1.0.0               修改原因
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
