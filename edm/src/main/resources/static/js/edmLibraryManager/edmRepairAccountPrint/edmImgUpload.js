$(document).ready(function() {	
		mini.parse();
     // 使用 IE6，7，8 时，如果 Flash 播放器版本低则需要升级一下
        if (!WebUploader.Uploader.support()) {
            alert( 'Web Uploader 不支持您的浏览器！如果你使用的是 IE 浏览器，请尝试升级 Flash 播放器');
            throw new Error( 'WebUploader does not support the browser you are using.' );
        }
        
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
            accept: {
                title: 'Files',
                extensions: 'pdf,docx,doc,xls,xlsx,txt,png,jpg',
                mimeTypes: 'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/pdf,text/*,img/*'
            },
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
        	mini.get("fileId").setValue(res.entityId);
        	$("#myObj").attr("src",$ctx+'/action/viewFile?fileId='+res.entityId);
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
	        	mini.alert("暂不支持该格式");
	        } else if (type == "Q_EXCEED_SIZE_LIMIT") {
	            mini.alert('文件[' + file.name + ']大小超出限制值');
	        }else if(type="Q_EXCEED_NUM_LIMIT "){
	        	mini.alert('只允许上传一份文件');
	        } else {
	            mini.alert("上传出错！请检查后重新上传！错误代码" + type);
	        }
	    });

});       