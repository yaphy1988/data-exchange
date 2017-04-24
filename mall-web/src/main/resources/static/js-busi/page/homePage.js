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
/** 获取地市列表 */
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
		async : false,
		data : {},
		success : function(data) {
			 if(data.success){
				 if(data.pageModuleList != null && data.pageModuleList != undefined){
					 $(data.pageModuleList).each(function(i,d){
						 switch (d.moduleId) {
						case '01'://01-首页轮播广告；
							queryHotSearch();
							break;
						case '02'://01-首页轮播广告；
							
							break;
						case '03'://03-首页数据定制；
							
							break;
						case '04'://04-平台动态
							
							break;
						case '05'://05-合作伙伴
							
							break;							
						default:
							break;
						}
					 });
				 }
			 }else{
				 console.error('查询楼层信息异常！');
			 }
	}});
}