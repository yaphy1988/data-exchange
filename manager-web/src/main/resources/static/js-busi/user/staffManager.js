/**
 * Created by yx on 2017/5/31.
 */
$(function () {
    queryAuthStaffPage(1);
})

function queryAuthStaffPage(pageNo){
    var params = {};
    params.pageNo = pageNo;
    $.ajax({
        url:WEB_ROOT + "/authStaff/queryAuthStaffPage",
        data:params,
        dataType:'html',
        type:'post',
        async:true,
        success:function (data) {
            $("#staffListDiv").html(data);
        }
    })
}