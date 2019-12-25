<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>设备资料</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script> 
	<script src="${globalContextPath}/res/third-party/webuploader/webuploader.min.js" type="text/javascript"></script>
	<!--<script src="${globalContextPath}/js/edmDataLibrary/equipmentDatabase/fileUpload.js" type="text/javascript"></script>-->
	<script src="${globalContextPath}/res/js/fileUpload.js" type="text/javascript"></script>
	<link href="${globalContextPath}/res/third-party/webuploader/webuploader.css" rel="stylesheet"  type="text/css" />
	<script type="text/javascript" src="${globalContextPath}/js/edmDataLibrary/equipmentDatabase/equipmentDatabaseEdit.js"></script>
	<link href="${globalContextPath}/css/edmDataLibrary/edmDataLibraryEdit.css" rel="stylesheet" type="text/css" />
	<input id="" type="hidden" id="globalContextPath" value="${globalContextPath}" />
</head>
	<body>
		<form id="form" method="post">
		    <div>
			    <input id="filesJson" name="filesJson" id="filesJson" class="mini-hidden" />
			    <input id="nodeCode" name="nodeCode" value="${objectBean.nodeCode!''}" class="mini-hidden" />
		    	<input id="entityDataCode" name="entityDataCode" class="mini-hidden" value="${objectBean.entityDataCode!''}"/>
		    	<input id="fileInfoId" name="fileInfoId" class="mini-hidden" value="${objectBean.id!''}"/>
		        <table>
		            <tr>
		           		<td><label for="category$text">资料类型：</label></td>
		               	<td> <input id="category" name="category" allowinput="false" style="width: 100%;" required="true" value="${objectBean.category!''}" class="mini-treeselect" url="${globalContextPath}/action/dataDictionaryData?parentCode=edmEquipmentInfo" multiselect="false" textfield="text" valuefield="id" parentfield="pid"  showradiobutton="true" showTreeIcon="false" showTreeLines="false"  emptyText="---请选择---"   /></td>
	            		<td><label for="entityName$text">所属设备：</label></td>
		                <td>    
		                    <input id="entityName" name="entityName" class="mini-textbox" maxLength="100" style="width: 100%;" value="${objectBean.entityName!''}"/>
		                </td>
		           	</tr>
		            <tr >
						<td><label>上传资料：</label></td>
						<td colspan="3" height="200">
							<div style="height: 100%">
								<div class="mini-datagrid" id="uploadGrid" onload="loadResult" style="width: 100%; height: 100%" showpager="false" 
									emptyText="尚未上传"  alwaysShowEmptyText="false" showEmptyText="false" showheader="false" showtoolbar="true"
									title="equipmentDatabase">
									
									<div property="toolbar" style="height: 30px; line-height: 27px">
										<a class="mini-button mini-button-primary" id="beginBtn">添加</a>
										<div class="uploadPlaceholder" id="uploadPlaceholder"></div>
									</div>
									<div property="columns">
										<div field="name" headerAlign="center" align="center" width="150">文件名</div>
										<div field="type" headerAlign="center" align="center" width="80">类型</div>
										<div field="creationTime" name="creationTime" headerAlign="center" align="center" width="100" dateFormat="yyyy-MM-dd">时间</div>
										<div field="creationName" name="creationName"headerAlign="center" align="center" width="80">用户</div>
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
						 	<a class="mini-button mini-button-primary dosave"  style="width:60px;" >确定</a>       
			            	<a class="mini-button mini-button-primary docancel"  style="width:60px;margin-left: 20px;">取消</a>     
		            	</td>
					</tr>       
		        </table>
		    </div>
		</form>
	</body>
</html>
