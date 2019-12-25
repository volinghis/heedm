<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>设备资料上传</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
    <script src="${globalContextPath}/js/edmLibraryManager/edmAccountPrint/edmImgUpload.js" type="text/javascript"></script>
    <script src="${globalContextPath}/res/third-party/webuploader/webuploader.min.js" type="text/javascript"></script>
    <link href="${globalContextPath}/res/third-party/webuploader/webuploader.css" rel="stylesheet"  type="text/css" />
    <link href="${globalContextPath}/css/edmLibraryManager/edmAccountPrint/edmAccountPrintEdit.css" rel="stylesheet" type="text/css" />

</head>
	<body>
		<form id="form" method="post">
		    <div>
			    <input id="filesJson" name="filesJson" id="filesJson" class="mini-hidden" />
		        <table>
		            <tr>
		                <td><label for="category$text">资料类型：</label></td>
		               	<td> <input id="category" name="category" allowinput="true" style="width: 100%;" required="true"  class="mini-treeselect" url="${globalContextPath}/action/dataDictionaryData?parentCode=edmEquipmentInfo" multiselect="false" textfield="text" valuefield="id" parentfield="pid"  showradiobutton="true" showTreeIcon="false" showTreeLines="false"  emptyText="---请选择---"   /></td>
		                </td>
		           	</tr>
		            <tr >
						<td><label>设备资料：</label></td>
						<td>
							<div id="picker" style="float:left;" ><a class="mini-button mini-button-primary" id="beginBtn">选择文件</a></div>
							<!--用来存放文件信息-->
   							<!-- <div id="thelist" class="uploader-list"></div>-->
						</td>
					</tr>
		        </table>
		    </div>
		</form>
	</body>
</html>
