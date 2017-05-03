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
	header.querySortInfo('','-1','1');
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
	querySortInfo:function(sortId,sortParentId,sortLever){
		$.ajax({
			url:WEB_ROOT+'/homePage/querySortInfo',
			data:{sortId:sortId,sortParentId:sortParentId,sortLever:sortLever},
			cache:false,
			async:true,
			type:'post',
			dataType:'json',
			success:function(data){
				var html='';
				if(data.success){
					if(sortLever== '2'){//2级导航
						html +='<h4>金融服务</h4><div class="sidebar-link">';
					}
					$(data.sortInfos).each(function(i,d){
						var link ;
						if(d.sortContentRespDTO!=null && d.sortContentRespDTO!= undefined){
							link =d.sortContentRespDTO.contentLink;
						}
						if(sortLever== '2'){//2级导航
							html +='<a href='+setLinkUrk(link)+' target="_blank">'+d.sortName+'</a>';
						}else{//1级导航
							html +='<li sortId="'+d.sortId+'"><a href='+setLinkUrk(link)+' target="_blank"><i>&rsaquo;</i>'+d.sortName+'</a> </li>';
						}
					});
				}
				if(sortLever== '2'){
					html+='<div class="ad-list clearfix">'+
	                    	'<div class="item floatL"></div>'+
	                    	'<div class="item floatL"></div>'+
	                    	'<div class="item floatL"></div>'+
							'</div>';
					$('#head_sidebar>div').html(html).show();
				}else{
					$('#head_sidebar>ul').html(html);
					$('#head_sidebar>ul').children().hover(function(){
						header.querySortInfo('',$(this).attr('sortId'),'2');
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