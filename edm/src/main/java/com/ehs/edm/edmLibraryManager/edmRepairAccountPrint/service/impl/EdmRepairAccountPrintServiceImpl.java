/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.service 
 * @author: chentm   
 * @date: 2019年7月19日 下午1:17:30 
 */
package com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.entity.EdmRepairAccountPrint;
import com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.service.EdmRepairAccountPrintService;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.entity.User;
import com.ehs.security.flow.service.FlowBaseService;
import com.ehs.security.flow.utils.FlowConstans;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.FileInfoService;
import com.ehs.security.service.UserService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmRepairAccountPrintServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月19日 下午1:17:30 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月19日      chentm          v1.0.0               修改原因
*/
@Service
public class EdmRepairAccountPrintServiceImpl implements EdmRepairAccountPrintService {

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FlowBaseService flowBaseService;

	@Resource
	private FileInfoService fileInfoService;
	
	@Resource
	private UserService userService;
	
	@Resource 
    private Environment environment;  
	
	/**
	 * 
	* @see com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.service.EdmRepairAccountPrintService#saveOrUpdate(com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.entity.EdmRepairAccountPrint, java.util.List)
	 */
	@Transactional
	@Override
	public BaseEntity saveOrUpdate(EdmRepairAccountPrint edmRepairAccountPrint, List<FileInfo> fileList) {
		EdmRepairAccountPrint erp=(EdmRepairAccountPrint)baseCommonService.saveOrUpdate(edmRepairAccountPrint);
		fileInfoService.saveFiles(fileList, erp.getCode());
		if(StringUtils.isBlank(erp.getFlowProcessInstanceId())) {
			Map map=new HashMap();
			map.put(FlowConstans.FLOW_FORM_DEFAULT_URL, environment.getProperty(FlowConstans.FLOW_SERVER_CONTEXT_PATH)+"/action/edmLibraryManager/edmRepairAccountPrintEdit?edmRepairAccountPrintId=");
			map.put(FlowConstans.FLOW_PROCESS_NAME, erp.getRepairName());
			flowBaseService.startProcess(erp,map);
		}
		return erp;
	}

	/**
	 * 
	 * @see com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.service.EdmRepairAccountPrintService#getCurrentOrgMembers(java.lang.String)
	 */
	@Override
	public String getCurrentOrgMembers(String orgCode) {
		List<User> users=userService.findUserByOrgCode(orgCode);
		StringBuffer sb = new StringBuffer();
		for (User user : users) {
			if(user!=null) {
				sb.append(user.getName()).append(",");
			}
		}
		if(sb.length()>0) {
			sb.deleteCharAt(sb.length()-1);
			return sb.toString();
		}
		return null;
	}
}
