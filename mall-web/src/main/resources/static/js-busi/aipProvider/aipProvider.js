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
            $('#pagerId').pager({callback: function(index){
                // var param = Search.generSearchParam();
                // param.pageNo = index;
                // Search.gridGdsInfo(param);
                searchGdsByProvider(index);
            }});
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