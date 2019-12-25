package com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.controller.EdmAccountPrintExportWordController;
import com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.entity.EdmRepairAccountPrint;
import com.ehs.edm.util.ExportBeanWord;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.OrganizationSearchService;

@Controller
public class EdmRepairAccountWordController {
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private OrganizationSearchService organizationSearchService;
	
	private static final Logger logger =LoggerFactory.getLogger(EdmAccountPrintExportWordController.class);

	@RequestMapping(value = "/action/edmRepairAccountPrintExportWord")
	public String ExportWord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("=========进入导出word方法========");
        try {
			String dataList = request.getParameter("dataList");
			String profession = request.getParameter("profession");
			String dutyPersonName = request.getParameter("dutyPersonName");
			String natureName = request.getParameter("natureName");
			String teamName = request.getParameter("teamName");
			String creationTime = request.getParameter("creationTime");
			String equipmentRating = request.getParameter("equipmentRating");
			logger.info("size================"+dataList.length());
			EdmRepairAccountPrint edmRepairAccountPrint = (EdmRepairAccountPrint)JSONObject.parseObject(dataList, EdmRepairAccountPrint.class);
			System.out.println(edmRepairAccountPrint.toString());
			Map<String, Object> datas = new HashMap<>();
			
			datas.put("title", edmRepairAccountPrint.getRepairName()+"记录");
			datas.put("deviceCode", edmRepairAccountPrint.getDeviceCode());
			datas.put("nodeCode", edmRepairAccountPrint.getNodeCode());
			datas.put("repairName", edmRepairAccountPrint.getRepairName());
			datas.put("repairTeam", teamName);
			datas.put("repairNatureName", natureName);
			datas.put("creationTime",creationTime);
			datas.put("beforeSatuation", edmRepairAccountPrint.getBeforeSatuation());
			datas.put("dutyPersonName", dutyPersonName);
			datas.put("founder", edmRepairAccountPrint.getFounder());
			datas.put("repairStartTime", new SimpleDateFormat("yyyy-MM-dd").format(edmRepairAccountPrint.getRepairStartTime()));
			datas.put("repairEndTime", new SimpleDateFormat("yyyy-MM-dd").format(edmRepairAccountPrint.getRepairEndTime()));
			datas.put("repairResult", edmRepairAccountPrint.getRepairResult());
			datas.put("profession", profession);
			datas.put("tester", edmRepairAccountPrint.getTester());
			datas.put("groupMember", edmRepairAccountPrint.getGroupMember());
			datas.put("testResults", edmRepairAccountPrint.getTestResults());
			datas.put("repairContent", edmRepairAccountPrint.getRepairContent());
			datas.put("causeAnalysis", edmRepairAccountPrint.getCauseAnalysis());
			datas.put("repairTest", edmRepairAccountPrint.getRepairTest());
			datas.put("trialSituation", edmRepairAccountPrint.getTrialSituation());
			datas.put("remark", edmRepairAccountPrint.getRemark());
			datas.put("repairResult", edmRepairAccountPrint.getRepairResult());
			datas.put("equipmentRating",equipmentRating);

			datas.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			
			System.out.println("datas.size======"+datas.size());
			new ExportBeanWord().exportDoc("edmRepairAccountPrintWord", edmRepairAccountPrint.getRepairName()+"信息表", datas, response,request);
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
