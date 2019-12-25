/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.controller 
 * @author: chentm   
 * @date: 2019年7月3日 下午2:41:49 
 */
package com.ehs.security.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.OrganizationInfo;
import com.ehs.security.entity.User;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.UserService;
import com.ehs.security.utils.TextNode;
import com.ehs.security.utils.TreeNodeLazy;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationOperController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月3日 下午2:41:49 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月3日      chentm          v1.0.0               修改原因
*/
@Controller
public class OrganizationSearchController {


	@Resource
	private UserService userService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	/**   
	* @Function: OrganizationOperController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月3日 下午3:27:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月3日     chentm           v1.0.0               修改原因
	*/
	@RequestMapping(value = "/action/organization/treeData")
	@ResponseBody
	public String treeData(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		List<OrganizationInfo> oiList= baseCommonService.findAll(OrganizationInfo.class, null);
		List<TreeNodeLazy> lazyList=new ArrayList<TreeNodeLazy>();
		if(oiList!=null&&oiList.size()>0) {
			for(OrganizationInfo s: oiList) {
				if(StringUtils.equals(s.getParentCode(), id)) {
					TreeNodeLazy tnl=new TreeNodeLazy();
					tnl.setId(s.getCode());
					tnl.setPid(s.getParentCode());
					tnl.setName(s.getName());
					tnl.setAttribute1(s.getSort()!=null?String.valueOf(s.getSort()):null);
	    			List list=oiList.stream().filter(d->StringUtils.equals(d.getParentCode(),s.getCode())).collect(Collectors.toList());
	    			tnl.setIsLeaf(list==null||list.size()<1);
	    			lazyList.add(tnl);
				}
			}
		}
		
		lazyList.sort((a, b) -> {
	    	int c=Integer.parseInt(StringUtils.defaultIfBlank(a.getAttribute1(), "0")) - Integer.parseInt(StringUtils.defaultIfBlank(b.getAttribute1(), "0"));
	    	return c;
	    });
		return JSON.toJSONString(lazyList);
	}
	
	/**
	 * 
	* @Function: OrganizationOperController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月3日 下午3:47:44 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月3日     chentm           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/organization/treeDataForUser")
	@ResponseBody
	public String treeDataForUser(HttpServletRequest request, HttpServletResponse response) {
		
		String id=request.getParameter("id");
		
		List<OrganizationInfo> oiList= baseCommonService.findAll(OrganizationInfo.class, null);
		List<TreeNodeLazy> lazyList=new ArrayList<TreeNodeLazy>();
		if(StringUtils.isNotBlank(id)) {
		    List<User> ubList= userService.findUserByOrgCode(id);
		    if(ubList!=null&&ubList.size()>0) {
		    	for(User s:ubList) {
					TreeNodeLazy tnl=new TreeNodeLazy();
					tnl.setId(s.getCode());
					tnl.setPid(id);
					tnl.setName(s.getName());
					tnl.setIsLeaf(true);
					lazyList.add(tnl);
		    	}
		    }
		}
		
		if(oiList!=null&&oiList.size()>0) {
			for(OrganizationInfo s: oiList) {
				if(StringUtils.equals(s.getParentCode(), id)) {
					TreeNodeLazy tnl=new TreeNodeLazy();
					tnl.setId(s.getCode());
					tnl.setPid(s.getParentCode());
					tnl.setName(s.getName());
					tnl.setAttribute5("orgelement");
					tnl.setIsLeaf(false);
					lazyList.add(tnl);
				}
			}
		}

		return JSON.toJSONString(lazyList);
	}
	/**
	 * 
	* @Function: OrganizationSearchController.java
	* @Description: 直接加载树结构数据
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年9月30日 下午1:55:59 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年9月30日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/organization/treeDataNoLazy")
	@ResponseBody
	public String treeDataNoLazy(HttpServletRequest request,HttpServletResponse response) {
		List<TextNode> list = new ArrayList<TextNode>();
		List<OrganizationInfo> oList = baseCommonService.findAll(OrganizationInfo.class, null);
		System.out.println("JSON.toJSONString(list)====="+oList.size());
		if(oList!=null&&oList.size()>0) {
			for(OrganizationInfo d:oList) {
				TextNode tn=new TextNode();
				tn.setId(d.getCode());
				tn.setPid(d.getParentCode());
				tn.setText(d.getName());
				tn.setAttribute1(d.getSort()!=null?String.valueOf(d.getSort()):null);
				tn.setAttribute5(d.getAttribute5());
				list.add(tn);
			}
		}
		list.sort((a, b) -> {
			int c=Integer.parseInt(StringUtils.defaultIfBlank(a.getAttribute1(), "0")) - Integer.parseInt(StringUtils.defaultIfBlank(b.getAttribute1(), "0"));
	    	return c;
		});
		System.out.println("JSON.toJSONString(list)====="+JSON.toJSONString(list));
		return JSON.toJSONString(list);
	}
	

	
}
