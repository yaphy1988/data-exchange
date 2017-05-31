/**
 * Created by yx on 2017/5/15.
 */
$(function(){

})

function serviceErrorCodeAddRow() {
    var html =  $("#serviceErrorCodeTr").html();
    $("#serviceErrorCodeTable").append('<tr class="serviceErrorCodeTr">'+html+'</tr>');
}

function systemErrorCodeAddRow(){
    var html =  $("#systemErrorCodeTr").html();
    $("#systemErrorCodeTable").append('<tr class="systemErrorCodeTr">'+html+'</tr>');
}

function delRow(obj){
    $(obj).parent().parent().parent().remove();
}

/**
 * 服务级错误代码
 * @param params
 * @returns {boolean}
 */
function initServiceErrorCodeParam(params){
    var serErrCodeStr = "";
    var serErrCodeArr = new Array();
    var len = $(".serviceErrorCodeTr").length;
    var flag = true;
    for (var i = 0 ; i < len ; i++){
        var serErrCode = {};
        var errorCode = $(".serviceErrorCodeTr").eq(i).find("td").eq(0).find("input").val();
        var errorMsg = $(".serviceErrorCodeTr").eq(i).find("td").eq(1).find("input").val();
        if (errorCode == undefined || $.trim(errorCode)==""){
            WEB.msg.info("提示","服务级错误代码不能为空");
            flag = false;
            break;
        }
        if (errorMsg == undefined || $.trim(errorMsg) == ""){
            WEB.msg.info("提示","服务级错误代码说明不能为空");
            flag = false;
            break
        }
        serErrCode.errorCode = $.trim(errorCode);
        serErrCode.errorMsg = $.trim(errorMsg);
        serErrCodeArr.push(serErrCode);
    }
    if (!flag){
        return false;
    }
    serErrCodeStr = JSON.stringify(serErrCodeArr);
    params.serErrCodeStr = serErrCodeStr;
    return true;
}

/**
 * 系统级错误代码
 * @param params
 * @returns {boolean}
 */
function initSystemErrorCodeParam(params){
    var sysErrCodeStr = "";
    var sysErrCodeArr = new Array();
    var len = $(".systemErrorCodeTr").length;
    var flag = true;
    for (var i = 0 ; i < len ; i++){
        var sysErrCode = {};
        var errorCode = $(".systemErrorCodeTr").eq(i).find("td").eq(0).find("input").val();
        var errorMsg = $(".systemErrorCodeTr").eq(i).find("td").eq(1).find("input").val();
        if (errorCode == undefined || $.trim(errorCode)==""){
            WEB.msg.info("提示","服务级错误代码不能为空");
            flag = false;
            break;
        }
        if (errorMsg == undefined || $.trim(errorMsg) == ""){
            WEB.msg.info("提示","服务级错误代码说明不能为空");
            flag = false;
            break
        }
        sysErrCode.errorCode = $.trim(errorCode);
        sysErrCode.errorMsg = $.trim(errorMsg);
        sysErrCodeArr.push(sysErrCode);
    }
    if (!flag){
        return false;
    }
    sysErrCodeStr = JSON.stringify(sysErrCodeArr);
    params.sysErrCodeStr = sysErrCodeStr;
    return true;
}

function submitErrCodeInfo(obj){
    var params = {};
    if (!initServiceErrorCodeParam(params)){
        return;
    }
    if (!initSystemErrorCodeParam(params)){
        return;
    }
    var serviceId = $("#errorInfo_serviceId").val();
    if (serviceId == undefined || $.trim(serviceId) == ""){
        serviceId = "";
    }
    params.serviceId = serviceId;
    var version = $("#errorInfo_version").val();
    if(version == undefined || $.trim(version) == ""){
        version = "";
    }
    params.version = version;
    $.ajax({
        url:WEB_ROOT + "/aipEntry/submitErrCodeInfo",
        data:params,
        dataType:'json',
        type:'post',
        async:false,
        success:function (data) {
            if (data.success){
                WEB.msg.info("提示","保存成功",function () {
                    if ($(obj).attr("isEdit") == "0"){
                        window.location.href = WEB_ROOT + "/aipEntry/exampleInfoInit?"+data.msg;
                    }
                });
            }else{
                WEB.msg.info("提示",data.msg);
            }
        }
    })
}

function backToList() {
    window.location.href=basePath + "/aipManager/pageInit";
}

function backToPrev(){
    history.back(-1);
}