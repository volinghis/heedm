package com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
import com.alibaba.fastjson.JSONArray;
import com.ehs.edm.edmLibraryManager.edmAccountPrint.entity.EdmAccountPrint;
import com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.entity.EdmRepairAccountPrint;
import com.ehs.edm.edmLibraryManager.edmRepairAccountPrint.service.EdmRepairAccountPrintService;
import com.ehs.security.entity.BaseEntity;
import com.ehs.security.entity.DataDictionary;
import com.ehs.security.entity.FileInfo;
import com.ehs.security.entity.OrganizationInfo;
import com.ehs.security.entity.User;
import com.ehs.security.execute.ResultBean;
import com.ehs.security.execute.ResultType;
import com.ehs.security.flow.service.FlowBaseService;
import com.ehs.security.query.Pagenate;
import com.ehs.security.service.BaseCommonService;
import com.ehs.security.service.DataDictionarySearchService;
import com.ehs.security.service.OrganizationSearchService;
import com.ehs.security.service.UserService;
import com.ehs.security.utils.BaseUtils;
import com.ehs.security.utils.SysAccessUser;
import com.ehs.security.utils.TextNode;

@Controller
public class EdmRepairAccountPrintController {
	
	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private UserService userService;
	
	@Resource
	private FlowBaseService flowBaseService;
	
	@Resource
	private EdmRepairAccountPrintService edmRepairAccountPrintService;
	
	@Resource
	private DataDictionarySearchService dataDictionarySearchService;
	
	@Resource
	private OrganizationSearchService organizationService;
	
	/**
	 * 
	* @Function:edmRepairAccountPrint 
	* @Description: 检修台账页面跳转
	* @param mv
	* @return 返回模型视图
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月17日 上午10:51:41 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月17日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmRepairAccountPrint")
	public ModelAndView edmRepairAccountPrint(ModelAndView mv) {
		String nameString = EdmRepairAccountPrint.class.getTypeName();
		String encodedText = BaseUtils.encode(nameString);
		mv.addObject("className",encodedText);
		mv.setViewName("/edmLibraryManager/edmRepairAccountPrint/edmRepairAccountPrint");
		return mv;
	}
	
	/**
	 * 
	* @Function:editEdmRepairAccountPrintEdit 
	* @Description: 检修台账新增或编辑页面跳转
	* @param mv
	* @param request
	* @param response
	* @return 返回模型视图
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月17日 上午10:54:30 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月17日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value="/action/edmLibraryManager/edmRepairAccountPrintEdit")
	public  ModelAndView  editEdmRepairAccountPrintEdit(ModelAndView mv,HttpServletRequest request,HttpServletResponse response) {
		String devCode=request.getParameter("devCode");
		String edmRepairAccountPrintId = request.getParameter("edmRepairAccountPrintId");
		String entityCode=request.getParameter("entityCode");
		String parentNode=request.getParameter("nodeCode");
		System.out.println("=================================:"+parentNode);
	    EdmAccountPrint ed=(EdmAccountPrint) baseCommonService.findByCode(EdmAccountPrint.class, devCode);
		EdmRepairAccountPrint dd=null;
		if(StringUtils.isNotBlank(edmRepairAccountPrintId)) {
			dd = (EdmRepairAccountPrint) baseCommonService.findById(EdmRepairAccountPrint.class, edmRepairAccountPrintId);
			if(dd==null) {
				mv.setViewName(ResultBean.NO_DATA_RESPONSE_URL);
				return mv;
			}
		}else {
			if(StringUtils.isNotBlank(entityCode)) {
				dd = (EdmRepairAccountPrint) baseCommonService.findByCode(EdmRepairAccountPrint.class, entityCode);
				
				if(dd==null) {
					mv.setViewName(ResultBean.NO_DATA_RESPONSE_URL);
					return mv;
				}
			}else {
				dd=new EdmRepairAccountPrint();
				if(ed!=null) {//
					dd.setProfession(ed.getInstallAddressFullName());
				}
				dd.setDeviceName(ed.getDeviceName());
				dd.setDeviceCode(devCode);
				dd.setNodeCode(parentNode);
				dd.setFounder(SysAccessUser.get().getName());
				dd.setRepairName(ed.getDeviceName()+new SimpleDateFormat("yyyyMMddHHmm").format(BaseUtils.getNow())+"检修");
			}
		}
		mv.addObject("objectBean", dd);
		mv.setViewName("/edmLibraryManager/edmRepairAccountPrint/edmRepairAccountPrintEdit");
		return mv;
	}
	
	/**
	 * 
	* @Function:saveEdmRepairAccountPrint 
	* @Description: 检修台账新增</br>
	* 				filesJson:json格式，页面传入的附件信息
	* @param edmRepairAccountPrint 检修台账实例
	* @param response
	* @param request
	* @return 返回json格式 操作结果
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月17日 上午10:55:32 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月17日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmRepairAccountPrintSave")
	@ResponseBody
	public String saveEdmRepairAccountPrint(@ModelAttribute EdmRepairAccountPrint edmRepairAccountPrint,HttpServletResponse response,HttpServletRequest request) {
		String filesJson=request.getParameter("filesJson");
		try {
			if(StringUtils.isNotBlank(edmRepairAccountPrint.getDutyPersonCode())) {
				User curUser=userService.findUser(edmRepairAccountPrint.getDutyPersonCode());
				if(curUser!=null) {
					edmRepairAccountPrint.setDutyPersonName(curUser.getName());
				}
			}
//			if(StringUtils.isNotBlank(edmRepairAccountPrint.getTesterCode())) {
//				User curtester=userService.findUser(edmRepairAccountPrint.getDutyPersonCode());
//				if(curtester!=null) {
//					edmRepairAccountPrint.setTester(curtester.getName());
//				}
//			}
			if(StringUtils.isBlank(edmRepairAccountPrint.getId())){
				OrganizationInfo org=organizationService.findByCode(edmRepairAccountPrint.getRepairTeamCode());
				if(org!=null) {
					edmRepairAccountPrint.setRepairTeam(org.getName());
				}
			}
			if(StringUtils.isNotBlank(edmRepairAccountPrint.getRepairNatureCode())) {
				String systemCode=request.getContextPath();
				systemCode=StringUtils.replace(systemCode, "/", "");
				String repairNature=dataDictionarySearchService.findOne(edmRepairAccountPrint.getRepairNatureCode()).getText();
				edmRepairAccountPrint.setRepairNatureName(repairNature);
			}
			BaseEntity base=edmRepairAccountPrintService.saveOrUpdate(edmRepairAccountPrint, JSON.parseArray(filesJson, FileInfo.class));
			return JSON.toJSONString(ResultBean.getBean("操作成功！",ResultType.OK,base.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("操作失败！",ResultType.ERROR,null));
		}
	}
	
	/**
	 * 
	* @Function:edmRepairAccountPrintList 
	* @Description:检修台账数据列表
	* 			<ul>
	* 			   <li>export：布尔类型，导出excel为true,否则false</li>		
	* 			   <li>devCode:检修设备code</li>	
	* 			   <li>dutyPersonCode:责任人code</li>	
	* 			   <li>repairNatureCode:检修性质（A级，B级，C级，抢修，临修，日常检修维护）</li>	
	*  			   <li>repairName：检修名称</li>	
	*  			</ul>
	* @param request
	* @param response
	* @return 返回检修台账数据集合
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月17日 上午10:56:07 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月17日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmRepairAccountPrintList")
	@ResponseBody
	public String edmRepairAccountPrintList( HttpServletRequest request, HttpServletResponse response) {
        Pagenate pageNate=new Pagenate();
        boolean export=Boolean.valueOf(request.getParameter("export"));
        String devCode=request.getParameter("devCode");
        String dutyPersonCode=request.getParameter("dutyPersonCode");
        String repairNatureCode=request.getParameter("repairNatureCode");
        String repairName=request.getParameter("repairName");
        List<DataDictionary> dataList=  dataDictionarySearchService.findAllDataDictionary();
		List<String> treelist=new ArrayList<String>();
		createMenuNode(treelist, dataList, devCode);
		List<Predicate> ps=new ArrayList<Predicate>();
        Specification<EdmRepairAccountPrint> sf=null;
        
        sf=(Root<EdmRepairAccountPrint> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)->{
    	   if(StringUtils.isNotBlank(devCode)) {
    		   EdmAccountPrint   edmAccount= (EdmAccountPrint) baseCommonService.findByCode(EdmAccountPrint.class, devCode);
    		   CriteriaBuilder.In<String> in=null;
    		   if(edmAccount!=null) {
    			   in= criteriaBuilder.in(root.get(EdmRepairAccountPrint.DEVICE_CODE));
    		   }else {
    			   in=criteriaBuilder.in(root.get(EdmRepairAccountPrint.NODE_CODE));
    			   if (treelist.size() > 0) {
    				   for (int j = 0; j < treelist.size(); j++) {
    					   in.value(treelist.get(j));
    				   }
    			   }
    		   }
    		   in.value(devCode);
    		   ps.add(criteriaBuilder.and(in));//存入条件集合里
    	   }
        	if(StringUtils.isNotBlank(dutyPersonCode)) {
        		ps.add(criteriaBuilder.equal(root.get(EdmRepairAccountPrint.DUTY_PERSON_CODE), dutyPersonCode));
        	}
        	if(StringUtils.isNotBlank(repairNatureCode)) {
        		ps.add(criteriaBuilder.equal(root.get(EdmRepairAccountPrint.REPAIR_NATURE_CODE), repairNatureCode));
        	}
        	if(StringUtils.isNotBlank(repairName)) {
        		ps.add(criteriaBuilder.like(root.get(EdmRepairAccountPrint.REPAIR_NAME), "%"+repairName+"%"));
        	}
//        	ps.add(criteriaBuilder.isNotNull(root.get(EdmRepairAccountPrint.FLOW_STATUS)));
        	return criteriaBuilder.and(ps.toArray(new Predicate[0]));
        };
    	pageNate.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
    	pageNate.setStartNum(Integer.parseInt(request.getParameter("pageIndex")));
        //动态获取枚举值
        List<EdmRepairAccountPrint> tempList= baseCommonService.findPagenate(EdmRepairAccountPrint.class, sf, pageNate).getData();
        tempList.stream().forEach(e->{
        	e.setFlowStatusName(dataDictionarySearchService.findOne(e.getFlowStatus()).getText());
        });
        pageNate.setData(tempList);
		return JSON.toJSONString(pageNate);
	}
	
	private void createMenuNode(List<String> list,List<DataDictionary> dataList,String parentCode) {
		dataList.stream().filter(s->StringUtils.equals(s.getParentCode(),parentCode)).forEach(c->{
			list.add(c.getCode());
			createMenuNode(list,dataList,c.getCode());
		});
	}
	
	/**
	 * 
	* @Function:removeEdmRepairAccountPrint 
	* @Description: 移除检修台账<br>
	* 				edmRepairAccountPrintId：String类型，检修台账唯一标识
	* @param request
	* @return 返回json格式操作结果
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月17日 上午10:56:46 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月17日     qjj           v1.0.0               修改原因
	 */
	@RequestMapping(value = "/action/edmLibraryManager/edmRepairAccountPrintRemove")
	@ResponseBody
	public String removeEdmRepairAccountPrint(HttpServletRequest request) {
		String edmRepairAccountPrintId = request.getParameter("edmRepairAccountPrintId");
		try {
			Assert.notNull(edmRepairAccountPrintId, " edmRepairAccountPrintId must be not null ");
			EdmRepairAccountPrint ep=(EdmRepairAccountPrint)baseCommonService.findById(EdmRepairAccountPrint.class, edmRepairAccountPrintId);
			if(ep==null) {
				return JSON.toJSONString(ResultBean.getBean(ResultBean.NO_DATA,ResultType.ERROR,null));
			}
			baseCommonService.deleteById(EdmRepairAccountPrint.class, ep.getId());
		}catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(ResultBean.getBean("删除失败！",ResultType.ERROR,null));
		}
		return JSON.toJSONString(ResultBean.getBean("删除成功！",ResultType.OK,null));
	} 
	
	
	/**
	 * 
	* @Function:edmRepairAccountPrint 
	* @Description: @ModelAttribute 数据绑定
	* @param request
	* @return  返回检修台账实例
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年7月17日 上午10:57:07 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月17日     qjj           v1.0.0               修改原因
	 */
	@ModelAttribute
	public EdmRepairAccountPrint edmRepairAccountPrint(HttpServletRequest request) {
		String id=request.getParameter("edmRepairAccountPrintId");
		if(StringUtils.isBlank(id)) {
			return new EdmRepairAccountPrint();
		}else {
			return (EdmRepairAccountPrint)baseCommonService.findById(EdmRepairAccountPrint.class, id);
		}
	}
	
}

