<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑面板</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
    <script src="${globalContextPath}/js/edmLibraryManager/edmLibrary/edmLibraryEdit.js" type="text/javascript"></script>
    <script src="${globalContextPath}/res/third-party/webuploader/webuploader.min.js" type="text/javascript"></script>
    <script src="${globalContextPath}/res/js/fileUpload.js" type="text/javascript"></script>
    <link href="${globalContextPath}/res/third-party/webuploader/webuploader.css" rel="stylesheet"  type="text/css" />
    <link href="${globalContextPath}/css/edmLibraryManager/edmLibrary/edmLibraryEdit.css" rel="stylesheet" type="text/css" />
</head>
<body >
       
<form id="edmLibraryForm">
    <div id="form1">
        <input name="edmLibraryId" id="edmLibraryId" class="mini-hidden" value="${objectBean.id!''}" />
        <input name="filesJson" id="filesJson" class="mini-hidden" />
        <input name="libraryParamJson" id="libraryParamJson" class="mini-hidden" />
        <table width="640">
            <tr>
               <td>
                    <label for="deviceShortName$text">设备简称：</label>
                </td>
                <td>
                    <input id="deviceShortName" name="deviceShortName" value="${objectBean.deviceShortName!''}" class="mini-textbox" maxLength="100" style="width: 100%;"  required="true"/>
                </td>
                <td>
                    <label for="deviceType$text">设备类型：</label>
                </td>
                <td>
                    <input id="deviceType"  name="deviceType" allowInput="false"   style="width: 100%;" required="true" class="mini-treeselect" url="${globalContextPath}/action/dataDictionaryTreeData?parentCode=edmBasicType" multiSelect="false"  valueFromSelect="false"
       					 textField="name" valueField="id" parentField="pid" value="${objectBean.deviceType!''}"  text="${objectBean.deviceTypeName!''}"
       					 showRadioButton="true" showFolderCheckBox="false"  onbeforenodeselect="beforenodeselect"/>
                </td>
            </tr>
            <tr>
               <td>
                    <label for="keepRepairTime$text">保修周期(月)：</label>
                </td>
                <td>
                 	<input id="keepRepairTime" name="keepRepairTime" class="mini-spinner" value="${objectBean.keepRepairTime!''}" minValue="1" maxValue="1200"  style="width: 100%;"  required="true"/>
                </td>
                <td>
                    <label for="deprecateTime$text">报废周期(月)：</label>
                </td>
                <td>
                	<input id="deprecateTime" name="deprecateTime" class="mini-spinner" value="${objectBean.deprecateTime!''}" minValue="1" maxValue="1200"  style="width: 100%;"  required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                    <label for="checkRate$text">检修频率(次/月)：</label>
                </td>
                <td>
                 	<input id="checkRate" name="checkRate" class="mini-spinner" value="${objectBean.checkRate!''}" minValue="1" maxValue="1200"  style="width: 100%;"  required="true"/>
                </td>
               	<td>
                    <label for="price$text">价格(万元)：</label>
                </td>
                <td>
                 	<input id="price" name="price" class="mini-textbox" value="${objectBean.price!''}" maxLength="5" style="width: 100%;"  vtype="float"/>
                </td>
        	</tr>
            <tr>
             	<td>
                    <label for="brand$text">品牌：</label>
                </td>
                <td>
                 	<input id="brand" name="brand" class="mini-textbox" value="${objectBean.brand!''}"     maxLength="100"  style="width: 100%;"  />
                </td>
               	<td>
                    <label for="deviceModel$text">设备型号：</label>
                </td>
                <td>
                 	<input id="deviceModel" name="deviceModel" class="mini-textbox" value="${objectBean.deviceModel!''}"    maxLength="100" style="width: 100%;" />
                </td>
        	</tr>
			<tr>
				<td>
                    <label for="productFactoryNumber$text">生产商：</label>
                </td>
                <td>
                    <input id="productFactoryNumber" name="productFactoryNumber" value="${objectBean.productFactoryNumber!''}"  class="mini-textbox" maxLength="100" style="width: 100%;" />
                </td>
                 <td>
                    <label for="supplyFactoryNumber$text">供应商：</label>
                </td>
                <td>
                    <input id="supplyFactoryNumber"  value="${objectBean.supplyFactoryNumber!''}"   name="supplyFactoryNumber" class="mini-textbox" maxLength="100" style="width: 100%;" />
                </td>
            </tr>
            <tr >
                <td >
                    <label for="decription$text">描述：</label>
                </td>
                <td colspan="3" height="100">
                    <input id="decription" name="decription"  value="${objectBean.decription!''}"  class="mini-textarea" maxLength="1000" style="width: 100%;height: 100%;"/>
                </td>
            </tr>
            
            <tr>
            	<td>
            		<label for="decription$text">设备参数：</label>
            	</td>
            	<td colspan="3" height="150">
            		<div style="height: 100%">
	            		<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;aglin:center" allowResize="false" showpager="false"
					      <#--    url="${globalContextPath}/action/edmAssembly/edmAssemblyList?dataCode=${objectBean.dataCode!''}"  -->
					      	url="${globalContextPath}/action/edmLibraryParam/paramList?dataCode=${objectBean.code!''}"
					        idField="id"  showtoolbar="true" allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        					editNextOnEnterKey="true"  editNextRowCell="true" showFilterRow="false" >
					        
						    <div property="toolbar" style="height: 30px; line-height: 27px">
								<a class="mini-button" iconcls="icon-add"  plain="true" onclick="addRow()" id="beginBtn">新增</a>
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
				<td><label>附件：</label></td>
				<td colspan="3" height="200">
					<div style="height: 100%">
						<div class="mini-datagrid" id="uploadGrid" onload="loadResult"
							url="${globalContextPath}/action/fileLists?dataCode=${objectBean.code!''}"
							style="width: 100%; height: 100%" showpager="false"
							emptyText="尚未上传" alwaysShowEmptyText="false"
							showEmptyText="false" showheader="false" showtoolbar="true"
							title="<font style='font-weight:bold'>文件上传</font>">

							<div property="toolbar" style="height: 30px; line-height: 20px">
								<a class="mini-button" plain="true" iconcls="icon-add" id="beginBtn">选择文件上传</a>
								<div class="uploadPlaceholder" id="uploadPlaceholder"></div>
							</div>
							<div property="columns">
								<div field="name" headerAlign="center" align="center" width="150">文件名</div>
								<div field="type" headerAlign="center" align="center" width="80">类型</div>
								<div field="fileSize" headerAlign="center" align="center" width="80">大小</div>
								<div field="status" headerAlign="center" align="center" width="80">状态</div>
								<div field="action" headerAlign="center" align="center" width="150">操作</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td colspan="3" style="text-align: center;">   
				 	<a class="mini-button dosave"  style="width:60px;">确定</a>       
	            	<a class="mini-button docancel"  style="width:60px;margin-left: 20px;">返回</a>     
            	</td>
			</tr>
        </table>
    </div>
</form>
</body>
</html>