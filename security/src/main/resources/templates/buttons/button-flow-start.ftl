<a class="mini-button mini-button-primary dosave"  style="width:90px;margin-left: 10px;float:right">提交审批</a>
<input id="flowCurrentPerson"  name="flowCurrentPerson" emptyText="审批人"   allowInput="false"  style="float:right;margin-left: 10px;"  
	required="true" class="mini-treeselect" url="${globalContextPath}/action/organization/treeDataForUser" multiSelect="false"  
	 textField="name" valueField="id" parentField="pid" showRadioButton="true" showFolderCheckBox="false" popupWidth="260" onbeforenodeselect="approvePersonbeforenodeselect" />