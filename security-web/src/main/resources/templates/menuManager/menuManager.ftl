<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>导航管理 </title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
   	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
	<script type="text/javascript" src="${globalContextPath}/js/menuManager/menuManager.js"></script>
	<link href="${globalContextPath}/css/menuManager/menuManager.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="mini-splitter" style="width:100%;height:100%;">
    <div size="240" showCollapseButton="true">
        <div class="mini-fit">
	        <ul id="tree" class="mini-tree" url="${globalContextPath}/action/menuTreeData?parentCode=${defaultSysMenu.menuCode}" style="width:100%;height:100%;padding:5px;" 
	             showTreeIcon="true" textField="text" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="false">        
	        </ul>
        </div>
    </div>
    <div showCollapseButton="true">
    	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">                
            <a class="mini-button mini-button-primary operControl" onclick="addRow()">授权</a>
            <a class="mini-button mini-button-primary operControl" onclick="removeRow()">取消授权</a>    
        </div>
        <div class="mini-fit" >
            <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" allowCellEdit="true" allowCellSelect="true" borderStyle="border:0;" 
            	 url="${globalContextPath}/action/menuManager/roleList" multiSelect="false" allowCellValid="true"  editNextRowCell="true">
                <div property="columns">
                	<div width="10" type="indexcolumn"></div>
	                    <div field="roleName" name="roleName" width="120" headerAlign="center" renderer="onRoleRenderer">角色名称                        
	                        <input property="editor" class="mini-text" style="width:100%;" required="true" />
	                    </div>
	                 	<div field="creationTime" sortField="creationTime" width="120" headerAlign="center" allowSort="true" dataType="int" visible="false" > 排序  </div>                
                </div>
            </div>  
        </div>
    </div>        
</div>
</body>
</html>