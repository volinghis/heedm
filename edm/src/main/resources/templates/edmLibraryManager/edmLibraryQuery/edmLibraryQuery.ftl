<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>设备检索</title>
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
    <script src="${globalContextPath}/js/edmLibraryManager/edmLibraryQuery/edmLibraryQuery.js" type="text/javascript"></script>
    <link href="${globalContextPath}/css/edmLibraryManager/edmLibraryQuery/edmLibraryQuery.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div style="width:100%;height:100%;">
	    <div id="form1" class="mini-toolbar" style="padding:2px;">                
		   	<table >
			    <tr>
		        	<td>
			            <label for="deviceNum$text">设备编号：</label>
			        </td>
			        <td>
			         <input id="deviceNum" name="deviceNum" class="mini-textbox" maxLength="100" style="width: 100%;" />
			        </td>
			       	<td>
			            <label for="deviceName$text">设备名称(泛)：</label>
			        </td>
			        <td>
			            <input id="deviceName" name="deviceName" value="" class="mini-textbox" maxLength="100" style="width: 100%;"  />
			        </td>
			        <td>
		                <label for="deviceModel$text">设备型号(泛)：</label>
		            </td>
		            <td>
		             	<input id="deviceModel" name="deviceModel" class="mini-textbox"    maxLength="100" style="width: 100%;" />
		            </td>
		    	</tr>
				<tr>
		           	<td>
		                <label for="installAddressCode$text">安装位置：</label>
		            </td>
		            <td>
		                 <input id="installAddressCode"  name="installAddressCode" allowInput="false" style="width: 100%;" class="mini-treeselect" 
		                 		url="${globalContextPath}/action/dataDictionaryTreeData?parentCode=edmGroupInfo" multiSelect="false" popupWidth="250"
		   					 	textField="name" valueField="id" parentField="pid" showRadioButton="true" showFolderCheckBox="false"/>
		            </td>
		          <!-- 	<td>
		                <label for="dutyOrgCode$text">检修班组：</label>
		            </td>
		            <td>
		           		<input id="checkRrepairTeamCode"  name="checkRrepairTeamCode" allowInput="false" style="width: 100%;"  class="mini-treeselect" 
		           				url="${globalContextPath}/action/organization/treeData" multiSelect="false" textField="name" valueField="id" parentField="pid" 
		   					 	showRadioButton="true" showFolderCheckBox="true"  />
		            </td>
		          -->
	             	<td>
		                <label for="productFactoryName$text">生产厂家(泛)：</label>
		            </td>
		            <td>
		               	<input id="productFactoryName" name="productFactoryName" class="mini-textbox" maxLength="100" style="width: 100%;" />
		            </td>
		            <td>
		                <label for="statusCode$text">设备状态：</label>
		            </td>
	               	<td>
	               		<input id="statusCode" name="statusCode" allowInput="false" style="width: 100%;" class="mini-treeselect" 
	               			url="${globalContextPath}/action/dataDictionaryData?parentCode=deviceStatus" multiSelect="false" 
	               			showTreeIcon="false" showTreeLines="false"  emptyText="---请选择---"  
	   					 	textField="text" valueField="id" parentField="pid" showRadioButton="true" showFolderCheckBox="false"/>
	            	</td>
		    	</tr>
		        <tr>
					<td>&nbsp;</td>
					<td colspan="5" style="text-align: center;">   
				 		<a class="mini-button mini-button-primary doquery"  style="width:60px;color: aliceblue;">查询</a>       
		            	<a class="mini-button mini-button-primary docancel"  style="width:60px;margin-left: 20px;color: aliceblue;">重置</a>     
		            </td>
				</tr>
		    </table>
		</div>
	    <div class="mini-fit" >
	        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" url="${globalContextPath}/action/edmLibraryManager/edmLibraryQueryList"
	             multiSelect="false" pageSize="50" allowCellSelect="false" allowCellEdit="false" onrowdblclick="onGridRowDbClick"             
	        >
	            <div property="columns">  
	            <div type="indexcolumn" width="20" ></div>  
	          		<div field="deviceNum" width="120" headerAlign="center" align="center" allowSort="false">设备编号</div>
	                <div field="deviceName" width="100" headerAlign="center" align="center" allowSort="false">设备名称</div>      
	                <div field="deviceModel" width="80" allowSort="false" align="center"  headerAlign="center">设备型号</div>          
	                <div field="statusName" width="60" allowSort="false" align="center" headerAlign="center">设备状态</div>   
	                <div field="installAddressFullName" width="120" align="center" allowSort="false" headerAlign="center">安装位置</div>
	              <!--  <div field="checkRrepairTeamName" width="100" align="center" headerAlign="center"  allowSort="false">检修班组</div>   -->       
	                <div field="productFactoryName" width="100" align="center" headerAlign="center"  allowSort="false">生产厂家</div>          
	                <div field="runTime" dateFormat="yyyy-MM-dd" width="80" align="center" headerAlign="center" allowSort="false">投运时间</div>    
	            </div>
	        </div>  
	    </div>
	</div>
</body>
</html>