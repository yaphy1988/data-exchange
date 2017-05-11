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
					htmlLever1 +='<li pSortId='+d.sortId+'>'+
						'<a href="javascript:;"><span>✖</span></a><a href="javascript:;" onclick="querySortFirstlever(this)"><i>&rsaquo;</i>'+content.contentName+'</a> </li>';
					var subSortInfoList = d.subSortInfoList;
					htmlLever2 += '<div pSortId='+d.sortId+' class="class_sidebar_secon" style="display: none">'+
						'<h4>'+content.contentName+'</h4>'+
						'<div class="class_link">';
					$(subSortInfoList).each(function(i,d){
						var subContent = d.sortContentVO;
						htmlLever2 +='<a href="javascript:;" sortId="'+d.sortId+'" onclick="querySortSenlever(this)">'+subContent.contentName+'</a><a href="javascript:;"><span>✖</span></a>';
					});
					htmlLever2 +='</div><div class="sidebar-link"></div><button class="btn btn-sm btn-default mt10">新增子菜单</button></div>'
				});
				html += htmlLever1 +'<div class="mt10 ml15"><button class="btn btn-sm btn-default"><i class="glyphicon glyphicon-plus"></i>新增菜单</button>'+
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
function querySortFirstlever(obj){
	$(obj).parent().siblings().removeClass('active');
	$('.class_link a[sortid]').removeClass('active');
	$(obj).parent().attr('class','active');
	var sortId = $(obj).parent().attr('psortid');
	querySortInfoById(sortId)
}
function querySortSenlever(obj){
	$('#head_sidebar').find('li').removeClass('active');
	$(obj).siblings().removeClass('active');
	$(obj).attr('class','active');
	var sortId = $(obj).attr('sortId');
	querySortInfoById(sortId)
}
function querySortInfoById(sortId){
	var url = WEB_ROOT+'/pageManage/querySortInfoById';
	var params={sortId:sortId};
	var callBack = function(data){
		$('#sortInfo-content').html(data);
	};
	goAjax(url,params,'html',callBack);
}
function insetOrUpdateContent(){
	var url = WEB_ROOT+'/pageManage/insetOrUpdateContent';
	var params={};
	var callBack = function(data){
		
	};
	goAjax(url,params,'json',callBack);
}

function saveSortInfo(){
	var url = WEB_ROOT+'/pageManage/insetOrUpdateSortInfo';
	var params={};
	var callBack = function(data){
		
	};
	goAjax(url,params,'json',callBack);
}
function goAjax(url,params,type,callBack){
	$.ajax({
		url:url,
		cache:false,
		async:true,
		data:params,
		dataType:type,
		success:callBack
	});
}

function setLinkUrk(linkUrl){
	if(linkUrl == null || linkUrl == undefined || linkUrl==''){
		return 'javascript:;'
	}else if(new RegExp('http').test(linkUrl)){
		return  linkUrl;
	}else{
		return MALL_ROOT+linkUrl;
	}
}