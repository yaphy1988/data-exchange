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