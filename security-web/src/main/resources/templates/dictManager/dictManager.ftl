<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数据字典</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
   	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
	<script type="text/javascript" src="${globalContextPath}/js/dictManager/dictManager.js"></script>
	<link href="${globalContextPath}/css/dictManager/dictManager.css" rel="stylesheet"  type="text/css" />
</head>
<body>
<div class="mini-splitter" style="width:100%;height:100%;">
    <div size="240" showCollapseButton="true">
        <div class="mini-fit">
        <ul id="tree1" class="mini-tree" url="${globalContextPath}/action/dataTreeDataNotLazy?parentCode=dataDict" style="width:100%;height:100%;padding:5px;" 
             showTreeIcon="true" textField="text" onnodeselect="onNodeSelect"  resultAsTree="false"  idField="id" parentField="pid" expandOnNodeClick="true" showTreeLines="false"  expandOnLoad="0">        
        </ul>
        </div>
    </div>
    <div showCollapseButton="true">
    	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">                
            <a class="mini-button mini-button-primary"  onclick="addChildRow()">新增</a>
            <a class="mini-button mini-button-primary"  onclick="removeRow()">删除</a>
            <span class="separator"></span>             
            <a class="mini-button mini-button-primary"  onclick="saveData()">保存</a> 
            <!--<input id="key" class="mini-textbox" emptyText="请输入字典名称" style="width:200px;"/>   
            <a class="mini-button mini-button-primary" onclick="search()">查询</a>-->                 
        </div>
        <div class="mini-fit" >
            <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" cellEditAction="celldblclick" allowCellEdit="true" allowCellSelect="true" borderStyle="border:0;" 
            	 url="${globalContextPath}/action/dictManager/getItemsByParentCode" multiSelect="false" allowCellValid="true"  editNextRowCell="true">
                <div property="columns">
			        <div type="checkcolumn" width="10"></div>
			        <div field="text" name="text" vtype="required"  width="120" headerAlign="center" >字典名称
			        	<input property="editor" class="mini-textbox" style="width:100%;" />
			        </div>
			        <div field="dataCode" vtype="required" width="100" headerAlign="center">字典编码
			        	<input property="editor" class="mini-textbox" style="width:100%;" />
			        </div>
			        <div field="sort" vtype="required" width="100" headerAlign="center">排序
			        	<input property="editor" class="mini-textbox" style="width:100%;" />
			        </div>
            	</div>  
        	</div>
    	</div>        
	</div>
</div>
</body>
</html>