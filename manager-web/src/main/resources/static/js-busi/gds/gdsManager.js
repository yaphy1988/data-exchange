/**
 * Created by yx on 2017/4/27.
 */
var basePath = WEB_ROOT;
var zTreeObj;
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    callback:{
        onClick:catSelClick
    }
};
$(function () {

    initCatZTree();

    queryGdsList(1);
    
})

function initCatZTree() {
    $.ajax({
        async:true,
        url:basePath+"/gdsManage/queryAllCats",
        type:'post',
        dataType:'json',
        data:{},
        success:function (jsonObj) {
            if(jsonObj!=null && jsonObj.success){
                var zNodes = new Array();
                var rootNode = {};
                rootNode.id = 0;
                rootNode.name = "全部";
                zNodes.push(rootNode);
                $.each(jsonObj.obj,function (i,catObj) {
                    var zNode = {};
                    zNode.id = catObj.catId;
                    zNode.name = catObj.catName;
                    zNode.pId = catObj.catPid;
                    zNodes.push(zNode);
                })
                zTreeObj = $.fn.zTree.init($("#catZTree"),setting,zNodes);
            }
        }
    })
}

function showCatSel(obj){

    $("#catZTree").parent().find(".ztree").show();
    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏父类的节点属性结构菜单
 */
var hideMenu = function () {
    $("#catZTree").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
};

var onBodyDown = function () {
    if (!(event.target.id == "catIdSel" || $(event.target).parents("#catZTree").length>0)) {
        hideMenu();
    }
};

function catSelClick(e, treeId, treeNode) {
    if (treeNode.id == "0"){
        $("#catIdSel").attr("catId","");
    }else{
        $("#catIdSel").attr("catId",treeNode.id);
    }
    $("#catIdSel").val(treeNode.name);
    hideMenu();
}

function queryGdsList(pageNo) {
    if(pageNo==undefined || $.trim(pageNo) == "" || Number(pageNo) <= 0){
        pageNo = 1;
    }
    var params = {};
    var gdsName = $("#gdsNameInput").val();
    var status = $("#gdsStatus").val();
    var catId = $("#catIdSel").attr("catId");
    if (gdsName==undefined || $.trim(gdsName) == ""){
        gdsName = "";
    }
    if (status == undefined || $.trim(status)==""){
        status = "";
    }
    if (catId == undefined || $.trim(catId) == ""){
        catId = "";
    }
    params.catId = catId;
    params.status = status;
    params.gdsName = gdsName;
    params.pageNo = pageNo;
    $.ajax({
        url:basePath+"/gdsManage/queryGdsList",
        async:true,
        type:'POST',
        dataType:'html',
        data:params,
        success:function (data) {
            $("#gdsListContent").html(data);
        }
    })
}

/**
 * 分页点击事件
 * @param pageNo
 */
function pagerClick(pageNo) {
    queryGdsList(pageNo);
}

/**
 * 改变查询状态
 */
function changeStatus() {
    var status = $("#gdsStatusSel").val();
    $("#gdsStatus").val(status);
}

function tabClick(status) {
    $("#gdsStatus").val(status);
    queryGdsList(1);
}

/**
 * 上下架、删除、启用
 * @param obj
 */
function doGdsStatus(obj) {
    var targetStatus = $(obj).attr("targetStatus");
    var gdsId = $(obj).attr("gdsId");
    var oldStatus = $(obj).attr("oldStatus");
    if (oldStatus == undefined){
        oldStatus = "";
    }
    if (targetStatus==undefined || gdsId == undefined || $.trim(targetStatus) == "" || $.trim(gdsId) == ""){
        WEB.msg.info("提示","系统错误，请联系管理员！");
        return ;
    }
    var params = {};
    params.gdsId = gdsId;
    params.targetStatus = targetStatus;
    params.oldStatus = oldStatus;
    $.ajax({
        url:basePath+"/gdsManage/doGdsStatus",
        type:'post',
        dataType:'json',
        data:params,
        async:true,
        success:function(jsonObj){
            $("#doGdsStatusModal").modal('hide');
            if (jsonObj){
                if (jsonObj.success){
                    if (targetStatus == "1"){
                        WEB.msg.info("提示","上架成功");
                    }else if (targetStatus == "2"){
                        if (oldStatus == "9"){
                            WEB.msg.info("提示","启用成功");
                        }else {
                            WEB.msg.info("提示","下架成功");
                        }
                    }else if (targetStatus == "9"){
                        WEB.msg.info("提示","删除成功");
                    }
                    queryGdsList(1);
                }else{
                    WEB.msg.info("提示",jsonObj.msg);
                }
            }else{
                WEB.msg.info("提示","系统错误，请联系管理员！");
            }

        },
        error:function(){
            $("#doGdsStatusModal").hide();
            WEB.msg.info("提示","系统ajax请求错误，请联系管理员!")
        }
    })
}

function doGdsStatusModal(obj) {
    var targetStatus = $(obj).attr("targetStatus");
    var gdsId = $(obj).attr("gdsId");
    var oldStatus = $(obj).attr("oldStatus");
    if (oldStatus == undefined){
        oldStatus = "";
    }
    var title = "";
    var content = "";

    if (targetStatus == "1"){
        title = "商品上架";
        content = "确认上架该商品？";
    }else if (targetStatus == "2"){
        if (oldStatus == "9"){
            title = "商品重新启用";
            content = "确认重新启用该商品？";
        }else {
            title = "商品下架";
            content = "确认下架该商品？";
        }
    }else if (targetStatus == "9"){
        title = "商品删除";
        content = "确认删除该商品？";
    }else if (targetStatus == "look"){
        window.location.href = basePath+"/gdsEdit/pageInit?gdsId="+gdsId+"&&isView=true";
        return;
    }else if (targetStatus == "edit"){
        window.location.href = basePath+"/gdsEdit/pageInit?gdsId="+gdsId;
        return;
    }
    $("#doGdsStatusModalTitle").html(title);
    $("#doGdsStatusModalContent").html(content);
    $("#doGdsStatusModalConfirm").attr("gdsId",gdsId);
    $("#doGdsStatusModalConfirm").attr("targetStatus",targetStatus);
    $("#doGdsStatusModalConfirm").attr("oldStatus",oldStatus);
    $("#doGdsStatusModal").modal('show');
}

function checkRecGds(obj){
    var ifRecGds = "";
    var gdsId = $(obj).attr("gdsId");
    if ($(obj).is(":checked")){
        ifRecGds = "1";
    }else{
        ifRecGds = "0";
    }
    if (gdsId==undefined || $.trim(gdsId) == ""){
        WEB.msg.info("提示","系统错误，请联系管理员！")
        return;
    }
    var params = {};
    params.gdsId = gdsId;
    params.ifRecGds = ifRecGds;
    $.ajax({
        url:basePath+"/gdsManage/dealGdsRec",
        data:params,
        dataType:'json',
        type:'post',
        async:true,
        success:function (jsonObj) {
            if (jsonObj){
                if (jsonObj.success){
                    if (ifRecGds=="1"){
                        WEB.msg.info("提示","商品推荐成功！");
                    }else{
                        WEB.msg.info("提示","取消商品推荐成功！");
                    }
                    queryGdsList(1);
                }else{
                    WEB.msg.info("提示",jsonObj.msg);
                }
            }else{
                WEB.msg.info("提示","系统错误，请联系管理员！");
            }
        },
        error:function () {
            WEB.msg.info("提示","系统ajax提交错误，请联系管理员！");
        }
    })

}