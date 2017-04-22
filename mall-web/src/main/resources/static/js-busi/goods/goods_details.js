/**
 * Created by yx on 2017/4/22.
 */
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