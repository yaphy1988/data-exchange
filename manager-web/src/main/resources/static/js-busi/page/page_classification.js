$(function(){
	querySortInfo('-1','1')
});

function querySortInfo(sortParentId,sortLever){
	$.ajax({
		url:MALL_ROOT+'/homePage/querySortInfo',
		data:{sortParentId:sortParentId,sortLever:sortLever},
		cache:false,
		async:true,
		type:'post',
		dataType:'jsonp',
		jsonp :'jsonpCallback',
		success:function(data){
			var html ='<h3>全部商品<i class="glyphicon glyphicon-list menuIcon"></i></h3>'+
					  '<ul>';
			var htmlLever1 = '';
			var htmlLever2 = '';
			if(data.success){
				var sortInfos = data.sortInfos;
				$(sortInfos).each(function(i,d){
					var content = d.sortContentVO;
					htmlLever1 +='<li pSortId='+d.sortId+'><a href="'+setLinkUrk(content.contentLink)+'"  target="_blank"><i>&rsaquo;</i>'+
						'<span>✖</span>'+content.contentName+'</a> </li>';
					var subSortInfoList = d.subSortInfoList;
					htmlLever2 += '<div pSortId='+d.sortId+' class="class_sidebar_secon" style="display: none">'+
						'<h4>'+content.contentName+'</h4>'+
						'<div class="class_link">';
					$(subSortInfoList).each(function(i,d){
						var subContent = d.sortContentVO;
						htmlLever2 +='<a href="'+setLinkUrk(subContent.contentLink)+'"  target="_blank">'+subContent.contentName+'<a href="javascript:;"><span>✖</span></a></a>';
					});
					htmlLever2 +='</div><div class="sidebar-link"></div><button class="btn btn-sm btn-default mt10">新增子菜单</button></div>'
				});
				html += htmlLever1 +'<div class="mt10 ml15"><button class="btn btn-sm btn-default">新增菜单</button>'+
                	'</div></ul><!--二级导航开始-->'+htmlLever2+'<!--二级导航结束-->';
				$('#head_sidebar').html(html);
                
                //分类事件
				$('#head_sidebar>ul').children().hover(function(){
					var pSortId = $(this).attr('pSortId');
                    $('#head_sidebar>div').hide();
					$('#head_sidebar>div[pSortId='+pSortId+']').show();
				},function(){
				    $('#head_sidebar>div').hide();
				});
				
				$('#head_sidebar>div').hover(function(){
                    $(this).show();
                 },function(){
                     $(this).hide();
                 });
			}
		}
	});
}

function insetOrUpdateContent(){
	var url = WEB_ROOT+'/pageManage/insetOrUpdateContent';
	var params={};
	var callBack = function(data){
		
	};
	goAjax(url,params,callBack);
}

function saveSortInfo(){
	var url = WEB_ROOT+'/pageManage/insetOrUpdateSortInfo';
	var params={};
	var callBack = function(data){
		
	};
	goAjax(url,params,callBack);
}
function goAjax(url,params,callBack){
	$.ajax({
		url:url,
		cache:false,
		async:true,
		data:params,
		dataType:'json',
		success:callBack
	});
}

function setLinkUrk(linkUrl){
	if(linkUrl == null || linkUrl == undefined){
		return 'javascript:void(0);'
	}else if(new RegExp('http').test(linkUrl)){
		return  linkUrl;
	}else{
		return MALL_ROOT+linkUrl;
	}
}