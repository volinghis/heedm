/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmDataLibrary.EquipmentDatabase.controller 
 * @author: qjj   
 * @date: 2019年9月23日 下午2:38:56 
 */
package com.ehs.edm.edmDataLibrary.EquipmentDatabase.controller;

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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ehs.edm.edmDataLibrary.EquipmentDatabase.service.EquipmentDatabaseService;
import com.ehs.edm.edmDataLibrary.RulesDatabase.entity.RulesDatabase;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint;
import com.ehs.security.entity.DataDictionary;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EquipmentDatabaseController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年9月23日 下午2:38:56 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月23日     qjj           v1.0.0               修改原因
*/
@Controller
public class EquipmentDatabaseController {
	
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private EquipmentDatabaseService equipmentDatabaseService;
	
	@Resource
	private DataDictionarySearchService dataDictSearchService;
	/**
	 * 
	* @Function:goEquipmentDatabase 
	* @Description: 设备资料库页面跳转
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月23日 下午3:39:07 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月23日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/action/edmDataLibrary/goEquipmentDatabase")
	public String  goEquipmentDatabase() {
		
		return "/edmDataLibrary/equipmentDatabase/equipmentDatabase";
	}
	
	
	
	/**
	 * 
	* @Function:equipmentDatabaseEdit 
	* @Description: 该函数的功能描述
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月23日 下午4:20:14 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月23日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/action/edmDataLibrary/equipmentDatabaseEdit")
	public ModelAndView equipmentDatabaseEdit(ModelAndView mv,HttpServletRequest request) {
		String code=request.getParameter("code");
		String nodeCode=request.getParameter("nodeCode");
		String fileInfoId=request.getParameter("fileInfoId");
		FileInfo fileInfo=null;
		if (StringUtils.isNotBlank(fileInfoId)) {
			fileInfo=(FileInfo)baseCommonService.findById(FileInfo.class, fileInfoId);
		}else {
			EdmAccountPrint edmAccountPrint=(EdmAccountPrint)baseCommonService.findByCode(EdmAccountPrint.class, code);
			if(edmAccountPrint!=null) {
				fileInfo=new FileInfo();
				fileInfo.setEntityName(edmAccountPrint.getDeviceName());
			}
			fileInfo.setNodeCode(nodeCode);
			fileInfo.setEntityDataCode(code);
		}
		mv.addObject("objectBean",fileInfo );
		mv.setViewName("/edmDataLibrary/equipmentDatabase/equipmentDatabaseEdit");
		return mv;
	}
	
	
	/**
	 * 
	* @Function:saveRulesDatabase 
	* @Description: 设备资料新增
	* @param rulesDatabase
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月23日 下午5:13:55 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月23日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/action/edmDataLibrary/equipmentDatabaseSave")
	@ResponseBody
	public String saveEquipmentDatabase(@ModelAttribute FileInfo fileInfo, HttpServletRequest request, HttpServletResponse response) {
		String filesJson=request.getParameter("filesJson");
		try {
			equipmentDatabaseService.saveEquipmentDatabase(JSON.parseArray(filesJson, FileInfo.class),fileInfo);
			return JSON.toJSONString(ResultBean.getBean("保存成功！",ResultType.OK,null));
		}catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("保存失败！",ResultType.ERROR,null));
		}
	}

	
	
	
	/**
	 * 
	* @Function:equipmentDatabaseList 
	* @Description: 设备资料列表展示
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月23日 下午6:47:07 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月23日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/action/edmDataLibrary/equipmentDatabaseList")
	@ResponseBody
	public String equipmentDatabaseList(ServletRequest request,ServletResponse response){
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String category=request.getParameter("category");
        Pagenate pageNate=new Pagenate();
        pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
        List<DataDictionary> dataList=  dataDictSearchService.findAllDataDictionary();
        List<String> treelist=new ArrayList<String>();
		createMenuNode(treelist, dataList, code);
		Specification<RulesDatabase> sf = null;
		List<Predicate> ps=new ArrayList<Predicate>();
		//多条件查询
		sf=(Root<RulesDatabase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->{
			if(StringUtils.isNotBlank(code)) {
				EdmAccountPrint   edmAccount= (EdmAccountPrint) baseCommonService.findByCode(EdmAccountPrint.class, code);
	    		   CriteriaBuilder.In<String> in=null;
	    		   if(edmAccount!=null) {
	    			   in= criteriaBuilder.in(root.get(FileInfo.ENTITY_DATA_CODE));
	    		   }else {
	    			   System.out.println("根据设备所属系统查寻");
	    			   in=criteriaBuilder.in(root.get(FileInfo.NODE_CODE));
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
        		ps.add(criteriaBuilder.like(criteriaBuilder.upper(root.get(FileInfo.NAME)), "%"+name.toString().toUpperCase()+"%"));
        	}
        	if(StringUtils.isNotBlank(category)) {
        		DataDictionary dd=dataDictSearchService.findOne(category);
        		if(dd!=null) {
        			ps.add(criteriaBuilder.like(root.get(FileInfo.CATEGORY), "%"+dd.getText()+"%"));
        		}
        	}
        	ps.add(criteriaBuilder.equal(root.get(FileInfo.SYS_CODE), "equipmentData"));
        	return criteriaBuilder.and(ps.toArray(new Predicate[0]));
        };
        return JSON.toJSONString(baseCommonService.findPagenate(FileInfo.class, sf, pageNate));
	}
	
	private void createMenuNode(List<String> list,List<DataDictionary> dataList,String parentCode) {
		dataList.stream().filter(s->StringUtils.equals(s.getParentCode(),parentCode)).forEach(c->{
			list.add(c.getCode());
			createMenuNode(list,dataList,c.getCode());
		});
	}
	
	
	/**
	 * 
	* @Function:removeEquipmentDatabase 
	* @Description: 设备资料移除
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月24日 下午3:59:20 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月24日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/action/edmDataLibrary/equipmentDatabaseRemove")
	@ResponseBody
	public String removeEquipmentDatabase(HttpServletRequest request) {
		try {
			String id=request.getParameter("fileInfoId");
			baseCommonService.deleteById(FileInfo.class,id);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("删除失败",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("删除成功",ResultType.OK,null));
	}
	
	
	
	@ModelAttribute
	public FileInfo fileInfo(HttpServletRequest request) {
		String id=request.getParameter("fileInfoId");
		if(StringUtils.isBlank(id)) {
			return new FileInfo();
		}else {
			return (FileInfo)baseCommonService.findById(FileInfo.class, id);
		}
	}
}
