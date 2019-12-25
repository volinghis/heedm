/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.edmLibraryManager.edmAccountPrint 
 * @author: chentm   
 * @date: 2019年7月3日 上午9:55:12 
 */
package com.ehs.edm.edmLibraryManager.edmAccountPrint.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrintParameter;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmPrincipal;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmAccountPrintParameterService;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmAccountPrintService;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.service.EdmPrincipalService;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.DataDictionary;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.entity.User;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;
import com.ehs.security.service.OrganizationSearchService;
import com.ehs.security.service.UserService;
import com.ehs.security.utils.BaseUtils;
import com.ehs.security.utils.TreeNodeLazy;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EdmAccountPrintController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月3日 上午9:55:12 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月3日      chentm          v1.0.0               修改原因
*/
@Controller
public class EdmAccountPrintController {
	
	private Logger logger = LoggerFactory.getLogger(EdmAccountPrintController.class);
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private  DataDictionarySearchService  dataDictionarySearchService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private OrganizationSearchService organizationSearchService;
	
	@Resource
	private EdmAccountPrintService edmAccountPrintService;
	
	@Resource
	private EdmAccountPrintParameterService accountPrintParamService;
	
	@Resource
	private EdmPrincipalService principalService;
	
	
	@RequestMapping(value = "/action/edmLibraryManager/goUploader")
	public String goUploader() {
		
		return "/edmLibraryManager/edmAccountPrint/miniUploader";
	}
	
	/**
	 * 
	* @Function: EdmAccountPrintController.java
	* @Description: 跳转编辑页面的入口，并且传入参数</br>
	* 				edmAccountPrintId :新增的时候为空值，编辑的时候不为空</br>
	* 				code:树结构的code，通过这个code和树结构关联</br>
	* 				founder：责任人，登录的用户</br>
	* 				objectBean：通过edmAccountPrintId去数据库查找如果没有新增，
	* 				如果有就就拿到这条数据到页面
	*
	* @param mv
	* @param request
	* @param response
	* @return 跳转到设备编辑页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午2:40:21 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmAccountPrintEdit")
	public ModelAndView edmLibrary(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		logger.info("=====================进入edmAccountPrintEdit==================== ");
		String edmAccountPrintId= request.getParameter("edmAccountPrintId");
		String founder = request.getParameter("founder");
		logger.info("founder=============="+founder);
		Specification sf = (Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->(criteriaBuilder.equal(root.get(User.ACCOUNT), founder));
		User user =(User) baseCommonService.findOne(User.class, sf);
		logger.info("userName==========================="+user.getName());
		EdmAccountPrint dd=null;
		if(StringUtils.isNotBlank(edmAccountPrintId)) {
			dd =  (EdmAccountPrint)baseCommonService.findById(EdmAccountPrint.class, edmAccountPrintId);
			if(dd==null) {
				mv.setViewName(ResultBean.NO_DATA_RESPONSE_URL);
				return mv;
			}
		}
		mv.addObject("orgCode", request.getParameter("code"));
		mv.addObject("founder", user.getName());
		mv.addObject("objectBean", dd == null ? new EdmAccountPrint() : dd);
		mv.setViewName("/edmLibraryManager/edmAccountPrint/edmAccountPrintEdit");
		logger.info("=====================退出edmAccountPrintEdit==================== ");
		return mv;
	}
	
	/**
	 * 
	* @Function: EdmAccountPrintController.java
	* @Description: 设备废弃功能</br>
	* 				通过edmAccountPrintId去数据库查找这条数据，将其设备状态置为废弃状态
	*
	* @param mv
	* @param request
	* @param response
	* @return 操作成功：设备状态为“废弃状态”</br>
	* 		    操作失败: 设备状态为“运行状态”
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午1:50:13 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmAccountPrintRemove")
	@ResponseBody
	public String edmAccountPrintRemove(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		String edmAccountPrintId= request.getParameter("edmAccountPrintId");
		try {
			Assert.notNull(edmAccountPrintId, " edmAccountPrintId must be not null ");
			EdmAccountPrint el=(EdmAccountPrint)baseCommonService.findById(EdmAccountPrint.class, edmAccountPrintId);
			if(el==null) {
				return JSON.toJSONString(ResultBean.getBean(ResultBean.NO_DATA_RESPONSE_URL,ResultType.ERROR,null));
			}
			el.setStatusCode("deviceStatus_discard");
			el.setStatusName(dataDictionarySearchService.findOne("deviceStatus_discard").getText());
			baseCommonService.saveOrUpdate(el);
			return JSON.toJSONString(ResultBean.getBean("操作成功！",ResultType.OK,null));
		}catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("操作失败！",ResultType.ERROR,null));
		}
	}
	
	/**
	 * 
	* @Function: EdmAccountPrintController.java
	* @Description: 展示所有设备台账数据，并且可以根据不同条件查询所要的数据
	*
	* @param request
	* @param response
	* @return 设备台账数据集合
	* @throws：无
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午1:54:16 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmAccountPrintList")
	@ResponseBody
	public String edmAccountPrintList( HttpServletRequest request, HttpServletResponse response) {
		logger.info("======进入edmAccountPrintList方法=====");
        Pagenate pageNate=new Pagenate();
//        boolean export=Boolean.valueOf(request.getParameter("export"));
        String code = request.getParameter("code");
        logger.info(" code============"+code);
        String deviceName=request.getParameter("deviceName");
        String deviceNum = request.getParameter("deviceNum");
        String deviceModel = request.getParameter("deviceModel");
		String statusCode = request.getParameter("statusCode");
		String productFactoryName = request.getParameter("productFactoryName");
        List<DataDictionary> dataList=  dataDictionarySearchService.findAllDataDictionary();
		List list=new ArrayList();
		createMenuNode(list, dataList, code);
        Specification sf=null;
        List<Predicate> ps=new ArrayList<Predicate>();
        sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)->{
//        	ps.add(criteriaBuilder.equal(root.get(EdmAccountPrint.STATUS_CODE), "deviceStatus_runing"));
        	if(StringUtils.isNotBlank(code)) {
    			CriteriaBuilder.In in=criteriaBuilder.in(root.get(EdmAccountPrint.INSTALL_ADDRESS_CODE));
    			if (list.size() > 0) {
    				for (int i = 0; i < list.size(); i++) {
    					in.value(list.get(i));
    				}
				}
    			in.value(code);
    			//存入条件集合里
    			ps.add(criteriaBuilder.and(criteriaBuilder.and(in)));
        	}
        	if(StringUtils.isNotBlank(deviceName)) {
        		ps.add(criteriaBuilder.like(root.get(EdmAccountPrint.DEVICE_NAME), "%"+deviceName+"%"));
        	}
        	if (StringUtils.isNotBlank(deviceNum)) {
				ps.add(criteriaBuilder.like(root.get(EdmAccountPrint.DEVICE_NUM), "%" + deviceNum + "%"));
			}
        	if (StringUtils.isNotBlank(deviceModel)) {
				ps.add(criteriaBuilder.like(root.get(EdmAccountPrint.DEVICE_MODEL), "%" + deviceModel + "%"));
			}
			if (StringUtils.isNotBlank(statusCode)) {
				ps.add(criteriaBuilder.equal(root.get(EdmAccountPrint.STATUS_CODE), statusCode));
			}
			if (StringUtils.isNotBlank(productFactoryName)) {
				ps.add(criteriaBuilder.like(root.get(EdmAccountPrint.PRODUCT_FACTORY_NAME), "%" + productFactoryName + "%"));
			}
        	return criteriaBuilder.and(ps.toArray(new Predicate[0]));
        };
    	pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
    	pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
        List<EdmAccountPrint> edmlist = baseCommonService.findPagenate(EdmAccountPrint.class, sf, pageNate).getData();
        edmlist.stream().forEach(s->{
        	s.setStatusName(dataDictionarySearchService.findOne(s.getStatusCode()).getText());
    	});
        pageNate.setData(edmlist);
		return JSON.toJSONString(pageNate);
	}
	
	/**
	 * 
	* @Function: EdmAccountPrintController.java
	* @Description: 通过该节点的code递归下面所有的节点
	*
	* @param list ---> 用来存放数据
	* @param dataList  ---> DataDictionary实体中所有数据
	* @param parentCode ---> 该节点的code
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午3:28:04 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	private void createMenuNode(List<String> list,List<DataDictionary> dataList,String parentCode) {
		dataList.stream().filter(s->StringUtils.equals(s.getParentCode(),parentCode)).forEach(c->{
			list.add(c.getCode());
			createMenuNode(list,dataList,c.getCode());
		});
	}
	
	/**
	 * 
	* @Function: EdmAccountPrintController.java
	* @Description: 设备台账的入口，并且传入设备台账实体类名，供后面的导出功能使用
	*
	* @param mv
	* @param request
	* @param response
	* @return 设备台账页面
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午2:17:25 
	*
	* Modification History:
	* Date         Author          Version        Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmAccountPrint")
	public ModelAndView edmAccountPrint(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入设备台账");
		String nameString = EdmAccountPrint.class.getTypeName();
		String encodedText = BaseUtils.encode(nameString);
		mv.addObject("className",encodedText);
		mv.setViewName("/edmLibraryManager/edmAccountPrint/edmAccountPrint");
		logger.info("准备跳转页面");
		return mv;
	}
	
	/**
	 * 
	* @Function: EdmAccountPrintController.java
	* @Description: 生成懒加载设备台账树结构
	*
	* @param request
	* @param response
	* @return 树形数据结构
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午2:20:57 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmAccountPrintTreeData")
	@ResponseBody
	public String edmAccountPrintTreeData(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		List<TreeNodeLazy> lazyList=new ArrayList<TreeNodeLazy>();
		if(StringUtils.isBlank(id)) {
			DataDictionary dd= dataDictionarySearchService.findOne("edmGroupInfo");
			TreeNodeLazy tnl=new TreeNodeLazy();
			tnl.setId(dd.getCode());
			tnl.setPid(dd.getParentCode());
			tnl.setName(dd.getText());
			tnl.setIsLeaf(false);
			lazyList.add(tnl);
		}else {
			Specification sf=(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder)-> 
				criteriaBuilder.and(criteriaBuilder.equal(root.get(EdmAccountPrint.INSTALL_ADDRESS_CODE), id));
			List<EdmAccountPrint> eapList= baseCommonService.findAll(EdmAccountPrint.class, sf);
			logger.info("edmAccountPrintTreeData   eapList================"+eapList.size());
			if(eapList!=null&&eapList.size()>0) {
				eapList.forEach(s->{
					TreeNodeLazy tnl=new TreeNodeLazy();
					tnl.setId(s.getCode());
					tnl.setPid(id);
					tnl.setName(s.getDeviceName());
					tnl.setAttribute1(s.getDeviceNum());
					tnl.setIsLeaf(true);
					lazyList.add(tnl);
				});
			}
			List<DataDictionary> dataList= dataDictionarySearchService.findDataDictionary(id);
			if(dataList!=null&&dataList.size()>0) {
				dataList.forEach(s->{
					TreeNodeLazy tnl=new TreeNodeLazy();
					tnl.setId(s.getCode());
					tnl.setPid(id);
					tnl.setName(s.getText());
					tnl.setIsLeaf(false);
					tnl.setAttribute5("orgelement");
					lazyList.add(tnl);
				});
			}
		}
		return JSON.toJSONString(lazyList);
	}
	
	/**
	 * 
	* @Function: EdmAccountPrintController.java
	* @Description: 保存设备台账,同时保存和设备相关的文件信息和参数信息</br>
	* 				filesJson:前台传过来的文件数据</br>
	* 				accountParamJson：前台传过来的设备参数数据
	*
	* @param edmAccountPrint 
	* @param request
	* @param response
	* @return 保存成功或者失败信息
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午2:07:42 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmAccountPrintSave")
	@ResponseBody
	public String saveEdmLibrary(@ModelAttribute EdmAccountPrint edmAccountPrint, HttpServletRequest request, HttpServletResponse response) {
		try {
			String filesJson=request.getParameter("filesJson");
			String accountParamJson = request.getParameter("accountParamJson");
			String principalJson = request.getParameter("principalParamJson");
			String groupCode = request.getParameter("orgCode");
			logger.info("save     groupCode============="+groupCode);
			edmAccountPrint.setInstallAddressCode(groupCode);
			logger.info("InstallAddressCode()=========+++++"+edmAccountPrint.getInstallAddressCode());
			if(StringUtils.isNotBlank(groupCode)) {
				List<DataDictionary> dataList= dataDictionarySearchService.findAllDataDictionary();
				List<DataDictionary> list= dataList.stream().filter(s->StringUtils.equals(s.getCode(), groupCode)).collect(Collectors.toList());
				DataDictionary d=list.get(0);
				edmAccountPrint.setInstallAddressName(d.getText());
				int i=0;
				String fullName=d.getText();
				while(StringUtils.isNotBlank(d.getParentCode())) {
					final String cc=d.getParentCode();
					List<DataDictionary> list1= dataList.stream().filter(s->StringUtils.equals(s.getCode(), cc)).collect(Collectors.toList());
					d=list1.get(0);
					fullName=d.getText()+"/"+fullName;
					i++;
					if(i>50) {
						throw new RuntimeException("进入死循环");
					}
				}
				fullName=StringUtils.contains(fullName, "/")?StringUtils.substringAfter(fullName, "/"):fullName;
				edmAccountPrint.setInstallAddressFullName(fullName);
			}
			if(StringUtils.isNotBlank(edmAccountPrint.getCheckRrepairTeamCode())) {
				edmAccountPrint.setCheckRrepairTeamName(organizationSearchService.findByCode(edmAccountPrint.getCheckRrepairTeamCode()).getName());
			}
//			if(StringUtils.isNotBlank(edmAccountPrint.getProductFactoryName())) {
//				DataDictionary dd=dataDictionarySearchService.findOne(edmAccountPrint.getProductFactoryName());
//				if (dd != null) {
//					String productFactoryName = dd.getText();
//					edmAccountPrint.setProductFactoryName(productFactoryName);
//				}
//			}
			if (StringUtils.isBlank(edmAccountPrint.getStatusCode())) {
				edmAccountPrint.setStatusCode("deviceStatus_runing");
				edmAccountPrint.setStatusName(dataDictionarySearchService.findOne("deviceStatus_runing").getText());
			}
			logger.info("+++++++++准备保存数据++++++++++++");
			BaseEntity base=edmAccountPrintService.saveOrUpdate(edmAccountPrint,
					JSONArray.parseArray(accountParamJson,EdmAccountPrintParameter.class), JSON.parseArray(filesJson, FileInfo.class),JSON.parseArray(principalJson, EdmPrincipal.class));
			return JSON.toJSONString(ResultBean.getBean("保存成功！",ResultType.OK,base.getId()));
		}catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("保存失败！",ResultType.ERROR,null));
		}
	}
	
	/**
	 * 
	* @Function: EdmAccountPrintController.java
	* @Description: 在方法调用之前执行的判断该数据是否在数据库中存在
	*
	* @param request
	* @return 如果id为空，返回一个新的实体，如果id不为空，去数据库里查询此条数据
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午2:10:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	@ModelAttribute
	public EdmAccountPrint edmLibrary(HttpServletRequest request) {
		String id=request.getParameter("edmAccountPrintId");
		logger.info("edmLibrary   edmAccountPrintId ============"+id);
		if(StringUtils.isBlank(id)) {
			return new EdmAccountPrint();
		}else {
			return (EdmAccountPrint)baseCommonService.findById(EdmAccountPrint.class, id);
		}
	}
	
	/**
	 * 
	* @Function: EdmAccountPrintController.java
	* @Description: 根据code查询所有EdmAccountPrintParameter关联的数据
	*
	* @param dataCode ---> 设备code
	* @return 一组设备参数表的数据
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年8月15日 下午2:12:57 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月15日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmAccountPrintParam/paramList")
	@ResponseBody
	public String paramList(@RequestParam("dataCode") String dataCode) {
		logger.info("=============进入libraryParamList方法===============");
		Assert.notNull(dataCode, "dataCode must be not null");
		logger.info("dataCode=============="+dataCode);
		List<EdmAccountPrintParameter> accountPrintParameters = accountPrintParamService.getEdmAccountPrintParameter(dataCode);
		logger.info("=============退出libraryParamList方法===============");
		return JSON.toJSONString(accountPrintParameters);
	}
	/**
	 * 
	 * @Function: EdmAccountPrintController.java
	 * @Description: 根据code查询所有EdmPrincipal关联的数据
	 *
	 * @param dataCode ---> 设备code
	 * @return 一组设备参数表的数据
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: zhaol
	 * @date: 2019年9月19日上午10:56:57 
	 *
	 * Modification History:
	 * Date         Author          Version            Description
	 *---------------------------------------------------------*
	 * 2019年9月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmPrincipal/principaList")
	@ResponseBody
	public String principaList(@RequestParam("code") String dataCode) {
		logger.info("=============进入principaList方法===============");
		Assert.notNull(dataCode, "dataCode must be not null");
		logger.info("dataCode=========="+dataCode);
		List<EdmPrincipal> principals = principalService.getEdmpEdmPrincipals(dataCode);
		logger.info("=============退出principaList方法===============");
		System.out.println("1111111111111="+JSON.toJSONString(principals));
		return JSON.toJSONString(principals);
	}
}
