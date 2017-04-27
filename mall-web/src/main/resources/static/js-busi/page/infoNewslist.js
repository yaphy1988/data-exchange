var basePath = WEB_ROOT;
$(function(){
	queryPageNewsModule();
	var moduleId=$('#news_moduleId').val();
	queryNewsPageInfo(1,moduleId);

})
function queryNewsPageInfo(pageNo,moduleId){
	var url=basePath+'/homePage/queryNewsPageInfo'
	var params={pageNo:pageNo,moduleId:moduleId};
	var callBack = function(data){
		var html='';
		if(data.success){
			$(data.pageInfo.result).each(function(i,d){
				html +='<div class="info_item"><dl ><dl >'+
					'<label class="label label-default">';
				html +=d.infoType+'</label><a href="'+basePath+'/homePage/pageNewsDetail?infoId='+d.infoId+'" target="_blank">';
				html += d.infoTitle+'</a></dt><dd infoId="'+d.infoId+'" infoUrl="'+d.infoUrl+'">  </dl>';
				html += '<div class="info_panel">'+
					'<span class="pr20">发布方:广州数据交易：www.gzbdex.com</span>';
				html += '<span>发布时间:'+d.pubTime+'</span></div>';
				html += '</div>';
			});
			$('#info_listitem').html(html);
			$("#pagerId").data({"count":data.pageInfo.count,
				"size":data.pageInfo.pageSize,
				"currentindex":data.pageInfo.pageNo});
			$('#pagerId').empty().pager({
				callback:function(index){
				queryNewsPageInfo(index,infoType);
        	}});
		}
	}
	doAjax(url,params,callBack);
}

//请求新闻资讯内容
function queryNewsInfo(url){
	$.appAjax({
		url : url,
		async : true,
		dataType : 'jsonp',
		jsonp :'jsonpCallback',//注意此处写死jsonCallback
		success: function (data) {
			$("#news_listcontent").html(data.result);
	    }
	});
}
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
				'<h3 class="title">';
				
		if(data.success){
			var infoType = '1';
			var pageModule = data.moduleRespDTO;
			var infoTypeName="行业资讯";
			if(pageModule.moduleId == '106'){
				infoTypeName="平台公告";
				infoType = '2';
			}else if(pageModule.moduleId == '107'){
				infoTypeName="平台活动";
				infoType = '3';
			}
			else if(pageModule.moduleId == '108'){
				infoTypeName="常见问题";
				infoType = '4';
			}
			html +=''+infoTypeName+'</h3><ul class="sideli">';
			html +='<a href="javascript:void(0)" onclick="queryNewsPageInfo(1,'+infoType+');" class="pull-right more">查看全部 »</a>';
			$(data.pageInfoList).each(function(i,d){
                html+='<li><a href="'+basePath+'/homePage/pageNewsDetail?infoId='+d.infoId+'" target="_blank">'+d.infoTitle+'</a></li>';
			});
			html += '</ul></div>';
		}
		$('#news_list').append(html);
	};
	doAjax(url,params,callBack);
}