<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>规章制度</title>
	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script> 
	<script type="text/javascript" src="${globalContextPath}/js/edmDataLibrary/rulesDatabase/rulesDatabase.js"></script>
	<link href="${globalContextPath}/css/edmDataLibrary/edmDataLibrary.css" rel="stylesheet" type="text/css" />
	<input type="hidden" id="globalContextPath" value="${globalContextPath}" />
</head>
	<body>
		<div class="mini-splitter" style="width:100%;height:100%;">
		    <div size="240" showCollapseButton="true">
		        <div class="mini-fit">
					<ul id="tree" class="mini-tree" url="${globalContextPath}/action/dataDictionaryTreeData?parentCode=edmLawsType" style="width:200px;height:100%;padding:5px;" 
			    		showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false">        
					</ul>
		        </div>
		   	</div>
		    <div id="form" showCollapseButton="true">
		        <div class="mini-toolbar" style="padding:6px;border-top:0;border-left:0;border-right:0;">                
		            <a class="mini-button  mini-button-primary"  onclick="add()">新增</a>
		            <a class="mini-button  mini-button-primary"  onclick="removeRow()">删除</a>  
		            <a class="mini-button  mini-button-primary"  onclick="editRow()">编辑</a>   
		            <span class="separator "></span>             
		            <input id="key" class="mini-textbox operControl" emptyText="请输入文件名" style="width:150px;" onenter="onKeyEnter"/>   
                    <a class="mini-button mini-button-primary operControl" onclick="search()">查询</a>                
		         </div>
		        <div class="mini-fit" >
		            <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" borderStyle="border:0;" url="${globalContextPath}/action/edmDataLibrary/rulesDatabaseList" 
		            	onrowdblclick="onGridRowDbClick" pageSize="20" showFilterRow="false" multiSelect="false" allowCellSelect="false" allowCellEdit="false">
		                <div property="columns">
		                	<div type="indexcolumn" width="5" align="center" headerAlign="center" >序号</div> 
		                   	<div field="name" width="40" align="center" headerAlign="center">文件名称 </div>                                        
				            <div field="uploadPerson" width="40" align="center" headerAlign="center">上传人员</div>
				            <div field="uploadDate" width="40" align="center" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">上传时间</div>
		                 </div>
		            </div>  
		         </div>
		     </div>        
		 </div>
	</body>
</html>    
    