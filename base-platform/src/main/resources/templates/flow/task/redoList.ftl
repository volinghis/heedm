<html>
<head>
    <title>已办事项</title>
    <meta content="text/html; charset=UTF-8" http-equiv="content-type" />
        <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
        <script src="${globalContextPath}/js/flow/task/redoList.js" type="text/javascript"></script>
        <link href="${globalContextPath}/css/flow/task/redoList.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="mini-fit" >
       <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" onrowdblclick="onGridRowDbClick" 
            url="${globalContextPath}/action/flow/task/redoList" multiSelect="false" pageSize="10" allowCellSelect="false" autoLoad="false" allowCellEdit="false"                
       >
		    <div property="columns">   
		    	<div type="indexcolumn" width="25"></div>
		        <div field="name" width="200" headerAlign="center"  align="center"  allowSort="false">已办事项</div>      
		        <div field="ownerName" width="50" headerAlign="center"  align="center"  allowSort="false">提交人</div>     
		        <div field="createDate" width="70" headerAlign="center" align="center"  dateFormat="yyyy-MM-dd" allowSort="false">处理时间</div>        
		    </div>
        </div>  
    </div>
</body>
</html>
