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
					htmlLever1 +='<li pSortId='+d.parentSortId+' sortId='+d.sortId+'>'+
						'<a href="javascript:;" onclick="insetOrUpdateContent(this)"><span>✖</span></a><a href="javascript:;" onclick="querySortFirstlever(this)"><i>&rsaquo;</i>'+content.contentName+'</a> </li>';
					var subSortInfoList = d.subSortInfoList;
					htmlLever2 += '<div pSortId='+d.sortId+' class="class_sidebar_secon" style="display: none">'+
						'<h4>'+content.contentName+'</h4>'+
						'<ol class="class_link">';
					$(subSortInfoList).each(function(i,k){
						var subContent = k.sortContentVO;
						htmlLever2 +='<li><a pSortId='+k.parentSortId+' href="javascript:;" sortId="'+k.sortId+'" onclick="querySortSenlever(this)">'+subContent.contentName+'</a><a href="javascript:;">✖</a></li>';
					});
					htmlLever2 +='</ol><button class="btn btn-default btn-sm"  style="margin-top:20px">'+
						'<i class="glyphicon glyphicon-plus"></i> 添加二级分类</button></div>'
				});
				html += htmlLever1 +'<div class="mt10 ml15"><button class="btn btn-sm btn-default"><i class="glyphicon glyphicon-plus"></i>新增菜单</button>'+
                	'</div></ul><!--二级导航开始-->'+htmlLever2+'<!--二级导航结束-->';
				$('#head_sidebar').html(html);
                
                //分类事件
				$('#head_sidebar>ul').children().hover(function(){
					var sortId = $(this).attr('sortId');
                    $('#head_sidebar>div').hide();
					$('#head_sidebar>div[pSortId='+sortId+']').show();
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
	var sortId = $(obj).parent().attr('sortid');
	querySortInfoById(sortId)
}
function querySortSenlever(obj){
//	$('#head_sidebar').find('li').removeClass('active');
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
function insetOrUpdateSortInfo(obj){
	var psortId=$(obj).parent().attr('pSortId');
	var sortId=$(obj).parent().attr('sortId');
	var url = WEB_ROOT+'/pageManage/insetOrUpdateSortInfo';
	var params={psortId:psortId,sortId:sortId,status:'2'};
	var callBack = function(data){
		if(!data.success){
			WEB.msg.info('提示','删除失败！'+data.erroMsg);
		}
	};
	goAjax(url,params,'json',callBack);
}

function saveSortInfo(){
	var sortId = $('#sortId').val();
	var sortName = $('#sortName').val();
	var contentLink = $('#contentLink_1').val();
	var url = WEB_ROOT+'/pageManage/insetOrUpdateSortInfo';
	var params={sortId:sortId,sortName:sortName,contentLink:contentLink};
	var callBack = function(data){
		
	};
	goAjax(url,params,'json',callBack);
}

function addSortInfoLever2(){
	var $cloneHtml = $('table[name=seclever_tab]').clone();
	$('#firstlever_tab').after($cloneHtml);
	$('table[name=seclever_tab]').show();
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