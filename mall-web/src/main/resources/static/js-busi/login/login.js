/**验证码切换*/
function checkImgChng(){
	/*jQuery 包装集IE不兼容,用JS*/
	//$("#VERIFY_CODE").val("");
	$("#verifyCode").val("");
	$("#verifyCode").css('color','#999');
	document.getElementById('check_img').src = WEB_ROOT + "/captcha/CapthcaImage?a="+ new Date().getTime();
}

/**登录*/
function doLogin(){
	var staffId = $.trim($("#STAFF_ID").val());
	if(!staffId){
		showMsg("用户名不能为空");
		return;
	}
	var password = $("#LOGIN_PASSWORD").val();
	if(!password){
		showMsg("密码不能为空");
		return;
	}
	var verifyCode = $("#verifyCode").val();
	if(!verifyCode){
		showMsg("验证码不能为空");
		return;
	}
	$.ajax({
		url : WEB_ROOT+"/login/dologin",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			staffId:staffId,
			password:password,
			verifyCode:verifyCode
			},
		success : function(obj){
			if(obj.success){
				window.location.href = WEB_ROOT + "/homePage/pageInit";
			}else{
				//刷新验证码
				showMsg(obj.errorMsg);
				checkImgChng();
			}
		}
	});
}

function showMsg(msg){
	$("#msgvalid").css('visibility','visible');
	$("#msgvalid").find('span').text(msg);
}

function hiddenmsg(){
	$("#msgvalid").css('visibility','hidden');
}
$(function(){
	checkImgChng();
	$("#check_img").click(function(){
		checkImgChng();
	});
	/**登录*/
    $("#bLoginSubmit").bind('click',function(){
    	doLogin();
    });
});