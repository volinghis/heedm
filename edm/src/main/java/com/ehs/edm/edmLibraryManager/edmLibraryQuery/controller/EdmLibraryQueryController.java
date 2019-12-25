/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmLibraryManager.edmLibraryQuery 
 * @author: chentm   
 * @date: 2019年7月4日 下午4:51:38 
 */
package com.ehs.edm.edmLibraryManager.edmLibraryQuery.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmAccountPrintController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月4日 下午4:51:38 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月4日      chentm          v1.0.0               修改原因
*/
@Controller
public class EdmLibraryQueryController {
	private static final Logger logger = LoggerFactory.getLogger(EdmLibraryQueryController.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private DataDictionarySearchService dataDictionarySearchService;
	
	/**
	 * 
	* @Function: EdmLibraryQueryController.java
	* @Description: 检索设备数据的入口
	*
	* @param mv
	* @param request
	* @param response
	* @return 检索页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月16日 上午10:08:50 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmLibraryQuery")
	public ModelAndView edmLibraryQuery(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		mv.setViewName("/edmLibraryManager/edmLibraryQuery/edmLibraryQuery");
		return mv;
	}
	
	/**
	 * 
	* @Function: EdmLibraryQueryController.java
	* @Description: 通过不同条件去查询设备数据</br>
	*				deviceNum：设备编码</br>
	*				deviceName：设备名称</br>
	*				installAddressCode：安装位置</br>
	*				checkRrepairTeamCode：检修班组</br>
	*				deviceModel：设备类型</br>
	*				statusCode：设备状态</br>
	*				productFactoryName：生产厂家</br>
	* @param request
	* @param response
	* @return 设备数据集合，并分页展示
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月16日 上午10:09:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmLibraryQueryList")
	@ResponseBody
	public String edmLibraryQueryList( HttpServletRequest request, HttpServletResponse response) {
		String deviceNum = request.getParameter("deviceNum");
		String deviceName = request.getParameter("deviceName");
//		String deviceType = request.getParameter("deviceType");
		String installAddressCode = request.getParameter("installAddressCode");
//		String dutyPersonCode = request.getParameter("dutyPersonCode");
		String checkRrepairTeamCode = request.getParameter("checkRrepairTeamCode");
//		String dutyOrgCode = request.getParameter("dutyOrgCode");
		String deviceModel = request.getParameter("deviceModel");
		String statusCode = request.getParameter("statusCode");
		String productFactoryName = request.getParameter("productFactoryName");
		
		List<Predicate> ps = new ArrayList<Predicate>();
		Specification sf = (Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) -> {
			if (StringUtils.isNotBlank(deviceNum)) {
				ps.add(criteriaBuilder.like(root.get(EdmAccountPrint.DEVICE_NUM), "%" + deviceNum + "%"));
			}
			if (StringUtils.isNotBlank(deviceName)) {
				ps.add(criteriaBuilder.like(root.get(EdmAccountPrint.DEVICE_NAME), "%" + deviceName + "%"));
			}
//			if (StringUtils.isNotBlank(deviceType)) {
//				ps.add(criteriaBuilder.equal(root.get(EdmAccountPrint.DEVICE_TYPE), deviceType));
//			}
			if (StringUtils.isNotBlank(installAddressCode)) {
				ps.add(criteriaBuilder.equal(root.get(EdmAccountPrint.INSTALL_ADDRESS_CODE), installAddressCode));
			}
//			if (StringUtils.isNotBlank(dutyPersonCode)) {
//				ps.add(criteriaBuilder.equal(root.get(EdmAccountPrint.DUTY_PERSON_CODE), dutyPersonCode));
//			}
			if (StringUtils.isNotBlank(checkRrepairTeamCode)) {
				ps.add(criteriaBuilder.equal(root.get(EdmAccountPrint.CHECKRREPAIR_TEAM_CODE), checkRrepairTeamCode));
			}
//			if (StringUtils.isNotBlank(dutyOrgCode)) {
//				ps.add(criteriaBuilder.equal(root.get(EdmAccountPrint.DUTY_ORG_CODE), dutyOrgCode));
//			}
			if (StringUtils.isNotBlank(deviceModel)) {
				ps.add(criteriaBuilder.like(root.get(EdmAccountPrint.DEVICE_MODEL), "%" + deviceModel + "%"));
			}
			if (StringUtils.isNotBlank(statusCode)) {
				ps.add(criteriaBuilder.equal(root.get(EdmAccountPrint.STATUS_CODE), statusCode));
			}
			if (StringUtils.isNotBlank(productFactoryName)) {
				ps.add(criteriaBuilder.like(root.get(EdmAccountPrint.PRODUCT_FACTORY_NAME), "%" + productFactoryName + "%"));
			}
			return criteriaBuilder.and(ps.toArray(new Predicate[0]));
		};

		Pagenate pageNate = new Pagenate();
		pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
		List<EdmAccountPrint> list = baseCommonService.findPagenate(EdmAccountPrint.class, sf, pageNate).getData();
		list.stream().forEach(s->{
			s.setStatusName(dataDictionarySearchService.findOne(s.getStatusCode()).getText());
		});
		pageNate.setData(list);
		return JSON.toJSONString(pageNate);
	}
	
	/**
	 * 
	* @Function: EdmLibraryQueryController.java
	* @Description: 点击一条数据后，展示所有信息</br>
	*				edmAccountPrintId：数据ID</br>
	*				code:关联树code</br>
	*				founder:责任人</br>
	*				objectBean：根据ID查询到的数据
	* @param mv
	* @param request
	* @param response
	* @return 跳转展示页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月16日 上午10:13:38 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmAccountQueryEdit")
	public ModelAndView edmLibrary(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		logger.info("=====================进入edmAccountQueryEdit==================== ");
		String edmAccountPrintId= request.getParameter("edmAccountPrintId");
		String founder = request.getParameter("founder");
		logger.info("founder=============="+founder);
		EdmAccountPrint dd=null;
		if(StringUtils.isNotBlank(edmAccountPrintId)) {
			dd = (EdmAccountPrint)baseCommonService.findById(EdmAccountPrint.class, edmAccountPrintId);
			if(dd==null) {
				mv.setViewName(ResultBean.NO_DATA_RESPONSE_URL);
				return mv;
			}
			mv.addObject("founder", dd.getFounder());
		}
		mv.addObject("orgCode", request.getParameter("code"));
		mv.addObject("objectBean", dd == null ? new EdmAccountPrint() : dd);
		mv.setViewName("/edmLibraryManager/edmAccountPrint/edmAccountPrintEdit");
		logger.info("=====================退出edmAccountQueryEdit,准备跳转到展示页面==================== ");
		return mv;
	}
}
