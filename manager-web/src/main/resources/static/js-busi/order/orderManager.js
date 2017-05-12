var basePath = WEB_ROOT;

$(function(){
	orderManagequery(1);
	
});




function orderManagequery(index){
	var param={pageNo:index};
	$.ajax({
		url:basePath+'/orderManage/orderManagequery',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#managerdata').empty();
			$('#managerdata').html(data);
		}
	});
}

