/**
 * Created by yx on 2017/5/9.
 */
var outParaExampleCkeditor;
var basePath = WEB_ROOT;
$(function () {

    createEditor("outParaExampleCkeditor");
    setCkeditValue();
})



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
                WEB.msg.info("提示","保存成功！",function () {
                    if (params.serviceId == ""){
                        window.location.href = basePath + "/aipEntry/paraInfoInit?"+jsonObj.msg;
                    }
                });
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
    var baseInfo_serviceId = $("#baseInfo_serviceId").val();
    if (baseInfo_serviceId == undefined || $.trim(baseInfo_serviceId)==""){
        baseInfo_serviceId = "";
    }
    params.serviceId = baseInfo_serviceId;
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

    var baseInfo_providerId = $.trim($("#baseInfo_providerServiceInfo").attr("providerId"));
    var baseInfo_pServiceId = $.trim($("#baseInfo_providerServiceInfo").attr("pServiceId"));
    var baseInfo_pVersion = $.trim($("#baseInfo_providerServiceInfo").attr("version"));
    if (baseInfo_providerId == "" || baseInfo_pServiceId == ""){
        WEB.msg.info("提示","供应商服务不能为空！");
        return null;
    }
    params.providerId = baseInfo_providerId;
    params.pServiceId = baseInfo_pServiceId;
    params.pVersion = baseInfo_pVersion;

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
 * 供应商服务点击事件
 */
function pServiceSel() {
    queryPServicePage(1);
    $("#pServiceModal").modal("show");
}

function queryPServicePage(pageNo) {
    var params = {};
    params.pageNo = pageNo
    $.ajax({
        url:basePath + "/aipEntry/queryProviderServicePage",
        dataType:'html',
        data:params,
        type:'post',
        async:true,
        success:function (data) {
            $("#pServiceModal_aipTable").html(data);
        }
    })
}

function pagerClick(pageNo) {
    queryPServicePage(pageNo);
}

function selPService(obj) {
    var pServiceId = $(obj).attr("pServiceId");
    var providerId = $(obj).attr("providerId");
    var serviceName = $(obj).attr("serviceName");
    var version = $(obj).attr("version");
    $("#baseInfo_providerServiceInfo").attr("providerId",providerId);
    $("#baseInfo_providerServiceInfo").val(serviceName);
    $("#baseInfo_providerServiceInfo").attr("pServiceId",pServiceId);
    $("#baseInfo_providerServiceInfo").attr("version",version);
    $("#pServiceModal").modal("hide");
}

function setCkeditValue() {
    var htmlId = $("#outParaExampleCkeditor").attr("htmlId");
    if (htmlId!=undefined && $.trim(htmlId) != ""){
        htmlId = htmlId + ".html";
        $.appAjax({
            url:htmlId,
            async : true,
            dataType : 'jsonp',
            jsonp :'jsonpCallback',//注意此处写死jsonCallback
            success: function (data) {
                outParaExampleCkeditor.setData(data.result);
            }
        });
    }
}

function backToList() {
    window.location.href=basePath + "/aipManager/pageInit";
}