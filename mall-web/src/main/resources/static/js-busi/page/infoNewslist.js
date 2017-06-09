var basePath = WEB_ROOT;
$(function(){
	var moduleId=$('#news_moduleId').val();
	queryNewsPageInfo(1,moduleId);

})
var pagerClick = function (index) {
    var moduleId=$('#news_moduleId').val();
    queryNewsPageInfo(index,moduleId);
}
function queryNewsPageInfo(pageNo,moduleId){
	var url=basePath+'/homePage/queryNewsPageInfo'
	var params={pageNo:pageNo,moduleId:moduleId};
	var callBack = function(data){
		$('#news_contain').html(data);
		//从mongDB获取内容
		$('#info_listitem').find('dd').each(function(i,d){
			var infoId = $(this).attr('infoid');
			var infourl = $(this).attr('infourl')+".html";
			queryNewsInfo(infoId,infourl)
		});
	}
	$.appAjax({
		url:url,
		data:params,
		dataType:'html',
		cache:false,
		async:true,
		success:callBack
	});
}

//请求新闻资讯内容
function queryNewsInfo(infoId,url){
	$.ajax({
		url : url,
		async : true,
		dataType : 'jsonp',
		jsonp :'jsonpCallback',//注意此处写死jsonCallback
		success: function (data) {
			$("#info_listitem").find('dd[infoid="'+infoId+'"]').html(data.result);
	    }
	});
}
