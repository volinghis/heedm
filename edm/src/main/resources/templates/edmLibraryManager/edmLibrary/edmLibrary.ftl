<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>设备库</title>
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
     <script src="${globalContextPath}/js/edmLibraryManager/edmLibrary/edmLibrary.js" type="text/javascript"></script>
    <link href="${globalContextPath}/css/edmLibraryManager/edmLibrary/edmLibrary.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<#-- 
    <div size="240" showCollapseButton="true">
        <div class="mini-fit">
            <ul id="tree1" class="mini-tree" url="${globalContextPath}/action/dataDictionaryTreeData"  onbeforeload="onBeforeTreeLoad"  style="width:100%;"
                showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false">        
            </ul>
        </div>
    </div>
     -->
    <input type="hidden" id="nameString" value="${nameString!''}" />
    <div style="width:100%;height:100%;">
        <div class="mini-toolbar" style="padding:2px;">                
            <a class="mini-button" iconCls="icon-add" plain="true" onclick="addRow()">新增</a>
            <a class="mini-button" iconCls="icon-edit" plain="true" onclick="editRow()">编辑</a>
            <a class="mini-button" iconCls="icon-redo" plain="true" onclick="prodRow()">投产</a>
            <a class="mini-button" iconCls="icon-remove" plain="true" onclick="removeRow()">删除</a>    
            <a class="mini-button" iconCls="icon-excel" plain="true" onclick="exportExcel()">导出</a>    
            <input id="key" class="mini-textbox" emptyText="请输入设备简称" style="width:150px;" onenter="onKeyEnter"/>   
            <a class="mini-button" onclick="search()">查询</a>
        </div>
        <div class="mini-fit" >
            <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" onrowdblclick="onGridRowDbClick"  onrowclick="onRowClick"
                 url="${globalContextPath}/action/edmLibraryManager/edmLibraryList"
                 multiSelect="false" pageSize="20" allowCellSelect="false" allowCellEdit="false"                
            >
                <div property="columns">   
                <div type="checkcolumn" width="20" ></div>           
                    <div field="deviceShortName" width="120" headerAlign="center" allowSort="false">设备简称</div>      
                    <div field="deviceTypeName" width="120" headerAlign="center" allowSort="false">设备类型 </div>                
                    <div field="deviceModel" width="100" allowSort="false"  headerAlign="center">设备型号 </div>            
                    <div field="brand" width="100" allowSort="false" headerAlign="center">品牌</div>
                    <div field="keepRepairTime" width="100" allowSort="false" headerAlign="center">保修期限(月)</div>                                    
                    <div field="deprecateTime" width="100" headerAlign="center" allowSort="false">报废期限(月)</div>     
                    <div field="checkRate" width="100" headerAlign="center" allowSort="false">检修频率(月)</div> 
                </div>
            </div>  
        </div>
        <div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;height:50%;margin-left: 0px;">
		    <div title="关联文档" name="relationFiles">
		       <div id="grid2" class="mini-datagrid" style="width:100%;height:100%;" borderStyle="border:1;" 
		             url="${globalContextPath}/action/fileLists">
                <div property="columns"> 
                	<div type="indexcolumn" width="10" headerAlign="center">序号</div> 
		            <div field="name" width="80" align="center" headerAlign="center">文件名</div> 
					<div field="type" width="80" align="center" headerAlign="center">文件类型</div> 
		            <div field="fileSize"  width="80" align="center" headerAlign="center">文件大小</div> 
		            <div field="action"  width="30" align="center" headerAlign="center" renderer="onFileRederer">操作</div> 
               </div>
              </div>
		   </div>
		    <div title="关联设备" name="relationDevice">
		          <div id="grid3" class="mini-datagrid" style="width:100%;height:100%;" borderStyle="border:1;" 
		             url="${globalContextPath}/action/edmLibraryManager/getEdmAccoutPrintByDataCode">
                <div property="columns"> 
                	<div type="indexcolumn" width="20" headerAlign="center">序号</div> 
		            <div field="deviceNum" width="80" headerAlign="center" align="center" >设备编码</div>  
                    <div field="kssNum" width="80" headerAlign="center" align="center" >KSS编码</div>      
                    <div field="deviceName" width="80" headerAlign="center" align="center" >设备名称</div>    
                    <div field="installTime" width="80" headerAlign="center" dateFormat="yyyy-MM-dd"  align="center">安装日期</div>      
                    <div field="installAddressFullName" width="80" headerAlign="center" align="center">安装位置</div>        
                    <div field="dutyPersonName" width="80"   headerAlign="center" align="center">责任人</div>          
                    <div field="dutyOrgName" width="80"  headerAlign="center" align="center">责任部门
                    </div>
               </div>
              </div>
		    </div>
		    <#-- <div title="信息履历">信息履历 </div> -->
		</div>
    </div>        
</body>
</html>