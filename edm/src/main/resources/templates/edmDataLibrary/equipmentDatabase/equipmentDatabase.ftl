<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>备件管理</title>
	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script> 
	<script type="text/javascript" src="${globalContextPath}/js/edmDataLibrary/equipmentDatabase/equipmentDatabase.js"></script>
	<link href="${globalContextPath}/css/edmDataLibrary/edmDataLibrary.css" rel="stylesheet" type="text/css" />
	<input type="hidden" id="globalContextPath" value="${globalContextPath}" />
</head>
	<body>
		<div class="mini-splitter" style="width:100%;height:100%;">
		    <div size="260" showCollapseButton="true">
		        <div class="mini-fit">
					<ul id="tree" class="mini-tree" url="${globalContextPath}/action/edmLibraryManager/edmAccountPrintTreeData" style="width:240px;height:100%;padding:5px;" 
			    		showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false">        
					</ul>
		        </div>
		   	</div>
		    <div showCollapseButton="true">
		        <div id="form" class="mini-toolbar" style="padding:6px;border-top:0;border-left:0;border-right:0;">                
		            <a class="mini-button operControl mini-button-primary"  onclick="add()">新增</a>
		            <a class="mini-button operControl mini-button-primary"  onclick="removeRow()">删除</a>  
		           <!-- <a class="mini-button operControl mini-button-primary"  onclick="editRow()">编辑</a>-->   
		            <span class="separator operControl"></span>
		            <label for="category$text">资料类别:</label>
	            	<input id="category" name="category"  emptyText="---请选择---"   allowinput="false" class="mini-treeselect" url="${globalContextPath}/action/dataDictionaryData?parentCode=edmEquipmentInfo" multiselect="false" textfield="text" valuefield="id" parentfield="pid" showradiobutton="true" showTreeIcon="false" showTreeLines="false" onenter="onKeyEnter" />&nbsp;&nbsp;            
	            	 <label for="name$text">文件名称:</label>
		            <input id="name" name="name" class="mini-textbox operControl" emptyText="请输入文件名" style="width:150px;" onenter="onKeyEnter"/>   
                    <a class="mini-button mini-button-primary operControl" onclick="search()">查询</a>
                    <a class="mini-button mini-button-primary   docancel" style="margin-left:10px;">重置</a>                
		         </div>
		        <div class="mini-fit" >
		            <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" borderStyle="border:0;" url="${globalContextPath}/action/edmDataLibrary/equipmentDatabaseList" 
		              pageSize="20" showFilterRow="false" multiSelect="false" allowCellSelect="false" allowCellEdit="false">
		                <div property="columns">
		                	<div type="indexcolumn" width="15" align="center" headerAlign="center" >序号</div> 
		                   	<div field="type" width="20" align="center" headerAlign="center" renderer="onTypeRender">类型</div>                                        
		                   	<div field="name" width="80" align="center" headerAlign="center">文件名称 </div>
		                   	<div field="entityName" width="80" align="center" headerAlign="center">关联设备</div>
		                   	<div field="category" width="80" align="center" headerAlign="center">资料类别</div>
				            <div field="creationName" width="60" align="center" headerAlign="center">上传人员</div>
				            <div field="creationTime" width="80" align="center" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">上传时间</div>
		                   	<div field="action"  width="60"  align="center" headerAlign="center" renderer="onActionRenderer" > 操作 </div>
		                 </div>
		            </div>  
		         </div>
		     </div>        
		 </div>
	</body>
</html>    
    