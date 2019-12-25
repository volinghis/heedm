package com.ehs.security.web.userBaseInfoManager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ehs.security.entity.DataDictionary;
import com.ehs.security.entity.OrganizationInfo;
import com.ehs.security.entity.UserBaseInfo;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;
import com.ehs.security.service.OrganizationSearchService;
import com.ehs.security.web.userBaseInfoManager.service.UserBaseInfoManagerService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserBaseInfoManagerController.java
* @Description: 用户管理维护控制层
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年7月5日 上午10:45:05 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月5日     qjj           v1.0.0               修改原因
*/
@Controller
public class UserBaseInfoManagerController {

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private OrganizationSearchService organizationSearchService;
	
	@Resource
	private UserBaseInfoManagerService userBaseInfoManagerService;
	
	@Resource
	private DataDictionarySearchService dataDictionarySearchService;
	
	/**
	 * 
	* @Function: UserBaseInfoManagerController.java
	* @Description: 用户管理页面跳转
	*
	* @param:描述1描述
	* @return：返回模型视图
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月5日 上午10:51:50 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月5日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value="/action/orgManager/userManager")
	public ModelAndView userBaseInfoManager(ModelAndView mv) {
		mv.setViewName("/userBaseInfoManager/userBaseInfoManager");
		return mv;
	}
	
	/**
	 * 
	* @Function: UserBaseInfoManagerController.java
	* @Description: 用户管理新增编辑页面跳转
	* 			<ul>
	* 				<li>userBaseInfoId：String类型，用户信息唯一标识id</li>
	* 				<li>orgCode:String类型，当前的组织机构code</li>
	* 			</ul>
	*
	* @param: HttpServletRequest
	* @return：ModelAndView
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月5日 下午2:45:59 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月5日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/userBaseInfoManager/userBaseInfoEdit")
	public ModelAndView userBaseInfoEdit(ModelAndView mv,HttpServletRequest request) {
		String userBaseInfoId = request.getParameter("userBaseInfoId");
		String orgCode=request.getParameter("orgCode");
		Assert.notNull(orgCode, "orgCode must not be null");
		UserBaseInfo dd=null;
		if(StringUtils.isNotBlank(userBaseInfoId)) {
			dd = (UserBaseInfo) baseCommonService.findById(UserBaseInfo.class, userBaseInfoId);
			if(dd==null) {
				mv.setViewName(ResultBean.NO_DATA_RESPONSE_URL);
				return mv;
			}
		}
		mv.addObject("objectBean", dd == null ? new UserBaseInfo() : dd);
		mv.addObject("org",organizationSearchService.findByCode(orgCode) );
		mv.setViewName("/userBaseInfoManager/userBaseInfoEdit");
		return mv;
	}
	
	/**
	 * 
	* @Function:userBaseInfoManagerList 
	* @Description: 当前组织下所有用户列表
	* @param request
	* @return 返回json格式用户数据集合
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 下午5:12:42 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value="/action/userBaseInfoManager/userBaseInfoManagerList")
	@ResponseBody
	public String userBaseInfoManagerList(HttpServletRequest request) {
		Pagenate pageNate=new Pagenate();
		String searchParam=request.getParameter("searchParam");
        pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
		String orgCode=request.getParameter("orgCode");
		List<OrganizationInfo> orgList = baseCommonService.findAll(OrganizationInfo.class, null);
        List<String> treelist=new ArrayList<String>();
		createMenuNode(treelist, orgList, orgCode);
		Specification<UserBaseInfo> sf=null;
		List<Predicate> ps=new ArrayList<Predicate>();
		//多条件查询
		sf=(Root<UserBaseInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->{
			if(StringUtils.isNotBlank(orgCode)) {
				OrganizationInfo orgInfos= (OrganizationInfo) baseCommonService.findByCode(OrganizationInfo.class, orgCode);
				CriteriaBuilder.In<String> in=null;
				if(orgInfos != null ) {
					in= criteriaBuilder.in(root.get(UserBaseInfo.ORG_CODE));
					if (treelist.size() > 0) {
						for (int j = 0; j < treelist.size(); j++) {
							in.value(treelist.get(j));
							System.out.println("递归查询到的第"+(j+1)+"个Code："+treelist.get(j));
						}
					}
				}
				in.value(orgCode);
				ps.add(criteriaBuilder.and(in));//存入条件集合里
			}
        	if(StringUtils.isNotBlank(searchParam)) {
        		ps.add(criteriaBuilder.or(criteriaBuilder.like(root.get(UserBaseInfo.DATA_CODE),"%"+searchParam+"%" ), criteriaBuilder.like(root.get(UserBaseInfo.NAME),"%"+searchParam+"%" )));
        	}
        	return criteriaBuilder.and(ps.toArray(new Predicate[0]));
        };
        return JSON.toJSONString(baseCommonService.findPagenate(UserBaseInfo.class, sf, pageNate));
	} 
	
	
	private void createMenuNode(List<String> list,List<OrganizationInfo> dataList,String parentCode) {
		dataList.stream().filter(s->StringUtils.equals(s.getParentCode(),parentCode)).forEach(c->{
			list.add(c.getCode());
			createMenuNode(list,dataList,c.getCode());
		});
	}
	/**
	 * 
	* @Function:SaveUserBaseInfoManager 
	* @Description: 用户管理新增和修改
	* @param userBaseInfo 用户信息实例
	* @param request
	* @param response
	* @return 返回json格式操作结果
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 下午5:15:45 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value="/action/userBaseInfoManager/userBaseInfoManagerSave")
	@ResponseBody
	public  String  SaveUserBaseInfoManager(@ModelAttribute UserBaseInfo userBaseInfo,HttpServletRequest request,HttpServletResponse response) {
		try {
			//关联表插入记录
			if(StringUtils.isNotBlank(userBaseInfo.getPosition())) {
				DataDictionary dd=dataDictionarySearchService.findOne(userBaseInfo.getPosition());
				if (dd != null) {
					String position=dd.getText();
					userBaseInfo.setPosition(position);
				}
			}
			if(StringUtils.isNotBlank(userBaseInfo.getOrgCode())) {
				OrganizationInfo org= organizationSearchService.findByCode(userBaseInfo.getOrgCode());
				if (org!=null) {
					userBaseInfo.setAttribute1(org.getName());
				}
			}
			userBaseInfoManagerService.saveUserInfoAndSysUser(userBaseInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("操作失败！",ResultType.OK,null));
		}
		return JSON.toJSONString(ResultBean.getBean("操作成功！",ResultType.OK,null));
	}
	/**
	 * 
	* @Function:RemoveUserBaseInfoManager 
	* @Description: 用户管理删除</br>
	* 				userBaseInfoId:String类型，用户信息唯一标识
	* @param request
	* @return 返回json格式操作结果
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 下午5:17:05 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/userBaseInfoManager/UserBaseInfoManagerRemove")
	@ResponseBody
	public String RemoveUserBaseInfoManager(HttpServletRequest request) {
		String userBaseInfoId=request.getParameter("userBaseInfoId");
		try {
			Assert.notNull(userBaseInfoId, " userBaseInfoId must be not null ");
			UserBaseInfo el=(UserBaseInfo)baseCommonService.findById(UserBaseInfo.class, userBaseInfoId);
			if(el==null) {
				return JSON.toJSONString(ResultBean.getBean(ResultBean.NO_DATA_RESPONSE_URL,ResultType.ERROR,null));
			}
			baseCommonService.deleteById(UserBaseInfo.class, el.getId());
		}catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("删除失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("删除成功！",ResultType.OK,null));
	}
	/**
	 * 
	* @Function:CheckUserBaseInfoCode 
	* @Description: 验证用户账号 是否重复
	* 				<ul>
	* 					<li>id:String类型，用户唯一标识</li>
	* 					<li>code:String类型，用户账号</li>
	* 				</ul>
	* @param request
	* @return isRepeat,布尔类型
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 下午1:53:25 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/userBaseInfoManager/UserBaseInfoCodeCheck")
	@ResponseBody
	public String CheckUserBaseInfoCode(HttpServletRequest request) {
		String id=request.getParameter("id");
		String code=request.getParameter("code");
		boolean isRepeat=false;
		Map<String, Object> resMap=new HashMap<String, Object>();
			UserBaseInfo ub=(UserBaseInfo)baseCommonService.findById(UserBaseInfo.class, id);
			if(ub!=null) {
				if(!code.equals(ub.getDataCode())) {
					isRepeat=false;
				}
		   }else {
			List<UserBaseInfo> userBaseInfoList=baseCommonService.findAll(UserBaseInfo.class,null);
			long count=userBaseInfoList.stream()
					.filter((e)->e.getDataCode().equals(code)).count();	
			if(count>0l) {
				isRepeat=true;
			}
		}
		resMap.put("isRepeat", isRepeat);
	return JSON.toJSONString(resMap);
}
	
	
	/**
	 * 
	* @Function:userBaseInfo 
	* @Description: 用户信息数据绑定
	* @param request
	* @return UserBaseInfo，用户信息实例
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年8月16日 下午1:58:14 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月16日     qjj           v1.0.0               修改原因
	 */
	@ModelAttribute
	public UserBaseInfo userBaseInfo(HttpServletRequest request) {
		String id=request.getParameter("userBaseInfoId");
		if(StringUtils.isBlank(id)) {
			return new UserBaseInfo();
		}else {
			return (UserBaseInfo)baseCommonService.findById(UserBaseInfo.class, id);
		}
	}
}
