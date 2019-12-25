/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmLibraryManager.edmLibrary.controller 
 * @author: chentm   
 * @date: 2019年6月28日 下午4:48:49 
 */
package com.ehs.edm.edmLibraryManager.edmLibrary.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrintParameter;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmAccountPrintParameterService;
import com.ehs.edm.edmLibraryManager.edmLibrary.entity.EdmLibrary;
import com.ehs.edm.edmLibraryManager.edmLibrary.entity.EdmLibraryParameter;
import com.ehs.edm.edmLibraryManager.edmLibrary.service.EdmLibraryParameterService;
import com.ehs.edm.edmLibraryManager.edmLibrary.service.EdmLibraryService;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.DataDictionary;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;

import sun.misc.BASE64Encoder;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EdmLibraryController.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: chentm
 * @date: 2019年6月28日 下午4:48:49
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年6月28日
 *        chentm v1.0.0 修改原因
 */
@Controller
public class EdmLibraryController {
	private Logger logger = LoggerFactory.getLogger(EdmLibraryController.class);
	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private EdmLibraryService edmLibraryService;
	
	@Resource
	private EdmLibraryParameterService libraryParameterService;
	
	@Resource
	private DataDictionarySearchService dataDictionaryService;
	
	@Resource
	private EdmAccountPrintParameterService accountPrintParamService;
	
	@Resource
	private EdmLibraryParameterService edmLibraryParameterService;

	/**
	 * 
	 * @Function: EdmLibraryController.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: chentm
	 * @throws UnsupportedEncodingException 
	 * @date: 2019年7月1日 上午11:30:38 
	 *
	 * Modification History:
	 * Date         Author          Version            Description
	 *---------------------------------------------------------*
	 * 2019年7月1日     chentm           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmLibrary")
	public ModelAndView edmLibrary(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String nameString = EdmLibrary.class.getTypeName();
		BASE64Encoder encoder = new BASE64Encoder();
		byte[] textByte = nameString.getBytes("UTF-8");
		String encodedText = encoder.encode(textByte);
		mv.addObject("nameString", encodedText);
		mv.setViewName("/edmLibraryManager/edmLibrary/edmLibrary");
		return mv;
	}
	/**
	 * 
	 * @Function: EdmLibraryController.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: chentm
	 * @date: 2019年7月1日 上午11:30:33 
	 *
	 * Modification History:
	 * Date         Author          Version            Description
	 *---------------------------------------------------------*
	 * 2019年7月1日     chentm           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmLibraryEdit")
	public ModelAndView edmLibraryEdit(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		String edmLibraryId = request.getParameter("edmLibraryId");
		EdmLibrary dd=null;
		if(StringUtils.isNotBlank(edmLibraryId)) {
			dd = (EdmLibrary) baseCommonService.findById(EdmLibrary.class, edmLibraryId);
			if(dd==null) {
				mv.setViewName(ResultBean.NO_DATA_RESPONSE_URL);
				return mv;
			}
		}
		mv.addObject("objectBean", dd == null ? new EdmLibrary() : dd);
		mv.setViewName("/edmLibraryManager/edmLibrary/edmLibraryEdit");
		return mv;
	}
	
	@RequestMapping(value = "/action/edmLibraryManager/edmLibraryRemove")
	@ResponseBody
	public String edmLibraryRemove(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		String edmLibraryId = request.getParameter("edmLibraryId");
		try {
			Assert.notNull(edmLibraryId, " edmLibraryId must be not null ");
			EdmLibrary el=(EdmLibrary)baseCommonService.findById(EdmLibrary.class, edmLibraryId);
			if(el==null) {
				return JSON.toJSONString(ResultBean.getBean(ResultBean.NO_DATA,ResultType.ERROR,null));
			}
//			Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(EdmAccountPrint.REF_EDM_LIBRARY), el.getCode()));
//			List list=baseCommonService.findAll(EdmAccountPrint.class, sf);
//			if(list !=null && list.size() > 0) {
//				return JSON.toJSONString(ResultBean.getBean("已有关联子设备，不能删除！",ResultType.ERROR,null));
//			}
			Specification sf1=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.equal(root.get(EdmLibraryParameter.PARAMETER_CODE), el.getCode()));
			List<EdmLibraryParameter> list1=baseCommonService.findAll(EdmLibraryParameter.class, sf1);
			if(list1 != null && list1.size() > 0) {
				for (EdmLibraryParameter libraryParameter : list1) {
					baseCommonService.deleteById(EdmLibraryParameter.class, libraryParameter.getId());
				}
			}
			baseCommonService.deleteById(EdmLibrary.class, el.getId());
			return JSON.toJSONString(ResultBean.getBean("删除成功！",ResultType.OK,null));
		}catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("删除失败！",ResultType.ERROR,null));
		}
	}
	
	@RequestMapping(value = "/action/edmLibraryManager/edmLibrarySave")
	@ResponseBody
	public String saveEdmLibrary(@ModelAttribute EdmLibrary edmLibrary, HttpServletRequest request, HttpServletResponse response) {
		String filesJson=request.getParameter("filesJson");
		logger.info("saveEdmLibrary      filesJson=== "+filesJson);
		String libraryParamJson = request.getParameter("libraryParamJson");
		logger.info("saveEdmLibrary      libraryParamJson=== "+libraryParamJson);
		try {
			if(StringUtils.isNotBlank(edmLibrary.getDeviceType())) {
				DataDictionary dd= dataDictionaryService.findOne(edmLibrary.getDeviceType());
				edmLibrary.setDeviceTypeName(dd.getText());
			}
			BaseEntity base= edmLibraryService.saveOrUpdate(edmLibrary, JSON.parseArray(filesJson, FileInfo.class),JSONArray.parseArray(libraryParamJson,EdmLibraryParameter.class));
			return JSON.toJSONString(ResultBean.getBean("保存成功！",ResultType.OK,base.getId()));
		}catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("保存失败！",ResultType.ERROR,null));
		}
		
	}
	
	@RequestMapping(value = "/action/edmLibraryManager/edmLibraryList")
	@ResponseBody
	public String edmLibraryList( HttpServletRequest request, HttpServletResponse response) {
		String deviceShortName=request.getParameter("deviceShortName");
        Pagenate pageNate=new Pagenate();
        pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
        Specification sf=null;
        if(StringUtils.isNotBlank(deviceShortName)) {
        	sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.and(criteriaBuilder.like(root.get(EdmLibrary.DEVICE_SHORT_NAME), "%"+deviceShortName+"%"));
        }
		return JSON.toJSONString(baseCommonService.findPagenate(EdmLibrary.class, sf, pageNate));
	}
	
	/**
     * 
    * @Function:getEdmAccoutPrintByDataCode 
    * @Description: 获取关联设备
    * @return 关联设备数据集合
    * @throws：异常描述
    * @version: v1.0.0
    * @author: qjj
    * @date: 2019年7月17日 下午7:24:27 
    *
    * Modification History:
    * Date         Author          Version            Description
    *---------------------------------------------------------*
    * 2019年7月17日     qjj           v1.0.0               修改原因
     */
	@RequestMapping(value="/action/edmLibraryManager/getEdmAccoutPrintByDataCode")
	@ResponseBody
	public String getEdmAccoutPrintByDataCode(HttpServletRequest request) {
		String code=request.getParameter("code");
		Pagenate pageNate=new Pagenate();
	    pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
	    pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
		Specification<EdmAccountPrint> sf=null;
//        if(StringUtils.isNotBlank(code)) {
//        	sf=(Root<EdmAccountPrint> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->criteriaBuilder.equal(root.get(EdmAccountPrint.REF_EDM_LIBRARY), code);
//        }
		return JSONArray.toJSONString(baseCommonService. findPagenate(EdmAccountPrint.class, sf,pageNate));
	}

	@ModelAttribute
	public EdmLibrary edmLibrary(HttpServletRequest request) {
		String id=request.getParameter("edmLibraryId");
		if(StringUtils.isBlank(id)) {
			return new EdmLibrary();
		}else {
			return (EdmLibrary)baseCommonService.findById(EdmLibrary.class, id);
		}
	}
	/**
	 * 
	* @Function: EdmLibraryController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年7月19日 下午5:10:21 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryParam/paramList")
	@ResponseBody
	public String libraryParamList(@RequestParam("dataCode") String dataCode) {
		logger.info("=============进入libraryParamList方法===============");
		Assert.notNull(dataCode, "dataCode must be not null");
		List<EdmLibraryParameter> libraryParameters = edmLibraryParameterService.getEdmLibraryParameter(dataCode);
		if (libraryParameters.size() > 0) {
			return JSON.toJSONString(libraryParameters);
		}else {
			List<EdmAccountPrintParameter> accountPrintParameters = accountPrintParamService.getEdmAccountPrintParameter(dataCode);
			logger.info("=============退出edmAssemblyList方法===============");
			return JSON.toJSONString(accountPrintParameters);
		}
	}
	
}
