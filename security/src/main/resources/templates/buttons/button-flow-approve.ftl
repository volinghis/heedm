<script src="${globalContextPath}/js/flow/base/base.js" type="text/javascript"></script>	
<input class="mini-hidden" id="processInstanceId" value="${processInstanceId}"/>
<a class="mini-button mini-button-primary dotracing"  style="width:90px;margin-left: 10px;float:right">流程跟踪</a>
<a class="mini-button mini-button-primary doreject"  style="width:90px;margin-left: 10px;float:right">驳回申请</a>
<a class="mini-button mini-button-primary donextstep"  style="width:90px;margin-left: 10px;float:right">下一环节</a>
<input id="flowApprovePersonList"  name="flowApprovePersonList" emptyText="审批人"   style="margin-left: 20px;float:right" allowInput="false"  
	style="float:right;margin-left: 20px;"  required="true" class="mini-treeselect" url="${globalContextPath}/action/organization/treeDataForUser" multiSelect="false"  
	textField="name" valueField="id" parentField="pid"  showRadioButton="true" showFolderCheckBox="false" popupWidth="260" onbeforenodeselect="approvePersonbeforenodeselect" />
	 