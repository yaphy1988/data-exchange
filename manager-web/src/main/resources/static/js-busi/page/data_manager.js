var basePath = WEB_ROOT;
$(function(){
	queryCustomData(1);
})
function queryCustomData(pageNo){
	var status=$.trim($('#statusselect').val());
	var url=basePath+'/pageManage/querymanageData'
	var params={pageno:pageNo,pagesize:10};
	var callBack = function(data){
			//查询成功了
             $("#queryresultShow").html(data);
	}
	$.ajax({
		url:url,
		dataType:'html',
		cacha:false,
		async:true,
		data:params,
		success:callBack
	});
}
