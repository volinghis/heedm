<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>设备检索</title>
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
    <script src="${globalContextPath}/js/loginLog/loginLog.js" type="text/javascript"></script>
    <link href="${globalContextPath}/css/loginLog/loginLog.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div style="width:100%;height:100%;">
	    <div id="form1" class="mini-toolbar" style="padding:2px;">                
	    	<table style="width:100%;">
        		<td style="white-space:nowrap;float:right;">
	            <input id="key" class="mini-textbox" emptyText="请输入姓名或账号" style="width:150px;"/>   
	            <a class="mini-button" onclick="search()">查询</a>
	            </td>
            </table> 
		</div>
	    <div class="mini-fit" >
	        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" url="${globalContextPath}/action/loginLog/loginLogList"
	             multiSelect="false" pageSize="25" allowCellSelect="false" allowCellEdit="false" onrowdblclick="onGridRowDbClick">
	            <div property="columns"> 
	            	<div type="indexcolumn" width="20"></div>  
	          		<div field="account" width="80" headerAlign="center" align="center" allowSort="false">账号</div>
	                <div field="name" width="100" headerAlign="center" align="center" allowSort="false">用户</div>      
	                <div field="ip" width="80" allowSort="false" align="center"  headerAlign="center">登录IP</div>          
	                <div field="time" width="80" allowSort="false" align="center" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">登录时间</div>   
	            </div>
	        </div>  
	    </div>
	</div>
</body>
</html>