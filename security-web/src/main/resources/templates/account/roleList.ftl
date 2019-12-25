<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>角色选择页面</title>
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
     <script src="${globalContextPath}/js/account/roleList.js" type="text/javascript"></script>
    <link href="${globalContextPath}/css/account/authorize.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div style="width:100%;height:100%;">
        <div class="mini-toolbar" style="padding:0px;">
         <table style="width:100%;">
        		<td style="white-space:nowrap;float:right;">
	            <input id="key" class="mini-textbox" emptyText="请输入角色编码或名称" style="width:180px;"/>   
	            <a class="mini-button mini-button-primary" onclick="search()">查询</a>
	            </td>
            </table>     
        </div>
        <div class="mini-fit" >
            <div id="grid1" class="mini-datagrid" style="width:100%;height:80%;" url="${globalContextPath}/action/roleManager/roleManagerList"
                 multiSelect="false"  allowCellSelect="true" borderStyle="border:1;" editNextRowCell="true">
               <div property="columns">   
                  <div type="indexcolumn" width="20" ></div>           
                  <div field="dataCode"  width="100" align="center" headerAlign="center">角色编码 </div>      
                  <div field="name"   width="100" align="center" headerAlign="center">角色名称 </div>                
               </div>
            </div>  
            </br>
            <div align="center">
	              <a class="mini-button mini-button-primary dosave" style="width:60px;">确定 </a>
	              <a class="mini-button mini-button-primary docancel" style="width:60px;margin-left: 20px;">返回 </a>
            </div>
        </div>
    </div>        
</body>
</html>