var basePath = WEB_ROOT;
$(function(){
	querymyCustomData(1);
})
function querymyCustomData(pageNo){
	var status=$.trim($('#statusselect').val());
	var url=basePath+'/pageManage/queryMymanageData';
	var params={
		pageno:pageNo,
		pagesize:10,
		status:status};
	var callBack = function(data){
			//查询成功了
		$('#queryresultShow').empty();
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
