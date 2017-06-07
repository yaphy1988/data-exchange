$(function(){
	querySortInfo('-1','1');
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
			var html ='<h3>全部商品</h3><ul>';
            
			var htmlLever1 = '';
			var htmlLever2 = '';
			if(data.success){
				var sortInfos = data.sortInfos;
				$(sortInfos).each(function(i,d){
					//一级菜单：
					var c = d.contentRespDTO;
					if(c != null){
						htmlLever1 +='<li pSortId='+c.parentSortId+' sortId='+c.sortId+'>'+
						'<a href="javascript:;" onclick="deleteSortInfo(this)"><span>✖</span></a><a href="javascript:;" onclick="querySortFirstlever(this)"><i>&rsaquo;</i>'+c.contentName+'</a> </li>';
					}else{
						htmlLever1 +='<li pSortId='+d.parentSortId+' sortId='+d.sortId+'>'+
						'<a href="javascript:;" onclick="deleteSortInfo(this)"><span>✖</span></a><a href="javascript:;" onclick="querySortFirstlever(this)"><i>&rsaquo;</i>'+d.sortName+'</a> </li>';
					}	
					
					//子菜单内容
					var subSortInfoList = d.sortInfoRespDTOList;
					if(subSortInfoList != null && subSortInfoList.length >0){
						//子菜单：目前只取一个
						var subMenu = subSortInfoList[0];
						htmlLever2 += '<div pSortId='+d.sortId+' class="class_sidebar_secon" style="display: none">';
                        var sc = subMenu.contentRespDTO;
                        htmlLever2 +='<h4 sortContentId='+sc.sortContentId+' sortId="'+sc.sortId+'"><a  href="javascript:;" onclick="querySortSenlever(this)">'+subMenu.sortName+'</a></h4>'+
						'<ol class="class_link">';
						var subContentList = subMenu.sortInfoRespDTOList;
						$(subContentList).each(function(i,k){
							var c = k.contentRespDTO;
							if(c != null){
								htmlLever2 +='<li sortContentId='+c.sortContentId+' sortId="'+c.sortId+'" ><a href="javascript:;" onclick="querySortSenlever(this)">'+c.contentName+'</a><a href="javascript:;" onclick="deleteSortInfo(this)">✖</a></li>';
							}
						});
						htmlLever2 +='</ol>'+
						'<button class="btn btn-sm btn-default mt10" onclick="addsortContent(3,'+subMenu.sortId+');">新增子类内容</button>'+
						'</div> ';
					}else{
						htmlLever2 += '<div pSortId='+d.sortId+' class="class_sidebar_secon" style="display: none">'+
						'<h4></h4>'+
						'<ol class="class_link">';
						htmlLever2 +='</ol>'+
						'<button class="btn btn-sm btn-default mt10" onclick="addsortContent(2,'+d.sortId+');">新增二级分类</button>'+
						'</div> ';
					}
				});
				htmlLever1 +=
					'<div class="mt10 ml15">'+
						'<button class="btn btn-default btn-sm" data-toggle="modal"  onclick="addsortContent(1,-1);"><i class="glyphicon glyphicon-plus"></i> 新增分类</button>'+
						'</div>'+
					'</ul>';
				html += htmlLever1 +htmlLever2;
				
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

function addsortContent(sortLever,parentSortId){
	var params={sortLever:sortLever,sortAdd:'add',parentSortId:parentSortId};
	querySortInfoById(params)
}
function querySortFirstlever(obj){
	$(obj).parent().siblings().removeClass('active');
	$('.class_link a[sortid]').removeClass('active');
	$(obj).parent().attr('class','active');
	var sortId = $(obj).parent().attr('sortid');
	var params={sortId:sortId};
	querySortInfoById(params)
}
function querySortSenlever(obj){
	$('#head_sidebar li').removeClass('active');
	$('#head_sidebar li[sortid='+sortId+']').attr('class','active');
	
	$(obj).attr('class','active');
	var sortId = $(obj).parent().attr('sortId');
	var params={sortId:sortId};
	querySortInfoById(params)
}
function querySortInfoById(params){
	var url = WEB_ROOT+'/pageManage/querySortInfoById';
	var callBack = function(data){
		$('#sortInfo-content').html(data);
	};
	goAjax(url,params,'html',callBack);
}
function deleteSortInfo(obj){
	var sortId = $(obj).parent().attr('sortId');
	var params={sortId:sortId,status:'2'};
	WEB.msg.confirm('提示','本次操作会将当前分类下所有内容删除。确认删除吗？',function(){
		updateSortInfo(params);
	})
}
function updateSortInfo(params){
	var url = WEB_ROOT+'/pageManage/updateSortInfo';
	var callBack = function(data){
		if(data.success){
			querySortInfo('-1','1');
			WEB.msg.info('提示','操作成功！');
		}else{
			WEB.msg.info('提示','操作失败！'+data.erroMsg);
		}
	};
	goAjax(url,params,'json',callBack);
}
function editSaveSortInfo(){
	var sortId = $('#sortId').val();
	var sortLever = $('#sortLever').val();
	var parentSortId = $('#parentSortId').val();
	var $table = $('#sortInfo table');
	var sortName = $table.find('input[name=sortName]').val();
	var contentLink = $table.find('input[name=contentLink]').val();
	var url = WEB_ROOT+'/pageManage/updateSortInfo';
	var params={sortId:sortId,sortLever:sortLever,pSortId:parentSortId,sortName:sortName,contentLink:contentLink}
	var callBack = function(data){
		if(data.success){
			querySortInfo('-1','1');
			WEB.msg.info('提示','操作成功！');
		}else{
			WEB.msg.info('提示','保存失败！'+data.erroMsg);
		}
	};
	goAjax(url,params,'json',callBack);
}
function saveSortInfo(){
	var sortLever = $('#sortLever').val();
	var parentSortId = $('#parentSortId').val();
	var sortContentArry = [];
	
	
	//组装数据
	$('#sortInfo table').each(function(i,d){
		var sortContentVO = {};
		var sortName = $(this).find('input[name=sortName]').val();
		var contentLink = $(this).find('input[name=contentLink]').val();
		sortContentVO.contentName = sortName;
		sortContentVO.contentLink = contentLink;
		sortContentArry.push(sortContentVO);
	});
	var params={sortContentArry:JSON.stringify(sortContentArry),sortLever:sortLever,parentSortId:parentSortId};
	var url = WEB_ROOT+'/pageManage/saveSortInfo';
	var callBack = function(data){
		if(data.success){
			querySortInfo('-1','1');
			WEB.msg.info('提示','操作成功！');
		}else{
			WEB.msg.info('提示','保存失败！'+data.erroMsg);
		}
	};
	goAjax(url,params,'json',callBack);
}
function deleteAddedSort(obj){
	$(obj).parents('table').remove();
}
function addSortInfo(sortId,sortLever){
	var url = WEB_ROOT+'/pageManage/addsort';
	var params={sortId:sortId,sortLever:sortLever};
	var callBack = function(data){
		$('#sortcontent_table').after(data);
		$('#sortInfo table').show();
	};
	goAjax(url,params,'html',callBack);

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