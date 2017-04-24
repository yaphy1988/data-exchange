/**验证码切换*/
function checkImgChng(){
	/*jQuery 包装集IE不兼容,用JS*/
	//$("#VERIFY_CODE").val("");
	$("#verifyCode").val("");
	$("#verifyCode").css('color','#999');
//	document.getElementById('check_img').src = WEB_ROOT + "/captcha/captcha?a="+ new Date().getTime();
}

/**登录*/
function doLogin(){
	var staffId = $.trim($("#STAFF_ID").val());
	var password = $("#LOGIN_PASSWORD").val();
	var verifyCode = $("#verifyCode").val();	
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
				window.location.href = WEB_ROOT + "/main/pageInit";
			}else{
				//刷新验证码
				alert(obj.errorMsg);
//				checkImgChng();
			}
		}
	});
}

$(function(){
	checkImgChng();
	
	/**登录*/
    $("#bLoginSubmit").bind('click',function(){
    	doLogin();
    });
});