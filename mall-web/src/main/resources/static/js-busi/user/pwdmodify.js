
//修改密码
function domoify(){
	var staffId = $("#staffIdHidden").val();
	if(!staffId){
		staffId = $("#staffIdInput").val();
	}
	if(!staffId){
		WEB.msg.info('提示',"staffId不能为空！",null);
		return;
	}
	var oldpasswd = $("#oldpasswd").val();
	if(!oldpasswd){
		WEB.msg.info('提示',"请输入旧密码！",null);
		return;
	}else if(oldpasswd.length<6||oldpasswd.length>18){
		WEB.msg.info('提示',"密码长度为6-18个字符！",null);
		return;
	}
	var newpasswd = $("#newpassword").val();
	if(!newpasswd){
		WEB.msg.info('提示',"请输入新密码！",null);
		return;
	}else if(newpasswd.length<6||newpasswd.length>16){
		WEB.msg.info('提示',"密码长度为6-16个字符！",null);
		return;
	}
	var confirmPass = $("#confirmpasswd").val();
	if(!confirmPass){
		WEB.msg.info('提示',"请输入确认密码！",null);
		return;
	}else if(confirmPass.length<6||confirmPass.length>16){
		WEB.msg.info('提示',"密码长度为6-16个字符！",null);
		return;
	}
	if(newpasswd!=confirmPass){
		WEB.msg.info('提示','两次输入的密码不一致');
		return;
	}
	var url = WEB_ROOT + "/password/domodify";
	var param = {
			staffId : staffId,
			oldpasswd : oldpasswd,
			newpasswd : newpasswd,
			confirmPass : confirmPass
		};
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		async : false,
		data : param,
		success : function(data) {
			if(data.success){
				WEB.msg.info("提示","修改成功",function(){
					clearData();
				});
			}else{
				WEB.msg.info("提示",data.msg);
			}			 
	}});
	
}

function clearData(){
	$("#staffIdInput").val('');
	$("#oldpasswd").val('');
	$("#newpassword").val('');
	$("#confirmpasswd").val('');
}