<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>角色管理</title>
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
     <script src="${globalContextPath}/js/role/roleManager.js" type="text/javascript"></script>
    <link href="${globalContextPath}/css/role/roleManager.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div style="width:100%;height:100%;">
        <div class="mini-toolbar" style="padding:2px;">                
            <a class="mini-button mini-button-primary" onclick="addRow()">新增</a>
            <a class="mini-button mini-button-primary" onclick="removeRow()">删除</a> 
            <span class="separator"></span>             
            <a class="mini-button mini-button-primary" onclick="saveData()">保存</a>
            <input id="key" class="mini-textbox" emptyText="请输入角色名称或编号" style="width:200px;"/>   
            <a class="mini-button mini-button-primary" onclick="search()">查询</a>
        </div>
        <div class="mini-fit" >
            <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" cellEditAction="celldblclick" url="${globalContextPath}/action/roleManager/roleManagerList"
                 multiSelect="false" allowCellEdit="true" allowCellSelect="true" borderStyle="border:1;" editNextRowCell="true">
                <div property="columns">   
	                <div type="indexcolumn" width="20" ></div>           
	                    <div field="dataCode" name="dataCode" width="100" align="center" headerAlign="center" vtype="required">角色编号
	                    	<input property="editor" class="mini-textbox" style="width:100%;"/>
	                    </div>      
	                    <div field="name" name="name" width="100" align="center" headerAlign="center" vtype="required">角色名称
	                    	<input property="editor" class="mini-textbox" style="width:100%;"/>
	                     </div>                
	                    <div field="remark" name="remark" width="120" align="center" headerAlign="center" vtype="required">备注
	                    	<input property="editor" class="mini-textbox" style="width:100%;"/>
	                    </div>            
	                </div>
                </div>
            </div>  
        </div>
    </div>        
</body>
</html>