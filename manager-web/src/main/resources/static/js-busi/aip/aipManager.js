/**
 * Created by yx on 2017/5/21.
 */
$(function () {

    queryAipList(1);
})

/**
 * 查询aip接口列表
 * @param pageNo
 */
function queryAipList(pageNo) {
    var params = {};
    params.pageNo = pageNo;
    var status = $("#aipQuery_status").val();
    if (status == undefined || $.trim(status)==""){
        status = "";
    }
    var serviceName = $("#aipQuery_serviceName").val();
    if (serviceName == undefined || $.trim(serviceName)==""){
        serviceName == "";
    }
    params.status = status;
    params.serviceName = serviceName;
    
    $.ajax({
        url:WEB_ROOT + "/aipManager/queryAipList",
        data:params,
        dataType:'html',
        type:'post',
        async:true,
        success:function (data) {
            $("#aipManagerList").html(data);
        }
    })
}

function pagerClick(pageNo){
    queryAipList(pageNo);
}