

/**登录*/
function doLogin(){
	var checkName = $.trim($("#CheckName").val());
	if(!checkName){
		WEB.msg.info('提示',"姓名不能为空");
		return;
	}
	var checkPSN = $("#CheckPSN").val();
	if(!checkPSN){
		WEB.msg.info('提示',"身份证号不能为空");
		return;
	}
	var checkPhone = $("#CheckPhone").val();
	if(!checkPhone){
		WEB.msg.info('提示',"手机号码不能为空");
		return;
	}
	$.ajax({
		url : WEB_ROOT+"/checkCredit/check",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			checkName:checkName,
			checkPSN:checkPSN,
			checkPhone:checkPhone
			},
		success : function(obj){
			if(obj.success){
				$("#CheckResult").val(obj.point);
			}else{
				//刷新验证码
				WEB.msg.info('提示',obj.errorMsg);
				clearData();
			}
		}
	});
}

function clearData() {
	$("#CheckName").val("");
	$("#CheckPSN").val("");
	$("#CheckPhone").val("");
	$("#CheckResult").val("");
}

function showMsg(msg){
	$("#msgvalid").css('visibility','visible');
	$("#msgvalid").find('span').text(msg);
}

function hiddenmsg(){
	$("#msgvalid").css('visibility','hidden');
}
$(function(){

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