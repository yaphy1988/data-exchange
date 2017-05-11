var basePath = WEB_ROOT;
$(function(){
	queryCustomData(1);
})
function queryCustomData(pageNo){
	var status=$.trim($('#statusselect').val());
	var url=basePath+'/pageManage/querymanageData'
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
//提示下是否要处理

function updateCustomDatastatus(dczaId){
	var url=basePath+'/pageManage/updateCustDataStatus'
	var params={dczaId:dczaId };

	WEB.msg.confirm("提示","您确定要将信息设置为已处理？",function(){
		$.ajax({
			url:url,
			cache:false,
			async:true,
			dataType:'json',
			data : params,
			success:function(data){
				WEB.msg.info("提示","定制数据信息处理成功！",function(){
					queryCustomData(1);
				});
			}
		});
	});
}