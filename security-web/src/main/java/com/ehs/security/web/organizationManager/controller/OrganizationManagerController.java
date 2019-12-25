package com.ehs.security.web.organizationManager.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ehs.security.entity.DataDictionary;
import com.ehs.security.entity.MenuRole;
import com.ehs.security.entity.OrganizationInfo;
import com.ehs.security.entity.UserBaseInfo;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.web.organizationManager.service.OrganizationManagerService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationManagerController.java
* @Description: 组织机构管理控制层
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年7月4日 上午11:05:24 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月4日     qjj           v1.0.0               修改原因
*/
@Controller
public class OrganizationManagerController {
	
	private static final Logger logger=LoggerFactory.getLogger(OrganizationManagerController.class);
	
	@Resource
	private OrganizationManagerService organizationManagerService;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * 
	* @Function: OrganizationManagerController.java
	* @Description: 组织管理页面跳转
	*
	* @param mv
	* @return：跳转到组织管理页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月4日 上午11:09:10 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月4日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value="/action/orgManager/orgManager")
	public ModelAndView organizationList(ModelAndView mv) {
		logger.info("进入组织机构管理Controller");
		mv.setViewName("/organization/organizationManager");
		return mv;
	}
	
	/**
	 * 
	* @Function: OrganizationManagerController.java
	* @Description: 根据节点查找子节点
	*
	* @param request
	* @return：该组织节点下所有子节点
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月4日 上午11:18:30 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月4日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/organization/getOrgsByParentCode")
	@ResponseBody
	public String getOrgsByParentCode(HttpServletRequest request) {
		String parentCode=request.getParameter("parentCode");
		List<OrganizationInfo> orgList =new LinkedList<OrganizationInfo>();
	    if(StringUtils.isNotBlank(parentCode)) {
	    	orgList=organizationManagerService.findOrgsByCode(parentCode);
	    }else {
	    	orgList=baseCommonService.findAll(OrganizationInfo.class, null, new Sort(Direction.ASC,OrganizationInfo.SORT));
	    }
		return JSON.toJSONString(orgList);
	}
	/**
	 * 
	* @Function: OrganizationManagerController.java
	* @Description: 组织机构新增和修改
	* 				data:数据集合
	*
	* @param request
	* @return：操作成功或者失败
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月4日 下午4:38:08 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月4日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/organization/saveOrganization")
	@ResponseBody
	public String saveOrganization(HttpServletRequest request) {
		try {
			String data = request.getParameter("data");
			//判断修改或新增的标识
			Assert.notNull(data, "data must not be null");
			List<OrganizationInfo> dataList=JSONArray.parseArray(data, OrganizationInfo.class);
			for (OrganizationInfo organization : dataList) {
				baseCommonService.saveOrUpdate(organization);
			}
			logger.info("页面传入的data:"+data);
		} catch (Exception e) {
			logger.error("新增失败",e.getMessage());
			return JSON.toJSONString(ResultBean.getBean("操作失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("操作成功！",ResultType.OK,null));
	}
	/**
	 * 
	* @Function: OrganizationManagerController.java
	* @Description: 删除
	*
	* @param request
	* @return：成功或者失败信息
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月4日 下午4:34:32 
	*
	* Modification History:
	* Date         Author          Version      Description
	*---------------------------------------------------------*
	* 2019年7月4日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/organization/deleteOrgById")
	@ResponseBody
	public String delOrganization(HttpServletRequest request) {
		logger.info("===============进入删除部门方法===================");
		try {
			String id=request.getParameter("data");
			String code=request.getParameter("code");
			Assert.notNull(id, "cant not find id");
			Assert.notNull(code, "cant not find code");
			Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> criteriaBuilder.and( criteriaBuilder.equal(root.get(UserBaseInfo.ORG_CODE),code));
			List<UserBaseInfo> userBaseInfos= baseCommonService.findAll(UserBaseInfo.class, sf);
			Specification sf1=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> criteriaBuilder.and( criteriaBuilder.equal(root.get(DataDictionary.RELATED_DEPART),code));
			List<DataDictionary> dataDictionaries = baseCommonService.findAll(DataDictionary.class, sf1);
			System.out.println("data======================"+dataDictionaries.size());
			if (dataDictionaries.size() > 0 || userBaseInfos.size() > 0) {
				if(dataDictionaries.size() > 0) {
					for (DataDictionary dataDictionary : dataDictionaries) {
						if(dataDictionary.getRelatedDepart().contains(code)) {
							return JSON.toJSONString(ResultBean.getBean("此部门已经关联设备树，请先去设备树维护下解除关联！",ResultType.ERROR,null));
						}
					}
				}
				if(userBaseInfos.size() > 0) {
					return JSON.toJSONString(ResultBean.getBean("此部门下已经存在用户，请先删除此部门下用户！",ResultType.ERROR,null));
				}
			}
			baseCommonService.deleteById(OrganizationInfo.class,id);
		} catch (Exception e) {
			logger.error("删除失败",e.getMessage());
			return JSON.toJSONString(ResultBean.getBean("删除失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("删除成功！",ResultType.OK,null));
	}
	
	
	
	/**
	 * 
	* @Function:updateMovedRow 
	* @Description: 组织机构上移下移更新
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年9月27日 下午3:46:31 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年9月27日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/action/organization/updateMovedRow")
	@ResponseBody
	public String updateMovedRow(HttpServletRequest request) {
		try {
			String orId=request.getParameter("orId");
			int orSort=Integer.parseInt(request.getParameter("orSort"));
			String toId=request.getParameter("toId");
			int toSort=Integer.parseInt(request.getParameter("toSort"));
			organizationManagerService.saveSortChangedEntity(orId, orSort, toId, toSort);
		} catch (Exception e) {
			return JSON.toJSONString(ResultBean.getBean("操作失败！",ResultType.ERROR,null));
		}
		
		return JSON.toJSONString(ResultBean.getBean("操作成功！",ResultType.OK,null));
	}
	
}
