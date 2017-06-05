/**
 * Created by yx on 2017/5/23.
 */
$(function () {

    searchGdsByProvider(1);

    // if (typeof pagerClick === "undefined") {
    //     pagerClick = function (index) {
    //     }
    // }
    // $('#pagerId').pager({callback: pagerClick});
})
function whethercollect(){
	var arr = new Array();
	$(".collect").each(function(){
		arr.push($(this).attr('gdsId'));
	});
	$.ajax({
		url:WEB_ROOT+'/search/whethercollect',
		cache:false,
		async:true,
		dataType:'json',
		data : {gdsIds:arr.toString()},
		success:function(data){
			if(data.success){
				var list = data.obj;
				if(list != null && list.length>=1){
					 $.each(list,function (i,collect) {
						 $(".collect").each(function(){
							if($(this).attr('gdsId')==collect.gdsId){
								var _obj = $(this);
								_obj.addClass("active")
							}
						});
					 });
				}
			}
		}
	});
}

function gdsCollection(obj,gdsId,skuId,catFirstId){
	var param = {
			gdsId : gdsId,
			catFirst : catFirstId
	};
	if(skuId !="null" && skuId !=""){
		param.skuId = skuId;
	}
	
	$.ajax({
		url:WEB_ROOT+'/search/gdscollection',
		cache:false,
		async:true,
		dataType:'json',
		data : param,
		success:function(data){
			if(data.success ){
				var _obj = $(obj);
				if(data.obj=="add"){
					_obj.addClass("active")
				}else if(data.obj=="cancel"){
					_obj.removeClass("active");
				}
			}
		}
	});
}
function searchGdsByProvider(pageNo) {
    var params = {};
    var providerId = $("#providerId").val();
    params.pageNo = pageNo;
    params.providerId = providerId;

    $.ajax({
        url:WEB_ROOT+"/aipProvider/queryGdsList",
        type:'post',
        dataType:'html',
        data:params,
        async:true,
        success:function (data) {
            $("#aipProvider_goodsListDiv").html(data);
            /**
             * 初始化收藏
             */
            whethercollect();
        }
    })
}

function pagerClick(pageNo) {
    searchGdsByProvider(pageNo);
}

function addToCart(gdsId,skuId) {
    var staff_id = "";
    if(staffInfoDTO != null)
    {
        staff_id = staffInfoDTO.staffId;
    }
    if(staff_id == null || staff_id =="")
    {
        WEB.msg.info("提示",'亲，请先登录哦，若无账户，请先注册。');
        return;
    }
    var url = WEB_ROOT+"/order/gdshopcart?gdsId="+gdsId+"&skuId="+skuId;
    window.open(url);
}