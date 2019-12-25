/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.security.controller 
 * @author: chentm   
 * @date: 2019年5月30日 下午4:00:04 
 */
package com.ehs.base.platform.security.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ehs.base.platform.base.config.SystemConfig;
import com.ehs.base.platform.security.utils.MenuNode;
import com.ehs.security.entity.SysMenu;
import com.ehs.security.entity.UserMenu;
import com.ehs.security.service.UserMenuService;
import com.ehs.security.utils.SysAccessUser;
import com.ehs.security.utils.TextNode;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: MenuController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月30日 下午4:00:04 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月30日      chentm          v1.0.0               修改原因
*/
/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: MenuController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月28日 下午5:00:22 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月28日      chentm          v1.0.0               修改原因
*/
@Controller
public class MenuController {

	@Resource
	private UserMenuService userMenuService;
	
	@Resource
	private SystemConfig  systemConfig;
	
	/**
	 * 
	* @Function:getMenu 
	* @Description: 菜单页面跳转
	* @param request
	* @param response
	* @return 返回菜单页面
	* @throws：异常描述
	* @version: v1.0.0
	* @author: chentm
	* @author: qjj 添加注释
	* @date: 2019年8月19日 上午10:35:10 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/menu")
	public String getMenu(HttpServletRequest request, HttpServletResponse response) {
		String curUser = SysAccessUser.get().getAccount();
		String parentCode=request.getParameter("parentCode");
	//	Map<String, String> sysConfigMap=systemConfig.getSystemMap();
		
		List<UserMenu> sysMenus= userMenuService.findUserMenus(curUser, parentCode, true,new Sort(Direction.ASC,UserMenu.MENU_SORT));
		
		//sysMenus.forEach((e)->e.setMenuUrl(StringUtils.isBlank(e.getMenuUrl())?"":sysConfigMap.get(StringUtils.substring(e.getMenuUrl(),1, StringUtils.indexOf(e.getMenuUrl(), "/", 1)))+ "/action/index?RURL="+StringUtils.substring(e.getMenuUrl(), StringUtils.indexOf(e.getMenuUrl(), "/", 1))));
		request.setAttribute("parentCode", parentCode);
		request.setAttribute("menuList",sysMenus);
		
		return "/base/menu";
	}
	/**
	 * 
	* @Function:menuData 
	* @Description: 获取系统菜单
	* @param request
	* @param response
	* @return 返回json格式系统菜单数据
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月19日 上午10:17:24 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/menuData")
	@ResponseBody
	public String menuData(HttpServletRequest request, HttpServletResponse response) {
		
		List<UserMenu> systemMenus= userMenuService.getUserMenusForCache(SysAccessUser.get().getAccount()); 
		//systemMenus.forEach((e)->e.setMenuUrl(StringUtils.isBlank(e.getMenuUrl())?"":systemConfig.getSystemMap().get(StringUtils.substring(e.getMenuUrl(),1, StringUtils.indexOf(e.getMenuUrl(), "/", 1)))+StringUtils.substring(e.getMenuUrl(), StringUtils.indexOf(e.getMenuUrl(), "/", 1))));

		systemMenus.sort((a, b) -> a.getMenuSort() - b.getMenuSort());

		String parentCode=request.getParameter("parentCode");
		
		 List<TextNode> menus=new ArrayList<TextNode>();
		createMenuNode(menus, systemMenus, parentCode);
		
		return JSON.toJSONString(menus);
	}
	
	/**
	 * 
	* @Function:createMenuNode 
	* @Description: 递归生成菜单树数据
	* @param menuNodes 当前系统下所有菜单数据
	* @param menus 封装的树结构数据
	* @param parentCode 父节点code
	* @throws：异常描述
	* @version: v1.0.0
	* @author: chentm
	* @author: qjj 添加注释
	* @date: 2019年8月19日 上午10:24:07 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     qjj           v1.0.0               修改原因
	 */
	private void createMenuNode(List<TextNode> menuNodes,List<UserMenu> menus,String parentCode) {
		menus.stream().filter(s->StringUtils.equals(s.getMenuParentCode(),parentCode)).forEach(c->{
			MenuNode menuNode=new MenuNode();
			menuNode.setId(c.getMenuCode());
			menuNode.setPid(c.getMenuParentCode());
			menuNode.setText(c.getMenuName());
			menuNode.setAttribute1(c.getMenuUrl());
			menuNode.setIconCls(c.getMenuIcon());
			List ll=new ArrayList();
			createMenuNode(ll,menus,c.getMenuCode());
			if(ll.size()>0) {
				menuNode.setChildren(ll);
			}

			menuNodes.add(menuNode);
		});
	}

	/**
	 * 
	* @Function:portalData 
	* @Description: 系统菜单数据集合
	* @param mv
	* @param request
	* @param response
	* @return 返回json格式菜单数据
	* @throws：异常描述
	* @version: v1.0.0
	* @author: chentm
	* @author: qjj 添加注释
	* @date: 2019年8月19日 上午10:43:22 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年8月19日     qjj        v1.0.0            修改原因
	 */
	@GetMapping(value = "/action/portalData")
	public ModelAndView portalData(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		String systemCode=request.getParameter("systemCode");
		List<UserMenu> menus= userMenuService.getUserMenusForCache(SysAccessUser.get().getAccount()); 
		menus.sort((a, b) -> a.getMenuSort() - b.getMenuSort());
		
		List<TextNode> menuNodes1=new ArrayList<TextNode>();		
		createMenuNode(menuNodes1, menus, systemCode);
		TextNode tn=new TextNode();
		UserMenu um=menus.stream().filter(s->StringUtils.equals(s.getMenuCode(),systemCode)).collect(Collectors.toList()).get(0);
		tn.setAttribute1(um.getMenuUrl());
		tn.setChildren(menuNodes1);
		tn.setId(um.getMenuCode());
//		tn.setName(um.getMenuName());
//		tn.setpId(um.getMenuParentCode());
//		
		List<TextNode> menuNodes=new ArrayList<TextNode>();
		menuNodes.add(tn);
		
		//menuNodes.forEach((e)->e.setAttribute1(StringUtils.isBlank(e.getAttribute1())?"":systemConfig.getSystemMap().get(StringUtils.substring(e.getAttribute1(),1, StringUtils.indexOf(e.getAttribute1(), "/", 1)))+ "/action/index?RURL="+StringUtils.substring(e.getAttribute1(), StringUtils.indexOf(e.getAttribute1(), "/", 1))));
		String menuNodeJson=JSON.toJSONString(menuNodes);
		mv.addObject("menuNodeJson", menuNodeJson);
		String defaultUrl=(menuNodes.get(0).getChildren()==null||menuNodes.get(0).getChildren().size()<1)?menuNodes.get(0).getAttribute1():menuNodes.get(0).getChildren().get(0).getAttribute1();
		mv.addObject("defaultUrl", defaultUrl);
		mv.setViewName("/portal/data");
		return mv;
	}


}
