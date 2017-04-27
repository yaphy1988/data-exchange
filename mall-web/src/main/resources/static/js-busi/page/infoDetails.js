var basePath = WEB_ROOT;
$(function(){
	queryPageNewsModule();
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

function queryPageNewsModule(){
	var url = basePath+'/homePage/queryPageModue';
	var params ={moduleType:'03',modulePid:'-1'}; //新闻资讯类型
	var callBack = function(data){
		$(data.pageModuleList).each(function(i,d){
			queryModue104Sub(d.subPageModuleList);
		});
	}	
	doAjax(url,params,callBack);	
}

//查询平台动态公告信息
function queryModue104Sub(subPageModuleList){
	if(subPageModuleList != undefined && subPageModuleList != null){
		$(subPageModuleList).each(function(i,d){
			querysubPageModuleList(d.moduleId,1);
		});
	}
}
//查询平台动态公告子楼层信息
function querysubPageModuleList(moduleId,pageNo){
	var url = basePath+'/homePage/queryPageInfoList';
	var params={
		moduleId:moduleId,
		pageNo:pageNo
	};
	var callBack =function(data){
		var html='<div class="sidebarBox" style="border-top:none;">'+
				'<h3 class="title">'+
				'<a href="'+basePath+'/homePage/pageNewsInfolist" class="pull-right more">查看全部 »</a>';
		if(data.success){
			var pageModule = data.moduleRespDTO;
			var infoTypeName="行业资讯";
			if(pageModule.moduleId == '106'){
				infoTypeName="平台公告";
			}else if(pageModule.moduleId == '107'){
				infoTypeName="平台活动";
			}
			else if(pageModule.moduleId == '108'){
				infoTypeName="常见问题";
			}
			html +=''+infoTypeName+'</h3><ul class="sideli">';
			$(data.pageInfoList).each(function(i,d){
                html+='<li><a href="'+basePath+'/homePage/pageNewsDetail?infoId='+d.infoId+'" target="_blank">'+d.infoTitle+'</a></li>';
			});
			html += '</ul></div>';
		}
		$('#news_list').append(html);
	};
	doAjax(url,params,callBack);
}