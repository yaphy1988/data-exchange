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
function saveAipProviderInfo(){
	var providerId=$("#providerId").val();
	var providerName=$("#providerNameInput").val();
	var providerDesc=$("#providerDesc").val();
	var providerLogo=$("#providerLogo").val();
	if(providerName.length>128){
		WEB.msg.error("提示","服务供应商名称不能超过128个字符");
		return;
	}
	if(providerDesc.length>1024){
		WEB.msg.error("提示","描述不能超过1024个字符");
		return;
	}
	var params={
			providerId:providerId,
			providerName:providerName,
			providerDesc:providerDesc,
			providerLogo:providerLogo
	}
	 $.ajax({
	        url:WEB_ROOT+"/aipProviderManage/saveAipProviderInfo",
	        type:'post',
	        dataType:'json',
	        data:params,
	        async:true,
	        success:function (data) {
	        	WEB.msg.info("提示","保存Aip供应商信息成功！",function(){
		        	$("#myModalAip").modal("hide");
		        	queryAipProviderInfoList(1);
	        	});
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
