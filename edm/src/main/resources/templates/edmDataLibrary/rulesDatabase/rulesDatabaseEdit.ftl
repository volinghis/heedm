<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>规章制度编辑</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script> 
	<script src="${globalContextPath}/res/third-party/webuploader/webuploader.min.js" type="text/javascript"></script>
	<script src="${globalContextPath}/res/js/fileUpload.js" type="text/javascript"></script>
	<link href="${globalContextPath}/res/third-party/webuploader/webuploader.css" rel="stylesheet"  type="text/css" />
	<script type="text/javascript" src="${globalContextPath}/js/edmDataLibrary/rulesDatabase/rulesDatabaseEdit.js"></script>
	<link href="${globalContextPath}/css/edmDataLibrary/edmDataLibraryEdit.css" rel="stylesheet" type="text/css" />
	<input id="" type="hidden" id="globalContextPath" value="${globalContextPath}" />
</head>
	<body>
		<form id="form" method="post">
		    <div>
			    <input id="filesJson" name="filesJson" id="filesJson" class="mini-hidden" />
			   	<input id="rulesDatabaseId" name="rulesDatabaseId" class="mini-hidden" value="${objectBean.id!''}"/>
		        <table>
		            <tr>
		           		<td>文件名称：</td>
		                <td colspan="3">
		                    <input id="name" name="name" required="true" class="mini-textbox" maxLength="100" style="width: 100%;" value="${objectBean.name!''}"/>
		                </td>
		                <td>    
		                    <input id="parentCode" name="parentCode" class="mini-hidden" maxLength="100" style="width: 100%;" value="${objectBean.parentCode!''}"/>
		                </td>
		           	</tr>
		            <tr >
						<td><label>附&nbsp;&nbsp;&nbsp;&nbsp;件：</label></td>
						<td colspan="3" height="200" >
							<div style="height: 100%">
								<div class="mini-datagrid" id="uploadGrid" onload="loadResult" style="width: 100%; height: 100%" showpager="false"
									url="${globalContextPath}/action/fileLists?dataCode=${objectBean.code!''}" 
									emptyText="尚未上传"  alwaysShowEmptyText="false" showEmptyText="false" showheader="false" showtoolbar="true"
									title="<font style='font-weight:bold'>文件上传</font>">
									
									<div property="toolbar" style="height: 30px; line-height: 27px">
										<a class="mini-button mini-button-primary" id="beginBtn">选择文件上传</a>
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
					<p>
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
