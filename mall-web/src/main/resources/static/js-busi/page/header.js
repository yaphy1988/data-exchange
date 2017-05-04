var basePath = WEB_ROOT;
$(document).ready(function(){
	var currentUrl = window.location.href;
	if(currentUrl.match(/\/homePage\/pageInit/)){//首页
		$('#head_sidebar>ul').show();
	}else{
		$('#head_sidebar>ul').hide();
		$('#head_sidebar').hover(function(){
			$('#head_sidebar>ul').show();
		},function(){
			$('#head_sidebar>ul').hide();
		});
	}
	header.setSpanDate();
	header.querySortInfo('-1','1');
	window.setInterval("header.setSpanDate()", 1000);
});
var header = new Object({
	/*时钟显示*/
	setSpanDate:function(){    
		var nowDate = new Date(new Date().getTime());  
		this.year = nowDate.getFullYear();
		this.month = nowDate.getMonth() + 1;
		this.date = nowDate.getDate();
		this.day = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")[nowDate.getDay()];
		this.weekDay = new Array("周日", "周一", "周二", "周三", "周四", "周五", "周六")[nowDate.getDay()];
		var clockDate=this.year+"年"+this.month+"月"+this.date+"日"+" "+this.day+'&nbsp;&nbsp;';
		var clocktime = this.setFull("&nbsp"+nowDate.getHours())+
			":"+this.setFull(nowDate.getMinutes())+":"+this.setFull(nowDate.getSeconds())
		$("#clockdate").html(clockDate+clocktime);
	},
	setFull:function(num){    
		  if(num < 10)        
			  return "0" + num;     
		  else        
			  return num;
	},
	querySortInfo:function(sortParentId,sortLever){
		$.ajax({
			url:WEB_ROOT+'/homePage/querySortInfo',
			data:{sortParentId:sortParentId,sortLever:sortLever},
			cache:false,
			async:true,
			type:'post',
			dataType:'json',
			success:function(data){
				var html ='<h3>全部商品<i class="glyphicon glyphicon-list"></i></h3>'+
						  '<ul>';
				var htmlLever1 = '';
				var htmlLever2 = '';
				if(data.success){
					var sortInfos = data.sortInfos;
					$(sortInfos).each(function(i,d){
						var content = d.sortContentVO;
						htmlLever1 +='<li pSortId='+d.sortId+'><a href="'+setLinkUrk(content.contentLink)+'"><i>&rsaquo;</i>'+content.contentName+'</a> </li>';
						var subSortInfoList = d.subSortInfoList;
						htmlLever2 += '<div pSortId='+d.sortId+' class="sidebar-hidden" style="display: none">'+
							'<h4>'+content.contentName+'</h4>'+
							'<div class="sidebar-link">';
						$(subSortInfoList).each(function(i,d){
							var subContent = d.sortContentVO;
							htmlLever2 +='<a href="'+setLinkUrk(subContent.contentLink)+'">'+subContent.contentName+'</a>';
							if(parseInt(i+1)%5 == 0){
								if(parseInt(i+1)== data.sortInfos.length){
									htmlLever2 +='</div>'; 
								}else{
									htmlLever2 +='</div><div class="sidebar-link">';
								}
							}
						});
						htmlLever2 +='</div></div>'
					});
					html += htmlLever1 +'</ul><!--二级导航开始-->'+htmlLever2+'<!--二级导航结束-->';
					$('#head_sidebar').html(html);
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
						$('#head_sidebar>div').hide();
					});
				}
			}
		});
	}
	
});

function setLinkUrk(linkUrl){
	if(linkUrl == null || linkUrl == undefined){
		return 'javascript:void(0);'
	}else if(new RegExp('http').test(linkUrl)){
		return  linkUrl;
	}else{
		return basePath+linkUrl;
	}
}

function doAjax(url,params,callBack){
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		async : true,
		data : params,
		success : callBack
	})
}