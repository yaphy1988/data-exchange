var basePath = WEB_ROOT;
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
	$.ajax({
		url : basePath+'/homePage/queryPageModue',
		type : "POST",
		dataType : "json",
		async : true,
		data : {},
		success : function(data) {
			 if(data.success){
				 if(data.pageModuleList != null && data.pageModuleList != undefined){
					 $(data.pageModuleList).each(function(i,d){
						 switch (d.moduleId) {
						case 101://01-首页轮播广告；
							//queryModue101(d.moduleId);
							break;
						case 102://01-首页数据推荐；
							querydata_recommend102(d.moduleId);
							break;
						case 103://03-首页数据定制；1
							
							break;
						case 104://04-平台动态
							
							break;
						case 105://05-合作伙伴
							
							break;							
						default:
							break;
						}
					 });
				 }
			 }else{
				 alert('查询楼层信息异常！');
			 }
	}});
}
//查询推荐楼层信息
function querydata_recommend102(moduleId){
	var url = basePath+'/homePage/queryPageModuleGoods';
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
	 					    html +='<li><a href='+gdsid+' target="_blank"><img src=11><span>'+name+'</span></a></li>';
	        		   }
	        	} 
			}
		 }
		$('#data_recommend').html(html);
	};
	doAjax(url,moduleId,callBack);
}
//查询推荐楼层信息
function queryModue101(moduleId){
	var url = basePath+'/homePage/queryPageModuleGoods';
	var callBack =function(data){
		var html = "";
		if(data.success){
	        if(data.success){
				$(data.hotSearchList).each(function(i,d){
//					html +='<li><a href='+1+' target="_blank"><img src=11><span>推荐数据</span></a></li>';
				});
			}
		 }
		$('#data_recommend').html(html);
	};
	doAjax(url,moduleId,callBack);
}
function doAjax(url,moduleId,callBack){
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		async : true,
		data : {moduleId:moduleId},
		success : callBack
	})
}