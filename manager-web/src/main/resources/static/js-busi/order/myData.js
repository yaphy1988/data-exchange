var basePath = WEB_ROOT;

$(function(){
	myAPIDataList(1);
	$("#tabList").find("li a").each(function(){
		$(this).click(function(){
			var hrefId = $(this).attr("href");
			if(hrefId=="#tab01"){
				myAPIDataList(1);
			}else{
				myCustomServiceDataList(1);
			}
		});
	});
});
function myAPIDataList(index){
	var param={
			pageNo:index,
			pageSize:10
			};
	$.ajax({
		url:basePath+'/orderManage/myAPIDataList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#tab01').empty();
			$('#tab01').html(data);
		}
	});
}
/**
 * 定制数据
 * @param index
 */
function myCustomServiceDataList(index){
	var param={
			pageNo:index,
			pageSize:10
			};
	$.ajax({
		url:basePath+'/orderManage/myCustomServiceDataList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#tab02').empty();
			$('#tab02').html(data);
		}
	});
}
/**
 * 再次购买
 * @param gdsId
 */
function buyGoodsAgain(gdsId,skuId){
	var url = MALL_ROOT+"/order/gdshopcart?gdsId="+gdsId+"&skuId="+skuId;
	window.open(url);
 	//window.location.href = basePath+"/goods/details/"+gdsId+"-"+skuId;
}
function jumpToInterface(gdsId,skuId){
	var url = MALL_ROOT+"/goods/details/"+gdsId+"-"+skuId;
	window.open(url);
}