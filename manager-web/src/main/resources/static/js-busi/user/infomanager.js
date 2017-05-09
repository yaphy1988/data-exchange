//var WEB_SHOW_IMG_PATH = "http://112.74.163.29:14751/ImageServer/image/";
function onImageFileChange(obj){
	
	uploadImage($(obj),function(data){
		//上传成功
		var fileId = data.fileId;
		var fileName = data.fileName;
		var fileType = data.fileType;
		
		var iamgeSize = "_80x80!";//可不设置
//		var imageUrl = WEB_SHOW_IMG_PATH + fileId +iamgeSize+"."+ fileType;
		var imageUrl = data.imageUrl;
		$("#headVfsid").attr("src",imageUrl);
		$("#headVfsid").attr('vfsId',fileId);
	});
}

function initModifyPhone(){
	$("#getcodebtn").html("获取验证码");
    $("#getcodebtn").removeAttr("disabled");
    $("#smsCode").val("");
    $("#picCode").val("");

	$("#myModal").modal({
      "backdrop":'static',
      "keyboard":false
    });

	checkImgChng();
}

/**验证码切换*/
function checkImgChng(){
	document.getElementById('imgcaptcha').src = WEB_ROOT + "/captcha/CapthcaImage?a="+ new Date().getTime();
}

/**验证码切换*/
function checkImgChngNew(){
	document.getElementById('imgcaptchaNew').src = WEB_ROOT + "/captcha/CapthcaImage?a="+ new Date().getTime();
}

//发送短信验证码
function sendSmsCode(){
	var picCode = $("#picCode").val();
	if(!picCode){
		WEB.msg.info('提示','请输入图片验证码',null);
		return;
	}
	$.ajax({
		url : MALL_ROOT+"/security/sendChangPhoneCode",
		type : 'POST',
		async : true,
		data : {picVerifyCode:picCode,busiType:'1'},
		dataType : 'json',
		success : function(obj){	
			if(obj.success){
				afterSend();
			}else{
			    checkImgChng();
				WEB.msg.info('提示',obj.msg);
			}
		}
	});
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
        checkImgChng();
      }
    },1000);
    
    WEB.msg.info("提示","发送验证码成功！");
}

function nextStep(){
	var smsCode = $("#smsCode").val();
	if(!smsCode){
		WEB.msg.info('提示','短信验证码不能为空');
		return;
	}
	$.ajax({
		url : MALL_ROOT+"/security/checkSendChangPhoneCode",
		type : 'POST',
		async : true,
		data : {verifyCode:smsCode,busiType:'1'},
		dataType : 'json',
		success : function(obj){	
			if(obj.success){
				bindNewPhone();
			}else{
				WEB.msg.info('提示',obj.msg);
			}			
		}
	});	
}

function bindNewPhone(){
	$("#myModal").modal('hide');
	$("#picCodeNew").val("");
	$("#smsCodeNew").val("");
    $("#newphoneNo").val("");

	checkImgChngNew();
	$("#myModalNew").modal({
  	  "backdrop":'static',
	  "keyboard":false
	});
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

//发送短信验证码
function sendSmsCodeNew(){
	var picCodeNew = $("#picCodeNew").val();
	if(!picCodeNew){
		WEB.msg.info('提示','请输入图片验证码！',null);
		return;
	}
	var phoneNo = $("#newphoneNo").val();
	if(!phoneNo){
		WEB.msg.info('提示','请输入手机号！',null);
		return;
	}else if(!isMobile(phoneNo)){
		WEB.msg.info('提示','手机号格式不正确！',null);
		return;
	}

	$.ajax({
        url : MALL_ROOT+"/security/sendChangPhoneCode",
        type : 'POST',
        async : true,
        data : {picVerifyCode:picCodeNew,newPhoneNo:phoneNo,busiType:'2'},
        dataType : 'json',
        success : function(obj){
            if(obj.success){
                afterSendNew();
            }else{
                WEB.msg.info('提示',obj.msg);
                checkImgChngNew();
            }
        }
    });
}

function afterSendNew(){
	//发送后，80秒效果
    $("#getcodebtnNew").attr("disabled",true);
    var se = 80;
    $("#getcodebtnNew").html("重新获取验证码("+se+"秒)");
    var timer = window.setInterval(function(){
      se --;
      $("#getcodebtnNew").html("重新获取验证码("+se+"秒)");
      if(se==0){
        $("#getcodebtnNew").html("重新获取验证码");
        $("#getcodebtnNew").attr("disabled",false);
        clearInterval(timer);
        checkImgChngNew();
      }
    },1000);
    
    WEB.msg.info("提示","发送验证码成功！");
}

function updatePhone(){
	var smsCode = $("#smsCodeNew").val();
	var phoneNo = $("#newphoneNo").val();
	if(!smsCode){
		WEB.msg.info('提示','短信验证码不能为空');
		return;
	}
	if(!phoneNo){
		WEB.msg.info('提示','手机号码不能为空');
		return;
	}else if(!isMobile(phoneNo)){
		WEB.msg.info('提示','手机号码格式不正确');
		return;
	}

	$.ajax({
        url : WEB_ROOT+"/infomanager/updatephone",
        type : 'POST',
        async : true,
        data : {phoneNo:phoneNo},
        dataType : 'json',
        success : function(obj){
            if(obj.success){
                $("#oldphoneNo").val(phoneNo);
                $("#spanphone").html(phoneNo);
                $("#myModalNew").modal('hide');
                WEB.msg.info("提示","修改成功！");
            }else{
                WEB.msg.info('提示',obj.msg);
            }
        }
    });
}

function updateInfo(){
	// var aliasName = $("#aliasName").val();
	// if(aliasName&&WEB.fucCheckLength(aliasName)>64){
	// 	WEB.msg.info('提示','别名不能超过32个汉字');
	// 	return;
	// }
	var staffName = $("#staffName").val();
	if(staffName&&WEB.fucCheckLength(staffName)>256){
		WEB.msg.info('提示','别名不能超过128个汉字');
		return;
	}
	var qq = $("#qq").val();
	var qqReg=/^[0-9]*$/;
	if(qq&&!qqReg.test(qq)){
		WEB.msg.info('提示','qq号格式不正确');
		return;
	}
	var weChat = $("#weChat").val();
	if(weChat&&WEB.fucCheckLength(weChat)>64){
		WEB.msg.info('提示','微信号太长');
		return;
	}
	var gender = $("#gender").val();
	var job = $("#job").val();
	if(job&&WEB.fucCheckLength(job)>256){
		WEB.msg.info('提示','职业描述太长！');
		return;
	}
	var headVfsid = $("#headVfsid").attr('vfsId');
	$.ajax({
		url : WEB_ROOT+"/infomanager/modify",
		type : 'POST',
		async : true,
		data : {
			// aliasName : aliasName,
			staffName : staffName,
			qq : qq,
			weChat : weChat,
			gender : gender,
			job : job,
			headVfsid : headVfsid},
		dataType : 'json',
		success : function(obj){	
			if(obj.success){
				WEB.msg.info('提示','修改成功',null);
			}else{
				WEB.msg.info('提示',obj.msg);
			}			
		}
	});
}