$(function(){
	queryComplaintList();
});
function queryComplaintList(){
	var url = MANAGE_ROOT+'/ordgdsComplaint/queryComplaint';
	var params={};
	$.ajax({
		url:url,
		data:params,
		dataType:'html',
		cache:false,
		async:true,
		success:function(data){
			$('#complant_content').html(data);
		}
	});
}
