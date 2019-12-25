<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>设备台账信息表</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
    <script src="${globalContextPath}/js/edmLibraryManager/edmAccountPrint/edmAccountPrintEdit.js" type="text/javascript"></script>
    <script src="${globalContextPath}/js/edmLibraryManager/edmAccountPrint/edmImgUpload.js" type="text/javascript"></script>
    <script src="${globalContextPath}/res/third-party/webuploader/webuploader.min.js" type="text/javascript"></script>
    <link href="${globalContextPath}/res/third-party/webuploader/webuploader.css" rel="stylesheet"  type="text/css" />
    <link href="${globalContextPath}/css/edmLibraryManager/edmAccountPrint/edmAccountPrintEdit.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form id="edmLibraryForm">
    <div id="form1">
        <input name="edmAccountPrintId" id="edmAccountPrintId" class="mini-hidden" value="${objectBean.id!''}" />
        <input name="filesJson" id="filesJson" class="mini-hidden" />
        <input name="accountParamJson" id="accountParamJson" class="mini-hidden" />
        <input name="principalParamJson" id="principalParamJson" class="mini-hidden" />
        <input name="dataList" id="dataList" class="mini-hidden" />
        <input name="orgCode" id="orgCode" class="mini-hidden" value="${orgCode!''}" />
        <input name="installFullName" id="installFullName" class="mini-hidden" value="${objectBean.installAddressFullName!''}" />
        <table width="750">
            <tr>
               <td>
                    <label for="deviceNum$text">设备编码：</label>
                </td>
                <td>
                    <input id="deviceNum" name="deviceNum" value="${objectBean.deviceNum!''}" class="mini-textbox" maxLength="100" style="width: 100%;"  required="true"/>
                </td>
                <td>
                    <label for="deviceName$text">设备名称：</label>
                </td>
                <td>
                    <input id="deviceName" name="deviceName" value="${objectBean.deviceName!''}" style="width: 100%;" maxLength="100" class="mini-textbox" required="true" />
                </td>
            </tr>
            <tr>
               <td>
                    <label for="deviceModel$text">规格型号：</label>
                </td>
                <td>
                 	<input id="deviceModel" name="deviceModel" class="mini-textbox" value="${objectBean.deviceModel!''}" maxLength="100" style="width: 100%;"/>
                </td>
                <td>
                    <label for="productFactoryName$text">生产厂家：</label>
                </td>
                <td>
	       		<!--	<input id="productFactoryName" name="productFactoryName" allowinput="false" style="width: 100%;" required="true" class="mini-treeselect" 
	       				url="${globalContextPath}/action/dataDictionaryData?parentCode=manufacturer" multiselect="false" textfield="text" valuefield="id" 
	       				parentfield="pid" value="${objectBean.productFactoryName!''}" showradiobutton="true" showTreeIcon="false" showTreeLines="false"  
	       				emptyText="---请选择---" popupWidth="260"  onbeforenodeselect="beforenodeselect"/></td> 
                	-->
                	<input id="productFactoryName" name="productFactoryName" class="mini-textbox" value="${objectBean.productFactoryName!''}" maxLength="100" style="width: 100%;"/>
                </td>
            </tr>
            <tr>
            	<td>
                    <label for="leaveFactoryTime$text">出厂日期：</label>
                </td>
                <td>
                 	<input id="leaveFactoryTime" name="leaveFactoryTime" class="mini-datepicker" format="yyyy-MM-dd" value="${objectBean.leaveFactoryTime!''}" maxLength="100" style="width: 100%;"/>
                </td>
            	<td>
                    <label for="leaveFactoryCode$text">出厂编号：</label>
                </td>
                <td>
                 	<input id="leaveFactoryCode" name="leaveFactoryCode" class="mini-textbox" value="${objectBean.leaveFactoryCode!''}" maxLength="100" style="width: 100%;" />
                </td>
        	</tr>
            <tr>
             	<td>
                    <label for="runTime$text">投运日期：</label>
                </td>
                <td>
                 	<input id="runTime" name="runTime"  class="mini-datepicker" format="yyyy-MM-dd" value="${objectBean.runTime!''}" maxLength="100"  style="width: 100%;"  />
                </td>
                
                <td>
                    <label for="founder$text">创&nbsp;&nbsp;建&nbsp;&nbsp;人：</label>
                </td>
                <td>
                    <input id="founder" name="founder" value="${founder!''}" class="mini-textbox" maxLength="100" style="width: 100%;" />
                </td>
        	</tr>
			<tr>
				<!--<td>
                    <label>检修班组：</label>
                </td>
                <td>
                    <input id="checkRrepairTeamCode"  name="checkRrepairTeamCode" allowInput="false" style="width: 100%;" class="mini-treeselect" 
           				url="${globalContextPath}/action/organization/treeData" multiSelect="false"  textField="name" valueField="id" parentField="pid"
           			 	value="${objectBean.checkRrepairTeamCode!''}" text="${objectBean.checkRrepairTeamName!''}" showRadioButton="true" showFolderCheckBox="true"/>
                </td>   
                <td>
                    <label for="associationDefect$text">关联缺陷：</label>
                </td>
                <td>
                 	<input id="associationDefect" name="associationDefect" class="mini-textbox" value="${objectBean.associationDefect!''}" maxLength="100" style="width: 100%;" />
                </td>  
            -->
            </tr>
            <tr>
                <td>
                    <label for="checkRrepairNorm$text">检修质量标准：</label>
                </td>
                <td colspan="3" height="100">
			        <input id="checkRrepairNorm" name="checkRrepairNorm"  value="${objectBean.checkRrepairNorm!''}"  class="mini-textarea" maxLength="1000" style="width: 100%;height: 100%;"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="regularWorkNorm$text">定期工作标准：</label>
                </td>
                <td colspan="3" height="100">
			        <input id="regularWorkNorm" name="regularWorkNorm" value="${objectBean.regularWorkNorm!''}" class="mini-textarea" maxLength="1000" style="width: 100%;height: 100%;"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="pastEquipInspectors$text">历任设备检点员</br>（专责人）：</label>
                </td>
                <td colspan="3" height="130">
			   <!--     <input id="pastEquipInspectors" name="pastEquipInspectors" value="${objectBean.pastEquipInspectors!''}" 
			   				class="mini-textarea" maxLength="1000" style="width: 100%;height: 100%;"/>
	   			--> 
	   				<div style="height: 100%">
	            		<div id="datagrid2" class="mini-datagrid" style="width:100%;height:100%;aglin:center" allowResize="false" showpager="false"
					      	url="${globalContextPath}/action/edmPrincipal/principaList?code=${objectBean.code!''}"
					        idField="id"  showtoolbar="true" allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        					editNextOnEnterKey="true"  editNextRowCell="true" showFilterRow="false" >
					        
						    <div property="toolbar" style="height: 30px; line-height: 27px">
								<a class="mini-button mini-button-primary" onclick="addPrincipal()" id="beginBtn">新增</a>
							</div>
							
					        <div property="columns">
					            <div name="principal" field="principal" width="120" headerAlign="center" align="center" allowSort="true">姓名
					                <input property="editor" required="true" class="mini-textbox" maxLength="100" style="width:100%;" />
					            </div>
					            <div name="serveTime" field="serveTime" width="120" headerAlign="center" Renderer="ondayRenderer" align="center" allowSort="true" >担任时间
					                <input id="serveTime" property="editor" required="true" class="mini-datepicker" maxLength="100" style="width:100%;"/>
					            </div>
					            <div name="leaveTime" field="leaveTime" width="120" headerAlign="center" Renderer="ondayRenderer" align="center" allowSort="true">离任时间
					                <input id="leaveTime" property="editor" required="true" class="mini-datepicker" maxLength="100" style="width:100%;"/>
					            </div>
					        </div>
				        </div>
				    </div> 
                </td>
            </tr>
            <tr>
            	<td>
            		<label>设备参数：</label>
            	</td>
            	<td colspan="3" height="130">
            		<div style="height: 100%">
	            		<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;aglin:center" allowResize="false" showpager="false"
					      	url="${globalContextPath}/action/edmAccountPrintParam/paramList?dataCode=${objectBean.code!''}"
					        idField="id"  showtoolbar="true" allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        					editNextOnEnterKey="true"  editNextRowCell="true" showFilterRow="false" >
					        
						    <div property="toolbar" style="height: 30px; line-height: 27px">
								<a class="mini-button mini-button-primary" onclick="addRow()" id="beginBtn">新增</a>
							</div>
							
					        <div property="columns">
					            <div name="parameterName" field="parameterName" width="120" headerAlign="center" align="center" allowSort="true">参数名称
					                <input property="editor" required="true"  class="mini-textbox" maxLength="100" style="width:100%;" />
					            </div>
					            <div name="parameterValue" field="parameterValue" width="120" headerAlign="center" align="center" allowSort="true">参数值
					                <input property="editor" required="true" class="mini-textbox" maxLength="100" style="width:100%;"/>
					            </div>
					        </div>
				        </div>
				    </div> 
            	</td>
            </tr>
     		<tr>
				<td><label>附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件：</label></td>
				<td colspan="3" height="200">
					<div style="height: 100%">
						<div class="mini-datagrid" id="uploadGrid" autoLoad="true" onload="loadResult" url="${globalContextPath}/action/fileLists?dataCode=${objectBean.code!''}"
							style="width: 100%; height: 100%" showpager="false" emptyText="尚未上传" alwaysShowEmptyText="false" showEmptyText="false" showheader="false"
							showtoolbar="true" title="<font style='font-weight:bold'>文件上传</font>">
							<div property="toolbar" style="height: 30px; line-height: 20px">
								<a class="mini-button mini-button-primary" id="uploadBtn">上传</a>
								<!--<div class="uploadPlaceholder" id="uploadPlaceholder"></div>-->
							</div>
							<div property="columns">
								<div field="name" headerAlign="center" align="center" width="150">文件名称</div>
								<div field="type" headerAlign="center" align="center" width="60">类型</div>
								<div field="category" name="category" headerAlign="center" align="center" width="60">资料类别</div>
								<div field="fileSize" headerAlign="center" align="center" width="60">大小</div>
								<div field="creationTime" name="creationTime" headerAlign="center" align="center" width="80" dateFormat="yyyy-MM-dd">上传时间</div>
								<div field="creationName" name="creationName" headerAlign="center" align="center" width="60">上传人</div>
								<div field="status" headerAlign="center" align="center" width="60">状态</div>
								<div field="action" headerAlign="center" align="center" width="150">操作</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td colspan="3" style="text-align: center;">   
				 	<a class="mini-button mini-button-primary exportWord"  style="width:60px;margin-left:20px;">导出</a>       
				 	<a class="mini-button mini-button-primary dosave"  style="width:60px;">确定</a>       
	            	<a class="mini-button mini-button-primary docancel"  style="width:60px;margin-left: 20px;">返回</a>     
            	</td>
			</tr>
        </table>
    </div>
</form>
</body>
</html>