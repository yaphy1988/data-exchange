var basePath = WEB_ROOT;
$(function(){
	myOrderInvoiceList(1);
	
});




function myOrderInvoiceList(index){
	var param={pageNo:index};
	$.ajax({
		url:basePath+'/invoiceManage/myOrderInvoiceList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#myOrderBillList').empty();
			$('#myOrderBillList').html(data);
		}
	});
}
/**
 * 发票开具申请保存
 */
function saveInvoiceTax(){
	var taxId=$("#taxId").val();
	var phone=$("#phone").val();
	var orderId=$("#orderId").val();
	var taxAddr=$("#taxAddr").val();//邮寄地址
	var taxAddrPhone=$("#taxAddrPhone").val();//邮寄电话
	var invoiceTitle=$("#invoiceTitle").val();//发票抬头
	var taxpayerNo=$("#taxpayerNo").val();//
	var contactInfo=$("#contactInfo").val();//地址
	var bankName=$("#bankName").val();//开户行
	var acctInfo=$("#acctInfo").val();//银行账户
	if(taxAddr==""){
		WEB.msg.info("提示","请输入邮寄地址");
		return;
	}
	if(taxAddr.length>64){
		WEB.msg.info("提示","邮寄地址不能超过64个字符！");
		return;
	}
	if(!WEB.check.isPhone(taxAddrPhone)){
		WEB.msg.info("提示","请输入正确的电话号码！");
		return;
	}
	if(bankName.length>256){
		WEB.msg.info("提示","开户行不能超过256个字符！");
		return;
	}
	if(!isInteger(acctInfo)){
		WEB.msg.info("提示","请输入正确的账户！");
		return;
	}
	if(acctInfo.length<12){
		WEB.msg.info("提示","请输入正确的账户！");
		return;
	}
	var params={
			taxId:taxId,
			phone:phone,
			orderId:orderId,
			taxAddr:taxAddr,
			taxAddrPhone:taxAddrPhone,
			invoiceTitle:invoiceTitle,
			taxpayerNo:taxpayerNo,
			contactInfo:contactInfo,
			bankName:bankName,
			acctInfo:acctInfo
			};
	var url="/invoiceManage/saveInvoiceTax";
		$.ajax({
			url:url,
			cache:false,
			async:true,
			dataType:'json',
			data : params,
			success:function(data){
				if(data.success){
					 WEB.msg.info("提示","发票开具申请保存成功！",function(){
			           	 	window.location.href = basePath+"/invoiceManage/mybill";
			       });
				}else{
					WEB.msg.info("提示","发票开具申请保存失败！");
				}
			}
           	
	});
}

//校验输入是否为正整数
function isInteger(str){
	 var reg = /^(([1-9][0-9]*))$/;
	 return reg.test(str);
}
