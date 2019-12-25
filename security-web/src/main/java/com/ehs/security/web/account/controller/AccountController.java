package com.ehs.security.web.account.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.SysUser;
import com.ehs.security.entity.User;
import com.ehs.security.entity.UserRole;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.SysUserSearchService;
import com.ehs.security.service.UserRoleService;
import com.ehs.security.service.UserService;
import com.ehs.security.utils.MD5Util;
import com.ehs.security.web.account.service.AccountService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: AccountController.java
* @Description: 账号管理控制层
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年6月6日 上午10:01:44 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月6日     	qjj           v1.0.0               修改原因
*/
@Controller
public class AccountController {

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private SysUserSearchService sysUserSearchService;
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private AccountService accountService;
	/**
	 * 
	* @Function:accountManager 
	* @Description: 账号管理页面跳转
	* @return 帐号管理页面
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月24日 下午4:09:17 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月24日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/accountManager/accountManager")
	public String accountManager() {
		return "/account/accountManager";
	}
	
	/**
	 * 
	* @Function: AccountController.java
	* @Description: 进入当前用户的授权页面</br>
	*				account：账号</br>
	*				name:该账号的用户名
	* @param request
	* @param mv
	* @return：跳转到授权界面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年6月13日 下午2:21:54 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月13日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value="/action/accountManager/authorize")
	public ModelAndView goAuthorize(ModelAndView mv,HttpServletRequest request) {
		String account=request.getParameter("account");
		User u= userService.findUserByAccount(account);
		if(u!=null) {
			mv.addObject("curName",u.getName());
		}
		mv.addObject("curAccount", account);
		mv.setViewName("/account/authorize");
		return mv;
	}
	
	/**
	 * 
	* @Function:goRoleList 
	* @Description: 跳转至角色选择页面
	* @param mv
	* @return 角色选择页面
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月24日 下午7:34:04 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月24日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/accountManager/roleList")
	public ModelAndView goRoleList(ModelAndView mv) {
		mv.setViewName("/account/roleList");
		return mv;
	}
	
	/**
	 * 
	* @Function: AccountController.java
	* @Description: 加载当前用户拥有的角色数据(授权数据)
	*
	* @param request
	* @return：角色集合
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年6月13日 下午2:21:54 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月13日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value="/action/accountManager/authorizeList")
	@ResponseBody
	public String authorizeList(HttpServletRequest request) {
		Pagenate pageNate=new Pagenate();	
        pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
		String accountParam=request.getParameter("account");
		return JSON.toJSONString(userRoleService.findPagenateByAccount(accountParam, UserRole.class, pageNate));
	}
	
	/**
	 * 
	* @Function: AccountController.java
	* @Description: 账户管理datatable数据加载
	*
	* @param request
	* @return：所有帐号
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年6月13日 下午2:20:26 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月13日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/accountManager/accountManagerList")
	@ResponseBody
	public String accountManagerList(HttpServletRequest request) {
		String searhParam=request.getParameter("searchParam");
		Pagenate pageNate=new Pagenate();
        pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
        
		if(StringUtils.isNotBlank(searhParam)) {
			Specification<User> sf=(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)-> criteriaBuilder.or(criteriaBuilder.like(root.get(User.ACCOUNT),"%"+searhParam+"%" ), criteriaBuilder.like(root.get(User.NAME),"%"+searhParam+"%" ));
			pageNate=userService.findUserListPagenate(User.class, sf, pageNate);
		}else {
			pageNate=userService.findUserListPagenate(User.class,null, pageNate);
		}
		System.out.println(JSON.toJSONString(pageNate));
		return JSON.toJSONString(pageNate);
	}
	
	/**
	 * 
	* @Function: AccountController.java
	* @Description: 重置密码</br>根据帐号查询当前用户，设置密码为加密后密码为:(帐号+12345)
	*
	* @param request
	* @return：返回操作成功或者失败
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年6月13日 下午8:24:00 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月13日     Administrator           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/accountManager/resetPwd")
	@ResponseBody
	public String  resetPwd(HttpServletRequest request) {
		String accoutParam=request.getParameter("accoutParam");
		try {
			SysUser sysUser=sysUserSearchService.findByUserAccount(accoutParam);
//			String resetPwd=MD5Util.string2MD5(accoutParam+"123456");
			String resetPwd=MD5Util.string2MD5("123456");
			if(sysUser!=null) {
				sysUser.setPassword(resetPwd);
			}
			baseCommonService.saveOrUpdate(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("重置密码失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("重置密码成功！",ResultType.OK,null));
	}
	
	/**
	 * 
	* @Function: AccountController.java
	* @Description: 锁定和解锁用户</br>
	* 				设置其状态0为启用，1为锁定
	*
	* @param request
	* @return：返回成功或者失败信息
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年6月13日 下午8:52:02 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月13日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/accountManager/lockOrUnlock")
	@ResponseBody
	public String lockOrUnlock(HttpServletRequest request) {
		String accoutParam=request.getParameter("accoutParam");
		Integer stateParam=Integer.parseInt(request.getParameter("stateParam"));
		System.out.println("accoutParam"+accoutParam+"stateParam"+stateParam);
		try {
			SysUser sysUser=sysUserSearchService.findByUserAccount(accoutParam);
			if(sysUser!=null) {
				sysUser.setState(stateParam);
				baseCommonService.saveOrUpdate(sysUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("操作失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("操作成功！",ResultType.OK,null));
	}
	
	/**
	 * 
	 * @Function: AccountController.java
	 * @Description: 给当前选中的用户分配角色</br>
	 *				account：账号</br>
	 *				roleCode：角色code
	 * @param request
	 * @return：返回操作成功或者失败
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2019年6月13日 下午8:52:02 
	 *
	 * Modification History:
	 * Date         Author          Version            Description
	 *---------------------------------------------------------*
	 * 2019年6月13日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/accountManager/authForCurUser")
	@ResponseBody
	public String authForCurUser(HttpServletRequest request) {
		try {
			String account=request.getParameter("account");
			String dataCode=request.getParameter("dataCode");
			System.out.println("当前的角色dataCode为："+dataCode);
			List<String> roleCodes=	userRoleService.findRoleCodeByAccount(account);
			for (String roleCode : roleCodes) {
				if(roleCode.equals(dataCode)) {
					return (String) JSON.toJSON(ResultBean.getBean("该角色已经存在！",ResultType.ERROR,null));
				}
			}
			accountService.saveAuthForCurUser(account,dataCode);
			return JSON.toJSONString(ResultBean.getBean("授权成功！",ResultType.OK,null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON.toJSONString(ResultBean.getBean("该角色已经存在！",ResultType.ERROR,null));
	}
			
	/**
	 * 
	 * @Function: AccountController.java
	 * @Description: 给当前选中的用户移除授权角色</br>
	 * 				account：账号</br>
	 *				roleCode：角色code
	 * @param requset
	 * @return：返回成功或者失败信息
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2019年6月13日 下午8:52:02 
	 *
	 * Modification History:
	 * Date         Author          Version            Description
	 *---------------------------------------------------------*
	 * 2019年6月13日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/accountManager/removeRoleForCurUser")
	@ResponseBody
	public String removeRoleForCurUser(HttpServletRequest request) {
		try {
			String account = request.getParameter("account");
			String roleCode = request.getParameter("roleCode");
			System.out.println("accoutParam:"+account+"codeParam:"+roleCode);
			accountService.removeRoleForCurUser(account, roleCode);
			return JSON.toJSONString(ResultBean.getBean("操作成功！",ResultType.OK,null));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return JSON.toJSONString(ResultBean.getBean("操作失败！",ResultType.ERROR,null));
	}
}
