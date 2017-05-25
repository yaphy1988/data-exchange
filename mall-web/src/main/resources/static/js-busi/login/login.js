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
		WEB.msg.info('提示',"用户名不能为空");
		return;
	}
	var password = $("#LOGIN_PASSWORD").val();
	if(!password){
		WEB.msg.info('提示',"密码不能为空");
		return;
	}
	var verifyCode = $("#verifyCode").val();
	if(!verifyCode){
		WEB.msg.info('提示',"验证码不能为空");
		return;
	}
    //记住密码
	var rememberPaas = $("#rememberPaas").is(':checked');
	$.ajax({
		url : WEB_ROOT+"/login/dologin",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			staffId:staffId,
			password:password,
			verifyCode:verifyCode,
			rememberPaas:rememberPaas
			},
		success : function(obj){
			if(obj.success){
			    if(login_toPage != null && login_toPage != ""){
			        window.location.href = login_toPage;
				}else{
				    window.location.href = WEB_ROOT + "/homePage/pageInit";
				}
			}else{
				//刷新验证码
				WEB.msg.info('提示',obj.errorMsg);
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
    
    document.onkeydown = function(e){
	   if(!e){
	    e = window.event;
	   }
	   if((e.keyCode || e.which) == 13){
		   doLogin();
	   }
	  }
});