var basePath = WEB_ROOT;
$(function(){
	var moduleId=$('#news_moduleId').val();
	queryNewsPageInfo(1,moduleId);

})
function queryNewsPageInfo(pageNo,moduleId){
	var url=basePath+'/homePage/queryNewsPageInfo'
	var params={pageNo:pageNo,moduleId:moduleId};
	var callBack = function(data){
		var html='';
		if(data.success){
			var newsList = data.pageInfo.result;
			$(newsList).each(function(i,d){
				html +='<div class="info_item"><dl ><dl >'+
					'<label class="label label-default">';
				html +=d.infoType+'</label><a href="'+basePath+'/homePage/newsDetail?infoId='+d.infoId+'" target="_blank">';
				html += d.infoTitle+'</a></dt><dd infoId="'+d.infoId+'" infoUrl="'+d.infoUrl+'">  </dl>';
				html += '<div class="info_panel">'+
					'<span class="pr20">发布方:广州数据交易：www.gzbdex.com</span>';
				var pubTime = new Date(d.pubTime);
				html += '<span>发布时间:'+initDate(pubTime)+'</span></div>';
				html += '</div>';
			});
			$('#info_listitem').html(html);
			$("#pagerId").data({"count":data.pageInfo.count,
				"size":data.pageInfo.pageSize,
				"currentindex":data.pageInfo.pageNo});
			$('#pagerId').empty().pager({
				callback:function(index){
				queryNewsPageInfo(index,moduleId);
        	}});
			//从mongDB获取内容
			$('#info_listitem').find('dd').each(function(i,d){
				var infoId = $(this).attr('infoid');
				var infourl = $(this).attr('infourl')+".html";
				queryNewsInfo(infoId,infourl)
			});
		}
	}
	doAjax(url,params,callBack);
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
function initDate(pubTime){
	var d=new Date(pubTime);
	var year=d.getFullYear();
	var month=+d.getMonth()+1;
	var day=d.getDate();
	/*var hour=d.getHours();
	var minute=d.getMinutes();
	var second=d.getSeconds();
	var misec=d.getMilliseconds();*/
	return year+"年"+month+"月"+day+"日";
}
