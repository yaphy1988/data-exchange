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
				  //打开一个新界面
                  // 
				  var paytype = $('input:radio:checked').val();
				   if(paytype ==  "zhifubao")
				    {
				    	//支付宝
 				    	 window.open("http://www.jb51.net");
				    	//去写一条请求支付的记录 
				    }
				    else
				    {
				       window.location.href=basePath+"/order/offline_remittance";  
 				    }
			} 
			else{
				 WEB.msg.info("提示","订单生成失败");
			}
		};
		doAjax(url,params,callBack);  
 }
/*//获取数据定制的图片
function queryModue103(moduleId){
	var url = basePath+'/homePage/queryPageModuleAd';
	var params={moduleId:moduleId};
	var callBack =function(data){
		var htmlOl = '';
		var htmDiv = '';
		if(data.success){
			$(data.moduleAdList).each(function(i,d){
				$("#datasetImg").attr("src",d.vfsId); 
			});
		} 
	};
	doAjax(url,params,callBack);
}*/
 