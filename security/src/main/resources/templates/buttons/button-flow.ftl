<script src="${globalContextPath}/js/flow/base/base.js" type="text/javascript"></script>	
<input class="mini-hidden" id="processInstanceId" value="${processInstanceId}"/>
<#if (stopProcess?string('yes', 'no'))=='yes'>
<!--暂未实现
<a class="mini-button dotracing"  style="width:90px;margin-left: 10px;float:right">撤回</a>
-->

</#if>

<#if (tracProcess?string('yes', 'no'))=='yes'>
<a class="mini-button mini-button-primary dotracing"  style="width:90px;margin-left: 10px;float:right">流程跟踪</a>
</#if>
<#if (rejectProcess?string('yes', 'no'))=='yes'>
<a class="mini-button mini-button-primary doreject"  style="width:90px;margin-left: 10px;float:right">驳回申请</a>
</#if>
<#if (endProcess?string('yes', 'no'))=='yes'>
<a class="mini-button mini-button-primary doend"  style="width:90px;margin-left: 10px;float:right">审批通过</a>
</#if>

<#if (startProcess?string('yes', 'no'))=='yes'>
<a class="mini-button mini-button-primary dosave"  style="width:90px;margin-left: 10px;float:right">提交审批</a>
	<input id="flowOper"  name="flowOper" emptyText="审批人"   allowInput="false"  style="float:right;margin-left: 10px;"  required="true" 
     class="mini-treeselect" url="${globalContextPath}/action/organization/treeDataForUser" multiSelect="false" textField="name" valueField="id" 
     parentField="pid" showRadioButton="true" showFolderCheckBox="false" popupWidth="260" onbeforenodeselect="approvePersonbeforenodeselect" />
</#if>
<#if (nextStep?string('yes', 'no'))=='yes'>
<a class="mini-button mini-button-primary donextstep"  style="width:90px;margin-left: 10px;float:right">下一环节</a>
	<input id="flowOper"  name="flowOper" emptyText="审批人" style="margin-left: 20px;float:right" allowInput="false"  style="float:right;margin-left: 20px;"  
		required="true" class="mini-treeselect" url="${globalContextPath}/action/organization/treeDataForUser" multiSelect="false"  
		textField="name" valueField="id" parentField="pid" showRadioButton="true" showFolderCheckBox="false" popupWidth="260" onbeforenodeselect="approvePersonbeforenodeselect" />
</#if>
