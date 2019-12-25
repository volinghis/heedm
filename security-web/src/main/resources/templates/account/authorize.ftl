<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>账号授权管理</title>
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
     <script src="${globalContextPath}/js/account/authorize.js" type="text/javascript"></script>
    <link href="${globalContextPath}/css/account/authorize.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div style="width:100%;height:100%;margin-left:0px">
        <div class="mini-toolbar" style="padding:2px;">
            <label> 账号： </label><input class="mini-textbox" id="curAccount" name="curAccount" value="${curAccount!''}" readOnly="true" >&nbsp;&nbsp;
            <label> 姓名： </label><input class="mini-textbox" id="curName" name="curName" value="${curName!''}" readOnly="true">
            <span class="separator"></span> 
    		<a class="mini-button mini-button-primary" iconCls="icon-addnew"  onclick="addRoleForAccount()">添加</a>
        </div>
        <div class="mini-fit" >
            <div id="authGrid" class="mini-datagrid" style="width:100%;height:100%;" url="${globalContextPath}/action/accountManager/authorizeList"
                 multiSelect="false" allowCellEdit="true" allowCellSelect="true" borderStyle="border:1;" editNextRowCell="true">
                <div property="columns">   
	                <div type="indexcolumn" width="20" ></div>           
	                <div field="roleCode"  width="100" align="center" headerAlign="center">角色编码 </div>      
	                <div field="roleName"   width="100" align="center" headerAlign="center">角色名称 </div>                
	                <div field="action"   width="100" align="center" headerAlign="center" renderer="onActionRenderer"> 操作 </div>                
	            </div>    
            </div>  
        </div>
    </div>        
</body>
</html>