<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>设备台账</title>
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
     <script src="${globalContextPath}/js/edmLibraryManager/edmAccountPrint/edmAccountPrint.js" type="text/javascript"></script>
     <script src="${globalContextPath}/js/utils/edmCommon.js" type="text/javascript"></script>
    <link href="${globalContextPath}/css/edmLibraryManager/edmAccountPrint/edmAccountPrint.css" rel="stylesheet" type="text/css" />
</head>
<body>
<input name="className" id="className" class="mini-hidden" value="${className!''}" />
<input name="founder" id="founder" class="mini-hidden" value="${SESSION_USER_ACCOUNT!''}" />
   	<div class="mini-splitter" style="width:100%;height:100%;">
		<div size="240" showCollapseButton="true">
	        <div class="mini-fit">
	            <ul id="tree1" class="mini-tree" url="${globalContextPath}/action/dataDictionaryTreeData?parentCode=edmGroupInfo" style="width:100%;height:100%;"
	                showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false"  >        
	            </ul>
	        </div>
	    </div>
	    <div style="width:100%;height:100%;">
	        <div id="form" class="mini-toolbar" style="padding:6px;">           
	           	<a class="mini-button mini-button-primary operControl"  onclick="addRow()">新增</a>  
	            <a class="mini-button mini-button-primary operControl"  onclick="editRow()">编辑</a>
	            <a class="mini-button mini-button-primary operControl"  onclick="removeRow()">废弃</a>  
	            <span class="separator"></span>     
	            <a class="mini-button mini-button-primary operControl"  onclick="exportExcel()">导出</a> 
	            <span class="separator"></span>   
	            <label for="deviceNum$text">设备编码:</label>   
		        <input id="deviceNum" name="deviceNum" class="mini-textbox operControl" emptyText="请输入设备编码" style="width:150px;"/>   
	            <label for="deviceName$text">设备名称:</label>   
		        <input id="deviceName" name="deviceName" class="mini-textbox operControl" emptyText="请输入设备名称" style="width:150px;"/>   
	            <label for="deviceModel$text">规格型号:</label>   
		        <input id="deviceModel" name="deviceModel" class="mini-textbox operControl" emptyText="请输入设备型号" style="width:150px;"/>   
	            <label for="statusCode$text">设备状态:</label>   
		        <input id="statusCode" name="statusCode" allowInput="false" style="width:150px;" class="mini-treeselect" 
	               			url="${globalContextPath}/action/dataDictionaryData?parentCode=deviceStatus" multiSelect="false" 
	               			showTreeIcon="false" showTreeLines="false"  emptyText="---请选择---"  
	   					 	textField="text" valueField="id" parentField="pid" showRadioButton="true" showFolderCheckBox="false"/> 
	            <label for="productFactoryName$text">生产厂家:</label>   
		        <input id="productFactoryName" name="productFactoryName" class="mini-textbox operControl" emptyText="请输入生产厂家" style="width:150px;"/>   
		        <a class="mini-button mini-button-primary operControl" onclick="search()">查询</a>
		        <a class="mini-button mini-button-primary docancel" style="margin-left:10px;">重置</a>
	        </div>
	        <div class="mini-fit" >
	            <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" onrowdblclick="onGridRowDbClick"  onrowclick="onRowClick"
	                 url="${globalContextPath}/action/edmLibraryManager/edmAccountPrintList" 
	                 multiSelect="false" pageSize="20" allowCellSelect="false" allowCellEdit="false"                
	            >
	                <div property="columns">
	                	<div type="indexcolumn" width="20" ></div>       
	                    <div field="deviceNum" width="140" headerAlign="center" align="center"  allowSort="false">设备编码</div>      
	                    <div field="deviceName" width="100" headerAlign="center" align="center"  allowSort="false">设备名称</div>      
	                    <div field="deviceModel" width="100" headerAlign="center" align="center"  allowSort="false">规格型号 </div>                
	                    <div field="productFactoryName" width="140" allowSort="false" align="center"   headerAlign="center">生产厂家 </div>            
	                    <div field="leaveFactoryCode" width="100" allowSort="false" align="center"  headerAlign="center">出厂编号 </div>  
	                    <div field="statusName" width="100" allowSort="false" align="center" headerAlign="center">设备状态</div>            
	                    <div field="installAddressFullName" width="100" align="center" allowSort="false" headerAlign="center">专业</div>          
	                    <div field="leaveFactoryTime" width="100" dateFormat="yyyy-MM-dd" align="center" allowSort="false" headerAlign="center">出厂日期 </div>            
	                    <div field="runTime" width="100" dateFormat="yyyy-MM-dd" allowSort="false" align="center" headerAlign="center">投运日期</div>            
	                </div>
	            </div>  
	        </div>
	     	<div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;;height:50%;margin-left: 0px;">
	   			<div title="检修记录  " >
	        		<div id="grid2" class="mini-datagrid" style="width:100%;height:100%;" borderStyle="border:1;" onrowdblclick="onRowDbClick" 
	        			url="${globalContextPath}/action/edmLibraryManager/edmRepairAccountPrintList">
		                <div property="columns"> 
		                	<div type="indexcolumn" width="20" headerAlign="center">序号</div> 
				            <div field="repairName" width="120" align="center" headerAlign="center">检修名称</div>
						    <div field="repairTeam" width="100" align="center" headerAlign="center">检修班组</div>
						    <div field="repairStartTime" width="100" align="center" allowSort="true" dateFormat="yyyy-MM-dd" headerAlign="center">检修开始时间</div>
						    <div field="repairNatureName" width="100" align="center" headerAlign="center">检修性质</div>
						    <div field="founder" width="100" align="center" headerAlign="center">创建人</div>
						    <div field="dutyPersonName" width="100" align="center" headerAlign="center">责任人</div> 
		               </div>
		            </div>
		    	</div>
			</div>
		</div>        
	</div>
</body>
</html>