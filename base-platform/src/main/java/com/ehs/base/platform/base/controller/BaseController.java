/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.portal.controller 
 * @author: chentm   
 * @date: 2019年5月30日 下午4:01:44 
 */
package com.ehs.base.platform.base.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ehs.base.platform.base.config.SystemConfig;
import com.ehs.base.platform.login.config.LoginConfig;
import com.ehs.security.config.PlatformConfig;
import com.ehs.security.entity.SysLoginLog;
import com.ehs.security.entity.User;
import com.ehs.security.entity.UserBaseInfo;
import com.ehs.security.entity.UserMenu;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.UserMenuService;
import com.ehs.security.utils.SysAccessUser;
/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: PortalController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月30日 下午4:01:44 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月30日      chentm          v1.0.0               修改原因
*/
@Controller
public class BaseController {

	
	@Resource
	private SystemConfig systemConfig;
	
	@Resource
	private LoginConfig loginConfig;
	
	@Resource
	private UserMenuService userMenuService;
	
	@Resource
	private PlatformConfig platformConfig;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * 
	* @Function: BaseController.java
	* @Description: 该函数的功能描述
	*
	* @param mv
	* @param request
	* @param response
	* @return 跳转登录页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午8:57:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/")
	public ModelAndView homePage(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		mv.setViewName("redirect:"+loginConfig.getLoginURL());
		return mv;
	}

	/**
	 * 
	* @Function: BaseController.java
	* @Description: 该函数的功能描述
	*
	* @param mv
	* @param request
	* @param response
	* @return 跳转到首页
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午9:01:39 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@GetMapping(value = "/action/index")
	public ModelAndView home(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		//mv.addObject("systemMap", systemConfig.getSystemMap());
		mv.setViewName("index");
		return mv;
	}
	
	/**
	 * 
	* @Function: BaseController.java
	* @Description: 根据不同的menuCode,展示不同的菜单内容
	*
	* @param mv
	* @param request
	* @param response
	* @return 页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午9:02:18 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@GetMapping(value = "/action/base/content")
	public ModelAndView content(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		mv.addObject("menuCode", request.getParameter("menuCode"));
		mv.addObject("groupCode", request.getParameter("groupCode"));
		mv.setViewName("/base/content");
		return mv;
	}
	
	/**
	 * 
	* @Function: BaseController.java
	* @Description: 跳转到首页，以卫星地图的形式表现
	*
	* @param mv
	* @param request
	* @param response
	* @return 跳转至首页
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午9:05:24 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@GetMapping(value = "/action/base/planetmap")
	public ModelAndView planetmap(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		mv.setViewName("/base/planetmap");
		return mv;
	}
	
	/**
	 * 
	* @Function: BaseController.java
	* @Description: 跳转首页</br>
	* 				subMenuList：MenuParentCode为空的数据集合</br>
	*				defaultSysMenu：MenuParentCode为空的数据集合</br>
	*				systemList：MenuParentCode为空的菜单集合</br>
	*				lastTime：上次登录时间
	* @param mv
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午9:06:47 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@GetMapping(value = "/action/portal")
	public ModelAndView portal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		//通过帐号查询该帐号所有菜单
		List<UserMenu> systemMenus= userMenuService.getUserMenusForCache(SysAccessUser.get().getAccount()); 
		//	systemMenus.forEach((e)->e.setMenuUrl(StringUtils.isBlank(e.getMenuUrl())?"":systemConfig.getSystemMap().get(StringUtils.substring(e.getMenuUrl(),1, StringUtils.indexOf(e.getMenuUrl(), "/", 1)))+ StringUtils.substring(e.getMenuUrl(), StringUtils.indexOf(e.getMenuUrl(), "/", 1))));
		//对用户菜单进行过滤，保留MenuParentCode为空的数据并将其stream转化为list
		List<UserMenu> systemList=systemMenus.stream().filter(s->StringUtils.isBlank(s.getMenuParentCode())).collect(Collectors.toList());
		//对数据进行排序
		systemList.sort((a, b) -> a.getMenuSort() - b.getMenuSort());
		String systemCode=request.getParameter("systemCode");
		if(StringUtils.isBlank(systemCode)) { 
			systemCode=systemList.get(0).getMenuCode();
			//systemCode=systemMenus.get(0).getMenuCode();
		}
		final String ssCode=systemCode;
		List<UserMenu> subMenuList=systemMenus.stream().filter(s->StringUtils.equals(s.getMenuParentCode(),ssCode)).collect(Collectors.toList());
		subMenuList.sort((a, b) -> a.getMenuSort() - b.getMenuSort());
		mv.addObject("subMenuList", subMenuList);
		mv.addObject("defaultSysMenu", systemMenus.stream().filter(s->StringUtils.equals(s.getMenuCode(),ssCode)).collect(Collectors.toList()).get(0));
		mv.addObject("systemList", systemList);
		
		String userAccount = SysAccessUser.get().getAccount();
		Specification sf1 = (Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) ->criteriaBuilder.and(criteriaBuilder.equal(root.get(User.ACCOUNT), userAccount));
		User user = (User) baseCommonService.findOne(User.class, sf1);
		//获取上次登录时间
		Specification sf = (Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) ->criteriaBuilder.and(criteriaBuilder.equal(root.get(SysLoginLog.ACCOUNT), userAccount));
		List<SysLoginLog> logs =(List) baseCommonService.findAll(SysLoginLog.class, sf);
		if (logs!=null&&logs.size()>1) {
			Timestamp lastTime = logs.get(1).getTime();
			System.out.println("您这次登录时间为："+logs.get(0).getTime());
			System.out.println("您上次登录时间为："+logs.get(1).getTime());
			request.setAttribute("lastTime", lastTime);
		}
		mv.addObject("user", user);
		mv.setViewName("/portal/main");
		return mv;
	}
	
	/**
	 * 
	* @Function: BaseController.java
	* @Description: 调用portal()方法，实现页面跳转
	*
	* @param mv
	* @param request
	* @param response
	* @return 首页
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午9:35:05 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@GetMapping(value = "/action/portalV2")
	public ModelAndView portalV2(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		portal(mv, request, response);		
		mv.setViewName("/portal/mainV3");
		return mv;
	}
	
	
//	@GetMapping(value = "/action/portalV3")
//	public ModelAndView portalV3(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
//		portal(mv, request, response);		
//		mv.setViewName("/portal/mainV3");
//		return mv;
//	}
	@GetMapping(value = "/action/portalV3")
	public ModelAndView portalV3(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		portal(mv, request, response);		
		mv.setViewName("/portal/mainV4");
		return mv;
	}
}
