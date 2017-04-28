/**
 * Created by yx on 2017/4/27.
 */
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
        url:"/gdsManage/queryAllCats",
        type:'post',
        dataType:'json',
        data:{},
        success:function (jsonObj) {
            if(jsonObj!=null && jsonObj.success){
                var zNodes = new Array();
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
    $("#catIdSel").attr("catId",treeNode.id);
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
        url:"/gdsManage/queryGdsList",
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