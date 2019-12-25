<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>组织机构管理 </title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
   	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
	<script type="text/javascript" src="${globalContextPath}/js/organization/organizationManager.js"></script>
	<link href="${globalContextPath}/css/organization/organizationManager.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="mini-splitter" style="width:100%;height:100%;">
    <div size="240" showCollapseButton="true">
        <div class="mini-fit">
        <ul id="tree" class="mini-tree" url="${globalContextPath}/action/organization/treeDataNoLazy" style="width:100%;height:100%;padding:5px;" 
             showTreeIcon="true" textField="text" idField="id" parentField="pid" resultAsTree="false" onNodeSelect="onNodeSelect" expandOnLoad="0">        
        </ul>
        </div>
    </div>
    <div showCollapseButton="true">
    	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">                
            <a class="mini-button mini-button-primary" onclick="addRow()">新增</a>
            <a class="mini-button mini-button-primary" onclick="removeRow()">删除</a>
            <a class="mini-button mini-button-primary"  onclick="moveUp()">上移</a>     
            <a class="mini-button mini-button-primary"  onclick="moveDown()">下移</a>
            <span class="separator"></span>             
            <a class="mini-button mini-button-primary" onclick="saveData()">保存</a>                  
        </div>
        <div class="mini-fit" >
            <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" cellEditAction="celldblclick" allowCellEdit="true" allowCellSelect="true" borderStyle="border:0;" 
            	 url="${globalContextPath}/action/organization/getOrgsByParentCode" multiSelect="false" allowCellValid="true" editNextRowCell="true">
                <div property="columns"> 
                	<div width="15" type="indexcolumn"></div>  
                    <div field="dataCode" name="dataCode" width="120" vtype="required" headerAlign="center" >编码                        
                        <input property="editor" class="mini-textbox" style="width:100%;" required="true"/>
                    </div>
                    <div field="name" name="name" width="120" vtype="required" headerAlign="center" >名称                        
                        <input property="editor" class="mini-textbox"  style="width:100%;"  required="true"/>
                    </div>                
                     <div field="sort" sortField="sort" width="120" headerAlign="center" allowSort="true" dataType="int" visible="false"> 排序                        
                     	<input property="editor" class="mini-textbox"  style="width:100%;"  required="true"/>
                    </div>                
                </div>
            </div>  
        </div>
    </div>        
</div>
</body>
</html>