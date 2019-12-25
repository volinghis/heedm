<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>备件信息编辑</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script> 
	<script src="${globalContextPath}/res/third-party/webuploader/webuploader.min.js" type="text/javascript"></script>
	<script src="${globalContextPath}/res/js/fileUpload.js" type="text/javascript"></script>
	<link href="${globalContextPath}/res/third-party/webuploader/webuploader.css" rel="stylesheet"  type="text/css" />
	<script type="text/javascript" src="${globalContextPath}/js/edmPartLibrary/edmPartLibraryEdit.js"></script>
	<link href="${globalContextPath}/css/edmPartLibrary/edmPartLibraryEdit.css" rel="stylesheet" type="text/css" />
	<input id="" type="hidden" id="globalContextPath" value="${globalContextPath}" />
</head>
	<body>
		<form id="form" method="post">
		    <div>
			    <input id="filesJson" name="filesJson" id="filesJson" class="mini-hidden" />
		    	<input id="edmPartLibraryId" name="edmPartLibraryId" class="mini-text" value="${objectBean.id!''}"  style="display:none"/>
		        <table style="width: 640;">
		            <tr>
		                <td>备件名称：</td>
		                <td>    
		                    <input id="name" name="name" value="${objectBean.name!''}" class="mini-textbox" required="true" emptyText="请输入备件名称"  maxLength="100" style="width: 100%;"/>
		                </td>
		                <td>归属设备：</td>
		                <td>    
			                <input id="type" name="type" class="mini-textbox" maxLength="100" style="width: 100%;" value="${typeName!''}" readonly="readonly"/>
		                </td> 
		            </tr>
		            <tr>   
		              	<td>库&nbsp;&nbsp;存&nbsp;&nbsp;量：</td>
		                <td>    
		                    <input id="amount" name="amount" value="${objectBean.amount!''}" class="mini-textbox" maxLength="100" style="width: 100%;"/>
		                </td>
		                <td>规格型号：</td>
		                <td>
		                    <input id="norm" name="norm" value="${objectBean.norm!''}"  class="mini-textbox" emptyText="请输入备件规格"  maxLength="100" style="width: 100%;" />
		                </td>
		           	</tr>
		            <tr>
		                <td>生产厂家：</td>
		                <td>    
		                  <!--  <input id="manufacturer" name="manufacturer" allowinput="false" style="width: 100%;" required="true" class="mini-treeselect" 
			       				url="${globalContextPath}/action/dataDictionaryData?parentCode=manufacturer" multiselect="false" textfield="text" valuefield="id" 
			       				parentfield="pid" value="${objectBean.manufacturer!''}" showradiobutton="true" showTreeIcon="false" showTreeLines="false"  
			       				emptyText="---请选择---" popupWidth="260"  onbeforenodeselect="beforenodeselect" />
			       			-->	
		                    <input id="manufacturer" name="manufacturer" value="${objectBean.manufacturer!''}" class="mini-textbox" maxLength="100" style="width: 100%;"/>
			       				
		                </td>
		              	<td>供&nbsp;&nbsp;应&nbsp;&nbsp;商：</td>
		                <td>  
		                 <!--  
		                    <input id="supplier" name="supplier" allowinput="false" style="width: 100%;" required="true" class="mini-treeselect" 
			       				url="${globalContextPath}/action/dataDictionaryData?parentCode=supplier" multiselect="false" textfield="text" valuefield="id" 
			       				parentfield="pid" value="${objectBean.supplier!''}" showradiobutton="true" showTreeIcon="false" showTreeLines="false"  
			       				emptyText="---请选择---" popupWidth="260"  onbeforenodeselect="beforenodeselect" />
			       		-->
		                    <input id="supplier" name="supplier" value="${objectBean.supplier!''}" class="mini-textbox" maxLength="100" style="width: 100%;"/>
		                </td>
		            </tr>
		            <tr>
		                <td>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</td>
		                <td>
		                    <input id="purchaseAmount" name="purchaseAmount" value="${purchaseAmount!''}" emptyText="请输入价格(数字)" class="mini-textbox" maxLength="100" style="width: 100%;"/>
		                </td>
		                <td>预&nbsp;&nbsp;警&nbsp;&nbsp;值：</td>
		                <td>    
		                    <input id="warningValue" name="warningValue" value="${objectBean.warningValue!''}" class="mini-textbox" maxLength="100" vtype="int" style="width: 100%;"/>
		                </td>
		            </tr>
		            <tr>
		                <td>物资编码：</td>
		                <td>    
		                    <input id="labelCode" name="labelCode" value="${objectBean.labelCode!''}" class="mini-textbox" maxLength="100" style="width: 100%;"/>
		                </td>
		              	<td>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</td>
		                <td>    
		                	<input id="profession" name="profession" class="mini-textbox" maxLength="100" style="width: 100%;" value="${profession!''}"/>
		                </td>
		            </tr> 
		            <tr>
		            	<td>物资类别：</td>
		            	<td>
		            		<input id="materialTypeCode" name="materialTypeCode" allowinput="false" style="width: 100%;" required="true" class="mini-treeselect" 
    			       			url="${globalContextPath}/action/dataDictionaryData?parentCode=materialType" multiselect="false" textfield="text" 
    			       			valuefield="id" parentfield="pid" value="${objectBean.materialTypeName!''}" showradiobutton="true" 
    			       			showTreeIcon="false" showTreeLines="false"  emptyText="---请选择---" onbeforenodeselect="beforenodeselect"/>
		            	</td>
		            	<td></td>
		            	<td></td>
		            </tr>
		            <tr>
	                    <input id="parentCode" name="parentCode" class="mini-hidden" maxLength="100" style="width: 100%;" value="${parentCode!''}"/>
	                    <input id="nodeCode" name="nodeCode" class="mini-hidden" maxLength="100" style="width: 100%;" value="${parentNodeCode!''}"/>
		            </tr>
		            <tr >
						<td><label>附件：</label></td>
						<td colspan="3" height="200" >
							<div style="height: 100%">
								<div class="mini-datagrid" id="uploadGrid" onload="loadResult" url="${globalContextPath}/action/fileLists?dataCode=${code!''}" 
									style="width: 100%; height: 100%" showpager="false" emptyText="尚未上传"  alwaysShowEmptyText="false" showEmptyText="false" 
									showheader="false" showtoolbar="true" title="<font style='font-weight:bold'>文件上传</font>">
									
									<div property="toolbar" style="height: 30px; line-height: 27px">
										<a class="mini-button mini-button-primary" id="beginBtn" >选择文件上传</a>
										<div class="uploadPlaceholder" id="uploadPlaceholder"></div>
									</div>
									<div property="columns">
										<div field="name" headerAlign="center" align="center" width="150">文件名</div>
										<div field="type" headerAlign="center" align="center" width="60">类型</div>
										<div field="fileSize" headerAlign="center" align="center" width="80">大小</div>
										<div field="creationTime" name="creationTime" headerAlign="center" align="center" width="80" dateFormat="yyyy-MM-dd">上传时间</div>
										<div field="creationName" name="creationName" headerAlign="center" align="center" width="60">上传人</div>
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
						 	<a class="mini-button mini-button-primary exportWord"  style="width:60px;">导出</a>       
						 	<a class="mini-button mini-button-primary dosave"  style="width:60px;">确定</a>       
			            	<a class="mini-button mini-button-primary docancel"  style="width:60px;margin-left: 20px;">取消</a>     
		            	</td>
					</tr>       
		        </table>
		    </div>
		</form>
	</body>
</html>
