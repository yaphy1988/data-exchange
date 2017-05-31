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
        dataType : 'jsonp',
        jsonp :'jsonpCallback',
		data : {picVerifyCode:picCode,busiType:'1'},
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
        dataType : 'jsonp',
        jsonp :'jsonpCallback',
		data : {verifyCode:smsCode,busiType:'1'},
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
        async : true,
        dataType : 'jsonp',
        jsonp :'jsonpCallback',
        data : {picVerifyCode:picCodeNew,newPhoneNo:phoneNo,busiType:'2'},
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
        data : {phoneNo:phoneNo,smsCode:smsCode},
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

    var userInfos = {
        qq : qq,
        weChat : weChat,
        gender : gender,
        job : job,
        headVfsid : headVfsid
    };

    //已认证的
    if(g_authenFlag != "0"){

        var relName = $("#relName").val();
        if(!relName){
            showwarm('relName','请填写联系人姓名！');
            return;
        }
        var phone = $("#phone").val();
        if(!phone){
            showwarm('phone','请填写联系人电话！');
            return;
        }

        //企业人数
        var companyPeople = $("#companyPeople").val();
        if(isNaN(companyPeople)){
            companyPeople = "";
            $("#companyPeople").val(companyPeople);
            showwarm('companyPeople','企业人数只能是数字！');
            return;
        }
        companyPeople = parseInt(companyPeople);

        if(WEB.fucCheckLength(companyPeople)>6){
            showwarm('companyPeople','企业人数太长！');
            return;
        }
        //主营业务
        var companyBusi = $("#companyBusi").val();
        if(WEB.fucCheckLength(companyBusi)>200){
            showwarm('companyBusi','主营业务太长！');
            return;
        }
        //企业网址
        var companyUrl = $("#companyUrl").val();
        if(WEB.fucCheckLength(companyUrl)>120){
            showwarm('companyUrl','企业网址太长！');
            return;
        }

        //固话
        var companyTel = $("#companyTel").val();
        if(WEB.fucCheckLength(companyTel)>20){
            showwarm('companyTel','固话太长！');
            return;
        }
        //传真
        var companyFax = $("#companyFax").val();
        if(WEB.fucCheckLength(companyFax)>20){
            showwarm('companyFax','传真太长！');
            return;
        }
        //邮箱
        var companyMail = $("#companyMail").val();

        if(companyMail && WEB.check.isEmail(companyMail) == false){
            showwarm('companyMail','邮箱格式错误！');
            return;
        }
        if(WEB.fucCheckLength(companyMail)>60){
            showwarm('companyMail','邮箱太长！');
            return;
        }

        var provinceCode = $("#province").val();
        if(!provinceCode){
            showwarm('areainfo','请选择省份！');
            return;
        }
        var provinceName = $("#province option[value='"+provinceCode+"']").html();

        var cityCode = $("#city").val();
        if(!cityCode){
            showwarm('areainfo','请选择地市！');
            return;
        }
        var cityName = $("#city option[value='"+cityCode+"']").html();

        var countryCode = $("#country").val();
        if(!countryCode){
            showwarm('areainfo','请选择区县！');
            return;
        }
        var countryName = $("#country option[value='"+countryCode+"']").html();
        var detailAddress = $("#detailAddress").val();
        if(!detailAddress){
            showwarm('detailAddress','请填写详细地址！');
            return;
        }
        var contactInfo = detailAddress;
        if(WEB.fucCheckLength(contactInfo)>512){
            showwarm('detailAddress','地址太长！');
            return;
        }

        userInfos = {
            qq : qq,
            weChat : weChat,
            gender : gender,
            job : job,
            headVfsid : headVfsid,
            relName : relName,
            phone : phone,
            companyPeople:companyPeople,
            companyBusi:companyBusi,
            companyUrl:companyUrl,
            companyTel:companyTel,
            companyFax:companyFax,
            companyMail:companyMail,
            provinceCode:provinceCode,
            cityCode:cityCode,
            countryCode:countryCode,
            contactInfo : contactInfo
        };
    }

	$.ajax({
		url : WEB_ROOT+"/infomanager/modify",
		type : 'POST',
		async : true,
		data : userInfos,
		dataType : 'json',
		success : function(obj){	
			if(obj.success){
				WEB.msg.info('提示','修改成功',null);
			}else{
				WEB.msg.info('提示',obj.msg);
			}
			$("p.error-tip").hide();
		}
	});
}

//显示提示
function showwarm(id,msg){
	$("#"+id+"_msg").css('display','');
	$("#"+id+"_msg").find("span").html(msg);
}
//隐藏提示
function hiddenwarm(id){
	$("#"+id+"_msg").css('display','none');
}

$(function(){

	$("#province").change(function () {
		var provinceId = $(this).val();
		var params = {};
		if (provinceId==undefined){
			provinceId = "";
		}
		params.code = provinceId;
		params.contentId = "city"
		$.ajax({
			url:WEB_ROOT + "/authenapply/queryAreaList",
			data:params,
			dataType:'html',
			type:'post',
			async:true,
			success:function (data) {
				$("#city").html($(data).html());
			}
		})
	})

	$("#city").change(function () {
		var cityId = $(this).val();
		var params = {};
		if (cityId==undefined){
			cityId = "";
		}
		params.code = cityId;
		params.contentId = "country"
		$.ajax({
			url:WEB_ROOT + "/authenapply/queryAreaList",
			data:params,
			dataType:'html',
			type:'post',
			async:true,
			success:function (data) {
				$("#country").html($(data).html());
			}
		})
	})
});