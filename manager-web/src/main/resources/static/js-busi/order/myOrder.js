var basePath = WEB_ROOT;
var basemailPath = MALL_ROOT;
$(function(){
	myOrderList(1);
	
});




function myOrderList(index){
	var param={pageNo:index};
	$.ajax({
		url:basePath+'/orderManage/myOrderList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#myOrderList').empty();
			$('#myOrderList').html(data);
		}
	});
}
/**
 * 取消订单
 * @param obj
 * @param orderId
 */
function cancelOrder(obj,orderId){
	var status="99";//取消订单
	var params={
			orderId:orderId,
			orderStatus:status,
			};
	var url=basePath+"/orderManage/cancelOrder";
	WEB.msg.confirm("提示","您确定要取消该订单吗？",function(){
		$.ajax({
			url:url,
			cache:false,
			async:true,
			dataType:'json',
			data : params,
			success:function(data){
           	 WEB.msg.info("提示","订单取消成功！",function(){
           		myOrderList(1);
           	 });
			}
		});
	});
}
/**
 * 去支付 弹出框
 * @param obj
 * @param orderId
*/
	function setPayModal(obj,orderId,subOrderId){
		$("#payOrderid").val(orderId);
		$("#paySubOrderid").val(subOrderId);
	    $("#myModalPay").modal();
	/*	//支付宝
		var tempwindow=window.open('_blank');
		var url = basemailPath+'/bdxalipay/alipayRequestPage?orderId='+orderId+"&subOrderid="+subOrderId;
		tempwindow.location=url;*/
}
/**
 * 去支付
 */
function toPay(){
	var orderId = $("#payOrderid").val();
	var suborderId = $("#paySubOrderid").val();
	var payWay = $('input[name="payWay"]:checked').val();
	if(payWay == "1"){
		//支付宝
		var tempwindow=window.open('_blank');
		var url =  basemailPath+'/bdxalipay/alipayRequestPage?orderId='+orderId+"&subOrderid="+suborderId;
  		tempwindow.location=url;
  	}
	else {
		//线下支付
		window.location.href=basemailPath+"/order/offline_remittance";
	}


}
