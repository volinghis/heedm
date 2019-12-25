/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.config 
 * @author: chentm   
 * @date: 2019年7月1日 下午6:44:13 
 */
package com.ehs.security.config;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: TimestampConverterConfig.java
* @Description: 格式化时间戳
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月1日 下午6:44:13 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月1日      chentm          v1.0.0               修改原因
*/
@Component
public class TimestampConverterConfig implements Converter<String, Timestamp> {

	/** 
	* @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)  
	* @Function: TimestampConverterConfig.java
	* @Description: 格式化时间戳
	*
	* @param source
	* @return：时间戳数据
	* @throws：无
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月1日 下午6:44:41 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月1日      chentm           v1.0.0               修改原因
	*/
	@Override
	public Timestamp convert(String source) {
		if(StringUtils.isBlank(source)) {
			return null;
		}
		if(StringUtils.length(source)<=10) {
			source=source+" 00:00:00";
		}
		return Timestamp.valueOf(source);
	}


}
