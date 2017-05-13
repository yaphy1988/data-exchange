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
/*订单主表状态：
01 申请订购中（未付款），
02 正在生效中（订购合同签订-已支付），
03 API接口下线，订单失效（管理员手工失效）、
04 订单已完成（订单调用量量已经达到最大值、或余额已为0，或有效期已经达到，三者中一种情况达到均为订单完成）、
99 已失效（取消订单）*/
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
