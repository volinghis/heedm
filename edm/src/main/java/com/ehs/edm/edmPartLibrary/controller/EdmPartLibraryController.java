package com.ehs.edm.edmPartLibrary.controller;

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
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint;
import com.ehs.edm.edmPartLibrary.entity.EdmPartLibrary;
import com.ehs.edm.edmPartLibrary.service.EdmPartLibraryService;
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
public class EdmPartLibraryController {

	private Logger logger = LoggerFactory.getLogger(EdmPartLibraryController.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private EdmPartLibraryService edmPartLibraryService;
	
	@Resource
	private DataDictionarySearchService dataDictionarySearchService;
	/**
	 * 
	* @Function:edmPartLibrary 
	* @Description: 备件台账页面跳转
	* @param request
	* @param response
	* @return 返回模型视图
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 下午5:31:51 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmPartLibrary")
	public String edmPartLibrary(ServletRequest request,ServletResponse response) {
		logger.info("====================进入edmPartLibrary方法=========================");
		return "/edmPartLibrary/edmPartLibrary";
	}
	
	/**
	 * 
	* @Function: EdmDataLibraryController.java
	* @Description: 备件数据信息列表
	* 			<ul>
	* 				<li>name:String类型，备件所属设备类型</li>
	* 				<li>parentCode:String类型，备件所属设备code</li>
	* 				<li>manufacturer:String类型，生产厂家</li>
	* 				<li>labelCode:String类型，备件标签码</li>
	* 			</ul>
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年6月28日 下午6:45:13 
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月28日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmPartLibrary/partLibraryTableData")
	@ResponseBody
	public String partLibraryTableData( HttpServletRequest request,HttpServletResponse response){
		logger.info("====================进入partLibraryTableData方法=========================");
		String parentCode = request.getParameter("parentCode");
		String name = request.getParameter("name");
		String norm = request.getParameter("norm");
		String manufacturer=request.getParameter("manufacturer");
		String type=request.getParameter("type");
		String amount=request.getParameter("amount");
		String warningValue=request.getParameter("warningValue");
		String profession=request.getParameter("profession");
		String materialTypeCode=request.getParameter("materialTypeCode");
	    List<DataDictionary> dataList=  dataDictionarySearchService.findAllDataDictionary();
		List<String> treelist=new ArrayList<String>();
		createMenuNode(treelist, dataList, parentCode);
		Pagenate pageNate=new Pagenate();
        pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
        Specification<EdmPartLibrary> sf=null;
        List<Predicate> ps=new ArrayList<Predicate>();
        //在设备台账表中查找 设备系统对应设备
//        Specification<EdmAccountPrint> sp=null;
//        sp=(Root<EdmAccountPrint> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->{
//    			CriteriaBuilder.In inn=criteriaBuilder.in(root.get(EdmAccountPrint.INSTALL_ADDRESS_CODE));
//    			if (treelist.size() > 0) {
//    				for (int i = 0; i < treelist.size(); i++) {
//    					inn.value(treelist.get(i));
//    				}
//				}
//    			inn.value(parentCode);
//    			return criteriaBuilder.and(inn);
//        };
//        List<EdmAccountPrint> edmlist= baseCommonService.findAll(EdmAccountPrint.class, sp);
        sf=(Root<EdmPartLibrary> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->{
        	if(StringUtils.isNotBlank(parentCode)) {
        		System.out.println("parentCode================="+parentCode);
        		EdmAccountPrint edm = (EdmAccountPrint) baseCommonService.findByCode(EdmAccountPrint.class, parentCode);
        		CriteriaBuilder.In in=null;
        		if (edm != null) {
        			System.out.println("具体设备查询");
        			in=criteriaBuilder.in(root.get(EdmPartLibrary.PARENTCODE));
				}else {
					System.out.println("设备系统查询");
					in = criteriaBuilder.in(root.get(EdmPartLibrary.NODECODE));
				}
      		   	if (treelist.size() > 0) {
      		   		for (int j = 0; j < treelist.size(); j++) {
      		   			in.value(treelist.get(j));
      		   		}
      		   	}else {
      		   		in.value(parentCode);
      		   	}
      		   	ps.add(criteriaBuilder.and(criteriaBuilder.and(in)));//存入条件集合里
        	}
        	if(StringUtils.isNotBlank(name)) {
        		ps.add(criteriaBuilder.like(root.get(EdmPartLibrary.NAME), "%"+name+"%"));
        	}
        	if(StringUtils.isNotBlank(manufacturer)) {
        		ps.add(criteriaBuilder.like(root.get(EdmPartLibrary.MANUFACTURER), "%"+manufacturer+"%"));
        	}
        	if(StringUtils.isNotBlank(type)) {
        		ps.add(criteriaBuilder.like(root.get(EdmPartLibrary.TYPE), "%"+type+"%"));
        	}
        	if(StringUtils.isNotBlank(amount)) {
        		ps.add(criteriaBuilder.like(root.get(EdmPartLibrary.AMOUNT), "%"+amount+"%"));
        	}
        	if(StringUtils.isNotBlank(norm)) {
        		ps.add(criteriaBuilder.like(root.get(EdmPartLibrary.NORM), "%"+norm+"%"));
        	}
        	if(StringUtils.isNotBlank(warningValue)) {
        		ps.add(criteriaBuilder.like(root.get(EdmPartLibrary.WARNINGVALUE), "%"+warningValue+"%"));
        	}
        	if(StringUtils.isNotBlank(profession)) {
        		ps.add(criteriaBuilder.like(root.get(EdmPartLibrary.PROFESSION), "%"+profession+"%"));
        	}
        	if(StringUtils.isNotBlank(materialTypeCode)) {
        		ps.add(criteriaBuilder.like(root.get(EdmPartLibrary.MATERIAL_TYPE_CODE), "%"+materialTypeCode+"%"));
        	}
        	return criteriaBuilder.and(ps.toArray(new Predicate[0]));
        };
        
		return JSON.toJSONString(baseCommonService.findPagenate(EdmPartLibrary.class, sf, pageNate));
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
	* @Description: 跳转新增页面
	* 			<ul>
	* 				<li>name:备件所属设备类型</li>
	* 				<li>parentCode:备件所属设备code</li>
	* 				<li>edmPartLibraryId:当前备件唯一标识</li>
	* 				<li>name:备件关联附件code</li>
	* 			</ul>	
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年6月28日 下午6:35:31 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月28日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmPartLibrary/partLibraryEdit")
	public ModelAndView partLibraryEdit(ModelAndView mv, ServletRequest request,ServletResponse response) {
		logger.info("====================进入partLibraryEdit方法=========================");
//		String typeName = request.getParameter("name");
		String parentCode = request.getParameter("parentCode");
		String code = request.getParameter("code");
		String edmPartLibraryId = request.getParameter("edmPartLibraryId");
		String parentNodeCode = request.getParameter("parentNodeCode");
		EdmAccountPrint ed=(EdmAccountPrint) baseCommonService.findByCode(EdmAccountPrint.class, parentCode);
		EdmPartLibrary dd=null;
		if(StringUtils.isNotBlank(edmPartLibraryId)) {
			dd =  (EdmPartLibrary)baseCommonService.findById(EdmPartLibrary.class, edmPartLibraryId);
			if(dd==null) {
				mv.setViewName(ResultBean.NO_DATA_RESPONSE_URL);
				return mv;
			}
				
			mv.addObject("purchaseAmount",String.valueOf(dd.getPurchaseAmount())==null ? 0 :dd.getPurchaseAmount());
		}
		mv.addObject("typeName", ed.getDeviceName());
		mv.addObject("parentCode", parentCode);
		mv.addObject("parentNodeCode", parentNodeCode);
		mv.addObject("code", code);
		mv.addObject("profession", ed.getInstallAddressFullName());
		mv.addObject("objectBean", dd == null ? new EdmPartLibrary() : dd);
		mv.setViewName("/edmPartLibrary/edmPartLibraryEdit");
		logger.info("====================退出partLibraryEdit方法=========================");
		return mv;
	}
	
	/**
	 * 
	* @Function:partLibrarySave 
	* @Description: 备件台账保存</br>
	*   			fileJson:备件相关的附件信息
	* @param edmPartLibrary 备件台账实体
	* @param request
	* @param response
	* @return json格式的操作结果
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月15日 下午5:40:36 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmPartLibrary/partLibrarySave")
	@ResponseBody
	public String partLibrarySave(@ModelAttribute EdmPartLibrary edmPartLibrary, HttpServletRequest request, HttpServletResponse response) {
		logger.info("============进入备件保存方法===============");
		String filesJson=request.getParameter("filesJson");
		System.out.println("getMaterialTypeCode========================"+edmPartLibrary.getMaterialTypeCode());
		try {
			if(StringUtils.isNotBlank(edmPartLibrary.getMaterialTypeCode())) {
				DataDictionary dd=dataDictionarySearchService.findOne(edmPartLibrary.getMaterialTypeCode());
				if (dd != null) {
					String materialType=dd.getText();
					edmPartLibrary.setMaterialTypeName(materialType);
				}
			}
//			if(StringUtils.isNotBlank(edmPartLibrary.getManufacturer())) {
//				DataDictionary dd=dataDictionarySearchService.findOne(edmPartLibrary.getManufacturer());
//				if (dd != null) {
//					String manufacturer=dd.getText();
//					edmPartLibrary.setManufacturer(manufacturer);
//				}
//			}
//			if(StringUtils.isNotBlank(edmPartLibrary.getSupplier())) {
//				DataDictionary dd=dataDictionarySearchService.findOne(edmPartLibrary.getSupplier());
//				if (dd != null) {
//					String supplier=dd.getText();
//					edmPartLibrary.setSupplier(supplier);
//				}
//			}
			System.out.println("=================="+edmPartLibrary.getPurchaseAmount());
			BaseEntity base= edmPartLibraryService.savePartLibrary(edmPartLibrary, JSON.parseArray(filesJson, FileInfo.class));
			logger.info(JSON.toJSONString(ResultBean.getBean("保存成功！",ResultType.OK,base.getId())));
			return JSON.toJSONString(ResultBean.getBean("保存成功！",ResultType.OK,base.getId()));
		}catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("保存失败！",ResultType.ERROR,null));
		}
	}
	
	/**
	 * 
	* @Function: EdmDataLibraryController.java
	* @Description: 删除<br>
	* 				data:String类型，备件台账唯一标识
	*
	* @param:描述1描述
	* @return：返回json格式的操作结果
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年7月1日 下午8:48:38 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月1日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmPartLibrary/partLibraryDel")
	@ResponseBody
	public String partLibraryDel(HttpServletRequest request) {
		logger.info("============进入dataLibrary    Del方法===========");
		try {
			String id=request.getParameter("data");
			String code=StringUtils.replace(id, ",", "");
			baseCommonService.deleteById(EdmPartLibrary.class,code);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("删除失败",e.getMessage());
			return JSON.toJSONString(ResultBean.getBean("删除失败",ResultType.ERROR,null));
		}
		logger.info("============退出dataLibrary    Del方法===========");
		return JSON.toJSONString(ResultBean.getBean("删除成功",ResultType.OK,null));
	}
	
	/**
	 * 
	* @Function: EdmDataLibraryController.java
	* @Description: 根据id获取备件台账信息<br>
	* 				id:String类型，备件台账唯一标识
	*
	* @param:描述1描述
	* @return：返回结果描述
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
//	@RequestMapping(value = "/action/edmPartLibrary/partLibraryGet")
//	@ResponseBody
//	public String partLibraryGet(ModelAndView mv, HttpServletRequest request) {
//		logger.info("============进入dataLibrary    Get方法===========");
//		String id = request.getParameter("id");
//		EdmPartLibrary partLibrary = (EdmPartLibrary) baseCommonService.findById(EdmPartLibrary.class, id);
//		EdmPartLibrary eLibrary = partLibrary == null ? new EdmPartLibrary() : partLibrary;
//		logger.info(JSON.toJSONString(eLibrary));
//		logger.info("============退出dataLibrary    Get方法===========");
//		return JSON.toJSONString(eLibrary);
//	}
	
	/**
	 * 
	* @Function:edmPartLibrary 
	* @Description: @ModelAttribute 数据绑定
	* @param request
	* @return 备件台账实体
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 上午9:10:05 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	@ModelAttribute
	public EdmPartLibrary edmPartLibrary(HttpServletRequest request) {
		String id=request.getParameter("edmPartLibraryId");
		logger.info("id=============="+id);
		if(StringUtils.isBlank(id)) {
			return new EdmPartLibrary();
		}else {
			EdmPartLibrary  edmPartLibrary =(EdmPartLibrary)baseCommonService.findById(EdmPartLibrary.class, id);
//			return (EdmPartLibrary)baseCommonService.findById(EdmPartLibrary.class, id);
			System.out.println("getPurchaseAmount=================="+edmPartLibrary.getPurchaseAmount());
			return edmPartLibrary;
		}
	}
	
}
