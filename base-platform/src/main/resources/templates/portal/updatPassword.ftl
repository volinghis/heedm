<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>密码管理页面</title> 
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
  	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&amp;domain=${domain}" type="text/javascript"></script> 
  	<script src="${globalContextPath}/js/portal/updatPassword.js" type="text/javascript"></script> 
</head> 
<body style ="overflow:hidden">
 	<div>
		<fieldset style="width:500px;border:solid 1px #aaa;margin:auto;text-align: center; margin-top: 30px;">
			<legend>帐号密码信息</legend>	
			<div style="width:100%;height:100%;">
		 		<form id="form1">
				 	<input name="userId" id="userId" class="mini-hidden" value="${sysUser.id!''}" />
		 			<lable style="width:30px;">帐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</lable>
		 			<input id="account" name="account" class="mini-textbox" value="${sysUser.account!''}" readyOnly="readyOnly"/><p/>
		 			<lable>当前密码：</lable>
		 			<input id="oldPassword" name ="oldPassword" class="mini-textbox"  emptyText="请填写旧密码" required="true"/><p/>
		 			<lable>新&nbsp;&nbsp;密&nbsp;&nbsp;码：</lable>
		 			<input id="newPassword" name ="newPassword"  class="mini-textbox" required="true" emptyText="请填写新密码"/><p/>
		 			<lable>确认密码：</lable>
		 			<input id="confirmNewPassword" name ="confirmNewPassword"  class="mini-textbox" required="true" emptyText="请确认新密码"/><p/>
		 		</form>
			</div>
	 	</fieleset>
 	</div>
 	</p>
 	<div>
		<a class="mini-button mini-button-primary dosave" style="width:90px;margin-left:460px">更改密码</a>       
 	</div>
</body>
</html>