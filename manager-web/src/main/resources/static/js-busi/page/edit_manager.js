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
function queryModulePageAdList(index){
	var url=basePath+'/pageManage/queryModulePageAdList';
	var params={pageNo:index};

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