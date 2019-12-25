package com.ehs.edm.edmLibraryManager.edmAccountPrint.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrintParameter;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmPrincipal;
import com.ehs.edm.util.ExportBeanWord;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.OrganizationSearchService;

@Controller
public class EdmAccountPrintExportWordController {
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private OrganizationSearchService organizationSearchService;
	
	private static final Logger logger =LoggerFactory.getLogger(EdmAccountPrintExportWordController.class);

	@RequestMapping(value = "/action/edmAccountPrintExportWord")
	@ResponseBody
	public String ExportWord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("=========进入导出word方法========");
        try {
			String accountParams = request.getParameter("accountParamJsons");
			String principalParamJson = request.getParameter("principalParamJson");
			String parentNode = request.getParameter("installFullName");
			String dataList = request.getParameter("dataList");
			List<EdmAccountPrintParameter> aparameters = JSONArray.parseArray(accountParams,EdmAccountPrintParameter.class);
			List<EdmPrincipal> principals = JSONArray.parseArray(principalParamJson, EdmPrincipal.class);
			EdmAccountPrint edmAccountPrint = (EdmAccountPrint)JSONObject.parseObject(dataList, EdmAccountPrint.class);
			Map<String, Object> datas = new HashMap<>();
			Date factoryDate = edmAccountPrint.getLeaveFactoryTime();
			if(factoryDate!=null) {
				datas.put("leaveFactoryTime", new SimpleDateFormat("yyyy-MM-dd").format(edmAccountPrint.getLeaveFactoryTime()));

			}
			Date runTime = edmAccountPrint.getRunTime();
			if(runTime!=null) {
				datas.put("runTime", new SimpleDateFormat("yyyy-MM-dd").format(edmAccountPrint.getRunTime()));
			}
			System.out.println(edmAccountPrint.toString());
			logger.info("aparameters============"+aparameters);
			datas.put("list", aparameters);
			datas.put("principals", principals);
			datas.put("title", edmAccountPrint.getDeviceName()+"信息表");
			datas.put("deviceNum", edmAccountPrint.getDeviceNum());
			datas.put("deviceName", edmAccountPrint.getDeviceName());
			datas.put("deviceModel", edmAccountPrint.getDeviceModel());
			datas.put("productFactoryName", edmAccountPrint.getProductFactoryName());
			datas.put("leaveFactoryCode", edmAccountPrint.getLeaveFactoryCode());
//			datas.put("associationDefect", edmAccountPrint.getAssociationDefect());
//			if(StringUtils.isNotBlank(edmAccountPrint.getCheckRrepairTeamCode())) {
//				edmAccountPrint.setCheckRrepairTeamName(organizationSearchService.findByCode(edmAccountPrint.getCheckRrepairTeamCode()).getName());
//			}
//			datas.put("checkRrepairTeamName", edmAccountPrint.getCheckRrepairTeamName());
			datas.put("founder", edmAccountPrint.getFounder());
			datas.put("checkRrepairNorm", edmAccountPrint.getCheckRrepairNorm());
			datas.put("regularWorkNorm", edmAccountPrint.getRegularWorkNorm());
			datas.put("pastEquipInspectors", edmAccountPrint.getPastEquipInspectors());
			datas.put("parentNode", parentNode);
			datas.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			System.out.println("datas.size======"+datas.size());
			new ExportBeanWord().exportDoc("edmAccountPrintWord", edmAccountPrint.getDeviceName()+"信息表", datas, response,request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("===+++==导出成功===++++==");
			return JSON.toJSONString(ResultBean.getBean("导出失败！", ResultType.ERROR, null));
		}
        logger.info("==+++++====导出成功==++++++===");
        return JSON.toJSONString(ResultBean.getBean("导出成功，请在D盘查看！", ResultType.OK, null));
	}
}
