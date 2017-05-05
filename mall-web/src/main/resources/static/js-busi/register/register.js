
function checkstaffId(obj){
	var staffId = $(obj).val();
	var reg = /^[a-zA-Z0-9]$/;	
	if(!staffId){
		showwarm('staffIdDiv','用户ID不能为空');
		return;
	}else if(!reg.test(staffId)){
		showwarm('staffIdDiv','用户ID格式不正确！');
		return;
	}else if(staffId.length<6||staffId.length>24){
		showwarm('staffIdDiv','用户ID为6-24位字母数字！');
		return;
	}
}
/**
 * 保存注册信息
 */
function saveInfo(){	
	var staffId = $("#staffId").val();
	var reg = /^[a-zA-Z0-9]*$/;	
	if(!staffId){
		showwarm('staffIdDiv','用户ID不能为空');
		return;
	}else if(!reg.test(staffId)){
		showwarm('staffIdDiv','用户ID格式不正确！');
		return;
	}else if(staffId.length<6||staffId.length>24){
		showwarm('staffIdDiv','用户ID为6-24位字母数字！');
		return;
	}
	var serialNumber = $("#phoneNo").val();
	if(!serialNumber){
		showwarm('phoneNoDiv','手机号码不能为空');
		return;
	}else if(!isMobile(serialNumber)){
		showwarm('phoneNoDiv','手机号码格式不正确');
		return;
	}
	var signpass = $("#signpass").val();
	if(!signpass){
		showwarm('signpassDiv','请输入密码');
		return;
	}else if(signpass.length<8){
		showwarm('signpassDiv','密码不能少于8位');
		return;
	}
	var confirmPass = $("#confirmPass").val();
	if(!confirmPass){
		showwarm('confirmPassDiv','请输入确认密码');
		return;
	}else if(confirmPass.length<8){
		showwarm('confirmPassDiv','密码不能少于8位');
		return;
	}
	if(signpass!=confirmPass){
		showwarm('confirmPassDiv','两次输入的密码不一致');
		return;
	}
	var checkFlag = $("input[type='checkbox'][id='checkAgree']:checked");
	if(checkFlag.length==0){
		WEB.msg.info("提示","请先阅读协议");
		return;
	}
	var url = WEB_ROOT + "/regist/savesign";
	var param = {
			staffId : staffId,
			serialNumber : serialNumber,
			signpass : signpass,
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
				WEB.msg.info("提示","注册成功",function(){
					window.location.href = WEB_ROOT+"/login/pageInit";
				});				
//				clearData();
			}else{
				WEB.msg.info("提示",data.msg);
			}			 
	}});
	
}

//显示提示
function showwarm(id,msg){
	$("#"+id).find("p").css('visibility','visible');
	$("#"+id).find("span").html(msg);
}
//隐藏提示
function hiddenwarm(id){
	$("#"+id).find("p").css('visibility','hidden');
}
/**
 * 清除数据
 */
function clearData(){
	$("#confirmPass").val('');
	$("#phoneNo").val('');
	$("#smsCode").val('');
	$("#signpass").val('');
	$("#confirmPass").val('');
	!$("#checkAgree").removeAttr('checked');
}
function hiddenpass(){
	$("#confirmPassDiv").find("p").css('visibility','hidden');
}

//验证手机号码
var isMobile=function(mobile){
	    var mobileReg =/^1[34578]\d{9}$/; 
	    if(!(mobileReg.test(mobile)))
	    { 
	        return false; 
	    } 
	    else
	    {return true;}  
};

//邮箱校验
var isEmail = function(email){
	var emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	if(!(emailReg.test(email))){
		return false;
	}else{
		return true;
	}
}

function agreement(){
	$("#checkAgree").attr('checked',true);
}

//发送短信验证码
function sendSmsCode(){
	var phoneNo = $("#phoneNo").val();
	$.smsDialogPlugin.sendSmsSecurity(phoneNo,'10',null,afterSend);
}

//验证短信验证码
function checkSmsCode(){
	var smsCode = $("#smsCode").val();
	var phoneNo = $("#phoneNo").val();
	if(!smsCode){
		showwarm('smsCodeDiv','短信验证码不能为空');
		return;
	}
	if(!phoneNo){
		showwarm('phoneNoDiv','手机号码不能为空');
		return;
	}else if(!isMobile(phoneNo)){
		showwarm('phoneNoDiv','手机号码格式不正确');
		return;
	}
	$.smsDialogPlugin.checkSmsSecurity(smsCode,phoneNo,saveInfo);
}

function afterSend(){
	//发送后，80秒效果
    $("#getcodebtn").attr("disabled",true);
    var se = 80;
    $("#getcodebtn").html("重新获取验证码("+se+"秒)");
    var timer = window.setInterval(function(){
      se --;
      $("#getcodebtn").html("重新获取验证码("+se+"秒)");
      if(se==0){
        $("#getcodebtn").html("重新获取验证码");
        $("#getcodebtn").attr("disabled",false);
        clearInterval(timer);
      }
    },1000);
    
    WEB.msg.info("提示","发送验证码成功！");
}

$(function(){
	$("#signpass").focus(function(){
		hiddenwarm('confirmPassDiv');
	});
	$("#confirmPass").focus(function(){
		hiddenwarm('confirmPassDiv');
	});
	$("#staffId").focus(function(){
		hiddenwarm('staffIdDiv');
	});
	$("#phoneNo").focus(function(){
		hiddenwarm('phoneNoDiv');
	});
	$("#smsCode").focus(function(){
		hiddenwarm('smsCodeDiv');
	});
});