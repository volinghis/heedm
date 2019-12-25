package com.ehs.base.platform.login.controller;
/**
 * 
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: PassWordController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年8月13日 上午9:40:20 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年8月13日     zhaol           v1.0.0               修改原因
 */

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.SysUser;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.utils.MD5Util;
import com.ehs.security.utils.SysAccessUser;

@Controller
public class PassWordController {
	
	private static final Logger logger = LoggerFactory.getLogger(PassWordController.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * 
	* @Function: PassWordController.java
	* @Description: 进入页面</br>
	* 				sysUser：登录帐号信息
	* @param mv
	* @param request
	* @param response
	* @return：修改密码页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月13日 下午2:09:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月13日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/password/passwordManager")
	public ModelAndView passwordManager(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		logger.info("+++++++++++++进入密码管理++++++++++++++");
		String userCode = SysAccessUser.get().getSysUserCode();
		SysUser sysUser =(SysUser) baseCommonService.findByCode(SysUser.class, userCode);
		mv.addObject("sysUser",sysUser);
		mv.setViewName("/portal/updatPassword");
		logger.info("+++++++++++++退出密码管理，准备跳转页面++++++++++++++");
		return mv;
	}
	
	/**
	 * 
	* @Function: PassWordController.java
	* @Description: 更改密码保存
	*
	* @param sysUser
	* @param request
	* @param response
	* @return：修改成功或者失败
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月14日 上午8:47:56 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月14日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/password/savePassword")
	@ResponseBody
	public String savePassword(@ModelAttribute SysUser sysUser,HttpServletRequest request,HttpServletResponse response) {
		try {
			String userCode = SysAccessUser.get().getSysUserCode();
			SysUser sUser =(SysUser) baseCommonService.findByCode(SysUser.class, userCode);
			String oldPwd = request.getParameter("oldPassword");
			logger.info("oldPwd=================="+oldPwd);
			String account = request.getParameter("account");
			logger.info("account=================="+account);
//			String oldpassword = MD5Util.string2MD5(account+oldPwd);
			String oldpassword = MD5Util.string2MD5(oldPwd);
			if (!sUser.getPassword().equals(oldpassword)) {
				return JSON.toJSONString(ResultBean.getBean("'当前密码'输入错误！",ResultType.ERROR,null));
			}
			String newPwd = request.getParameter("newPassword");
//			String newpassword = MD5Util.string2MD5(sUser.getAccount()+newPwd);
			String newpassword = MD5Util.string2MD5(newPwd);
			sysUser.setPassword(newpassword);
			sysUser.setState(0);
			baseCommonService.saveOrUpdate(sysUser);
			return JSON.toJSONString(ResultBean.getBean("修改成功！",ResultType.OK,null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON.toJSONString(ResultBean.getBean("修改失败！",ResultType.ERROR,null));
	}
	
	/**
	 * 
	* @Function: PassWordController.java
	* @Description: 判断该ID的数据是否存在数据库中
	*
	* @param request
	* @param response
	* @return 一个新的实体，或者数据库对应的数据
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月19日 上午10:30:57 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月19日     zhaol           v1.0.0               修改原因
	 */
	@ModelAttribute
	public SysUser getSysUser(HttpServletRequest request,HttpServletResponse response) {
		String userId = request.getParameter("userId");
		logger.info("userId===================="+userId);
		if (StringUtils.isEmpty(userId)) {
			return new SysUser();
		}
		return (SysUser)baseCommonService.findById(SysUser.class, userId);
	}

}
