var basePath =  WEB_ROOT;
 $(function(){
		// 数量input框的x修改
		$("#gdscount").bind('change', function() {
			var amount =  $("#gdscount").val();
			if (amount == 0) { 
				WEB.msg.confirm("提示信息", "您确定要删除该商品吗？", deleteGoods, function() {
					$("#gdscount").val(1);
				}, true);
			} else {
				amountUpdate($("#gdscount").val());
			}
		}); 
})  
function deleteGoods()
{
     //跳转到首页
	 self.location=basePath+"homePage/pageInit"; 
}
function reduce()
{
    //减少数量
	 var num =  parseInt($("#gdscount").val());
	 if(num > 1)
	 {
		 $("#gdscount").val(num - 1);
	 }
	 //提交到后台
	 amountUpdate($("#gdscount").val()); 
 }
 function addnum()
 {
    //增加数量
	 var num =  parseInt($("#gdscount").val());
  	$("#gdscount").val(num + 1);
	 //提交到后台
  	amountUpdate($("#gdscount").val());
 }
 function amountUpdate(num)
{
   // 后台更新数据	  
		var url = basePath+'/order/updategdstmpsave';
		var params={updatenum:num};
		var callBack =function(data){ 
			if(data.success){ 
				$("#allmoney").text(data.money);
				$("#allmoneypay").text(data.money); 
				$("#buycount").text(data.iorderamount); 
				
			} 
			else{
				 WEB.msg.info("提示","更新失败");
			}
		};
		doAjax(url,params,callBack); 
}
 //创建订单
 function createOrder(){

 	    var url = basePath+'/order/creatOrder';
		var params={};
		
		var callBack =function(data){ 
			if(data.success){
				  var paytype = $('input:radio:checked').val();
				   if(paytype ==  "zhifubao")
				    {
				    	//支付宝
						var orderid = data.orderid;
						var suborderid = data.suborderid;
						var url = basePath+'/bdxalipay/alipayRequestPage?orderId='+orderid+"&subOrderid="+suborderid;
//						window.location.href=url;
						var tempwindow=window.open('_blank');
						tempwindow.location=url;
				    }
				    else
				    {
				       window.location.href=basePath+"/order/offline_remittance";  
 				    }
			} 
			else{
				 WEB.msg.info("提示",data.ERRORINFO);
			}
		};
		doAjax(url,params,callBack);  
 }
/*//遇见问题*/
function haveProblome(){
	window.location.href=basePath+"/homePage/pageNewsInfolist?moduleId=108";
}
//支付成功  去我的订单
function createOrdSusse(){

	window.location.href=MANAGE_ROOT+"/orderManage/myOrder";
}

/*//模拟支付成功*/
function successDone(){
	//模拟支付成功 ,去修改状态
	var orderid = $("#orderid").val();
	var suborderid =  $("#suborderid").val();
	var url = basePath+'/order/pay_successDone';
	var params={orderid:orderid,suborderid:suborderid};
	var callBack =function(data){
		if(data == "1"){
			WEB.msg.info("提示","更新状态成功");
		}
		else{
			WEB.msg.info("提示","更新状态失败");
		}
	};
	doAjax(url,params,callBack);
}
//模拟失败
function failureDone(){
	window.location.href=basePath+"/homePage/pageNewsInfolist?moduleId=108";
}
