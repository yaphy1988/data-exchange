var ckedit = null;
$(document).ready(function(){
	
});

function onImageFileChange(obj){
	
	uploadImage($(obj),function(data){
		//上传成功
		var fileId = data.fileId;
		var fileName = data.fileName;
		var fileType = data.fileType;
		
		var iamgeSize = "_80x80";//可不设置
		var imageUrl = WEB_SHOW_IMG_PATH + fileId +iamgeSize+"."+ fileType;
		$("#iamgeTag").attr("src",imageUrl);
	});
}