<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>授权角色</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
    <script src="${globalContextPath}/js/menuManager/menuManagerEdit.js" type="text/javascript"></script>
     <style type="text/css">
    	body {
			body {
			margin: 0;
			padding: 0;
			border: 0;
			width: 100%;
			height: 100%;
			overflow: hidden;
			}
		}
    </style>
</head>
<body> 
<div style="width:100%;height:100%;">
	<div class="mini-fit">
		<input id="menuCode" name= "menuCode" class="mini-hidden" value="${menuCode!''}"/>
       	<div id="grid" class="mini-datagrid" style="width:98%;height:80%;" url="${globalContextPath}/action/roleManager/roleManagerList"
             multiSelect="false" allowCellEdit="false" pageSize="20" allowCellSelect="true" borderStyle="border:1;" editNextRowCell="true">
            <div property="columns">
            <div type="indexcolumn" width="30" ></div>           
                <div field="dataCode"  width="100" align="center" headerAlign="center" font-weight: bolder;>角色编号
                	<input property="editor" class="mini-textbox" vtype="required"  style="width:100%;"  required="true"/>
                </div>      
                <div field="name"  property="editor" width="100" align="center" headerAlign="center" >角色名称
                	<input property="editor" class="mini-textbox" vtype="required"  style="width:100%;"  required="true"/>
                 </div>                
                <div field="remark"  property="editor" width="120"  align="center" headerAlign="center">备注
                	<input property="editor" class="mini-textbox" vtype="required"  style="width:100%;"  required="true"/>
                </div>            
            </div>
        </div> 
        </br>
		<div align="center">
			<lable> 
				<a class="mini-button mini-button-primary dosave" style="width:60px;">确定</a> 
				<a class="mini-button mini-button-primary docancel" style="width:60px;margin-left: 20px;">返回</a> 
			</lable> 
		</div>
    </div>
</div>
    
</body>
</html>