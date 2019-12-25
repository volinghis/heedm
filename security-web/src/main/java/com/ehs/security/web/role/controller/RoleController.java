package com.ehs.security.web.role.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ehs.security.entity.SysRole;
import com.ehs.security.entity.UserBaseInfo;
import com.ehs.security.entity.UserMenu;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: RoleController.java
* @Description: 角色管理控制层
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年7月24日 下午1:23:32 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月24日     qjj           v1.0.0               修改原因
*/
@Controller
public class RoleController {
	
	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	* @Function: RoleController.java
	* @Description: 跳转至角色列表页面
	* @param:描述1描述
	* @return：返回模型视图
	* @throws：异常描述
	* @version: v1.0.0
	* @author: Qjj
	* @date: 2019年6月4日 下午2:55:57 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月4日     	Qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/roleManager/roleManager")
	public ModelAndView role(ModelAndView mv,HttpServletRequest request,HttpServletResponse response) {
		mv.setViewName("/role/roleManager");
		return mv;
	}
	
	/**
	 * 
	* @Function:roleManagerList 
	* @Description: 角色信息数据集合
	* @param request
	* @param response
	* @return 角色分页数据集合
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 下午2:44:59 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/roleManager/roleManagerList")
	@ResponseBody
	public String roleManagerList(HttpServletRequest request,HttpServletResponse response) {
		//获取查询参数
		String searchParam=request.getParameter("searchParam");
		Pagenate pageNate=new Pagenate();
        pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
        Specification<SysRole> sf=null;
		if(StringUtils.isNotBlank(searchParam)) {
			sf=(Root<SysRole> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)-> criteriaBuilder.or(criteriaBuilder.like(root.get(SysRole.DATA_CODE),"%"+searchParam+"%" ), criteriaBuilder.like(root.get(SysRole.NAME),"%"+searchParam+"%" ));
		}
//		pageNate=baseCommonService.findPagenate(SysRole.class, sf, pageNate);
		List<SysRole> sysRoles = baseCommonService.findAll(SysRole.class, sf);
		List<SysRole> list= sysRoles.stream().filter(s-> !StringUtils.equals(s.getDataCode(), "normal") ).collect(Collectors.toList());
		pageNate.setData(list);
		System.out.println(JSON.toJSONString(pageNate));
		return JSON.toJSONString(pageNate);
	}
	
	
	/**
	 * 
	* @Function:saveRoleManager 
	* @Description: 角色的新增和修改</br>
	* 				data：json格式，页面提交的的数据
	* @param request
	* @param response
	* @return 返回json格式操作结果
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 下午3:11:01 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/roleManager/roleManagerSave")
	@ResponseBody
	public String saveRoleManager(HttpServletRequest request, HttpServletResponse response) {
		try {
			String data = request.getParameter("data");
			//判断修改或新增的标识
			Assert.notNull(data, "data must not be null");
			List<SysRole> dataList=JSONArray.parseArray(data, SysRole.class);
			List<SysRole> sysRoles=baseCommonService.findAll(SysRole.class,null);
			for (SysRole sysRole : dataList) {
				long count=sysRoles.stream().filter((e)->e.getDataCode().equals(sysRole.getDataCode())).count();
				if (count > 0) {
					return JSON.toJSONString(ResultBean.getBean("此角色编号已经存在，请重新输入",ResultType.ERROR,null));
				}
				long count1=sysRoles.stream().filter((e)->e.getName().equals(sysRole.getName())).count();
				if (count1 > 0) {
					return JSON.toJSONString(ResultBean.getBean("此角色名称已经存在，请重新输入",ResultType.ERROR,null));
				}
				baseCommonService.saveOrUpdate(sysRole);
			}
		} catch (Exception e) {
			return JSON.toJSONString(ResultBean.getBean("操作失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("操作成功！",ResultType.OK,null));
	}
	
	/**
	 * 
	* @Function:removeRoleManager 
	* @Description: 移除角色</br>
	* 				id:String类型，角色唯一标识
	* @param request
	* @return 返回json格式操作结果
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月24日 下午3:10:30 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月24日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/roleManager/roleManagerRemove")
	@ResponseBody
	public String removeRoleManager(HttpServletRequest request) {
		String id =request.getParameter("roleId");
		try {
			Assert.notNull(id, " id must be not null ");
			SysRole sr=(SysRole)baseCommonService.findById(SysRole.class, id);
			if(sr==null) {
				return JSON.toJSONString(ResultBean.getBean(ResultBean.NO_DATA,ResultType.ERROR,null));
			}
			baseCommonService.deleteById(SysRole.class, sr.getId());
		}catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("删除失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("删除成功！",ResultType.OK,null));
	}
	
}
