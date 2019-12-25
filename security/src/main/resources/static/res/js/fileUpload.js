$(document).ready(function() {
		mini.parse();
	   var grid = mini.get("uploadGrid");
	   if(grid.title!="equipmentDatabase"){//剔除设备资料文件上传数据加载
		   grid.load();
	   }

						   
	   grid.on("drawcell", function (e) {
	        var field = e.field;
	        var record = e.record;
	        var uid = record._uid;
	        var fileId=record.fileId;
	        var value = e.value;
	        if (field == "status") {
	            if (record.status == "等待上传") {

	            } else if (record.status == "已完成") {

	            } else if (record.status == "上传成功") {

	            } else {
	            	if(value){
		                e.cellHtml = '<div class="progressbar">'
                            + '<div class="progressbar-percent" style="width:' + value + '%;"></div>'
                            + '<div class="progressbar-label">' + value + '%</div>'
                        + '</div>';
	            	}else{
	            		e.cellHtml ='<div class="mini-grid-cell-inner  mini-grid-cell-nowrap " style="">已完成</div>';
	            	}
	            	

	            }
	        }
	        if (field == "action") {
//	            if (record.action == "ok") {
	        	e.cellHtml = '<a href="#" class="file-remove" name="'+uid+'" >删除</a>&nbsp;&nbsp;<a href="'+$ctx+'/action/downloadFile?fileId='+fileId+'"   target="_blank" >下载</a>'
	        					+ '&nbsp;<a class="file-view" view="'+fileId+'" href="#" >预览</a>';
//	            }
	        }
	    })


	    var uploader = WebUploader.create({

	        // swf文件路径
	        swf: $ctx + '/res/third-party/webuploaderr/Uploader.swf',

	        // 文件接收服务端。
	        server: $ctx + '/action/fileUpload',

	        // 选择文件的按钮。可选。
	        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	        pick: '#uploadPlaceholder',

	        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	        resize: false,

	        // 自动上传
	        auto: true,

	        // 文件上传参数表，用来标记文件的所有者（如果是一篇文章的附件，就是文章的ID）
	        formData: {
	            owner: 'webuploader.webuploader'
	        },
	        fileVal: 'Fdata',
	        // 单个文件大小限制（单位：byte），这里限制为 100M
	        fileSingleSizeLimit: 1000 * 1024 * 1024

	    });

	    // 添加到上传队列
	    uploader.on('fileQueued', function (file) {
	        var size = bytesToSize(file.size);
	        console.log(file.ext);
	        var row = { fileId: file.id, name: file.name, type: file.ext, fileSize: size, status: "等待上传" };
	        grid.addRow(row);

	    });

	    uploader.on('uploadProgress', function (file, percentage) {

	        // var percent = mini.formatNumber(complete / total, "n2") * 100;

	        var row = grid.findRow(function (row) {
	            if (row.fileId == file.id) return true;
	        })
	        grid.updateRow(row, { status: percentage });


	    });

	    uploader.on('uploadSuccess', function (file,e) {

	    	Object.keys(e).forEach(function(key){

	    	     console.log(key,e[key]);

	    	});
	        var row = grid.findRow(function (row) {
	            if (row.fileId == file.id) return true;
	        })
	        row.fileId=e.entityId;
	        grid.updateRow(row, { status: "上传成功", action: "ok" });

	    });

	    uploader.on('uploadError', function (file, reason) {


	        var row = grid.findRow(function (row) {
	            if (row.fileId == file.id) return true;
	        })
	        grid.updateRow(row, { status: "上传出错" });
	    });

	    // 不管上传成功还是失败，都会触发 uploadComplete 事件
	    uploader.on('uploadComplete', function (file) {
	        uploader.removeFile(file, true);
	    });


	    // 当开始上传流程时触发
	    uploader.on('startUpload', function () {
	        mini.get("beginBtn").setEnabled(false);
	    });

	    // 当所有文件上传结束时触发
	    uploader.on('uploadFinished', function () {
	        mini.get("beginBtn").setEnabled(true);
	    });


	    uploader.on('error', function (type, arg, file) {
	        if (type == "Q_TYPE_DENIED") {
	            mini.alert("文件版本过低，请上传.docx文件");
	        } else if (type == "Q_EXCEED_SIZE_LIMIT") {
	            mini.alert('文件[' + file.name + ']大小超出限制值');
	        } else {
	            mini.alert("上传出错！请检查后重新上传！错误代码" + type);
	        }
	    });
	    $(document.body).on("click", ".file-remove", function (e) {
	        var uid = $(this).attr("name");
	        var row = grid.getRowByUID(uid);
	        grid.removeRow(row);
	    })
	    
	     $(document.body).on("click", ".file-view", function (e) {
	    	 var id=$(this).attr('view');
	    	 mini.open({
	    		showMaxButton: true, 
	 	        url:$ctx+'/action/viewFile?fileId='+id,
	 	        title: "预览页面", width:800, height:850,
	 	    });
	    })

});

function bytesToSize(bytes) {
    if (bytes === 0) return '0 B';
    var k = 1024,
        sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
       i = Math.floor(Math.log(bytes) / Math.log(k));

    return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
}

function getUploadGridData()
{
	 var grid = mini.get("uploadGrid");
     var data = grid.getData();
     var json = mini.encode(data);
     return json;
}