var basePath = WEB_ROOT;
$(function(){
	myInvoiceCheckList(1);
	
});




function myInvoiceCheckList(index){
	var param={pageNo:index};
	$.ajax({
		url:basePath+'/invoiceManage/myInvoiceCheckList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#myInvoiceCheckList').empty();
			$('#myInvoiceCheckList').html(data);
		}
	});
}
/**
 * 开票
 */
function makeInvoiceTax(orderTaxId,orderId){
	var status="2";//
	var params={
			orderTaxId:orderTaxId,
			orderId:orderId,
			status:status
			};
	var url=basePath+"/invoiceManage/makeInvoiceTax";
	WEB.msg.confirm("提示","是否确定开票？",function(){
		$.ajax({
			url:url,
			cache:false,
			async:true,
			dataType:'json',
			data : params,
			success:function(data){
				if(data.success){
					 WEB.msg.info("提示","开票成功！",function(){
						 myInvoiceCheckList(1);
			       });
				}else{
					WEB.msg.info("提示","发票开具申请保存失败！");
				}
			}
		});
	});
}

//校验输入是否为正整数
function isInteger(str){
	 var reg = /^(([1-9][0-9]*))$/;
	 return reg.test(str);
}
