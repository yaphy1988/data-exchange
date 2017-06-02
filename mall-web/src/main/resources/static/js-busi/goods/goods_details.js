/**
 * Created by yx on 2017/4/22.
 */
var basePath =  WEB_ROOT;
$(function(){
    $("#tabContentUl li").each(function (i,element) {
        if ($(this).attr("proType") == "2"){
            var htmlFileId = $(this).attr("htmlFileId");
            if (htmlFileId!=undefined && $.trim(htmlFileId) != ""){
                $.ajax({
                    url : htmlFileId,
                    async : false,
                    dataType : 'jsonp',
                    jsonp : 'jsonpCallback',// 注意此处写死jsonCallback
                    success : function(data) {
                        $("#tabContentDiv .tab-pane").eq(i).html(data.result);
                        // object.html(data.result);
                    }
                });
            }
        }
    });

    queryRecGdsList();
    
    /**
     * 用户浏览商品记录保存。gongxq
     * @param obj
     * @returns
     */
    userfootprint();
    $(".gd_etails .collect").on('click',function(){
    	var param = {
				gdsId : $("#gdsId_detail").val(),
				catFirstId : $("#catFirst_detail").val()
		};
    	var skuSel =$("#skuListSel").find(".active").find("a");
	    if (skuSel.attr("skuId") !=undefined && $.trim(skuSel.attr("skuId"))!=""){
	    	param.skuId = $.trim(skuSel.attr("skuId"));
	    }
		$.ajax({
			url:WEB_ROOT+'/search/gdscollection',
			cache:false,
			async:true,
			dataType:'json',
			data : param,
			success:function(data){
				if(data.success ){
					if(data.obj=="add"){
						WEB.msg.info("提示",'收藏成功');
					}else if(data.obj=="cancel"){
						WEB.msg.info("提示",'取消成功');
					}
				}
			}
		});
    })
})

function changeGdsSku(obj) {
    $(obj).parent().parent().find("li").removeClass("active");
    $(obj).parent().addClass("active");

    var packPriceStr = $(obj).attr("packPriceStr");
    var packTimes = $(obj).attr("packTimes");
    var packDay = $(obj).attr("packDay");
    $("#gds_priceDisplay").html(packPriceStr);
    $("#gds_timesDisplay").html("/"+packTimes+"次");
    $("#gds_dayDisplay").html("有效期"+packDay+"天");
   
}

function queryRecGdsList() {
    $.ajax({
        url:basePath+"/goods/queryRecomGdsList",
        dataType:'html',
        type:'post',
        async:true,
        success:function (data) {
            $("#recGdsList").html(data);
        }
    })
}

function applyData(obj) {
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
    var params = {};
    var gdsId = $(obj).attr("gdsId");
    var gdsName = $(obj).attr("gdsName");
    if (gdsName!=undefined && $.trim(gdsName)==""){
        gdsName = "";
    }else {
        gdsName = encodeURI2(gdsName);
    }
    var skuSel =$("#skuListSel").find(".active").find("a");
    var skuId = skuSel.attr("skuId");
    var skuName = skuSel.attr("skuName");
    if (skuName!=undefined && $.trim(skuName)==""){
        skuName = "";
    }else {
        skuName = encodeURI2(skuName)
    }
    if (skuId==undefined || $.trim(skuId)==""){
        WEB.msg.info("提示","请选择单品");
        return;
    }
    params.gdsId = gdsId;
    params.skuId = skuId;
    $.ajax({
        url:basePath + "/goods/applyDataValidate",
        async:false,
        type:'post',
        dataType:'json',
        data:params,
        success:function (jsonObj) {
            if(jsonObj!=null){
                if (jsonObj.success){
                    window.location.href = basePath + "/order/gdstmpsavesession?gdsid="+gdsId+"&skuid="+skuId+"&gdsname="+gdsName+"&skuname="+skuName
                }else{
                    if (jsonObj.errorCode == "0"){
                        WEB.msg.info("提示","系统判断是否购买过该商品异常！")
                    }else if (jsonObj.errorCode == "1"){
                        WEB.msg.info("提示","您已订购套餐，是否继续订购",function () {
                            window.location.href = basePath + "/order/gdstmpsavesession?gdsid="+gdsId+"&skuid="+skuId+"&gdsname="+gdsName+"&skuname="+skuName
                        })
                    }
                }
            }else {
                WEB.msg.info("系统错误，请联系管理员！");
            }
        }
    })

}

function encodeURI2(strinfo) {
    //中文编码一次，后台解析即可
    var strinfo1 = encodeURI(strinfo);
    return strinfo1;
}

function addToCart(gdsId,skuId){
    var skuSel =$("#skuListSel").find(".active").find("a");
    if (skuSel.attr("skuId") !=undefined && $.trim(skuSel.attr("skuId"))!=""){
            skuId = $.trim(skuSel.attr("skuId"));
    }
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
	window.location.href=WEB_ROOT+"/order/gdshopcart?gdsId="+gdsId+"&skuId="+skuId;
}

function userfootprint(){
	 $.ajax({
	        url:basePath + "/goods/userfootprint",
	        async:true,
	        type:'post',
	        dataType:'json',
	        data:{gdsId:$("#gdsId_detail").val(),catFirst:($("#catFirst_detail").val())},
	        success:function (jsonObj) {
	        }
	    })
}