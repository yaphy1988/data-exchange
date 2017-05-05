$(function(){
	GdsCat.initZTree();
});
/**
 * 分页点击事件
 * @param pageNo
 */
function pagerClick(pageNo) {
	var param = {
		catId:$("#catId").val(),
		pageNo:pageNo
	};
	GdsCat.queryCatChildList(param);
}
var GdsCat = {
	setting : {
	    data: {
	        simpleData: {
	            enable: true
	        }
	    },
	    callback:{
	        onClick:selClick
	    }
	},
	initZTree : function(){
		 $.ajax({
	        async:true,
	        url:WEB_ROOT+"/gdscat/querygdscat",
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
	                zTreeObj = $.fn.zTree.init($("#catZTree"),GdsCat.setting,zNodes);
	            }
	        }
	    });
	},
	querycatnodeinfo : function(param){
		$.ajax({
	        async:true,
	        url:WEB_ROOT+"/gdscat/querycatnodeinfo",
	        type:'post',
	        dataType:'html',
	        data:param,
	        success:function (data) {
	        	$("#nodeInfo").html(data);
	        	param.pageNo = 1;
        		GdsCat.queryCatChildList(param);
	        }
	    });
	},
	queryCatChildList : function(param){
		$.ajax({
	        url:WEB_ROOT+"/gdscat/querycatchild",
	        async:true,
	        type:'POST',
	        dataType:'html',
	        data:param,
	        success:function (data) {
	            $("#childList").html(data);
	        }
	    });
	}
};
function selClick(e, treeId, treeNode) {
//    $("#catIdSel").attr("catId",treeNode.id);
//    $("#catIdSel").val(treeNode.name);
	//获取当前节点的信息
	var param = {
		catId:treeNode.id
	};
	GdsCat.querycatnodeinfo(param);
}