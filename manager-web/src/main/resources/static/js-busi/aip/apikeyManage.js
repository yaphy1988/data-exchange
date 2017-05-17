var basePath = WEB_ROOT;
$(function(){

});


function queryClient(pageNo){
	var userName=$("#userName").val();
	var state=$("#statusSel").val();
	alert(userName+":"+state);
	var data={
			username:$("#userName").val(),
			status:$("#statusSel").val(),
			pageNo:pageNo
	};	
	$.ajax({
		url:basePath+"/apiKeyManage/getClientInfoPage",
		data:data,
		dataType:'html',
		type:'post',
		success:function(data){
			$("#tab01").html(data);
		}	
	})
	
}

function pagerClick(pageNo) {
	queryClient(pageNo);
}

function changeStatus(clientId,status){
	var data={
			clientId:clientId,
			status:status
	}
	$.ajax({
		url:basePath+"/apiKeyManage/updateStateByClentId",
		data:data,
		dataType:'json',
		type:'post',
		async:false,
		success:function(data){
			if(data.success){
				WEB.msg.info("提示",data.msg);
				queryClient(1);
			}else{
				WEB.msg.info("提示",data.msg);
			}		
		}	
	})
}

function edit(obj){
	WEB.msg.info("提示",obj.clientId);
}

function createClient(){
	
}
function delAllSel(){
	var clients=[];
	$('input[name="clientBox"]:checked').each(function(){
		clients.push($(this).attr("value"));
	});
	var data={
			ClientIdList:clients,
			status:'0'
	}
	$.ajax({
		url:basePath+"/apiKeyManage/batchUpdateStateByClentId",
		data:data,
		dataType:'json',
		type:'post',
		traditional:true,
		success:function(data){
			if(data.success){
				WEB.msg.info("提示",data.msg);
				queryClient(1);
			}else{
				WEB.msg.info("提示",data.msg);
			}		
		}	
	})
}

function clickSingleBox(obj){
	if(!$(obj).is(":checked")){
		$("#allBox").attr("checked",false);
	}
}
function clickAllBox(obj){
	if($(obj).is(":checked")){
		$('input[name="clientBox"]').attr("checked",true);
	}
}

