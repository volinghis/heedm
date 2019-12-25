package com.ehs.edm.edmDataLibrary.RulesDatabase.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ehs.edm.edmDataLibrary.RulesDatabase.entity.RulesDatabase;
import com.ehs.edm.edmDataLibrary.RulesDatabase.service.RulesDatabaseService;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.DataDictionary;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmDataLibraryController.java
* @Description: 备件库管理
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年6月28日 下午6:34:21 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月28日     zhaol           v1.0.0               修改原因
*/
@Controller
public class RulesDatabaseController {

	private static final Logger logger = LoggerFactory.getLogger(RulesDatabaseController.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private RulesDatabaseService reRulesDatabaseService;
	
	@Resource
	private DataDictionarySearchService dataDictionaryService;
	
	/**
	 * 
	* @Function: EdmDataLibraryController.java
	* @param mv
	* @param request
	* @param response
	* @return 跳转规章制度展示页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午3:48:16 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmDataLibrary/goRulesDatabase")
	public ModelAndView goRulesDatabase(ModelAndView mv, ServletRequest request,ServletResponse response) {
		mv.setViewName("/edmDataLibrary/rulesDatabase/rulesDatabase");
		logger.info("========= 退出edmDataLibrary方法，准备跳转页面===========");
		return mv;
	}
	
	/**
	 * 
	* @Function: EdmDataLibraryController.java
	* @Description: 点击树节点展示所包含的所有设备资料</br>
	* 				parentCode:树结构code</br>
	* 				name:设备资料名称，用来模糊查询
	*
	* @param request
	* @param response
	* @return 分页形式的数据集合
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年6月28日 下午6:45:13 
	*
	* Modification History:
	* Date         Author          Version         Description
	*---------------------------------------------------------*
	* 2019年6月28日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmDataLibrary/rulesDatabaseList")
	@ResponseBody
	public String rulesDatabaseList(ServletRequest request,ServletResponse response){
		logger.info("========进入edmDataLibraryTableData方法==========");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		logger.info("edmDataLibraryTableData     code==============="+code);
		logger.info("edmDataLibraryTableData     name==============="+name);
        Pagenate pageNate=new Pagenate();
        pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
        List<DataDictionary> dataList = dataDictionaryService.findAllDataDictionary();
        List<String> treelist=new ArrayList<String>();
		createMenuNode(treelist, dataList, code);
		Specification<RulesDatabase> sf = null;
		List<Predicate> ps=new ArrayList<Predicate>();
		//多条件查询
		sf=(Root<RulesDatabase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->{
			if(StringUtils.isNotBlank(code)) {
				DataDictionary dataDictionary= (DataDictionary) baseCommonService.findByCode(DataDictionary.class, code);
				CriteriaBuilder.In<String> in=null;
				if(dataDictionary != null ) {
					in= criteriaBuilder.in(root.get(RulesDatabase.PARENTCODE));
					if (treelist.size() > 0) {
						for (int j = 0; j < treelist.size(); j++) {
							in.value(treelist.get(j));
							System.out.println("递归查询到的第"+(j+1)+"个Code："+treelist.get(j));
						}
					}
				}
				in.value(code);
				ps.add(criteriaBuilder.and(in));//存入条件集合里
			}
        	if(StringUtils.isNotBlank(name)) {
        		ps.add(criteriaBuilder.like(root.get(RulesDatabase.NAME), "%"+name+"%"));
        	}
        	return criteriaBuilder.and(ps.toArray(new Predicate[0]));
        };
        logger.info("======退出edmDataLibraryTableData方法==========");
        return JSON.toJSONString(baseCommonService.findPagenate(RulesDatabase.class, sf, pageNate));
	}
	
	private void createMenuNode(List<String> list,List<DataDictionary> dataList,String parentCode) {
		dataList.stream().filter(s->StringUtils.equals(s.getParentCode(),parentCode)).forEach(c->{
			list.add(c.getCode());
			createMenuNode(list,dataList,c.getCode());
		});
	}
	/**
	 * 
	* @Function: EdmDataLibraryController.java
	* @Description: 跳转新增页面</br>
	* 				code:树节点code</br>
	* 				name:文件名称</br>
	* 				dataCode:资料实体dataCode</br>
	* 				edmDataLibraryId：资料ID
	*
	* @param mv
	* @param request
	* @param response
	* @return：进入资料编辑页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年6月28日 下午6:35:31 
	*
	* Modification History:
	* Date         Author          Version       Description
	*---------------------------------------------------------*
	* 2019年6月28日     zhaol         v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmDataLibrary/rulesDatabaseEdit")
	public ModelAndView edmDataLibraryAdd(ModelAndView mv, HttpServletRequest request,HttpServletResponse response) {
		logger.info("=====进入edmDataLibraryEdit方法========");
		String code = request.getParameter("code");
		String rulesDatabaseId = request.getParameter("rulesDatabaseId");
		RulesDatabase rulesDatabase=null;
		if(StringUtils.isNotBlank(rulesDatabaseId)) {
			rulesDatabase=(RulesDatabase) baseCommonService.findById(RulesDatabase.class, rulesDatabaseId);
		}else {
			rulesDatabase=new RulesDatabase();
			rulesDatabase.setParentCode(code);
		}
		mv.addObject("objectBean", rulesDatabase);
		mv.setViewName("/edmDataLibrary/rulesDatabase/rulesDatabaseEdit");
		logger.info("======退出edmDataLibraryEdit方法========");
		return mv;
	}
	/**
	 * 
	* @Function: EdmDataLibraryController.java
	* @Description: 保存功能，同时保存文件</br>
	* 				filesJson：上传的文件数据
	*
	* @param edmDataLibrary
	* @param request
	* @param response
	* @return 返回成功或者失败信息
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年7月1日 上午9:02:33 
	*
	* Modification History:
	* Date         Author          Version        
	* 
	* Description
	*---------------------------------------------------------*
	* 2019年7月1日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmDataLibrary/rulesDatabaseSave")
	@ResponseBody
	public String saveRulesDatabase(@ModelAttribute RulesDatabase rulesDatabase, HttpServletRequest request, HttpServletResponse response) {
		logger.info("=======进入edmDataLibrarySave方法==========");
		String filesJson=request.getParameter("filesJson");
		try {
			BaseEntity base= reRulesDatabaseService.saveDataLibrary(rulesDatabase,JSON.parseArray(filesJson, FileInfo.class));
			return JSON.toJSONString(ResultBean.getBean("保存成功！",ResultType.OK,base.getId()));
		}catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("保存失败！",ResultType.ERROR,null));
		}
	}
	
	/**
	 * 
	* @Function: EdmDataLibraryController.java
	* @Description: 得到一行数据的id,删除这行数据
	*
	* @param request
	* @return：返回成功或者失败
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年7月1日 下午8:48:38 
	*
	* Modification History:
	* Date         Author          Version        Description
	*---------------------------------------------------------*
	* 2019年7月1日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmDataLibrary/rulesDatabaseDel")
	@ResponseBody
	public String delRulesDatabase(HttpServletRequest request) {
		logger.info("============进入edmDataLibrary    Del方法===========");
		try {
			String id=request.getParameter("data");
			baseCommonService.deleteById(RulesDatabase.class,id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除失败",e.getMessage());
			return JSON.toJSONString(ResultBean.getBean("删除失败",ResultType.ERROR,null));
		}
		logger.info("============退出edmDataLibrary    Del方法===========");
		return JSON.toJSONString(ResultBean.getBean("删除成功",ResultType.OK,null));
	}
	
	/**
	 * 
	* @Function: EdmDataLibraryController.java
	* @Description: 通过Id判断该数据是否存在于数据库
	*
	* @param request
	* @return：一个新实体，或者返回该id的实体
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年7月2日 上午9:43:34 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月2日     zhaol           v1.0.0               修改原因
	 */
	@ModelAttribute
	public RulesDatabase getRulesDatabase(HttpServletRequest request) {
		String id=request.getParameter("rulesDatabaseId");
		if(StringUtils.isBlank(id)) {
			return new RulesDatabase();
		}else {
			return (RulesDatabase)baseCommonService.findById(RulesDatabase.class, id);
		}
	}
}
