var basePath = WEB_ROOT;

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
	var url="/orderManage/cancelOrder";
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
function toPayModal(obj,orderId){
	$("#orderId").val(orderId);
	$("#myModalPay").modal();
}
/**
 * 去支付
 */
function toPay(){
	var orderId = $("#orderId").val();
	var payWay = $('input[name="payWay"]:checked').val();
 	window.location.href = basePath+"/orderManage/myOrder?orderId="+orderId+"&payWay="+payWay;

}
