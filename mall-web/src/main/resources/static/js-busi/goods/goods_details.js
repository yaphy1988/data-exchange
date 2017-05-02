/**
 * Created by yx on 2017/4/22.
 */

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
        url:"/goods/queryRecomGdsList",
        dataType:'html',
        type:'post',
        async:true,
        success:function (data) {
            $("#recGdsList").html(data);
        }
    })
}