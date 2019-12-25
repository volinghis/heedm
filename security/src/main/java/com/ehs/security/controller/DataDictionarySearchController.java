/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.controller 
 * @author: chentm   
 * @date: 2019年6月26日 上午8:45:48 
 */
package com.ehs.security.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.DataDictionary;
import com.ehs.security.service.DataDictionarySearchService;
import com.ehs.security.utils.TextNode;
import com.ehs.security.utils.TreeNodeLazy;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DataDictionaryController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月26日 上午8:45:48 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月26日      chentm          v1.0.0               修改原因
*/
@Controller
public class DataDictionarySearchController {
	private static final Logger logger = LoggerFactory.getLogger(DataDictionarySearchController.class);
	
	@Resource
	private DataDictionarySearchService dataDictionaryService;
	
	/**
	 * 
	* @Function:dataDictionaryTreeData 
	* @Description: 懒加载树
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月20日 下午2:36:42 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月20日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/action/dataDictionaryTreeData")
	@ResponseBody
	public String dataDictionaryTreeData(HttpServletRequest request, HttpServletResponse response) {
		List<TreeNodeLazy> trees=new ArrayList<TreeNodeLazy>();
		String parentCode=request.getParameter("parentCode");
		logger.info("DataDictionary   parentCode======="+parentCode);
	
		String id=request.getParameter("id");
		if(StringUtils.isBlank(id)) {
			DataDictionary dd= dataDictionaryService.findOne(parentCode);
			TreeNodeLazy tn=new TreeNodeLazy();
			tn.setId(dd.getCode());
			tn.setPid(dd.getParentCode());
			tn.setName(dd.getText());
			tn.setAttribute1(dd.getAttribute1());
			tn.setAttribute2(dd.getAttribute2());
			tn.setAttribute3(dd.getAttribute3());
			tn.setAttribute4(dd.getAttribute4());
			tn.setAttribute5(dd.getAttribute5());
			tn.setIsLeaf(false);
			trees.add(tn);
			return JSON.toJSONString(trees);
		}else {
		    List<DataDictionary> dataList=  dataDictionaryService.findAllDataDictionary();
		    
		    if(dataList!=null&&dataList.size()>0) {
		    	for(DataDictionary d:dataList) {
		    		if(StringUtils.equals(d.getParentCode(), id)) {
		    			TreeNodeLazy tn=new TreeNodeLazy();
		    			tn.setId(d.getCode());
		    			tn.setAttribute1(d.getSort()!=null?String.valueOf(d.getSort()):null);
		    			tn.setAttribute2(d.getCreationTime()==null?(System.currentTimeMillis()+""):(d.getCreationTime().getTime()+""));
		    			tn.setPid(d.getParentCode());
		    			tn.setName(d.getText());
						tn.setAttribute5(d.getAttribute5());
		    			List list=dataList.stream().filter(s->StringUtils.equals(s.getParentCode(),d.getCode())).collect(Collectors.toList());
		    			tn.setIsLeaf(list==null||list.size()<1);
		    			trees.add(tn);
		    		}
		    		
		    	}
		    }
		    trees.sort((a, b) -> {
		    	int c=Integer.parseInt(StringUtils.defaultIfBlank(a.getAttribute1(), "0")) - Integer.parseInt(StringUtils.defaultIfBlank(b.getAttribute1(), "0"));
		    	if(c==0) {
		    		return ((Long)(Long.parseLong(a.getAttribute2()) - Long.parseLong(b.getAttribute2()))).intValue();
		    	}
		    	return c;
		    });
			return JSON.toJSONString(trees);
		}
	}
	
	
	/**
	 * 
	* @Function:dataDictionaryTreeDataNotLazy 
	* @Description: 获取当前父节点下的所有子节点</br>parentCode 当前的父节点code
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年10月10日 下午5:10:32 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年10月10日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/action/dataDictionaryData")
	@ResponseBody
	public String dataDictionaryTreeDataNotLazy(HttpServletRequest request, HttpServletResponse response) {
		String parentCode=request.getParameter("parentCode");
	    List<DataDictionary> dataList= dataDictionaryService.findDataDictionary(parentCode);
		 List<TextNode> trees=new ArrayList<TextNode>();
		if(dataList!=null&&dataList.size()>0) {
			for(DataDictionary d:dataList) {
				TextNode tn=new TextNode();
				tn.setId(d.getCode());
				tn.setPid(d.getParentCode());
				tn.setText(d.getText());
				tn.setAttribute5(d.getAttribute5());
				trees.add(tn);
			}
		}
		System.out.println(JSON.toJSONString(trees));
		return JSON.toJSONString(trees);
	}

	/**
	 * 
	* @Function: DataDictionarySearchController.java
	* @Description: 直接加载树样式
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年9月30日 下午1:56:39 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年9月30日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/dataTreeDataNotLazy")
	@ResponseBody
	public String dataTreeDataNotLazy(HttpServletRequest request, HttpServletResponse response) {
		String parentCode=request.getParameter("parentCode");
	    List<DataDictionary> dataList1= dataDictionaryService.findAllDataDictionary();
	    List<DataDictionary> dataList =dataList1.stream().filter(s->StringUtils.equals(s.getSystemCode(), parentCode)).collect(Collectors.toList());
		List<TextNode> trees=new ArrayList<TextNode>();
		if(dataList!=null&&dataList.size()>0) {
			for(DataDictionary d:dataList) {
				TextNode tn=new TextNode();
				tn.setId(d.getCode());
				tn.setPid(d.getParentCode());
				tn.setText(d.getText());
				tn.setAttribute1(d.getSort()!=null?String.valueOf(d.getSort()):null);
				tn.setAttribute2(d.getCreationTime()==null?(System.currentTimeMillis()+""):(d.getCreationTime().getTime()+""));
				trees.add(tn);
			}
		}
		trees.sort((a, b) -> {
	    	int c=Integer.parseInt(StringUtils.defaultIfBlank(a.getAttribute1(), "0")) - Integer.parseInt(StringUtils.defaultIfBlank(b.getAttribute1(), "0"));
	    	if(c==0) {
	    		return ((Long)(Long.parseLong(a.getAttribute2()) - Long.parseLong(b.getAttribute2()))).intValue();
	    	}
	    	return c;
	    });
		System.out.println(JSON.toJSONString(trees));
		return JSON.toJSONString(trees);
	}
	
}
