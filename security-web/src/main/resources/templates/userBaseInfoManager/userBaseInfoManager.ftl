<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户管理 </title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
   	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
	<script type="text/javascript" src="${globalContextPath}/js/userBaseInfoManager/userBaserInfoManager.js"></script>
 	<style type="text/css">
	    body{
	        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
	    }    
    </style>
</head>
<body>
<div class="mini-splitter" style="width:100%;height:100%;">
    <div size="240" showCollapseButton="true">
        <div class="mini-fit">
        <ul id="tree" class="mini-tree" url="${globalContextPath}/action/organization/treeDataNoLazy" style="width:100%;height:100%;padding:5px;" 
             showTreeIcon="true" textField="text" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="0">        
        </ul>
        </div>
    </div>
    <div showCollapseButton="true">
    	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">                
            <a class="mini-button mini-button-primary " onclick="addRow()">新增</a>
            <a class="mini-button mini-button-primary " onclick="editRow()">编辑</a>
            <a class="mini-button mini-button-primary " onclick="removeRow()">删除</a>
               <span class="separator"></span>
            <input id="key" class="mini-textbox" emptyText="请输入用户账号或姓名" style="width:200px;"/>   
            <a class="mini-button mini-button-primary" onclick="search()">查询</a>
        </div>
        <div class="mini-fit" >
            <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" borderStyle="border:0;" 
            	url="${globalContextPath}/action/userBaseInfoManager/userBaseInfoManagerList" pageSize="25"
           	 	multiSelect="false" allowCellEdit="false" allowCellValid="true">
                <div property="columns"> 
                	<div type="indexcolumn" width="20"></div>  
                    <div field="dataCode" width="120" headerAlign="center" align="center"  allowSort="true">用户账号         
                        <input property="editor" style="width:100%;"  required="true"/>
                    </div>                
                    <div field="name" width="120" align="center" headerAlign="center" allowSort="true">用户姓名                     
                        <input property="editor" style="width:100%;"  required="true"/>
                    </div>                
                    <div field="email" width="120" align="center" headerAlign="center" allowSort="true">邮箱                        
                        <input property="editor" style="width:100%;"  required="true"/>
                    </div>                
                    <div field="telephone" width="120" align="center" headerAlign="center" allowSort="true">手机                        
                        <input property="editor" style="width:100%;"  required="true"/>
                    </div>                
                    <div field="position" width="120" align="center" headerAlign="center" allowSort="true">职务                      
                        <input property="editor" style="width:100%;"  required="true"/>
                    </div>                
                    <div field="ownerCreationTime" width="120" align="center" dateFormat="yyyy-MM-dd HH:mm" headerAlign="center" allowSort="true">创建时间                        
                        <input property="editor" style="width:100%;"  required="true"/>
                    </div>                
                    <div field="creationTime" width="120" align="center" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm" allowSort="true">修改时间                        
                        <input property="editor" style="width:100%;"  required="true"/>
                    </div>                
                    <div field="attribute1" width="120" align="center" headerAlign="center" allowSort="true">所属部门                  
                        <input property="editor" style="width:100%;"  required="true"/>
                    </div>                
                    <div field="remark" width="120" align="center" headerAlign="center" allowSort="true">备注                      
                        <input property="editor" style="width:100%;"  required="true"/>
                    </div>                
                     <div field="creationTime" sortField="creationTime" width="120" headerAlign="center" allowSort="true" dataType="int" visible="false" > 排序                        
                    </div>                
                </div>
            </div>  
        </div>
    </div>        
</div>
</body>
</html>