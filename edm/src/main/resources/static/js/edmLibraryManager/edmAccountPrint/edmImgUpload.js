var grid_d;
$(document).ready(function() {	
		mini.parse();
		var grid=top["win"].mini.get("uploadGrid");
		grid_d=grid;
		//console.log(grid_d);
		
		
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
		        	e.cellHtml = '<a href="#" class="file-remove" name="'+uid+'" >删除</a>&nbsp;&nbsp;<a href="'+$ctx+'/action/downloadFile?fileId='+fileId+'"   target="_blank" >下载</a>'
		        					+ '&nbsp;<a class="file-view" view="'+fileId+'" href="#" >预览</a>';
		        }
		    })

		
     // 初始化Web Uploader
        var uploader = WebUploader.create({

            // 选完文件后，是否自动上传。
            auto: true,
            // swf文件路径
            swf: $ctx + '/res/third-party/webuploaderr/Uploader.swf',

            // 文件接收服务端。
            server: $ctx + '/action/fileUpload',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker',
            
            fileVal: 'Fdata',
            // 只允许选择图片文件。
//            accept: {
//                title: 'Files',
//                extensions: 'pdf,docx,doc,txt,png,jpg',
//                mimeTypes: 'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document'+',application/pdf,text/*,img/*'
//            },
        	fileNumLimit:1,
        	// 单个文件大小限制（单位：byte），这里限制为 100M
	        fileSingleSizeLimit: 100 * 1024 * 1024
        })
        
        var $list = $("#thelist");
        
        // 当有文件被添加进队列的时候
        uploader.on( 'fileQueued', function( file ) {
            $list.append( '<div id="' + file.id + '" class="item">' +
                '<h4 class="info">' + file.name + '</h4>' +
                '<p class="state">等待上传...</p>' +
            '</div>' );
        });
        
        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                $percent = $li.find('.progress .progress-bar');
            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                  '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                  '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css( 'width', percentage * 100 + '%' );
        });
        
        uploader.on( 'uploadSuccess', function( file,res ) {
        	var c=mini.get("category").getText();
        	var size=bytesToSize(file.size);
        	var row = { fileId: res.entityId, name: file.name, type: file.ext, fileSize:size,category:c};
        	console.log(row);
	        grid_d.addRow(row);
	        closeWin(true);
        	
            $( '#'+file.id ).find('p.state').text('已上传');
        });

        uploader.on( 'uploadError', function( file ) {
        	
            $( '#'+file.id ).find('p.state').text('上传出错');
        });

        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });

        //当validate不通过时，会以派送错误事件的形式通知调用者
        uploader.on('error', function (type, arg, file) {
	        if (type == "Q_TYPE_DENIED") {
	        	mini.alert("文件版本过低，请上传.docx文件");
	        } else if (type == "Q_EXCEED_SIZE_LIMIT") {
	            mini.alert('文件[' + file.name + ']大小超出限制值');
	        }else if(type="Q_EXCEED_NUM_LIMIT "){
	        	mini.alert('只允许上传一份文件');
	        } else {
	            mini.alert("上传出错！请检查后重新上传！错误代码" + type);
	        }
	    });

        $('#picker').on('click',function(){
        	var cat=mini.get("category").getValue();
        	console.log($(this));
        	if(cat==""){
        		mini.showTips({
                    content: "请选择资料类别",
                    state:"warning",
                    x:"center",
                    y: "center",
                    timeout: 2000
                });
        		return false;
        	}
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

function getUploadGridData(){
     var data = grid_d.getData();
     var json = mini.encode(data);
     return json;
}