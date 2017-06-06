$(function() {
	queryAipProviderInfoList(1);
});

function queryAipProviderInfoList(pageNo) {
	var providerName = $("#providerName").val();
	var status = $("#status").val();
	var params = {
		providerName : providerName,
		status : status,
		pageNo : pageNo
	};
	$.ajax({
		url : WEB_ROOT + "/aipProviderManage/queryAipProviderInfoList",
		type : 'post',
		dataType : 'html',
		data : params,
		async : true,
		success : function(data) {
			$("#aipProviderInfoList").empty();
			$("#aipProviderInfoList").html(data);
		}
	});
}
function addAipModal() {
	$("#providerId").val("");
	$("#providerNameInput").val("");
	$("#providerDesc").val("");
	$("#providerLogoUrl").attr("src", "")
	$("#providerLogo").val("");
	$("#myModalAip").modal();
}
function editAipModal(providerId) {
	$("#providerId").val("");
	$("#providerNameInput").val("");
	$("#providerDesc").val("");
	$("#providerLogoUrl").attr("src", "")
	$("#providerLogo").val("");
	var params = {
		providerId : providerId
	};
	$.ajax({
		url : WEB_ROOT + "/aipProviderManage/getAipProviderInfo",
		type : 'post',
		dataType : 'json',
		data : params,
		async : true,
		success : function(data) {
			if (data.success) {
				$("#providerId").val(data.providerInfo.providerId);
				$("#providerNameInput").val(data.providerInfo.providerName);
				$("#providerDesc").val(data.providerInfo.providerDesc);
				$("#providerLogoUrl").attr("src",
						data.providerInfo.providerLogoUrl);
				$("#providerLogo").val(data.providerInfo.providerLogo);
				$("#myModalAip").modal();
			} else {
				WEB.msg.info("提示", "保存Aip供应商信息失败！");
			}
		}
	});
}
function saveAipProviderInfo() {
	var providerId = $("#providerId").val();
	var providerName = $("#providerNameInput").val();
	var providerDesc = $("#providerDesc").val();
	var providerLogo = $("#providerLogo").val();
	if (providerName == "") {
		WEB.msg.error("提示", "服务供应商名称不能为空!");
		return;
	}
	if (providerName.length > 128) {
		WEB.msg.error("提示", "服务供应商名称不能超过128个字符!");
		return;
	}
	if (providerDesc.length > 1024) {
		WEB.msg.error("提示", "描述不能超过1024个字符!");
		return;
	}
	var params = {
		providerId : providerId,
		providerName : providerName,
		providerDesc : providerDesc,
		providerLogo : providerLogo
	};
	$.ajax({
		url : WEB_ROOT + "/aipProviderManage/saveAipProviderInfo",
		type : 'post',
		dataType : 'json',
		data : params,
		async : true,
		success : function(data) {
			if (data.success) {
				WEB.msg.info("提示", "保存Aip供应商信息成功！", function() {
					$("#myModalAip").modal("hide");
					queryAipProviderInfoList(1);
				});
			} else {
				WEB.msg.info("提示", "保存Aip供应商信息失败！");
			}
		}
	});

}
function updateAipProviderInfo(providerId, status) {
	var text = "";
	if (status == '1') {
		text = "生效";
	} else {
		text = "失效";
	}
	var params = {
		providerId : providerId,
		status : status
	};
	$.ajax({
		url : WEB_ROOT + "/aipProviderManage/updateAipProviderInfoStatus",
		type : 'post',
		dataType : 'json',
		data : params,
		async : true,
		success : function(data) {
			if (data.success) {
				WEB.msg.info("提示", text + "操作成功！", function() {
					queryAipProviderInfoList(1);
				});
			} else {
				WEB.msg.info("提示", text + "操作失败！");
			}
		}
	});

}
function onImageFileChange(obj) {

	uploadImage($(obj), function(data) {
		//上传成功
		var fileId = data.fileId;
		var fileName = data.fileName;
		var fileType = data.fileType;
		var iamgeSize = "_80x80!";//可不设置
		//		var imageUrl = WEB_SHOW_IMG_PATH + fileId +iamgeSize+"."+ fileType;
		var imageUrl = data.imageUrl;
		$("#providerLogo").val(fileId);
		$("#providerLogoUrl").attr("src", imageUrl)
	});
}
function clickSelAipAll(obj) {
	if ($(obj).is(":checked")) {
		$("input[name=delAipList]").each(function() {
			$(this).attr("checked",true);
		});
	} else {
		$("input[name=delAipList]").each(function() {
			$(this).removeAttr("checked");
			$(this).prop("checked", false);

		});
	}
}
function clickDelAipList(obj){
	if (!$(obj).is(":checked")) {
		 $("input[name=selectAipAll]").prop("checked", false);
	} else if ($(obj).is(":checked")) {
		var allFlag = true;
		 $("input[name=delAipList]").each(function() {
			if (!$(this).attr("checked")) {
				allFlag = false;
			}
		});
		if (allFlag) {
			$("input[name=selectAipAll]").prop("checked", true);
		} else {
			$("input[name=selectAipAll]").prop("checked", false);
		}
	}
}
