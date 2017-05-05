var basePath = WEB_ROOT;

$(function(){
	myOrderDataList(1);
	
});




function myOrderDataList(index){
	var param={pageNo:index};
	$.ajax({
		url:basePath+'/orderManage/myOrderDataList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#tab01').empty();
			$('#tab01').html(data);
		}
	});
}
