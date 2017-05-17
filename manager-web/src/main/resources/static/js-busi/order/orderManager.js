var basePath = WEB_ROOT;

$(function(){
	// 数量input框的x修改
	$("#ordernum").bind('change', function() {
		var ordernum   =  $("#ordernum").val();
		var packprice  =  $("#packprice").val();
        var ordermoney = parseInt(ordernum)* parseInt(packprice);
		if (typeof(ordermoney) != "undefined")
		{
			$("#allmoney").val(ordermoney);
		}
	});
	$("#packprice").bind('change', function() {
		var ordernum   =  $("#ordernum").val();
		var packprice  =  $("#packprice").val();
		var ordermoney = parseInt(ordernum)* parseInt(packprice);
		if (typeof(ordermoney) != "undefined")
		{
			$("#allmoney").val(ordermoney);
		}
	});

	$("#trsku").hide();
	$("#gdsname").attr("readonly","readonly");
	$("#skuname").attr("readonly","readonly");
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
 	$("#echecount").removeAttr("readonly");
	$("#packprice").removeAttr("readonly");
	$("#innavateDate").removeAttr("readonly");
	$("#allmoney").removeAttr("readonly");
	$("#packtimes").removeAttr("readonly");
 	$("#gdsid").val();
	$("#gdsname").val();

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

		$("#packtimes").attr("readonly","readonly");
		$("#echecount").attr("readonly","readonly");
		$("#packprice").attr("readonly","readonly");
		$("#innavateDate").attr("readonly","readonly");
		$("#allmoney").attr("readonly","readonly");
	}
	//20 --自定义套餐
	 if(seleopValue == "20"){
		 $("#trgds").show();//商品
		 $("#trsku").hide();//套餐 --自定义不用显示这个
		 $("#trechecount").show();//每份多少次
		 $("#trorderprice").show();//单价
		 $("#trordernum").show();//购买套餐数量，默认1
		 $("#innavateDate").show(); //有截止日期
		 $("#trallmoney").show(); //总金额，单位元
     }
	//30跨类套餐订单
	if(seleopValue == "30"){
        $("#trgds").show();//商品
		$("#trsku").hide();//套餐
		$("#trechecount").hide();//每份多少次
		$("#trorderprice").hide();//单价
		$("#trordernum").show();//购买套餐数量，默认1
		$("#trinavitime").show(); //有截止日期
		$("#trallmoney").show(); //总金额，单位元
		$("#gdsname").attr("readonly","readonly");
		$("#gdsid").val($("#staticgdsid").val());
		$("#gdsname").val($("#staticgdsname").val());
	}
}
//管理员生成订单
function createOrderBymaneger(){
	var ordertype    = $("#typeOption").val();//订单类型
	var staffid      = $("#staffid").val();//用户ID
	var gdsid        = $("#gdsid").val();//商品ID
	var skuid        = $("#skuid").val();//套餐ID
	var api_id       = $("#api_id").val();//接口ID
	var inavidate    = $("#innavateDate").val();//失效日期
	var packtimes    = $("#packtimes").val();//套餐里面的次数
	var packprice    = $("#packprice").val();//套餐的单价 --单位元
	var ordernum     = $("#ordernum").val();//购买的套餐数量
	var ordermoney   = $("#allmoney").val();//总金额
    var skuname         = $("#skuname").val();//套餐名称 -- skuname
	var gdsname         = $("#gdsname").val();//商品名称 --  商品名称

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
		ordertype:ordertype,
		staffid:staffid,
		gdsid:gdsid,
		skuid:skuid,
		api_id:api_id,
		inavidate:inavidate,
		packtimes:packtimes,
		packprice:packprice,
		ordernum:ordernum,
		ordermoney:ordermoney,
		skuname:encodeURI2(skuname),
		gdsname:encodeURI2(gdsname)
	};
	var url = "";
	if(ordertype  == "30")
	{
		url = basePath+'/orderManage/createOrderBymaneger';
	}
	else{
		//固定和自定义套餐
		url = basePath+'/orderManage/createStaticOrderBymaneger';
	}
	var callBack =function(data){
		if(data.success){
			WEB.msg.info("提示","创建成功");
			$("#myModal").attr("class", "modal");
			$("#myModal").hide();
			$(".modal-backdrop").attr("class", "modal-backdrop");
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
   // 设置类数据为空
	$(".skuclass").val("");
 }
 function setGetskuinfo() {
	 var skuid     = $("input[name='radiosku']:checked").val();
	 if (typeof(skuid) == "undefined")
	 {
	 	return ;
	 }
 	 var skuname =  $("input[name='radiosku']:checked").attr("skutname_tmp");
	 var packprice =  $("input[name='radiosku']:checked").attr("packPrice");
	 var packtimes =  $("input[name='radiosku']:checked").attr("packTimes");
	 var inavitime = $("input[name='radiosku']:checked").attr("activeEndTime");
	 //如果是无期限，则inavitime 为空

	 //设置上级窗口
	 //单价设置为元
	 var yuanpackprice = packprice/100;
	 $("#skuname").val(skuname);
	 $("#skuid").val(skuid);
	 $("#innavateDate").val(inavitime);
	 $("#packtimes").val(packtimes);
	 $("#packprice").val(yuanpackprice);
	 var allmoney = parseInt(yuanpackprice) * parseInt($("#ordernum").val());
     $("#allmoney").val(allmoney);
 }
 function  getskuBygds(index) {
	var gdsid =  $("#gdsid").val();
	 if(gdsid == "")
	 {
		 WEB.msg.info("提示","先选择商品");
		 return;
	 }
 	 var param={
		 gdsid:gdsid,
		 pageSize:6,
		 pageNo:index
	 };
	 $.ajax({
		 url:basePath+'/orderManage/qryskubyGds',
		 cache:false,
		 async:true,
		 dataType:'html',
		 data : param,
		 success:function(data){
			 $('#skuquery').empty();
			 $('#skuquery').html(data);
		 }
	 });
  }