$(function () {
	queryAipProviderInfoList(1);
})

function queryAipProviderInfoList(pageNo) {
    var providerName = $("#providerName").val();
    var status = $("#status").val();
    var params = {
    		providerName:providerName,
    		status:status,
    		pageNo:pageNo
    };

    $.ajax({
        url:WEB_ROOT+"/aipProviderManage/queryAipProviderInfoList",
        type:'post',
        dataType:'html',
        data:params,
        async:true,
        success:function (data) {
        	$("#aipProviderInfoList").empty();
            $("#aipProviderInfoList").html(data);
        }
    })
}
function onImageFileChange(obj){
	
	uploadImage($(obj),function(data){
		//上传成功
		var fileId = data.fileId;
		var fileName = data.fileName;
		var fileType = data.fileType;
		var iamgeSize = "_80x80!";//可不设置
//		var imageUrl = WEB_SHOW_IMG_PATH + fileId +iamgeSize+"."+ fileType;
		var imageUrl = data.imageUrl;
		$("#providerLogo").val(fileId);
		$("#providerLogoUrl").attr("src",imageUrl)
	});
}
