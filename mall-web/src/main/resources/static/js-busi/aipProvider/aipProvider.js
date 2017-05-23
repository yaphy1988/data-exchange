/**
 * Created by yx on 2017/5/23.
 */
$(function () {

    searchGdsByProvider(1);
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
            $("#aipprovider_goodslist").html(data);
        }
    })
}

function pagerClick(pageNo) {
    searchGdsByProvider(pageNo);
}