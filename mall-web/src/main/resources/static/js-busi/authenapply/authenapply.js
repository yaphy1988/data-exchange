
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
	var contactInfo = provinceName+cityName+countryName+detailAddress;
	if(WEB.fucCheckLength(contactInfo)>512){
		showwarm('detailAddress','地址太长！');
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
	var param = {
			invoiceTitle : invoiceTitle,
			contactInfo : contactInfo,
			taxpayerNo : taxpayerNo,
			taxNo : taxNo,
			organCode : organCode,
			vfsId1 : vfsId1,
			relName : relName,
			phone : phone
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
				WEB.msg.info('提示','提交审核成功',function(){
					windows.location.href=WEB_ROOT+'/authenapply/userinfo';
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
	$("#"+id+"_td").find("p").css('visibility','visible');
	$("#"+id).find("span").html(msg);
}
//隐藏提示
function hiddenwarm(id){
	$("#"+id+"_td").find("p").css('visibility','hidden');
}