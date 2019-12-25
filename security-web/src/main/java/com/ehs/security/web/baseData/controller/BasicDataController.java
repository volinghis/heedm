package com.ehs.security.web.baseData.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ehs.security.web.baseData.service.BasicDataService;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: BasicDataController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年7月22日 上午10:55:33 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月22日     qjj           v1.0.0               修改原因
*/
@Controller
public class BasicDataController {

	private static final Logger logger=LoggerFactory.getLogger(BasicDataController.class);
	
	@Resource
	private DataDictionarySearchService dataDictionaryService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private BasicDataService basicDataService;
	
	
	/**
	 * 
	* @Function:edmBasicData 
	* @Description: 该函数的功能描述
	* @param request
	* @param mv
	* @return 返回模型视图
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月22日 上午10:55:51 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月22日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/basicData/goBasicData")
	public ModelAndView goBasicData(HttpServletRequest request,ModelAndView mv) {
		String parentCode=request.getParameter("code");
		mv.addObject("parentCode", parentCode);
		mv.setViewName("/basicData/basicData");
		return mv;
	}
	
	/**   
	* @Function:com.ehs.edm.edmBasicData.controller.EdmBasicDataController.getDictByParentCode 
	* @Description: 获取当前节点下所有数据
	* @param request
	* @return 返回json格式数据集合
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月17日 上午10:31:17 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月17日     qjj           v1.0.0               修改原因
	*/
	@RequestMapping(value="/action/basicData/getDictByParentCode")
	@ResponseBody
	public  String  getDictByParentCode(HttpServletRequest request) {
		String parentCode=request.getParameter("parentCode");
		Assert.notNull(parentCode, "parentCode must not be null");
		List<DataDictionary> dictList=dataDictionaryService.findDataDictionary(parentCode);
		return JSON.toJSONString(dictList);
	}
	
	/**
	 * 
	* @Function:saveBasicData 
	* @Description: 基础数据新增或修改
	* 				<p>data:Json格式，页面传入的数据</p>
	* @param request
	* @return 返回json格式的操作结果
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月22日 上午11:02:14 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月22日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/basicData/saveBasicData")
	@ResponseBody
	public String saveBasicData(HttpServletRequest request) {
		try {
			String data = request.getParameter("data");
			//判断修改或新增的标识
			String flag=request.getParameter("flag");
			Assert.notNull(data, "data must not be null");
			Assert.notNull(flag, "flag must not be null");
			List<DataDictionary> dataList= 	JSONArray.parseArray(data, DataDictionary.class);
			basicDataService.saveBasicData(flag,dataList);
			logger.info("页面传入的data:"+data);
		} catch (Exception e) {
			logger.error("新增失败",e.getMessage());
			return JSON.toJSONString(ResultBean.getBean("操作失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("操作成功！",ResultType.OK,null));
	}
	
	/**
	 * 
	* @Function:deleteDictById 
	* @Description: 基础数据删除
	* @param request
	* @return 返回json格式的操作结果
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月22日 上午11:04:38 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月22日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/basicData/deleteBaseDataById")
	@ResponseBody
	public String deleteBaseDataById(HttpServletRequest request) {
		try {
			String id=request.getParameter("data");
			Assert.notNull(id, "cant not find id");
			basicDataService.deleteBasicData(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除失败",e.getMessage());
			return JSON.toJSONString(ResultBean.getBean("删除失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("删除成功！",ResultType.OK,null));
	}
	
	
	/**
	 * 
	* @Function:updateMovedRow 
	* @Description:基础数据上移，下移操作
	* 			<ul>
	* 				<li>orId 当前选中记录id</li>
	* 				<li>orSort 当前选中记录的顺序</li>
	* 				<li>toId 目标位置记录id</li>
	* 				<li>toSort 目标位置记录顺序</li>
	*           </ul>
	* @param request
	* @return 返回json格式的操作结果
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月22日 上午11:05:08 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月22日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/basicData/updateMovedRow")
	@ResponseBody
	public String updateMovedRow(HttpServletRequest request) {
		try {
			String orId=request.getParameter("orId");
			int orSort=Integer.parseInt(request.getParameter("orSort"));
			String toId=request.getParameter("toId");
			int toSort=Integer.parseInt(request.getParameter("toSort"));
			basicDataService.saveSortChangedEntity(orId, orSort, toId, toSort);
		} catch (Exception e) {
			return JSON.toJSONString(ResultBean.getBean("操作失败！",ResultType.ERROR,null));
		}
		
		return JSON.toJSONString(ResultBean.getBean("操作成功！",ResultType.OK,null));
	}
	
}
