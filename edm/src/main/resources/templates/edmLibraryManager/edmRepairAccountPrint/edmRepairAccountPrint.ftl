<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>检修台账</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
    <script type="text/javascript" src="${globalContextPath}/js/edmLibraryManager/edmRepairAccountPrint/edmRepairAccountPrint.js"></script>
    <link href="${globalContextPath}/css/edmLibraryManager/edmRepairAccountPrint/edmRepairAccountPrint.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <input name="className" id="className" class="mini-hidden" value="${className!''}" />
    <div class="mini-splitter" style="width:100%;height:100%;">
    	<div size="240" showCollapseButton="true">
	        <div class="mini-fit">
	         	<ul id="tree1" class="mini-tree" url="${globalContextPath}/action/edmLibraryManager/edmAccountPrintTreeData" style="width:100%;height:100%;"
	                showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false"  >        
	            </ul>
	        </div>
      	</div>
      	<div style="width:100%;height:100%;">
	        <div id="form" class="mini-toolbar" style="padding:6px;">
	          	<a class="mini-button mini-button-primary"  onclick="addRow()" >新增</a>
	        <!--<a class="mini-button mini-button-primary"  onclick="editRow()" >编辑</a> -->
	            <a class="mini-button mini-button-primary"  onclick="removeRow()" >删除</a>
	          	<a class="mini-button mini-button-primary"  onclick="exportExcel()" >导出</a>
	          	<span class="separator"></span>  
	            <label for="repairName$text">检修名称:</label>
	            <input class="mini-textbox" id="repairName" name="repairName" class="mini-textbox" emptyText="请输入检修名称 " />&nbsp;&nbsp;
	            <label for="repairNatureCode$text">检修性质:</label>
	            <input id="repairNatureCode" name="repairNatureCode"  emptyText="---请选择---"   allowinput="false" class="mini-treeselect" url="${globalContextPath}/action/dataDictionaryData?parentCode=edmRepairNature" multiselect="false" textfield="text" valuefield="id" parentfield="pid" showradiobutton="true" showTreeIcon="false" showTreeLines="false" onbeforenodeselect="beforenodeselect" onenter="onKeyEnter" />&nbsp;&nbsp;
	            <label for="dutyPersonCode$text">责任人 :</label>
	            <input id="dutyPersonCode" name="dutyPersonCode" emptyText="---请选择--- "  allowinput="false" class="mini-treeselect" url="${globalContextPath}/action/organization/treeDataForUser" multiselect="false" textfield="name" valuefield="id" parentfield="pid" showradiobutton="true" showfoldercheckbox="false" onbeforenodeselect="beforenodeselect" onenter="onKeyEnter"  popupWidth="260"/>
	            <a class="mini-button mini-button-primary"  onclick="search()">查询</a>
	            <a class="mini-button mini-button-primary   docancel" style="margin-left:10px;">重置</a>
	        </div>
        	<div class="mini-fit">
          		<div id="grid1"  class="mini-datagrid" style="width:100%;height:100%;" onrowdblclick="onGridRowDbClick" borderStyle="border:0;" 
          			url="${globalContextPath}/action/edmLibraryManager/edmRepairAccountPrintList" pageSize="50">
            		<div property="columns">
					  <div type="indexcolumn" width="20"></div>
					  <div field="repairName" width="120" align="center" headerAlign="center">检修名称</div>
					  <div field="repairTeam" width="100" align="center" headerAlign="center">检修班组</div>
					  <div field="repairStartTime" width="80" align="center" allowSort="true" dateFormat="yyyy-MM-dd" headerAlign="center">检修开始时间</div>
					  <div field="repairNatureName" width="100" align="center" headerAlign="center">检修性质</div>
					  <div field="founder" width="80" align="center" headerAlign="center">创建人</div>
					  <div field="dutyPersonName" width="80" align="center" headerAlign="center">责任人</div>
					  <div field="flowStatusName" width="80" align="center" headerAlign="center" renderer="onStatusRenderer">状态</div></div>
           			</div>
    			</div>
  			</div>
    	</div>
</body>
</html>