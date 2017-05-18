/**
 * Created by yx on 2017/5/16.
 */
var exampleCodeEditorArr = new Array();
$(function () {

    $(".exampleCode").each(function (i) {
        var exampleCode = $(this).attr("id");
        if (exampleCode != undefined && $.trim(exampleCode) !=""){
            createEditor(exampleCode);
        }
    })
    
    // $(".deleteBtn").on('click',function () {
    //     $(this).parent().parent().remove();
    //     alert($(this).index());
    // })
})

function createEditor(id){
    var config = {
        filebrowserImageUploadUrl :WEB_ROOT+ "/ck/upload?type=Image",
        filebrowserUploadUrl :WEB_ROOT+"/ck/upload?type=File",
        filebrowserFlashUploadUrl :WEB_ROOT+"/ck/upload?type=Flash"
    };
    var exampleCodeEditor = CKEDITOR.replace(id,config)
    exampleCodeEditorArr.push(exampleCodeEditor);
    setEditor(exampleCodeEditor,id);

}

function setEditor(editor,id) {
    var thisId = "#"+id;
    var htmlId = $(thisId).attr("htmlId");
    if (htmlId!=undefined || $.trim(htmlId)!=""){
        // htmlId = htmlId + ".html";
        $.appAjax({
            url:htmlId,
            async : true,
            dataType : 'jsonp',
            jsonp :'jsonpCallback',//注意此处写死jsonCallback
            success: function (data) {
                editor.setData(data.result);
            }
        });
    }
}

/**
 * 隐藏按钮点击事件
 * @param obj
 */
function hiddenCode(obj) {
    $(obj).parent().parent().find(".editTable").find("tr:last").hide();
    $(obj).next().show();
    $(obj).hide();
}

/**
 * 展开按钮点击事件
 * @param obj
 */
function showCode(obj){
    $(obj).parent().parent().find(".editTable").find("tr:last").show();
    $(obj).prev().show();
    $(obj).hide();
}

/**
 * 删除按钮点击事件
 * @param obj
 */
function deleteExample(obj) {
    var index = $(obj).index(".deleteBtn");
    var newEditorArr = new Array();
    for(var i=0 ; i<exampleCodeEditorArr.length; i++){
        if (i!=index-1){
            newEditorArr.push(exampleCodeEditorArr[i]);
        }
    }
    exampleCodeEditorArr = newEditorArr;
    $(obj).parent().parent().remove();
}

/**
 * 新增按钮点击事件
 */
function addExample(obj) {
    $(".exampleCode").each(function (i) {
        if (i>0){
            $(this).parent().parent().hide();
            $(this).parent().parent().parent().parent().parent().find(".openBtn").show();
            $(this).parent().parent().parent().parent().parent().find(".closeBtn").hide();
        }
    })
    var html = $("#exampleCodeHidden").html();
    $(obj).parent().before(html);
    var len = $(".exampleCode").length-2;
    var id = "exampleCode"+len;
    $(".exampleCode").eq(len+1).attr("id",id);
    // $(".deleteBtn").eq(len+1).attr("thisIndex",len+1);
    createEditor(id);
    // $("#exampleCodeContent").append(html);
}


function initExampleParams(params) {
    var exampleArr = new Array();
    var len = $(".exampleCodeDiv").length;
    var flag = true;
    for (var i = 1 ; i < len ; i++){
        var exampleCode = {};
        var thisDiv = $(".exampleCodeDiv").eq(i);
        var program = $(thisDiv).find(".exampleCode_program").val();
        var author = $(thisDiv).find(".exampleCode_author").val();
        var title = $(thisDiv).find(".exampleCode_title").val();
        var exampleCodeDesc = exampleCodeEditorArr[i-1].getData();
        if (program==undefined || $.trim(program) == ""){
            WEB.msg.info("提示","语言不能为空！");
            flag = flase;
            break;
        }
        if (author == undefined || $.trim(author) == ""){
            WEB.msg.info("提示","提供者不能为空！");
            flag = false;
            break;
        }
        if (title == undefined || $.trim(title) == ""){
            WEB.msg.info("提示","标题不能为空！");
            flag = false;
            break;
        }
        if (exampleCode==undefined || $.trim(exampleCode) == ""){
            WEB.msg.info("提示","示例代码不能为空！");
            flag = false;
            break;
        }
        exampleCode.program = $.trim(program);
        exampleCode.author = $.trim(author);
        exampleCode.title = $.trim(title);
        exampleCode.docId = $.trim(exampleCodeDesc);
        exampleArr.push(exampleCode);
    }
    if (!flag){
        return false;
    }
    var exampleString = JSON.stringify(exampleArr);
    params.exampleStr = exampleString;
    return true;
}

function sumbitExampleInfo() {
    var params = {};
    if (!initExampleParams(params)){
        return;
    }
    var serviceId = $("#exampleInfo_serviceId").val();
    if (serviceId == undefined || $.trim(serviceId)==""){
        serviceId = "";
    }
    params.serviceId = $.trim(serviceId);
    var version = $("#exampleInfo_version").val();
    if (version == undefined || $.trim(version) == ""){
        version = "";
    }
    params.version = version;
    $.ajax({
        url:WEB_ROOT + "/aipEntry/sumbitExampleInfo",
        async:false,
        data:params,
        dataType:'json',
        type:'post',
        success:function (data) {
            if (data.success){
                WEB.msg.info("提示","保存成功");
            }else {
                WEB.msg.info("提示",data.msg);
            }
        }
    })
}
