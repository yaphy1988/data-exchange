var basePath = WEB_ROOT;

$(function(){
	orderManagequery(1);
});
//订单管理界面查询
function orderManagequery(index){
	var param={pageNo:index};
	$.ajax({
		url:basePath+'/orderManage/orderManagequery',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#managerdata').empty();
			$('#managerdata').html(data);
		}
	});
}
//使订单变为已支付
function orderChangePay(orderid,suborderid){
	var param={orderid:orderid,suborderid:suborderid};
	var url = basePath+'/orderManage/orderChangePay';
	var callBack =function(data){
			if(data.success){
				WEB.msg.info("提示","更新成功");
				orderManagequery(1);
		}
		else{
			WEB.msg.info("提示",data.ERRORINFO);
		}
		};
		doAjax(url,param,callBack);
	}
//使订单变为已失效
	function orderManageoffline(orderid,suborderid){
		var param={orderid:orderid,suborderid:suborderid};
		var url = basePath+'/orderManage/orderManageoffline';
		var callBack =function(data){
			if(data.success){
				WEB.msg.info("提示","更新成功");
				orderManagequery(1);
			}
			else{
			WEB.msg.info("提示",data.ERRORINFO);
		}
	};
	doAjax(url,param,callBack);
}
function doAjax(url,params,callBack){
	$.ajax({
		    	url : url,
			   type : "POST",
	      dataType : "json",
		      async : true,
		       data : params,
		    success : callBack
	})
}
function changeCreateType(){
	//10固定套餐订单; 20自定义套餐订单； 30跨类套餐订单//
 	$("#trechecount").removeAttr("readonly");
	$("#trorderprice").removeAttr("readonly");
	$("#trinavitime").removeAttr("readonly");
	$("#trallmoney").removeAttr("readonly");

	var seleopValue = $("#typeOption").val();
	if(seleopValue == "10"){
     //单价，接口次数，总额不能修改
		$("#trgds").show();//商品
		$("#trsku").show();//套餐
		$("#trechecount").show();//每份多少次，不可修改
		$("#trorderprice").show();//单价
		$("#trordernum").show();//购买套餐数量，默认1
		$("#trinavitime").show(); //有截止日期
		$("#trallmoney").show(); //总金额，单位元

		$("#trechecount").attr("readonly","readonly");
		$("#trorderprice").attr("readonly","readonly");
		$("#trinavitime").attr("readonly","readonly");
		$("#trallmoney").attr("readonly","readonly");
	}
	//20 --自定义套餐
	 if(seleopValue == "20"){
		 $("#trgds").show();//商品
		 $("#trsku").hide();//套餐 --自定义不用显示这个
		 $("#trechecount").show();//每份多少次
		 $("#trorderprice").show();//单价
		 $("#trordernum").show();//购买套餐数量，默认1
		 $("#trinavitime").show(); //有截止日期
		 $("#trallmoney").show(); //总金额，单位元
     }
	//30跨类套餐订单
	if(seleopValue == "30"){
        $("#trgds").hide();//商品
		$("#trsku").hide();//套餐
		$("#trechecount").hide();//每份多少次
		$("#trorderprice").hide();//单价
		$("#trordernum").show();//购买套餐数量，默认1
		$("#trinavitime").show(); //有截止日期
		$("#trallmoney").show(); //总金额，单位元
	}
}
//管理员生成订单
function createOrderBymaneger(){
	var gdsid = $("#gdsid").val();
	var skuid = $("#skuid").val();
	var staffid =  $("#staffid").val();
	var skunum =  $("#skunum").val();
	var ordertype = $("#typeOption").val();
	var ordermoney = $("#allmoney").val();
	var inavidate = $("#innavateDate").val();
	var api_id = $("#api_id").val();
	//总额和有效期都是从后台取数
    // 30
	/*staffid
	* ordermoney
	*inavidate
	* */

	// 20 - 10
	/*staffid
	 * gdsid
	 * skuid
	 * skunum
	 * ordertype
	 * ordermoney
	 *inavidate
	 * */
	var param={
		gdsid:gdsid,
		skuid:skuid,
		api_id:api_id,
		staffid:staffid,
		skunum:skunum,
		ordertype:ordertype,
		ordermoney:ordermoney,
		inavidate:inavidate
	};
	var url = basePath+'/orderManage/createOrderBymaneger';
	var callBack =function(data){
		if(data.success){
			WEB.msg.info("提示","创建成功");
			orderManagequery(1);
		}
		else{
			WEB.msg.info("提示",data.ERRORINFO);
		}
	};
	doAjax(url,param,callBack);
}
/**
 * 查询待选择商品
 * @param defaultIndex
 * @returns
 */
function encodeURI2(strinfo) {
	//中文编码一次，后台解析即可
	var strinfo1 = encodeURI(strinfo);
	return strinfo1;
}
function qryModuleGoods(index){
	var gdsName= $("#querygdsname").val() ;
	if(gdsName == "")
	{
		WEB.msg.info("提示","请输入商品名称");
		return;
	}
	var gdsName=encodeURI2($("#querygdsname").val());
 	var param={
		gdsName:gdsName,
		pageSize:6,
		pageNo:index
	};
	$.ajax({
		url:basePath+'/orderManage/qryModuleGoods',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#gdsquery').empty();
			$('#gdsquery').html(data);
		}
	});
}
//设置选择的商品名称和ID
function  setGetgdsinfo() {
	var gdsid  = $("input[name='radiogds']:checked").val();
	var gdsname =  $("input[name='radiogds']:checked").attr("gdstname_tmp");
	var api_id =  $("input[name='radiogds']:checked").attr("api_id_tmp");
	//设置上级窗口
	$("#gdsname").val(gdsname);
	$("#gdsid").val(gdsid);
	$("#api_id").val(api_id);
 }
 function  getskuBygds() {
	var gdsid =  $("#gdsid").val();
	 WEB.msg.info("提示","要根据gds去获取skuID"+gdsid);
 }