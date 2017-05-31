function saveapply(){
	var invoiceTitle = $("#invoiceTitle").val();
	if(!invoiceTitle){
		showwarm('invoiceTitle','公司名称不能为空！');
		return;
	}else if(WEB.fucCheckLength(invoiceTitle)>512){
		showwarm('invoiceTitle','公司名称不能超过512个汉字！');
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

	var taxpayerNo = $("#taxpayerNo").val();
	if(!taxpayerNo){
		showwarm('taxpayerNo','请填写营业执照编号！');
		return;
	}
	
	var taxNo = $("#taxNo").val();
	var organCode = $("#organCode").val();
	var vfsId1 = $("#vfsId1").val();
	var relName = $("#relName").val();
	if(!relName){
		showwarm('relName','请填写联系人姓名！');
		return;
	}
	var phone = $("#phone").val();
	if(!phone){
		showwarm('phone','请填写手机号码！');
		return;
	}
	if(!WEB.check.isPhone(phone)){
		showwarm('phone','手机号码格式不正确！');
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

	var param = {
			invoiceTitle : invoiceTitle,
			contactInfo : contactInfo,
			taxpayerNo : taxpayerNo,
			taxNo : taxNo,
			organCode : organCode,
			vfsId1 : vfsId1,
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
            countryCode:countryCode
	};
	$("#savebtn").attr('disabled',true);
	$.ajax({
		url : WEB_ROOT + "/authenapply/saveauthen",
		type : 'POST',
		data : param,
		dataType : 'json',
		async : true,
		cache : false,
		success : function(obj){
			if(obj.success){
				WEB.msg.info('提示','提交认证成功',function(){
					window.location.href=WEB_ROOT+'/authenapply/userinfo';
				});
			}else{
				WEB.msg.info('提示',obj.msg,null);
			}
			$("#savebtn").removeAttr('disabled');
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

function onImageFileChange(obj){
	
	uploadImage($(obj),function(data){
		//上传成功
		var fileId = data.fileId;
		var fileName = data.fileName;
		var fileType = data.fileType;
		
		var iamgeSize = "_120x120!";//可不设置
//		var imageUrl = WEB_SHOW_IMG_PATH + fileId +iamgeSize+"."+ fileType;
		var imageUrl = data.imageUrl;
		$("#iamgeTag").attr("src",imageUrl);
		$("#vfsId1").val(fileId);
	});
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