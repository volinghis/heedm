package com.ehs.security.web.menuManager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ehs.security.entity.MenuRole;
import com.ehs.security.entity.SysRole;
import com.ehs.security.entity.SysRoleMenu;
import com.ehs.security.entity.UserMenu;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.UserMenuService;
import com.ehs.security.utils.SysAccessUser;
import com.ehs.security.utils.TextNode;
import com.ehs.security.web.config.SystemConfig;
import com.ehs.security.web.menuManager.service.MenuManagerService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: MenuManagerController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年8月16日 上午10:34:12 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年8月16日     zhaol           v1.0.0               修改原因
*/
@Controller
public class MenuManagerController {
	private static final Logger logger = LoggerFactory.getLogger(MenuManagerController.class);
	
	@Resource
	private UserMenuService userMenuService;
	
	@Resource
	private SystemConfig systemConfig;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private MenuManagerService menuManagerService;
	
	/**
	 * 
	* @Function: MenuManagerController.java
	* @Description: 导航管理授权的入口
	*
	* @param mv
	* @param request
	* @param response
	* @return：跳转到导航管理页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年7月24日 下午2:45:01 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月24日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/menuManager/menuManager")
	public ModelAndView menuManager(ModelAndView mv,HttpServletRequest request,HttpServletResponse response) {
		logger.info("======进入导航管理======");
		List<UserMenu> systemMenus= userMenuService.getUserMenusForCache(SysAccessUser.get().getAccount()); 
		systemMenus.forEach((e)->e.setMenuUrl(StringUtils.isBlank(e.getMenuUrl())?"":systemConfig.getSystemMap().get(
				StringUtils.substring(e.getMenuUrl(),1, StringUtils.indexOf(e.getMenuUrl(), "/", 1)))+ 
				StringUtils.substring(e.getMenuUrl(), StringUtils.indexOf(e.getMenuUrl(), "/", 1))));
		List<UserMenu> systemList=systemMenus.stream().filter(s->StringUtils.isBlank(s.getMenuParentCode())).collect(Collectors.toList());
		systemList.sort((a, b) -> a.getMenuSort() - b.getMenuSort());
		String systemCode=request.getParameter("systemCode");
		if(StringUtils.isBlank(systemCode)) { 
			systemCode=systemList.get(0).getMenuCode();
		}
		final String ssCode=systemCode;
		mv.addObject("defaultSysMenu", systemMenus.stream().filter(s->StringUtils.equals(s.getMenuCode(),ssCode)).collect(Collectors.toList()).get(0));
		logger.info("========准备跳转页面======");
		mv.setViewName("/menuManager/menuManager");
		return mv;
	}
	/**
	 * 
	* @Function: MenuManagerController.java
	* @Description: 导航树
	*
	* @param request
	* @param response
	* @return：树结构数据
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年7月24日 下午2:44:41 
	*
	* Modification History:
	* Date         Author          Version         Description
	*---------------------------------------------------------*
	* 2019年7月24日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/menuTreeData")
	@ResponseBody
	public String menuData(HttpServletRequest request, HttpServletResponse response) {
		List<UserMenu> systemMenus= userMenuService.getUserMenusForCache(SysAccessUser.get().getAccount()); 
		systemMenus.forEach((e)->e.setMenuUrl(StringUtils.isBlank(e.getMenuUrl())?"":systemConfig.getSystemMap().get(StringUtils.substring(e.getMenuUrl(),1, StringUtils.indexOf(e.getMenuUrl(), "/", 1)))+StringUtils.substring(e.getMenuUrl(), StringUtils.indexOf(e.getMenuUrl(), "/", 1))));
		systemMenus.sort((a, b) -> a.getMenuSort() - b.getMenuSort());
		String parentCode=request.getParameter("parentCode");
		List<TextNode> menus=new ArrayList<TextNode>();
		createMenuNode(menus, systemMenus, parentCode);
		return JSON.toJSONString(menus);
	}
	/**
	 * 
	* @Function: MenuManagerController.java
	* @Description: 递归查询
	*
	* @param menuNodes
	* @param menus
	* @param parentCode
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月16日 上午11:03:36 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     zhaol           v1.0.0               修改原因
	 */
	private void createMenuNode(List<TextNode> menuNodes,List<UserMenu> menus,String parentCode) {
		menus.stream().filter(s->StringUtils.equals(s.getMenuParentCode(),parentCode)).forEach(c->{
			TextNode menuNode=new TextNode();
			menuNode.setId(c.getMenuCode());
			menuNode.setPid(c.getMenuParentCode());
			menuNode.setText(c.getMenuName());
			menuNode.setAttribute1(c.getMenuUrl());
			createMenuNode(menuNodes,menus,c.getMenuCode());
			menuNodes.add(menuNode);
		});
	}
	
	/**
	 * 
	* @Function: MenuManagerController.java
	* @Description: 角色展示
	*
	* @param request
	* @param response
	* @return：返回角色数据集合
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年7月30日 下午1:56:05 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月30日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/menuManager/roleList")
	@ResponseBody
	public String roleManagerList(HttpServletRequest request,HttpServletResponse response) {
		logger.info("==========进入roleManagerList方法==========");
		Pagenate pageNate=new Pagenate();
        pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
		String menuCode=request.getParameter("menuCode");
		System.out.println("roleManagerList     menuCode  ================="+menuCode);
		Assert.notNull(menuCode, "menuCode must not be null");
		Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> criteriaBuilder.and( criteriaBuilder.equal(root.get(MenuRole.CODE),menuCode));
//		List<MenuRole> menuRoles = (List<MenuRole>) baseCommonService.findAll(MenuRole.class, sf);
		List<MenuRole> menuRoles = (List<MenuRole>) baseCommonService.findAll(MenuRole.class, sf).stream().filter(s-> !StringUtils.equals(((MenuRole) s).getRoleCode(), "normal")).collect(Collectors.toList());
	    System.out.println(baseCommonService.findPagenate(MenuRole.class, sf,pageNate));
	    logger.info("======退出roleManagerList方法======");
		return JSON.toJSONString(menuRoles);
	}
	
	/**
	 * 
	* @Function: MenuManagerController.java
	* @Description: 进入编辑页面<p>
	* 				menuCode：菜单code
	*
	* @param mv
	* @param request
	* @param response
	* @return：跳转到编辑页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年7月30日 下午1:56:36 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月30日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/menuManager/menuManagerEdit")
	public ModelAndView menuManagerEdit(ModelAndView mv,HttpServletRequest request,HttpServletResponse response) {
		logger.info("========进入menuManagerEdit方法========");
		String menuCode=request.getParameter("menuCode");
		Assert.notNull(menuCode, "menuCode must not be null");
		mv.addObject("menuCode", menuCode);
		mv.setViewName("/menuManager/menuManagerEdit");
		logger.info("======退出menuManagerEdit方法=======");
		return mv;
	}
	
	/**
	 * 
	* @Function: MenuManagerController.java
	* @Description: 给导航授权角色，保存到菜单角色表中
	*
	* @param sysRoleMenu
	* @param request
	* @param response
	* @return：返回添加成功或者失败信息
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年7月30日 下午1:57:07 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月30日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/menuManager/menuManagerSave")
	@ResponseBody
	public String menuManagerSave(HttpServletRequest request,HttpServletResponse response) {
		logger.info("========进入menuManagerSave方法========");
		try {
//			String menRoleJson = request.getParameter("menuRoleJson");
			String roleCode = request.getParameter("roleCode");
			String menuCode = request.getParameter("menuCode");
			Assert.notNull(roleCode, "roleCode must not be null");
			Assert.notNull(menuCode, "menuCode must not be null");
//			List<SysRole> dateList = JSONArray.parseArray(menRoleJson, SysRole.class);
//			String roleCode= null;
//			for (SysRole sysRole : dateList) {
//				roleCode = sysRole.getCode();
//			}
			List<Predicate> predicates = new ArrayList<>();
			Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->{
				predicates.add(criteriaBuilder.equal(root.get(SysRoleMenu.MENU_CODE), menuCode));
				predicates.add(criteriaBuilder.equal(root.get(SysRoleMenu.ROLE_CODE), roleCode));
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			};
			SysRoleMenu roleMenu =(SysRoleMenu) baseCommonService.findOne(SysRoleMenu.class, sf);
			if (roleMenu != null) {
				return JSON.toJSONString(ResultBean.getBean("该角色已经存在，请勿重复添加！",ResultType.ERROR,null));
			}
			menuManagerService.saveRoleMenu(menuCode, roleCode);
			logger.info("======添加成功，退出menuManagerSave方法=======");
			return JSON.toJSONString(ResultBean.getBean("添加成功！",ResultType.OK,null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("======添加失败，退出menuManagerSave方法=======");
			return JSON.toJSONString(ResultBean.getBean("添加失败，请重试！",ResultType.ERROR,null));
		}
	}
	
	/**
	 * 
	* @Function: MenuManagerController.java
	* @Description: 取消导航的角色授权,删除菜单角色表信息
	*
	* @param request
	* @param response
	* @return：返回添加成功或者失败信息
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年7月30日 下午1:57:41 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月30日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/menuManager/menuManagerRemove")
	@ResponseBody
	public String menuManagerRemove(HttpServletRequest request,HttpServletResponse response) {
		logger.info("===进入delRoleMenu方法===");
		try {
			String roleCode = request.getParameter("roleCode");
			String menuCode = request.getParameter("menuCode");
			System.out.println("deleteMenu      menuCode="+menuCode);
			System.out.println("deleteMenu      roleCode="+roleCode);
			menuManagerService.deleteRoleMenu(menuCode, roleCode);
			logger.info("====取消授权成功,退出delRoleMenu方法====");
			return JSON.toJSONString(ResultBean.getBean("取消授权成功！",ResultType.OK,null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("====取消授权失败,退出delRoleMenu方法====");
			return JSON.toJSONString(ResultBean.getBean("取消授权失败，请重试！",ResultType.ERROR,null));
		}
	}
}
