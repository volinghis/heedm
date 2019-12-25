<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head> 
  <title>账号管理页面</title> 
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
  <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&amp;domain=${domain}" type="text/javascript"></script> 
  <script src="${globalContextPath}/js/account/accountManager.js" type="text/javascript"></script> 
  <link href="${globalContextPath}/css/account/accountManager.css" rel="stylesheet" type="text/css" />
 </head> 
 <body>
 	 <input name="SESSION_USER_ACCOUNT" id="SESSION_USER_ACCOUNT" class="mini-hidden" value="${SESSION_USER_ACCOUNT}" /> 
 	 <div style="width:100%;height:100%;">
 	 <div style="width:100%;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
        	<table style="width:100%;">
        		<td style="white-space:nowrap;float:right;">
	            <input id="key" class="mini-textbox" emptyText="请输入姓名或账号" style="width:150px;"/>   
	            <a class="mini-button mini-button-primary" onclick="search()">查询</a>
	            </td>
            </table>  
        </div>
    </div>
   <div class="mini-fit" > 
      <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" idField="id" allowResize="true" pageSize="25" url="${globalContextPath}/action/accountManager/accountManagerList">
        <div property="columns">
            <div type="indexcolumn" width="20" ></div> 
            <div field="name"  align="center"  width="80" headerAlign="center" allowSort="true">姓名</div> 
            <div field="account"  width="100"  align="center" headerAlign="center">账号 </div> 
            <div field="state"  width="100"  align="center" headerAlign="center" renderer="onStateRenderer" > 状态 </div> 
            <div field="action"  width="120"  align="center" headerAlign="center" renderer="onActionRenderer" > 操作 </div> 
        </div>
      </div>
    </div>
   </div>
 </body>
</html>