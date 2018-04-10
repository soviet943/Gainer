var url = $('.cut-submit').data('url');
var img = 'http://120.78.164.143:8080/images/';
var pic_url;
var cover_filename="default";
$(function() {

	var game_type;//文章对应的游戏类型
	var article_type;//文章类型（饰品科普或新闻资讯等等）
	var title;//标题
	var brief_introduction;//简介
	var editor_html;//正文
	var editor = new wangEditor('#editor');
	editor.customConfig.uploadImgMaxSize = 2 * 1024 * 1024
	editor.customConfig.uploadImgMaxLength = 5
	editor.customConfig.uploadImgServer = "http://120.78.164.143:8080/aroma/picture/upload_article_pics"
	//editor.customConfig.uploadImgServer = "picture/upload_pics"
	editor.create();
	
	var picScale = {width: 200, height: 120};
	var $image = $('#cut-img');
	var $fileUp = $("#fileUp");
	var files, file;
	var picUrl;
	var cropBoxData;
	var canvasData;
	//兼容性判定
    var support = {
        fileList: !!$('<input type="file">').prop('files'),
        blobURLs: !!window.URL && URL.createObjectURL,
        formData: !!window.FormData
    };
    support.datauri = support.fileList && support.blobURLs;
	
	// +图标点击 触发上传图片操作
	$('#set_cover').delegate('#addPic', 'click', function() {
		$('input[name="fileUp"]').val("");
		$('input[name="fileUp"]').click();
	})
	$('#fileUp').change(function() {
		beginCut();
	})
	
	function beginCut() {
        if (support.datauri) {
            files = $fileUp.prop("files");
            if (files.length > 0) {
                file = files[0];
            }
            if (isImageFile(file)) {
                picUrl = URL.createObjectURL(file);
                startCropper();
            }
        }
    }
	
	//判断是否为图片格式
	function isImageFile(file) {
        if (file.type) {
            return /^image\/\w+$/.test(file.type);
        } else {
            return /\.(jpg|jpeg|png|gif)$/.test(file);
        }
    }
	
	//开始裁剪
	var active = false;
	function startCropper() {
		$('#cutimg').modal('show');
		if (active) {
            $img.cropper('replace', picUrl);
        } else {
        	$img = $('<img src="' + picUrl + '">');
            $(".cut-container").empty().html($img);
            $img.cropper({
                aspectRatio: picScale.width / picScale.height,
                autoCrop: false,
                zoomable: false,
                scalable: false,
                rotatable: false,
                mouseWheelZoom: true,
                ready: function () {
                    var result = $img.cropper("getImageData");
                    $img.cropper('crop');
                    $img.cropper('setData', {
                        width: picScale.bWidth,
                        height: picScale.bHeight
                    });
                }
            });
            
            $img.on('cropmove', function (e) {
                var data = $img.cropper('getData');
                if (data.width < picScale.width || data.height < picScale.height) {
                    e.preventDefault();
                }
            });
            $img.on('cropend', function (e) {
                var data = $img.cropper('getData');
                if (data.width < picScale.width || data.height < picScale.height) {
                    $img.cropper('setData', {
                        width: picScale.width,
                        height: picScale.height
                    });
                }
            });
            
            active = true;
            
        }
	}
	//删除裁剪
	function stopCropper() {
        if (active) {
            $img.cropper("destroy");
            $img.remove();
            $fileUp.val("");
            active = false;
        }
    }
	
	//压缩上传图片
	function convertCanvasToBlob(canvas) {
        var format = "image/jpeg";
        var base64 = canvas.toDataURL(format);
        var code = window.atob(base64.split(",")[1]);
        var aBuffer = new window.ArrayBuffer(code.length);
        var uBuffer = new window.Uint8Array(aBuffer);
        for(var i = 0; i < code.length; i++){
            uBuffer[i] = code.charCodeAt(i);
        }
        var Builder = window.WebKitBlobBuilder || window.MozBlobBuilder;
        if(Builder){
            var builder = new Builder;
            builder.append(uBuffer);
            return builder.getBlob(format);
        } else {
            return new window.Blob([ uBuffer ], {type: format});
        }
    }

	//提交上传图片按钮
	$(".cut-submit").on('click', function() {
		if(!$img) {
			alert("请上传图片！");
			return;
		}
		$img.cropper("getCroppedCanvas").toBlob(function (aaa) {
			var blob = convertCanvasToBlob($img.cropper("getCroppedCanvas"));
			if(blob.size > 1024*1024*1.5){
                alert('您上传的图片大于1.5M，请压缩后上传');
                return;
            };
            var formData = new FormData();
            formData.append('uploadFile', blob, file.name);
            
            $.ajax({
            	method: "POST",
            	url: 'http://120.78.164.143:8080/aroma/picture/upload_cover',
            	async: false,
            	data: formData,
            	processData: false,
                contentType: false,
                dataType: "text",
            	success: function (result) {
            		cover_filename = result;
            		pic_url = "http://120.78.164.143/images/discuss/cover/"+result;//实际ngix访问地址
            		$("#addPic").before('<span class="msg-pic-detail"><input type="hidden" name="image_url" value="' + result + '"><span class="msg-delete">&times;</span><img id="small_img" src="' + pic_url + '" height="46" width="76"  data-action="zoom"></span>');
            		$("#test_img").attr("src", pic_url);
                    $("#addPic").remove();
            		$('#cutimg').modal('hide');
            	}
            	
            })
		})
	})
	
	
	//预览
	$("#preview-btn").click(function() {
		var game_type = $("#select_game option:selected").val();
		var article_type = $("#select_type option:selected").val();
		var title = $("#input_title").val();
		var brief_introduction = $("#input_abstract");
		var editor_html = $(".w-e-text").html();
		$("#editor_preview").modal("show");
		$("#preview_title").html(title);
		$(".wiki-body").html(editor_html);
	})
	//发布
	$("#submit-btn").click(function() {
		var game_type = $("#select_game option:selected").val();
		var article_type = $("#select_type option:selected").val();
		var title = $("#input_title").val();
		var brief_introduction = $("#input_abstract").val();
		var editor_html = $(".w-e-text").html();
		var cover = cover_filename;
		$.ajax({
        	method: "POST",
        	url: 'discuss/submit',
        	data: {
        		game_type: game_type,
        		article_type: article_type,
        		title: title,
        		brief: brief_introduction,
        		content: editor_html,
        		cover: cover
        	},
        	success: function (result) {
        		if(result.success) {
        			alert("上传成功")
        		}
        	},
        	error: function (result) {
        		alert(result.msg);
        	}
        })
	})
	
	$("#set_cover").delegate('#small_img' ,'click', function() {
		$("#img_zoom_in").modal('show');
	}).delegate('.msg-delete','click', function() {
		$(".msg-pic-detail").before('<span class="glyphicon glyphicon-plus" id="addPic"></span>');
		$(".msg-pic-detail").remove();
	})
	$("#zoom-close").on('click', function() {
		$("#img_zoom_in").modal('hide');
	})
	$("#preview").bind('click', function() {
		$('input[name="fileUp"]').click();
	})
	$("#preview_close").on('click', function() {
		$("#editor_preview").modal('hide');
	})
})