<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>基础数据</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
   	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
	<script type="text/javascript" src="${globalContextPath}/js/basicData/basicData.js"></script>
	<link href="${globalContextPath}/css/basicData/basicData.css" rel="stylesheet"  type="text/css" />
</head>
<body>
<div class="mini-splitter" style="width:100%;height:100%;">
    <div size="240" showCollapseButton="true">
        <div class="mini-fit">
        <ul id="tree" class="mini-tree" url="${globalContextPath}/action/dataTreeDataNotLazy?parentCode=edmGroupInfo" style="width:100%;height:100%;" 
             showTreeIcon="true" textField="text" onnodeselect="onNodeSelect" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="0">        
        </ul>
        </div>
    </div>
    <div style="width:100%;height:100%;">
    	<div class="mini-toolbar" style="padding:2px;">                
            <a class="mini-button mini-button-primary"  onclick="addRow()">新增</a>
           <#--<a class="mini-button mini-button-primary" iconCls="icon-edit" onclick="editRow()">编辑</a>-->
           	<a class="mini-button mini-button-primary"  onclick="removeRow()">删除</a>
            <a class="mini-button mini-button-primary"  onclick="moveUp()">上移</a>     
            <a class="mini-button mini-button-primary"  onclick="moveDown()">下移</a>     
            <span class="separator"></span>             
            <a class="mini-button mini-button-primary"  onclick="saveData()">保存</a>                  
        </div>
        <div class="mini-fit" >
            <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" cellEditAction="celldblclick"
                borderStyle="border:0;" url="${globalContextPath}/action/basicData/getDictByParentCode" allowCellEdit="true" allowCellSelect="true" allowCellValid="true"
                idField="id">
                <div property="columns"> 
                	<div width="10" type="indexcolumn"></div>  
                    <div field="text" name="text" width="120" headerAlign="center" vtype="required">名称                        
                        <input property="editor" class="mini-textbox" style="width:100%;"/>
                    </div>                
       				 <div field="relatedDepart" name="relatedDepart" displayField="relatedDepartNames" width="120" allowSort="false" headerAlign="center">关联部门  
			           <input property="editor" class="mini-treeselect" url="${globalContextPath}/action/organization/treeData" multiSelect="true" 
			                textField="name" valueField="id" parentField="pid" allowInput="true" expandOnLoad="true"/>
       				 </div>             
                    <div field="sort" sortField="sort" width="120" headerAlign="center"  dataType="int" visible="false" > 排序
                    	<input property="editor" class="mini-textbox" style="width:100%;"/>	
                    </div>         
                </div>
            </div>  
        </div>
    </div>        
</div>
</body>
</html>