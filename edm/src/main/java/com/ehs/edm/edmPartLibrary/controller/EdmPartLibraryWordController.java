package com.ehs.edm.edmPartLibrary.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ehs.edm.edmPartLibrary.entity.EdmPartLibrary;
import com.ehs.edm.util.ExportBeanWord;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.OrganizationSearchService;

@Controller
public class EdmPartLibraryWordController {
	private static final Logger logger =LoggerFactory.getLogger(EdmPartLibraryWordController.class);

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private OrganizationSearchService organizationSearchService;

	@RequestMapping(value = "/action/edmPartLibrarEexportWord")
	@ResponseBody
	public String ExportWord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("=========进入导出word方法========");
        try {
			String dataList = request.getParameter("dataList");
			String profession = request.getParameter("profession");
			String materialTypeName = request.getParameter("materialTypeName");
			EdmPartLibrary edmPartLibrary = (EdmPartLibrary)JSONObject.parseObject(dataList, EdmPartLibrary.class);
			Map<String, Object> datas = new HashMap<>();
			datas.put("title", edmPartLibrary.getName()+"信息");			
			datas.put("name", edmPartLibrary.getName());
			datas.put("typeName", edmPartLibrary.getType());
			datas.put("norm", edmPartLibrary.getNorm());
			datas.put("amount", edmPartLibrary.getAmount());
			datas.put("manufacturer", edmPartLibrary.getManufacturer());
			datas.put("supplier", edmPartLibrary.getSupplier());
			datas.put("purchaseAmount", edmPartLibrary.getPurchaseAmount());
			datas.put("warningValue", edmPartLibrary.getWarningValue());
			datas.put("labelCode", edmPartLibrary.getLabelCode());
			datas.put("materialType", materialTypeName);
			datas.put("profession", profession);
			datas.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			System.out.println("datas.size======"+datas.size());
			new ExportBeanWord().exportDoc("edmPartLibraryWord", edmPartLibrary.getName()+"信息", datas, response,request);
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
