package com.ehs.security.web.dictManager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ehs.security.entity.DataDictionary;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;
import com.ehs.security.utils.TreeNodeLazy;
import com.ehs.security.web.dictManager.service.DictManagerService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: dictManagerController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年8月12日 上午9:56:02 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年8月12日     qjj           v1.0.0               修改原因
*/
@Controller
public class DictManagerController {
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private DataDictionarySearchService dataDictionaryService;
	
	@Resource
	private DataDictionarySearchService dataDictionarySearchService;
	
	@Resource
	private DictManagerService  dictManagerService;
	
	@RequestMapping(value = "/action/dict")
	public ModelAndView goDict(ModelAndView mv,HttpServletRequest request) {
		mv.setViewName("/dictManager/dictManager");
		return mv;
	}
	
	/**
	 * 
	* @Function:getDictList 
	* @Description: 获取所有字典数据树
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月12日 上午11:28:16 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月12日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value="/action/dictManager/getDictTree")
	@ResponseBody
	public String getDictList(HttpServletRequest request,HttpServletResponse response) {
		List<DataDictionary> dictList=dataDictionarySearchService.findAllDataDictionary();
		String filterCode="edmGroupInfo";//过滤掉不属于数据字典的记录
		List<DataDictionary> templist=new ArrayList<DataDictionary>();
		filterItems(templist, dictList,filterCode);
		dictList.removeAll(templist);
		List<TreeNodeLazy> treeNodes=new ArrayList<TreeNodeLazy>();
		dictList.stream().filter(s->!StringUtils.equals(s.getCode(),filterCode)).forEach(c->{
			TreeNodeLazy treeNode=new TreeNodeLazy();
			treeNode.setId(c.getCode());
			treeNode.setPid(c.getParentCode());
			treeNode.setName((c.getText()));
			treeNode.setIsLeaf(true);
			treeNodes.add(treeNode);
		});
		return JSONArray.toJSONString(treeNodes); 
	}
	
	
	private void filterItems(List<DataDictionary> list,List<DataDictionary> dataList,String parentCode) {
		dataList.stream().filter(s->StringUtils.equals(s.getParentCode(),parentCode)).forEach(c->{
			list.add(c);
			filterItems(list,dataList,c.getCode());
		});
	}
	
	/**
	 * 
	* @Function:getItemsByParentCode 
	* @Description: 该函数的功能描述
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月19日 下午6:29:49 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月19日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/action/dictManager/getItemsByParentCode")
	@ResponseBody
	public String  getItemsByParentCode(HttpServletRequest request) {
		String parentCode=request.getParameter("parentCode");
		//Assert.notNull(parentCode, "parentCode must not be null");
		List<DataDictionary> dictList=dataDictionaryService.findDataDictionary(parentCode);
		return JSON.toJSONString(dictList);
	}
	
	
	/**
	 * 
	* @Function:saveBasicData 
	* @Description: 该函数的功能描述
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月19日 下午7:01:24 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月19日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/action/dictManager/saveDictData")
	@ResponseBody
	public String saveDictData(HttpServletRequest request) {
		try {
			String data = request.getParameter("data");
			//判断修改或新增的标识
			String flag=request.getParameter("flag");
			List<DataDictionary> dataList= 	JSONArray.parseArray(data, DataDictionary.class);
			dictManagerService.saveDictData(flag,dataList);
		} catch (Exception e) {
			return JSON.toJSONString(ResultBean.getBean("操作失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("操作成功！",ResultType.OK,null));
	}
	
	
	/**
	 * 
	* @Function:deleteDictById 
	* @Description:数据字典删除
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月20日 上午11:24:31 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月20日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/action/dictManager/deleteDictById")
	@ResponseBody
	public String deleteDictById(HttpServletRequest request) {
		try {
			String id=request.getParameter("data");
			Assert.notNull(id, "cant not find id");
			dictManagerService.deleteDictData(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("删除失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("删除成功！",ResultType.OK,null));
	}
}
