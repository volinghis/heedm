package com.ehs.security.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.utils.BaseUtils;
import com.ehs.security.utils.ExportBeanExcel;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: ExportExcelController.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年7月12日 下午2:00:10
 *
 * Modification History: 
 *      Date      Author  Version    Description
 * ---------------------------------------------------------* 
 *  2019年7月12日           qjj    v1.0.0     修改原因
 */
@Controller
public class ExportExcelController {

	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	* @Function:exportExcel 
	* @Description: 公共excel导出控制层
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月5日 下午2:50:25 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月5日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/exportExcel")
	@ResponseBody
	public String exportExcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			String className = BaseUtils.decode(request.getParameter("className"));
			String dataList = request.getParameter("dataList");
			// 文件名
			String title = request.getParameter("title");
			// 获取表头
			String json = request.getParameter("columns");
			JSONArray columns = JSONArray.parseArray(json);
			List<String> headersName = new ArrayList<String>();
			List<String> headersId = new ArrayList<String>();
			for (int i = 0; i < columns.size(); i++) {
				headersName.add(JSONObject.parseObject(columns.get(i).toString()).getString("header"));
				headersId.add(JSONObject.parseObject(columns.get(i).toString()).getString("field"));
			}
			List<?> contentList = JSONArray.parseArray(dataList, Class.forName(className));
			// 导出excel
			ExportBeanExcel.exportExcel(title, headersName, headersId, contentList, title + "信息列表", response);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("导出失败！", ResultType.ERROR, null));
		}
		return JSON.toJSONString(ResultBean.getBean("导出成功！", ResultType.OK, null));
	}

}
