var basePath = WEB_ROOT;

$(function(){
	myOrderList(1);
	
});




function myOrderList(index){
	var param={pageNo:index};
	$.ajax({
		url:basePath+'/orderManage/myOrderList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#myOrderList').empty();
			$('#myOrderList').html(data);
		}
	});
}
