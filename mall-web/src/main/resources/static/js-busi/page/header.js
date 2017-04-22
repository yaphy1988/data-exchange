var basePath = WEB_ROOT;
$(document).ready(function(){
	header.queryHotSearch();
	header.queryHeaderNav();
	header.setSpanDate();
	window.setInterval("header.setSpanDate()", 1000);
});
var header = new Object({
	queryHotSearch:function(){
		 $.ajax({
			url:WEB_ROOT+'/homePage/queryHotSearch',
			cache:false,
			async:true,
			dataType:'json',
			success:function(data){
				var html = '<a href="#" class="more floatR">更多&nbsp;&gt;</a>';
				if(data.success){
					$(data.hotSearchList).each(function(i,d){
						html +='<a href='+WEB_ROOT+d.searchUrl+' target="_blank">'+d.searchKey+'</a>';
					});
				}
				$('#search-hot').html(html);
			}
		});

	},
	queryHeaderNav:function(){
		$.ajax({
			url:WEB_ROOT+'/homePage/queryHeaderNav',
			cache:false,
			async:true,
			dataType:'json',
			success:function(data){
				var html;
				if(data.success){
					var html='';
					if(data.success){
						$(data.searchNavList).each(function(i,d){
							html +='<li><a href='+WEB_ROOT+d.navLink+' target="_blank">'+d.navName+'</a></li>';
						});
					}
				}
				$('#head_navbar').html(html);
			}
		});
	},
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
	  }
});
