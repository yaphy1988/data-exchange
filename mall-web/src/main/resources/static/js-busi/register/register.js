//校验用户ID
function checkstaffId(obj){
	var staffId = $("#staffId").val();
	var reg = /^[A-Za-z0-9]+$/;
	if(!staffId){
		showwarm('staffIdDiv','用户ID不能为空');
		return false;
	}else if(!reg.test(staffId)){
		showwarm('staffIdDiv','用户ID格式不正确！');
		return false;
	}else if(staffId.length<6||staffId.length>24){
		showwarm('staffIdDiv','用户ID为6-24位字母数字！');
		return false;
	}
	return true;
}
/**
 * 保存注册信息
 */
function saveInfo(){
    //校验用户ID
	if(checkstaffId(null) ==false){
	    return;
	}

	var staffId = $("#staffId").val();

    //校验手机号
	var serialNumber = $("#phoneNo").val();
	if(!serialNumber){
		showwarm('phoneNoDiv','手机号码不能为空');
		return;
	}else if(!WEB.check.isMobile(serialNumber)){
		showwarm('phoneNoDiv','手机号码格式不正确');
		return;
	}
    //校验手机验证码
	var smsCode = $("#smsCode").val();
    if(!smsCode){
        showwarm('smsCodeDiv','短信验证码不能为空');
        return;
    }
    //校验密码
	var signpass = $("#signpass").val();
	if(!signpass){
		showwarm('signpassDiv','请输入密码');
		return;
	}else if(signpass.length<8){
		showwarm('signpassDiv','密码不能少于8位');
		return;
	}
	var confirmPass = $("#confirmPass").val();
	if(signpass!=confirmPass){
		showwarm('confirmPassDiv','两次输入的密码不一致');
		return;
	}
	//阅读协议
	var checkFlag = $("input[type='checkbox'][id='checkAgree']:checked");
	if(checkFlag.length==0){
		WEB.msg.info("提示","请先阅读协议");
		return;
	}

	$.ajax({
        url : WEB_ROOT+"/regist/savesign",
        type : "POST",
        dataType : "json",
        async : true,
        data : {
            staffId : staffId,
            serialNumber : serialNumber,
            confirmPass : confirmPass,
            smsCode : smsCode
        },
        success : function(data){
            if(data.success){
                WEB.msg.info("提示","注册成功",function(){
                    window.location.href = WEB_ROOT+"/login/pageInit";
                });
            }else{
                WEB.msg.info("提示",data.msg);
            }
        }
    });
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

//发送短信验证码
function sendSmsCode(){
	var phoneNo = $("#phoneNo").val();
	if(!phoneNo){
		showwarm('phoneNoDiv','手机号码不能为空');
		return;
	}else if(!WEB.check.isMobile(phoneNo)){
		showwarm('phoneNoDiv','手机号码格式不正确');
		return;
	}
	$.ajax({
        url : WEB_ROOT+"/security/sendChangPhoneCode",
        type : "POST",
        dataType : "json",
        async : true,
        data : {newPhoneNo:phoneNo,busiType:'2',picVerifyCode:""},
        success : function(obj){
            if(obj.success){
                afterSend();
            }else{
                WEB.msg.info('提示',obj.msg);
            }
        }
    });
}

function afterSend(){
	//发送后，80秒效果
    $("#getcodebtn").attr("disabled",true);
    var se = 80;
    $("#getcodebtn").html("("+se+"秒)");
    var timer = window.setInterval(function(){
      se --;
      $("#getcodebtn").html("("+se+"秒)");
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