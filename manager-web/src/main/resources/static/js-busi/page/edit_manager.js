var basePath = WEB_ROOT;
$(function(){

})
/**
 * 保存编辑模块
 */
function savePageModule(){
	var moduleId=$("#moduleId").val();
	var moduleName=$("#moduleName").val();
	var modulePid=$("#modulePid").val();
	var moduleCount=$("#moduleCount").val();
	var orderNo=$("#orderNo").val();
	var status=$('input[name="status"]:checked').val();
	var remark=$("#remark").val();
	if(moduleName==""){
		WEB.msg.info("提示","模块名称不能为空");
		return;	
	}
	if(moduleName.length>128){
		WEB.msg.info("提示","模块名称不能超过128个字符");
		return;
	}
	if(moduleCount==""){
		WEB.msg.info("提示","展示数量不能为空");
		return;
	}
	if(!isInteger(moduleCount)){
		WEB.msg.info("提示","展示数量只能是正整数！");
		return;
	}
	if(remark.length>128){
		WEB.msg.info("提示","备注不能超过128个字符");
		return;
	}
	var params={
			moduleName:moduleName,
			moduleId:moduleId,
			modulePid:modulePid,
			moduleCount:moduleCount,
			orderNo:orderNo,
			remark:remark,
			status:status
			};
	var url=basePath+"/pageManage/savePageModule";
	$.ajax({
			url:url,
			cache:false,
			async:true,
			dataType:'json',
			data : params,
			success:function(data){
           	 WEB.msg.info("提示","保存成功",function(){
             	window.location.href = basePath+"/pageManage/moduleManage";
           	 });
		}
	});
}

//校验输入是否为正整数
function isInteger(str){
	 var reg = /^(([1-9][0-9]*))$/;
	 return reg.test(str);
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
		$("#vfsId").val(fileId);
		$("#imgSrcURL").attr("src",imageUrl)
	});
}
/**
 * 保存广告信息
 */
function savePageModuleAd(){
	var adId=$("#adId").val();
	var moduleId=$("#moduleId").val();
	var adTitle=$("#adTitle").val();
	var linkPage=$("#linkPage").val();
	var bmpName=$("#bmpName").val();
	var vfsId=$("#vfsId").val();
	var url=basePath+'/pageManage/savePageModuleAdInfo';
	if(adTitle==""){
		WEB.msg.info("提示","广告语不能为空");
		return;	
	}
	if(adTitle.length>128){
		WEB.msg.info("提示","广告语不能超过128个字符");
		return;
	}
	if(vfsId==""){
		WEB.msg.info("提示","请上传图片!");
		return;	
	}
	if(linkPage==""){
		WEB.msg.info("提示","链接内容不能为空！");
		return;	
	}
	if (linkPage != null && linkPage != 'undefined' && linkPage != '') {
		if (linkPage.indexOf('http://') == -1) {
			WEB.msg.info("提示","链接内容以http://开头！");
			return;
		}
	}
	var params={
			adId:adId,
			moduleId:moduleId,
			adTitle:adTitle,
			linkPage:linkPage,
			bmpName:bmpName,
			vfsId:vfsId
			};

		$.ajax({
			url:url,
			cache:false,
			async:true,
			dataType:'json',
			data : params,
			success:function(data){
           	 WEB.msg.info("提示","保存成功",function(){
             	window.location.href = basePath+"/pageManage/adManage?moduleId="+moduleId;
           	 });
			}
		});
}