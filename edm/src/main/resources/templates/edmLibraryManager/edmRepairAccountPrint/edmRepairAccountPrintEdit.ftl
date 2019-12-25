<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	  <title>检修台账编辑页面</title> 
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
	  <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&amp;domain=${domain}" type="text/javascript"></script> 
	  <script src="${globalContextPath}/js/edmLibraryManager/edmRepairAccountPrint/edmRepairAccountPrintEdit.js" type="text/javascript"></script> 
	  <!--<script src="${globalContextPath}/js/plugin/ckeditor/ckeditor.js" type="text/javascript"></script>-->
	  <script src="${globalContextPath}/res/third-party/webuploader/webuploader.min.js" type="text/javascript"></script>
	  <script src="${globalContextPath}/js/edmLibraryManager/edmRepairAccountPrint/edmImgUpload.js" type="text/javascript"></script>
	  <script src="${globalContextPath}/res/js/fileUpload.js" type="text/javascript"></script>
	  <link href="${globalContextPath}/res/third-party/webuploader/webuploader.css" rel="stylesheet"  type="text/css" />
	  <link href="${globalContextPath}/css/edmLibraryManager/edmRepairAccountPrint/edmRepairAccountPrintEdit.css" rel="stylesheet" type="text/css" /> 
 </head> 
 <body> 
 	<form id="edmRepairAccountPrintForm"> 
   		<div id="form1" bodyStyle="width:100%;height:100%;"> 
   	<!--  <div id="form1" class="mini-tabs" activeIndex="0" plain="false"  bodyStyle="width:100%;height:100%;"> 
   		  <div title="检修记事"  name="repairTab">
             	 <div id="picker" required="true"><a class="mini-button mini-button-primary" id="beginBtn">添加</a></div>
             	 <p><span>*&nbsp;</span>文件格式要求为*.doc,*.docx,*.pdf,*.txt,*.jpg,*png</p>
				 <embed id="myObj" src="${globalContextPath}/action/viewFile?fileId=${objectBean.fileId!''}" type="application/pdf" width="100%" height="660px">
             	 <a id="downLoad" href="${globalContextPath}/action/downloadFile?fileId=${objectBean.fileId!''}">下载原文件</a>
	      </div>   -->
   		  <div title="检修台账 ">
	   		<input name="edmRepairAccountPrintId" id="edmRepairAccountPrintId" class="mini-hidden" value="${objectBean.id!''}" />
		   	<input name="filesJson" id="filesJson" class="mini-hidden" />
		   	<input id="deviceCode" name="deviceCode" value="${objectBean.deviceCode!''}" class="mini-hidden" />
		   	<input id="deviceName" name="deviceName" value="${objectBean.deviceName!''}" class="mini-hidden" />
		   	<input id="nodeCode" name="nodeCode" value="${objectBean.nodeCode!''}" class="mini-hidden" />
		   	<input name="repairContent" id="repairContent" class="mini-hidden" />
		   	<input name="fileId" id="fileId" value="${objectBean.fileId!''}" class="mini-hidden"/>
	    	<table> 
	     		<tbody>
			      	<tr> 
			       		<td> <label for="repairName$text">检修名称：</label></td> 
			       		<td> <input id="repairName" name="repairName" value="${objectBean.repairName!''}" class="mini-textbox" maxlength="100" style="width:100%;" required="true"/></td> 
			       		<td> <label for="dutyPersonCode$text">工作负责人：</label></td> 
			       		<td> <input id="dutyPersonCode" name="dutyPersonCode" allowinput="false" style="width: 100%;" required="true" class="mini-treeselect" url="${globalContextPath}/action/organization/treeDataForUser" multiselect="false" textfield="name" valuefield="id" parentfield="pid" value="${objectBean.dutyPersonCode!''}" text="${objectBean.dutyPersonName!''}" showradiobutton="true" showfoldercheckbox="false" emptyText="---请选择---"  popupWidth="250" onbeforenodeselect="beforenodeselect" /> </td> 
			      	</tr> 
			      	<tr>
			       		<td> <label for="founder$text">创建人：</label></td> 
			       		<td> <input id="founder" name="founder" value="${objectBean.founder!''}" class="mini-textbox" allowinput="false" readOnly="true" style="width: 100%;" /></td> 
			       		<td> <label for="creationTime$text">创建时间：</label></td> 
			       		<td> <input id="creationTime" name="creationTime" value="${objectBean.creationTime!'new Date()'}" class="mini-datepicker" format="yyyy-MM-dd" allowinput="false" style="width: 100%;" readOnly="true" /></td> 
			      	</tr> 
		      		<tr>
			       		<td> <label for="repairStartTime$text">开始时间：</label></td> 
			       		<td> <input id="repairStartTime" name="repairStartTime" value="${objectBean.repairStartTime!''}"  required="true" class="mini-datepicker" format="yyyy-MM-dd" allowinput="false" style="width: 100%;" /></td> 
			       		<td> <label for="repairEndTime$text">结束时间：</label></td> 
			       		<td> <input id="repairEndTime" name="repairEndTime" value="${objectBean.repairEndTime!''}"  required="true" class="mini-datepicker" format="yyyy-MM-dd" allowinput="false" style="width: 100%;" /></td> 
			      	</tr> 
			      	<tr>
			       		<td> <label for="repairTeamCode$text">检修班组：</label></td> 
			       		<td> <input id="repairTeamCode" name="repairTeamCode" value="${objectBean.repairTeamCode!''}" allowinput="false" style="width: 100%;" required="true" class="mini-treeselect" url="${globalContextPath}/action/organization/treeData" multiselect="false" textfield="name" valuefield="id" parentfield="pid" text="${objectBean.repairTeam!''}" showradiobutton="true" showfoldercheckbox="false" emptyText="---请选择---" popupWidth="250"  onbeforenodeselect="beforenodeselect"/></td> 
			       		<td> <label for="repairNatureCode$text">检修性质：</label></td> 
			       		<td> <input id="repairNatureCode" name="repairNatureCode"   allowinput="false" style="width: 100%;" required="true" class="mini-treeselect" url="${globalContextPath}/action/dataDictionaryData?parentCode=edmRepairNature" multiselect="false" textfield="text" valuefield="id" parentfield="pid" text="${objectBean.repairNatureName!''}" showradiobutton="true" showTreeIcon="false" showTreeLines="false"  emptyText="---请选择---"   onbeforenodeselect="beforenodeselect"/></td> 
			      	</tr> 
			      	<tr>
			       		<td> <label for="profession$text">专业：</label></td> 
			       		<td> <input id="profession" name="profession" style="width: 100%;" value="${objectBean.profession!''}" class="mini-textbox" allowinput="false" readOnly="readOnly" /></td> 
			       		<td> <label for="testerCode$text">试验人员：</label></td> 
			       	<!--	<td> <input id="testerCode" name="testerCode" allowInput="true" style="width: 100%;" required="true" class="mini-treeselect" url="${globalContextPath}/action/organization/treeDataForUser" multiselect="false" textfield="name" valuefield="id" parentfield="pid" value="${objectBean.testerCode!''}" text="${objectBean.tester!''}" showradiobutton="true" showfoldercheckbox="false" emptyText="---请选择---"   popupWidth="250"  onbeforenodeselect="beforenodeselect" /> </td> -->
			       		<td> <input id="tester" name="tester" style="width: 100%;" required="true" class="mini-textbox" value="${objectBean.tester!''}" /> </td> 
			      	</tr> 
			      	<tr> 
			       		<td> <label for="groupMember$text">工作组成员：</label></td> 
			       	<!--	<td colspan="3"> <input id="groupMember" name="groupMember" allowInput="true" allowinput="false" style="width: 100%;" class="mini-treeselect" url="${globalContextPath}/action/organization/treeDataForUser" multiselect="true" textfield="name" valuefield="id" parentfield="pid" value="${objectBean.groupMember!''}" showradiobutton="true" showfoldercheckbox="false" emptyText="---请选择---"       onbeforenodeselect="beforenodeselect" /> </td> -->
			       		<td colspan="3"> <input id="groupMember" name="groupMember" style="width: 100%;" class="mini-textbox" value="${objectBean.groupMember!''}"/> </td> 
			      	</tr>
			      	<tr> 
			       		<td> <label for="beforeSatuation$text">修前状况：</label></td> 
			       		<td colspan="3" height="50"> <input id="beforeSatuation" name="beforeSatuation" value="${objectBean.beforeSatuation!''}" class="mini-textarea" maxlength="1000" style="width: 100%;height: 100%;" /></td> 
			      	</tr>
			       <!-- <tr> 
			       		 <td> <label for="repairContent$text">检修记事：</label></td> 
	      				 <td colspan="3" height="100">
	      				 <textarea id="editor" name="editor"  rows="10" cols="80"></textarea></td>
			      	</tr>-->
			       <tr> 
			       		 <td> <label for="repairContent$text">检修记事栏：</label></td> 
	      				 <td colspan="3" height="100" style="border:2px solid #ddd">
	      				 	<!--表中表-->
	      				 	<div id="innerTab" class="innerTab">
								<span>一.解体检查情况：</span>
							    <input name="testResults" class="mini-textarea" value="${objectBean.testResults!''}" style="width:100%;"/>
							    <span>二.检修内容：</span>	
							    <input name="repairContent" class="mini-textarea" value="${objectBean.repairContent!''}" style="width:100%;"/>
							    <span>三.更换备品备件及原因分析：</span>
							    <input name="causeAnalysis" class="mini-textarea" value="${objectBean.causeAnalysis!''}"  style="width:100%;"/>
							    <span>四.修后试验：</span>
							    <input name="repairTest" class="mini-textarea" value="${objectBean.repairTest!''}" style="width:100%;"/>
							    <span>五.试运情况：</span>
							    <input name="trialSituation" class="mini-textarea" value="${objectBean.trialSituation!''}" style="width:100%;"/>
							    <span>六.检修建议及改进内容：</span> 
							    <input name="remark" class="mini-textarea" value="${objectBean.remark!''}" style="width:100%;"/>
           					 </div>
	      				 </td>
			      	</tr>
			      	<tr> 
			       		<td> <label for="repairResult$text">遗留缺陷/隐患：</label></td> 
			       		<td colspan="3" height="50"> <input id="repairResult" name="repairResult" value="${objectBean.repairResult!''}" class="mini-textarea" maxlength="1000" style="width: 100%;height: 100%;" /></td> 
			      	</tr>
			      	<tr> 
			       		<td> <label for="equipmentRating$text">设备评级：</label></td> 
			       		<td colspan="3"> <input id="equipmentRating" name="equipmentRating"   allowinput="false" style="width: 100%;" required="true" class="mini-treeselect" url="${globalContextPath}/action/dataDictionaryData?parentCode=quipmentRating" multiselect="false" textfield="text" valuefield="id" parentfield="pid" text="${objectBean.equipmentRating!''}" showradiobutton="true" showTreeIcon="false" showTreeLines="false"  emptyText="---请选择---"   onbeforenodeselect="beforenodeselect"/></td>
			      	</tr>
	      			<tr>
						<td><label>附件：</label></td>
						<td colspan="3" height="200">
							<div style="height: 100%">
								<div class="mini-datagrid" id="uploadGrid" onload="loadResult" url="${globalContextPath}/action/fileLists?dataCode=${objectBean.code!''}"
									style="width: 100%; height: 100%" showpager="false" emptyText="尚未上传" alwaysShowEmptyText="false"
									showEmptyText="false" showheader="false" showtoolbar="true" title="<font style='font-weight:bold'>文件上传</font>">
										<div property="toolbar" style="height: 30px; line-height: 20px">
											<a class="mini-button mini-button-primary" id="beginBtn">选择文件上传</a>
											<div class="uploadPlaceholder" id="uploadPlaceholder"></div>
										</div>
										<div property="columns">
											<div field="name" headerAlign="center" align="center" width="150">文件名</div>
											<div field="type" headerAlign="center" align="center" width="50">类型</div>
											<div field="fileSize" headerAlign="center" align="center" width="60">大小</div>
										    <div field="status" headerAlign="center" align="center" width="60">状态</div>
											<div field="creationTime" name="creationTime" headerAlign="center" align="center" width="100" dateFormat="yyyy-MM-dd">时间</div>
											<div field="creationName" name="creationName"headerAlign="center" align="center" width="80">用户</div>
											<div field="action" headerAlign="center" align="center" width="100">操作</div>
										</div>
									</div>
								</div>
							</td>
		  				</tr> 
	      				<tr> 
	       					<td colspan="4">
	       						<a class="button-element" type="flow" processInstanceId="${objectBean.flowProcessInstanceId!''}"></a>
	       						<a class="mini-button mini-button-primary exportWord"  style="width:60px;margin-left:20px;">导出</a> </td>
	       					</td>
							<!-- <td>&nbsp;</td>
							<td colspan="3" style="text-align: center;">   
					 			<a class="mini-button dosave"  style="width:60px;">确定</a>       
	            				<a class="mini-button docancel"  style="width:60px;margin-left: 20px;">返回</a>     
	            			</td>
	         				-->
	      				</tr> 
	     			</tbody>
	    		</table> 
    	 </div>
   		</div> 
  	</form>  
</body>
</html>