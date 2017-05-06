var basePath = WEB_ROOT;
$(function(){
	$("#tabList").find("li a").each(function(){
		$(this).click(function(){
			var href=$(this).attr("href");
			if(href=="#tab03"){
				queryModulePageAdList(1);

			}
		});
	});

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
function addModuleAd(){
	var moduleId=$("#moduleId").val();
 	window.location.href = basePath+"/pageManage/editModulePageAd?moduleId="+moduleId;
}
function editModuleAd(adId,moduleId){
 	window.location.href = basePath+"/pageManage/editModulePageAd?moduleId="+moduleId+"&adId="+adId;

}
function queryModulePageAdList(index){
	var moduleId=$("#moduleId").val();
	var url=basePath+'/pageManage/queryModulePageAdList';
	var params={
			pageNo:index,
			moduleId:moduleId
			};

	$.ajax({
		url:url,
		cache:false,
		async:true,
		dataType:'html',
		data : params,
		success:function(data){
			$("#tab03").empty();
			$("#tab03").html(data);
		}
	});
}
function updatePageModuleAd(adId,moduleId,status){
	var text=""
	if(status=="0"){
		text="失效";
	}else{
		text=="删除";
	}
	var url=basePath+'/pageManage/updatePageModuleAdInfo';
	var params={
			adId:adId,
			moduleId:moduleId,
			status:status
			};
	WEB.msg.confirm("提示","您确定要"+text+"该广告吗",function(){
		$.ajax({
			url:url,
			cache:false,
			async:true,
			dataType:'json',
			data : params,
			success:function(data){
           	 WEB.msg.info("提示",text+"成功！",function(){
           		queryModulePageAdList(1);
           	 });
			}
		});
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
             	window.location.href = basePath+"/pageManage/editModule?moduleId="+moduleId;
           	 });
			}
		});
}
/**
 * 上传图片
 * 
 * @param {}
 *            object
 * @param {}
 *            path
 */
function uploadImage(object) {
    var filepath = $(object).val();
    if(filepath==""){
    	return false;
    }
	var width=$(object).attr("width");
	var height=$(object).attr("height");
	if(width==undefined||height==undefined){
		width="";
		height="";
	}
	filepath = (filepath + '').toLowerCase();
	var regex = new RegExp(
			'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$', 'gi');
	/** 上传图片文件格式验证 */
	if (!filepath || !filepath.match(regex)) {
		alert('请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp).');
		return;
	}
	var url =basePath+'/pageManage/uploadImage';
	var callback = function(data, status) {

		/** 上传成功，隐藏上传组件，并显示该图片 */
		if (data.flag == true) {
			$("#vfsId").val(data.imageId);
			$("#bmpName").val(data.imageName);
			$("#imgSrcURL").attr("src",data.imagePath)
		} else {
			alert(data.error);
		}
	};
	var params={
			width:width,
			height:height
	}
	ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json",params, callback);
}
/**
 * 上传文件
 * @param {} url
 * @param {} secureuri
 * @param {} fileElementId
 * @param {} type
 * @param {} dataType
 * @param {} callback
 */
function ajaxFileUpload(url, secureuri, fileElementId, type, dataType,params, callback) {
	$.ajaxFileUpload({
				url : url, // 用于文件上传的服务器端请求地址
				secureuri : secureuri, // 一般设置为false
				fileElementId : fileElementId, // 文件上传空间的id属性 <input
				type : type, // get 或 post
				data:params,
				dataType : dataType, // 返回值类型
				success : callback, // 服务器成功响应处理函数
				error : function(data, status, e) // 服务器响应失败处理函数
				{
					alert(e);
				}
			});
}
//校验输入是否为正整数
function isInteger(str){
	 var reg = /^(([1-9][0-9]*))$/;
	 return reg.test(str);
}