/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.security.utils 
 * @author: chentm   
 * @date: 2019年5月7日 上午11:54:22 
 */
package com.ehs.security.utils;

import java.security.MessageDigest;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: MD5Util.java
 * @Description: MD5加密
 *
 * @version: v1.0.0
 * @author: chentm
 * @date: 2019年5月7日 上午11:54:22
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年5月7日
 *        chentm v1.0.0 修改原因
 */
public class MD5Util {

	/**
	 * 
	* @Function: MD5Util.java
	* @Description: MD5加密
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月7日 上午11:56:09 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月7日     chentm           v1.0.0               修改原因
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}
}
