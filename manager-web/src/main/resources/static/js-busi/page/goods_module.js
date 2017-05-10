var basePath = WEB_ROOT;
$(function(){
	myOrderInvoiceList(1);
	
});
function applyInvoice(orderId){
	window.location.href = basePath+"/invoiceManage/applyInvoice?orderId="+orderId;
}
/**
 * 查询已选择商品
 * @param defaultIndex
 * @returns
 */
function qryModuleGoodsSelList(index){
	var gdsName=$("#gdsName").val();
	var status=$("#gdsStatus").val();
	var catId=$("#selCatId").val();
	var param={
			gdsName:gdsName,
			status:status,
			catId:catId,
			pageNo:defaultIndex
			};
	$.ajax({
		url:basePath+'/pageModuleGoods/qryModuleGoodsSelList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#selGoodsList').empty();
			$('#selGoodsList').html(data);
		}
	});
}
/**
 * 查询待选择商品
 * @param defaultIndex
 * @returns
 */
function qryModuleGoodsUnSelList(index){
	var gdsName=$("#unSelGdsName").val();
	var catId=$("#unselCatId").val();
	var param={
			gdsName:gdsName,
			catId:catId,
			pageNo:index
			};
	$.ajax({
		url:basePath+'/pageModuleGoods/qryModuleGoodsUnSelList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#unSelGoodsList').empty();
			$('#unSelGoodsList').html(data);
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
						 qryModuleGoodsSelList(1);
			       });
				}else{
					WEB.msg.info("提示","发票开具申请保存失败！");
				}
			}
	});
}

function deletePageModuleGoods(pmgId){
	var status="0";//失效
	var params={
			pmgId:pmgId,
			status:status
			
	};
	var url=basePath+"pageModuleGoods/updatePageModuleGoods";
	WEB.msg.confirm("提示","确定要删除该商品吗？",function(){
		$.ajax({
			url:url,
			cache:false,
			async:true,
			dataType:'json',
			data : params,
			success:function(data){
				if(data.success){
					 WEB.msg.info("提示","删除商品成功！",function(){
						 qryModuleGoodsUnSelList(1);
						 qryModuleGoodsSelList(1);
			       });
				}else{
					WEB.msg.info("提示","删除商品失败！");
				}
			}
		});
	});
}
function savePageModuleGoods(gdsId){
	var moduleId=$("#moduleId").val();
	var status="1";//失效
	var params={
			moduleId:moduleId,
			gdsId:gdsId,
			status:status
		};
	var url = basePath + "pageModuleGoods/savePageModuleGoods";
	$.ajax({
		url : url,
		cache : false,
		async : true,
		dataType : 'json',
		data : params,
		success : function(data) {
			if (data.success) {
				WEB.msg.info("提示", "删除商品成功！", function() {
					qryModuleGoodsUnSelList(1);
					qryModuleGoodsSelList(1);
				});
			} else {
				WEB.msg.info("提示", "删除商品失败！");
			}
		}
	});
}
