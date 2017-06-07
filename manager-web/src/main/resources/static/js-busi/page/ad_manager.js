var basePath = WEB_ROOT;
$(function(){
	queryModulePageAdList(1);
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
	var url="/pageManage/savePageModule";
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
	if(moduleId==""||moduleId==undefined){
	 	window.location.href = basePath+"/pageManage/editModulePageAd";
	}else{
	 	window.location.href = basePath+"/pageManage/editModulePageAd?moduleId="+moduleId;
	}
}
function editModuleAd(adId,moduleId){
 	window.location.href = basePath+"/pageManage/editModulePageAd?moduleId="+moduleId+"&adId="+adId;

}
function queryModulePageAdList(index){
	var moduleId=$("#moduleId").val();
	var adTitle=$.trim($("#ad_title").val());
	var status=$("#ad_status").val();
	var url=basePath+'/pageManage/queryModulePageAdList';
	var params={
		pageNo:index,
		moduleId:moduleId,
		adTitle:adTitle,
		status:status
	};

	$.ajax({
		url:url,
		cache:false,
		async:true,
		dataType:'html',
		data : params,
		success:function(data){
			$("#ad_content").html(data);
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

//校验输入是否为正整数
function isInteger(str){
	 var reg = /^(([1-9][0-9]*))$/;
	 return reg.test(str);
}
