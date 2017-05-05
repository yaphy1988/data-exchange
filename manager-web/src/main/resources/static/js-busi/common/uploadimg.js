(function($){
	
})(jQuery);

function uploadImage($file,callback){
	
	var fileName = $file.val();
    var ext = "";
    var arr = fileName.split(".");
    if (arr.length > 1){
	    ext = arr[arr.length - 1].toLowerCase();
    }
	
    if(ext == "jpg" || ext == "png" || ext == "gif"){
    }else{
    	$file.after($file.clone().val(""));
    	$file.remove();
    	alert('请选择jpg或png格式文件！');
    	return;
    }
	var fileElementId = $file.attr("id");
	$.ajaxFileUpload({
		url : WEB_ROOT+"/fileupload/imgupload", // 用于文件上传的服务器端请求地址
		secureuri : false, // 一般设置为false
		fileElementId : fileElementId, // 文件上控件的id属性 <input
		type : "post", // get 或 post
		dataType:'json', // 返回值类型
		success : function(data){
			if(data.status == "1"){
				callback(data);
			}else{
				WEB.msg.info('提示',data.msg);
			}
		}, // 服务器成功响应处理函数
		error : function(data, status, e) // 服务器响应失败处理函数
		{
			alert(e);
		}
	});
}