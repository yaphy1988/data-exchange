var basePath = WEB_ROOT;
var imgPath = "http://112.74.163.29:14751/ImageServer/image/"; 
$(function(){
	queryPageModue();
})
function  showMyModal() {
	$("#myModal").show();
}
function encodeURI2(strinfo) {
	//中文编码一次，后台解析即可
	var strinfo1 = encodeURI(strinfo);
	return strinfo1;
}
/**
 * 
 */
/** 保存数据定制信息 */
function saveMadeData() {
	var needTiel    =  encodeURI2($("#needTiel").val());
	var needcontent =  encodeURI2($("#needcontent").val());
	var lnkposen    =  encodeURI2($("#lnkposen").val());
	var lnkphone    =  $("#lnkphone").val();
	var lnkemail    =  $("#lnkemail").val();
	var url = WEB_ROOT + "/homePage/saveMadeData";
	param = {
		needTiel : needTiel,
		needcontent : needcontent,
		lnkposen : lnkposen,
		lnkphone : lnkphone,
		lnkemail : lnkemail
	};
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		async : false,
		data : param,
		success : function(data) {
			 alert("保存成功");
	}});
}
/**查询楼层信息，异步加载楼层内容*/
function queryPageModue(){
	var url = basePath+'/homePage/queryPageModue';
	var params={moduleId:moduleId};
	var callBack = function(data) {
		 if(data.success){
			 if(data.pageModuleList != null && data.pageModuleList != undefined){
				 $(data.pageModuleList).each(function(i,d){
					 switch (d.moduleId) {
					case 101://01-首页轮播广告；
						queryModue101(d.moduleId);
						break;
					case 102://01-首页数据推荐；
						querydata_recommend102(d.moduleId);
						break;
					case 103://03-首页数据定制；1
						
						break;
					case 104://04-平台动态
						queryModue104('105',1,10);
						break;
					case 105://109-合作伙伴
						queryPartner109(d.moduleId);
						break;							
					default:
						break;
					}
				 });
			 }
		 }else{
			 alert('查询楼层信息异常！');
		 }
	};
}
//查询推荐楼层信息
function querydata_recommend102(moduleId){
	var url = basePath+'/homePage/queryPageModuleGoods';
	var params={moduleId:moduleId};
	var callBack =function(data){
		var html = "";
		if(data.success){
	        if(data.success){
	        	if(data.moduleGoodsList.count > 0)
	        	{ 
	        			var obj = data.moduleGoodsList ;
	        			for(var i = 0 ; i < obj.result.length; i++)
		        		{
	        				var name =  obj.result[i].recommendName;
	        				var gdsid =  obj.result[i].gdsId; 
	        				var gdsdetailurl = basePath+"/goods/details/"+gdsid;
	 					    html +='<li><a href='+gdsdetailurl+' target="_blank"><img src="http://112.74.163.29:14751/ImageServer/image/58fef10a1d17873197dbc2ef.jpg"><span>'+name+'</span></a></li>';
	        		   }
	        	} 
			}
		 }
		$('#data_recommend').html(html);
	};
	doAjax(url,params,callBack);
}
//查询轮播广告信息
function queryModue101(moduleId){
	var url = basePath+'/homePage/queryPageModuleAd';
	var params={moduleId:moduleId};
	var callBack =function(data){
		var htmlOl = '';
		var htmDiv = '';
		if(data.success){
			$(data.moduleAdList).each(function(i,d){
				if(i==0){
					htmlOl +='<li data-target="#carousel-example-generic" data-slide-to="'+i+'" class="active"></li>';
					htmDiv +='<div class="item active">'+
	                '<img src="'+basePath+'/images/index-banner.jpg" alt="'+d.adTitle+'">'+
	            '</div>';
				}else{
					htmlOl +='<li data-target="#carousel-example-generic" data-slide-to="'+i+'"></li>';
					htmDiv +='<div class="item">'+
		                '<img src="'+basePath+'/images/index-banner.jpg" alt="'+d.adTitle+'">'+
		            '</div>';
				}
			});
		}
		$('#carousel-example-generic>ol').html(htmlOl);
		$('#carousel-example-generic>div').html(htmDiv);
		$('#carousel-example-generic').carousel({interval: 3000});
	};
	doAjax(url,params,callBack);
}
//查询平台动态公告信息
function queryModue104(moduleId,pageNo,pageSize){
	var url = basePath+'/homePage/queryPageModuleAd';
	var params={
		moduleId:moduleId,
		pageNo:pageNo,
		pageSize:pageSize
	};
	var callBack =function(data){
		if(data.success){
			$(data.moduleAdList).each(function(i,d){
				
			});
		}
	};
	doAjax(url,params,callBack);
}

//查询合作伙伴
function queryPartner109(moduleId){
	var url = basePath+'/homePage/queryPageModuleAd';
	var params={moduleId:moduleId};
	var callBack =function(data){
		var htmlOl = '<li>';
		if(data.success){
			$(data.moduleAdList).each(function(i,d){
				//向下取整--
				htmlOl +='<a href="'+d.link_page+'"><img src="'+imgPath+d.vfs_id+'_150x150.jpg"></a>';
 			   /*if(Math.floor(i/5)){
					htmlOl +='<li data-target="#carousel-example-generic" data-slide-to="'+i+'" class="active"></li>';
					htmDiv +='<div class="item active">'+
	                '<img src="'+basePath+'/images/index-banner.jpg" alt="'+d.adTitle+'">'+
	            '</div>';
				} */
			});
		}
		$('#partnert_div').html(htmlOl); 
	};
	doAjax(url,params,callBack);
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