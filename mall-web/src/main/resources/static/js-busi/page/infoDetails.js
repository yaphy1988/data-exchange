var basePath = WEB_ROOT;
$(function(){
    var url=$("#news_infoUrl").val()+".html";
	$.appAjax({
		url : url,
		async : true,
		dataType : 'jsonp',
		jsonp :'jsonpCallback',//注意此处写死jsonCallback
		success: function (data) {
			$("#news_content").html(data.result);
	    }
	});
})
