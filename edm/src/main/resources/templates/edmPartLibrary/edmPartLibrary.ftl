<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>备件管理</title>
	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script> 
	<script type="text/javascript" src="${globalContextPath}/js/edmPartLibrary/edmPartLibrary.js"></script>
	<link href="${globalContextPath}/css/edmPartLibrary/edmPartLibrary.css" rel="stylesheet" type="text/css" />
	<input type="hidden" id="globalContextPath" value="${globalContextPath}" />
</head>
	<body>
		<div class="mini-splitter" style="width:100%;height:100%;">
		    <div size="260" showCollapseButton="true">
		        <div class="mini-fit">
					<ul id="tree" class="mini-tree" url="${globalContextPath}/action/edmLibraryManager/edmAccountPrintTreeData" style="width:100%;height:100%;padding:5px;" 
			    		showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false">        
					</ul>
		        </div>
		   	</div>
		    <div showCollapseButton="true">
		        <div id="form" class="mini-toolbar" style="padding:6px; "> 
		        	<table>
		        		<tr>
		        			<td><label for="name$text">备件名称：</label></td>
		        			<td><input id="name" name="name" class="mini-textbox " emptyText="请输入备件名称" maxLength="100" style="width: 100%;"/></td>
		        			<td><label for="manufacturer$text">生产厂家：</label></td>
		        			<td><input class="mini-textbox" id="manufacturer" name="manufacturer" class="mini-textbox" emptyText="请输入生产厂家 " maxLength="100" style="width: 100%;"/></td>
		        			<td><label for="norm$text">规格型号：</label></td>
		        			<td><input class="mini-textbox" id="norm" name="norm" class="mini-textbox" emptyText="请输入规格型号 " maxLength="100" style="width: 100%;"/></td>
		        			<td><label for="type$text">归属设备：</label></td>
		        			<td><input class="mini-textbox" id="type" name="type" class="mini-textbox" emptyText="请输入设备名称 " maxLength="100" style="width: 100%;"/></td>
		        		</tr>
		        		<tr>
		        			<td><label for="profession$text">专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</label></td>
		        			<td><input class="mini-textbox" id="profession" name="profession" class="mini-textbox" emptyText="请输入专业 " maxLength="100" style="width: 100%;" maxLength="100" style="width: 100%;"/></td>
		        			<td><label for="materialTypeName$text">物资类别：</label></td>
		        			<td>
		        				<input id="materialTypeCode" name="materialTypeCode" allowinput="false" class="mini-treeselect" url="${globalContextPath}/action/dataDictionaryData?parentCode=materialType" 
		        				showTreeIcon="false" showTreeLines="false"  emptyText="---请选择---"  multiselect="false" textfield="text" valuefield="id" parentfield="pid" 
		        				showradiobutton="true" maxLength="100" style="width: 100%;"/>
		        			</td>
		        			<td><label for="amount$text">库存余量：</label></td>
		        			<td><input class="mini-textbox" id="amount" name="amount" class="mini-textbox" emptyText="请输入库存余量 " maxLength="100" style="width: 100%;"/></td>
		        			<td><label for="warningValue$text">&nbsp;预&nbsp;&nbsp;警&nbsp;&nbsp;值：</label></td>
		        			<td><input class="mini-textbox" id="warningValue" name="warningValue" class="mini-textbox" emptyText="请输入预警值 " maxLength="100" style="width: 100%;"/></td>
		        		</tr>
		        		<tr>
			        		<td>&nbsp;</td>
							<td colspan="5" style="text-align: center;">   
					 			<a class="mini-button mini-button-primary" onclick="search()" style="text-align:center">查询</a>
					 			<a class="mini-button mini-button-primary docancel"  style="margin-left:10px;">重置</a>
				            </td>
		        		</tr>
		        	</table>               
                    <HR align=center width=100% color=#ccc SIZE=1>
                    <div style="text-align: left;">
	                    <a class="mini-button mini-button-primary" onclick="add()">新增</a>
			            <a class="mini-button mini-button-primary" onclick="editRow()">编辑</a>   
			            <a class="mini-button mini-button-primary" onclick="removeRow()">删除</a>  
		         	</div>
		         </div>
		        <div class="mini-fit" >
		            <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" borderStyle="border:0;" url="${globalContextPath}/action/edmPartLibrary/partLibraryTableData" 
		            	onrowdblclick="onGridRowDbClick" pageSize="50" showFilterRow="false" multiSelect="false" allowCellSelect="false" allowCellEdit="false">
		                <div property="columns"> 
		                	<div type="indexcolumn" width="10" headerAlign="center" ></div>
		                   	<div field="name"  width="40" align="center" headerAlign="center" >备件名称 </div>                                        
				            <div field="manufacturer" width="40" align="center" headerAlign="center" >生产厂家 </div>
				            <div field="norm" width="40"  align="center" headerAlign="center" >规格型号 </div>
				            <div field="type" width="40" align="center" headerAlign="center" >归属设备</div>
				            <div field="profession" width="40" align="center" headerAlign="center" >专业</div>
				            <div field="materialTypeName" width="40" align="center" headerAlign="center" >物资类别</div>
				            <div field="amount" width="40" align="center" headerAlign="center" >库存余量</div>
				            <div field="warningValue" width="40" align="center" headerAlign="center" >预警值</div>
		                 </div>
		            </div>  
		         </div>
		     </div>        
		 </div>
	</body>
</html>    
    