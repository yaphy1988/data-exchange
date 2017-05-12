/**
 * Created by yx on 2017/5/9.
 */
var outParaExampleCkeditor;
var basePath = WEB_ROOT;
$(function () {

    createEditor("outParaExampleCkeditor");
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


//创建编辑器
function createEditor(id) {
    // Create a new editor inside the <div id="editor">, setting its value to
    // html
    var config = {
        filebrowserImageUploadUrl :basePath+ "/ck/upload?type=Image",
        filebrowserUploadUrl :basePath+"/ck/upload?type=File",
        filebrowserFlashUploadUrl :basePath+"/ck/upload?type=Flash"
    };
    if(id=="outParaExampleCkeditor"){
        outParaExampleCkeditor=CKEDITOR.replace(id, config);
    }
}

function submitInfo(){
    var params = {};
    params = initParamInfo();
    if (params == null){
        return;
    }
    $.ajax({
        url:basePath + "/aipEntry/submitInfo",
        data:params,
        dataType:'json',
        type:'post',
        async:false,
        success:function (jsonObj) {
            if(jsonObj.success){
                WEB.msg.info("提示","保存成功！");
            }else{
                WEB.msg.info("提示",jsonObj.msg);
            }
        },
        error:function (jsonObj) {
            WEB.msg.info("提示","系统错误，请联系管理员！");
        }
    })
}

/**
 * 初始化aip保存的参数
 * @returns {*}
 */
function initParamInfo(){
    var params = {};
    var baseInfo_serviceName = $.trim($("#baseInfo_serviceName").val());
    if (baseInfo_serviceName == ""){
        WEB.msg.info("提示","服务名称不能为空！");
        return null;
    }
    params.serviceName = baseInfo_serviceName;
    var baseInfo_serviceDesc = $.trim($("#baseInfo_serviceDesc").val());
    if (baseInfo_serviceDesc == ""){
        WEB.msg.info("提示","服务描述不能为空！");
        return null;
    }
    params.serviceDesc = baseInfo_serviceDesc;
    var baseInfo_providerId = $.trim($("#baseInfo_providerId").val());
    if (baseInfo_providerId == ""){
        WEB.msg.info("提示","服务供货商不能为空！");
        return null;
    }
    params.providerId = baseInfo_providerId;
    var baseInfo_aipUrl = $.trim($("#baseInfo_aipUrl").val());
    if (baseInfo_aipUrl == ""){
        WEB.msg.info("提示","接口地址不能为空!");
        return null;
    }
    params.apiUrl = baseInfo_aipUrl;
    var baseInfo_supportFormat = $.trim($("#baseInfo_supportFormat").val());
    if (baseInfo_supportFormat == ""){
        WEB.msg.info("提示","支持格式不能为空！");
        return null;
    }
    params.supportFormat = baseInfo_supportFormat;
    var baseInfo_reqType = $.trim($("#baseInfo_reqType").val());
    if (baseInfo_reqType == ""){
        WEB.msg.info("提示","请求方式不能为空！");
        return null;
    }
    params.reqType = baseInfo_reqType;
    var baseInfo_exampleUrl = $.trim($("#baseInfo_exampleUrl").val());
    if (baseInfo_exampleUrl == ""){
        WEB.msg.info("提示","请求实例不能为空！");
        return null;
    }
    params.exampleUrl = baseInfo_exampleUrl;
    var baseInfo_testTool = $.trim($("#baseInfo_testTool").val());
    if (baseInfo_testTool == ""){
        WEB.msg.info("提示","在线测试地址不能为空！");
        return null;
    }
    params.testTool = baseInfo_testTool;
    var baseInfo_apiRemark = $.trim($("#baseInfo_apiRemark").val());
    params.apiRemark = baseInfo_apiRemark;
    var baseInfo_returnExample = outParaExampleCkeditor.getData();
    params.returnExample = baseInfo_returnExample;
    // if (!initInParaParams(params)){
    //     return null;
    // }
    // if (!initOutParaParams(params)){
    //     return null;
    // }

    return params;
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