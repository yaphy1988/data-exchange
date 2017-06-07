var basePath = WEB_ROOT;
var zTreeObj;
var zTreeObjUnSel;
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
var settingUnSel = {
	    data: {
	        simpleData: {
	            enable: true
	        }
	    },
	    callback:{
	        onClick:catUnSelClick
	    }
	};
$(function(){
	 initCatZTree();
	qryModuleGoodsSelList(1);
	qryModuleGoodsUnSelList(1);
	$("#moduleId").on('click',function(){
		qryModuleGoodsSelList(1);
		qryModuleGoodsUnSelList(1);	
	});
	
});
function applyInvoice(orderId){
	window.location.href = basePath+"/invoiceManage/applyInvoice?orderId="+orderId;
}
/**
 * 查询已选择商品
 * @param defaultIndex
 * @returns
 */
function qryModuleGoodsSelList(index){
	var gdsName=$("#gdsName").val();
	var gdsStatus=$("#gdsStatusSel").val();
	var catId=$("#catIdSel").attr("catId");
	var moduleId=$("#moduleId").val();
	var param={
			moduleId:moduleId,
			gdsName:gdsName,
			gdsStatus:gdsStatus,
			catId:catId,
			pageSize:10,
			pageNo:index
			};
	$.ajax({
		url:basePath+'/pageModuleGoods/qryModuleGoodsSelList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#selGoodsList').empty();
			$('#selGoodsList').html(data);
		}
	});
}
/**
 * 查询待选择商品
 * @param defaultIndex
 * @returns
 */
function qryModuleGoodsUnSelList(index){
	var gdsName=$("#unSelGdsName").val();
	var catId=$("#catIdUnSel").attr("catId");
	var moduleId=$("#moduleId").val();
	var param={
			moduleId:moduleId,
			gdsName:gdsName,
			catId:catId,
			pageSize:10,
			pageNo:index
			};
	$.ajax({
		url:basePath+'/pageModuleGoods/qryModuleGoodsUnSelList',
		cache:false,
		async:true,
		dataType:'html',
		data : param,
		success:function(data){
			$('#unSelGoodsList').empty();
			$('#unSelGoodsList').html(data);
		}
	});
}
function deletePageModuleGoods(pmgId){
	var status="0";//失效
	var params={
			pmgId:pmgId,
			status:status
			
	};
	var url=basePath+"/pageModuleGoods/updatePageModuleGoods";
	WEB.msg.confirm("提示","确定要删除该商品吗？",function(){
		$.ajax({
			url:url,
			cache:false,
			async:true,
			dataType:'json',
			data : params,
			success:function(data){
				if(data.success){
					 WEB.msg.info("提示","删除商品成功！",function(){
						 qryModuleGoodsUnSelList(1);
						 qryModuleGoodsSelList(1);
			       });
				}else{
					WEB.msg.info("提示","删除商品失败！");
				}
			}
		});
	});
}
function savePageModuleGoods(gdsId){
	var moduleId=$("#moduleId").val();
	var status="1";//失效
	var params={
			moduleId:moduleId,
			gdsId:gdsId,
			status:status
		};
	var url = basePath + "/pageModuleGoods/savePageModuleGoods";
	$.ajax({
		url : url,
		cache : false,
		async : true,
		dataType : 'json',
		data : params,
		success : function(data) {
			if (data.success) {
				WEB.msg.info("提示", "选择商品成功！", function() {
					qryModuleGoodsUnSelList(1);
					qryModuleGoodsSelList(1);
				});
			} else {
				WEB.msg.info("提示", "选择商品失败！");
			}
		}
	});
}

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
                zTreeObjUnSel = $.fn.zTree.init($("#catZTreeUnSel"),settingUnSel,zNodes);

            }
        }
    })
}

function showCatSel(obj){

    $("#catZTree").parent().find(".ztree").show();
    $("body").bind("mousedown", onBodyDown);
    hideMenuUnSel();
}
function showCatUnSel(obj){

    $("#catZTreeUnSel").parent().find(".ztree").show();
    $("body").bind("mousedown", onBodyDownUnSel);
    hideMenu();
}

/**
 * 隐藏父类的节点属性结构菜单
 */
var hideMenu = function () {
    $("#catZTree").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
};
var hideMenuUnSel = function () {
    $("#catZTreeUnSel").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDownUnSel);
};
var onBodyDown = function () {
    if (!(event.target.id == "catIdSel" || $(event.target).parents("#catZTree").length>0)) {
        hideMenu();
    }
};
var onBodyDownUnSel = function () {
    if (!(event.target.id == "catIdUnSel" || $(event.target).parents("#catZTreeUnSel").length>0)) {
    	hideMenuUnSel();
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
function catUnSelClick(e, treeId, treeNode) {
    if (treeNode.id == "0"){
        $("#catIdUnSel").attr("catId","");
    }else{
        $("#catIdUnSel").attr("catId",treeNode.id);
    }
    $("#catIdUnSel").val(treeNode.name);
    hideMenuUnSel();
}