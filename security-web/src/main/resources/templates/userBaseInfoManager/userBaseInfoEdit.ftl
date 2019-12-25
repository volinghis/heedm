<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户编辑</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
    <script src="${globalContextPath}/js/userBaseInfoManager/userBaseInfoEdit.js" type="text/javascript"></script>
    <link href="${globalContextPath}/css/userBaseInfoManager/userBaseInfoEdit.css" rel="stylesheet" type="text/css" />
</head>
<body> 
	<form id="userBaseInfo"> 
		<div id="form1" bodyStyle="width:100%;height:100%;"> 
	    	<input name="userBaseInfoId" id="userBaseInfoId" class="mini-hidden" value="${objectBean.id!''}" /> 
	    	<input name="orgCode" id="orgCode" class="mini-hidden" value="${org.code!''}" /> 
	    	<table> 
	     		<tbody>
	      			<tr> 
				       <td> <label for="dataCode$text">用户账号：</label> </td> 
				       <td> <input id="dataCode" name="dataCode" value="${objectBean.dataCode!''}" class="mini-textbox" maxlength="100" style="width: 100%;" required="true" onvalidation="onUserCodeValidation" /> </td> 
				       <td> <label for="name$text">用户姓名：</label> </td> 
				       <td> <input id="name" name="name" value="${objectBean.name!''}" class="mini-textbox" maxlength="100" style="width: 100%;" required="true" /> </td> 
	      			</tr> 
	      			<tr> 
	      				<td> <label for="email$text">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱： </label> </td> 
				        <td> <input id="email" name="email" value="${objectBean.email!''}" class="mini-textbox" maxlength="100" style="width: 100%;" required="true" vtype="email" requiredErrorText="邮箱不能为空" /> </td> 
				        <td> <label for="orgName$text">所属组织：</label> </td>
				        <td> <input id="orgName" name="orgName" value="${org.name!''}"  class="mini-textbox" maxlength="100" style="width: 100%;" required="true" readOnly="true" /></td> 
			      	</tr> 
			      	<tr> 
			       		<td> <label for="telephone$text">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</label> </td> 
			       		<td> <input id="telephone" name="telephone" class="mini-textbox" value="${objectBean.telephone!''}" maxlength="11" style="width: 100%;"  required="true"  vtype="float" /> </td> 
			       		<td> <label for="position$text">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：</label> </td> 
			       		<td> 
	   					 	<input id="position" name="position" allowInput="false" style="width: 100%;" class="mini-treeselect" 
	               			url="${globalContextPath}/action/dataDictionaryData?parentCode=position" multiSelect="false" 
	               			showTreeIcon="false" showTreeLines="false"  emptyText="---请选择---"  required="true" value="${objectBean.position!''}" 
	   					 	textField="text" valueField="id" parentField="pid" showRadioButton="true" showFolderCheckBox="false"/>
		       			</td> 
			      	</tr> 
			      	<tr> 
			       		<td> <label for="telephone$text">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label> </td> 
			       		<td colspan="3" height="30">
			       			<input id="remark" name="remark" class="mini-textbox" value="${objectBean.remark!''}" maxlength="100" style="width: 100%;"/> 
		       			</td> 
			      	</tr> 
			      	</br>
	 			</tbody>
			</table> 
   		</div> 
   		<br>
   		<div style="text-align: center;">
   			 <a class="mini-button mini-button-primary  dosave" style="width:60px;">确定</a> 
   			 <a class="mini-button mini-button-primary  docancel" style="width:60px;margin-left: 20px;">返回</a>
   		</div>
	</form>  
</body>
</html>