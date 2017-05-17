function saveCheck(status){
	var taxId = $("#taxId").val();
	var checkDesc = $.trim($("#checkdesc").val());
	if(!checkDesc){
		WEB.msg.info('提示','请输入审核意见',null);
//		showmsg('checkdescdiv','请输入审核意见','');
		return;
	}else if(checkDesc.length>256){
		WEB.msg.info('提示','审核意见太长了!',null);
//		showmsg('checkdescdiv','审核意见太长了！','');
		return;
	}
	alert(checkDesc);
	$.ajax({
		url : WEB_ROOT+"/authencheck/docheck",
		type : 'POST',
		dataType : 'json',
		data : {
			taxId : taxId,
			status : status,
			checkDesc : checkDesc
		},
		async : true,
		cahce : false,
		success : function(obj){
			if(obj.success){
				WEB.msg.info('提示','审核成功',function(){
					window.location.href = WEB_ROOT+"/authencheck/pageInit";
				});
			}else{
				WEB.msg.info('提示',obj.msg,null);
			}
		}
	});
	
}

function showmsg(id,msg,tyle){
	$("#"+id).css('display',tyle);
	$("#"+id).find('span').html(msg);
}

$(function(){
	$("#checkdesc").focus(function(){
		showmsg('checkdescdiv','','none');
	});
});