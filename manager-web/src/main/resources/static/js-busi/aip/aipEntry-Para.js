/**
 * Created by yx on 2017/5/15.
 */
$(function () {

})


function inParaAddRow(obj) {
    var html =  $(obj).parent().parent().prev().html();
    $(obj).parent().parent().before('<tr class="inParaTr">'+html+'</tr>');
}

function outParaAddRow(obj){
    var html =  $(obj).parent().parent().prev().html();
    $(obj).parent().parent().before('<tr class="outParaTr">'+html+'</tr>');
}

function delRow(obj){
    $(obj).parent().parent().parent().remove();
}

/**
 * 获取请求参数
 * @returns {Array}
 */
function initInParaParams(params){
    var inParaStr = "";
    var inParaArr = new Array();
    var len = $(".inParaTr").length;
    var flag = true;
    for (var i = 0 ; i < len ; i++){
        var inPara = {};
        var paraCode = $(".inParaTr").eq(i).find("td").eq(0).find("input").val();
        var paraType = $(".inParaTr").eq(i).find("td").eq(1).find("select").val();
        var type = $(".inParaTr").eq(i).find("td").eq(2).find("select").val();
        var remark = $(".inParaTr").eq(i).find("td").eq(3).find("input").val();
        if ($.trim(paraCode) == ""){
            WEB.msg.info("提示","请求参数名称不能为空！");
            flag = false;
            break;
        }
        if ($.trim(paraType)==""){
            WEB.msg.info("提示","请求参数类型不能为空！");
            flag = false;
            break;
        }
        inPara.paraCode = $.trim(paraCode);
        inPara.paraType = $.trim(paraType);
        inPara.type = $.trim(type);
        inPara.remark = $.trim(remark);
        inParaArr.push(inPara);
    }
    if (!flag){
        return false;
    }
    inParaStr = JSON.stringify(inParaArr);
    params.inParaStr = inParaStr;
    return true;
}

/**
 * 获取返回参数
 * @returns {Array}
 */
function initOutParaParams(params){
    var outParaStr = "";
    var outParaArr = new Array();
    var flag = true;
    var len = $(".outParaTr").length;
    for (var i = 0 ; i < len ; i++){
        var outPara = {};
        var paraCode = $(".outParaTr").eq(i).find("td").eq(0).find("input").val();
        var paraType = $(".outParaTr").eq(i).find("td").eq(1).find("select").val();
        var remark = $(".outParaTr").eq(i).find("td").eq(2).find("input").val();
        if ($.trim(paraCode) == ""){
            WEB.msg.info("提示","返回参数名称不能为空！");
            flag = false;
            break;
        }
        if ($.trim(paraType)==""){
            WEB.msg.info("提示","返回参数类型不能为空！");
            flag = false;
            break;
        }
        outPara.paraCode = $.trim(paraCode);
        outPara.paraType = $.trim(paraType);
        outPara.remark = $.trim(remark);
        outParaArr.push(outPara);
    }
    if (!flag){
        return false;
    }
    outParaStr = JSON.stringify(outParaArr);
    params.outParaStr = outParaStr;
    return true;
}

function submitParaInfo(obj){
    var params = {};
    if (!initInParaParams(params)){
        return ;
    }
    if (!initOutParaParams(params)){
        return ;
    }
    var serviceId = $("#para_serviceId").val();
    if (serviceId == undefined || $.trim(serviceId) == ""){
        serviceId="";
    }
    params.serviceId = serviceId;
    var version = $("#para_version").val();
    if (version == undefined || $.trim(version) == ""){
        version = "";
    }
    params.version = version;
    $.ajax({
        url:WEB_ROOT + "/aipEntry/submitParaInfo",
        data:params,
        dataType:'json',
        type:'post',
        async:false,
        success:function (data) {
            if (data.success){
                WEB.msg.info("提示","保存成功");
                if ($(obj).attr("isEdit") == "0"){
                    window.location.href = WEB_ROOT + "/aipEntry/errorCodeInfoInit?"+data.msg;
                }
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