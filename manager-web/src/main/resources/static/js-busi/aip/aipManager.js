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

function gotoBaseInfoEdit(obj) {
    var serviceId = $(obj).attr("serviceId");
    var version = $(obj).attr("version");
    window.location.href = WEB_ROOT + "/aipEntry/baseInfoInit?serviceId="+serviceId+"&version="+version+"&isEdit=1";
}

function gotoParaInfoEdit(obj) {
    var serviceId = $(obj).attr("serviceId");
    var version = $(obj).attr("version");
    window.location.href = WEB_ROOT + "/aipEntry/paraInfoInit?serviceId="+serviceId+"&version="+version+"&isEdit=1";
}

function gotoErrorInfoEdit(obj) {
    var serviceId = $(obj).attr("serviceId");
    var version = $(obj).attr("version");
    window.location.href = WEB_ROOT + "/aipEntry/errorCodeInfoInit?serviceId="+serviceId+"&version="+version+"&isEdit=1";
}

function gotoExampleInfoEdit(obj) {
    var serviceId = $(obj).attr("serviceId");
    var version = $(obj).attr("version");
    window.location.href = WEB_ROOT + "/aipEntry/exampleInfoInit?serviceId="+serviceId+"&version="+version+"&isEdit=1";
}

function checkBaseInfoEdit(obj) {
    var serviceId = $(obj).attr("serviceId");
    var version = $(obj).attr("version");
    window.location.href = WEB_ROOT + "/aipEntry/baseInfoInit?serviceId="+serviceId+"&version="+version+"&isView=1";
}

function checkParaInfoEdit(obj) {
    var serviceId = $(obj).attr("serviceId");
    var version = $(obj).attr("version");
    window.location.href = WEB_ROOT + "/aipEntry/paraInfoInit?serviceId="+serviceId+"&version="+version+"&isView=1";
}

function checkErrorInfoEdit(obj) {
    var serviceId = $(obj).attr("serviceId");
    var version = $(obj).attr("version");
    window.location.href = WEB_ROOT + "/aipEntry/errorCodeInfoInit?serviceId="+serviceId+"&version="+version+"&isView=1";
}

function checkExampleInfoEdit(obj) {
    var serviceId = $(obj).attr("serviceId");
    var version = $(obj).attr("version");
    window.location.href = WEB_ROOT + "/aipEntry/exampleInfoInit?serviceId="+serviceId+"&version="+version+"&isView=1";
}

function doAipStatus(obj) {
    var targetStatus = $(obj).attr("targetStatus");
    var serviceId = $(obj).attr("serviceId");
    var version = $(obj).attr("version");
    var params = {};
    params.status = targetStatus;
    params.serviceId = serviceId;
    params.version = version;
    $.ajax({
        url:WEB_ROOT + "/aipManager/doAipStatus",
        data:params,
        dataType:'json',
        type:'post',
        async:false,
        success:function (jsonObj) {
            if (jsonObj.success){
                if (targetStatus == "1"){
                    WEB.msg.info("提示","生效成功");
                }else if (targetStatus == "2"){
                    WEB.msg.info("提示","失效成功");
                }
                queryAipList(1);
            }else{
                WEB.msg.info("提示",data.msg);
            }
        }
    })
}