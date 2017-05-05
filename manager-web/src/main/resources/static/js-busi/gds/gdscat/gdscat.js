var zTreeObj;
$(function(){
	GdsCat.initZTree();
	//新增同级分类
	$(".addNewSomeLevel").die().live('click',function(e){
		$("#editCatId").val('');
		$("#editInfoCatId").hide();
		$("#netChildCat").val("");
		$("#myModal2").modal();
		e.preventDefault();
	});
	//新增下级分类
	$(".addChildCat").die().live('click',function(e){
		$("#editCatId").val('');
		//表示进行的是新增下级分类的操作
		$("#netChildCat").val("true");
		$("#editInfoCatId").hide();
		$("#myModal2").modal();
		e.preventDefault();
	});
	//编辑
	$(".editThisCat").die().live('click',function(e){
		$("#editInfoCatId").show();
		$("#netChildCat").val("");
		GdsCat.queryEditCatInfo($(this).attr('catId'));
		$("#myModal2").modal();
		e.preventDefault();
	});
	//确认
	$("#btnConfirm").die().live('click',function(){
		if(!$("#gdsCatForm").valid()){
			return;
		}
		if($("#editCatId").val()==""){
			GdsCat.saveGdsCat();
		}else{
			GdsCat.updateGdsCat();
		}
	});
	$("#gdsCatForm").validate({
		errorPlacement: function(error, element) {
			if(element.attr("name") == "provinces" || element.attr("name") == "cities")
				error.insertAfter("#cities");
			else if(element.attr("name") == "projectTag")
				error.insertAfter("#projectTagPlacement");
			else if(element.attr("name") == "projectType")
				error.insertAfter("#projectTypePlacement");
			else if(element.attr("name") == "financingAmount")
				error.insertAfter("#financingAmountPlacement");
			else if(element.attr("name") == "financingMax")
				error.insertAfter("#financingMaxPlacement");
			else if(element.attr("name") == "homeImgId")
				error.insertAfter("#homeImgIdPlacement");
			else if(element.attr("name") == "adImgId")
				error.insertAfter("#adImgIdPlacement");
			else
				error.insertAfter(element);
		},
		rules : {
			catName : {
				required : true,
				maxlength : 16
			}
		},
		messages : {
			catName : {
				required : '<font style="color:red">请输入分类名称</font>',
				maxlength : "<font style='color:red'>最多16个字符</font>"
			}
		}
	});
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
	        url: WEB_ROOT+"/gdscat/querygdscat",
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
	                var treeObj = $.fn.zTree.getZTreeObj("catZTree");
	                if(treeObj != null && treeObj.getNodes().length >=1){
	                	var param = {
                			catId:treeObj.getNodes()[0].id
                		};
	                	$("#catZTree_"+treeObj.getNodes()[0].id+"_a").trigger('click');
	                }
	            	
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
	},
	saveGdsCat : function(){
		var param ={
			catName : $.trim($("#catName").val()),
			showOrder : $.trim($("#showOrder").val()),
			catPid : $("#catPid").val()
		};
		$.ajax({
	        url:WEB_ROOT+"/gdscat/savegdscat",
	        async:true,
	        type:'POST',
	        dataType:'json',
	        data:param,
	        success:function (data) {
	        	if(data.success){
	        		WEB.msg.info("提示", "保存成功", function(r) {
	        			var param = {
	        					catId:$("#catId").val()
	        				};
        				GdsCat.querycatnodeinfo(param);
	                 });
	        	}else{
	        		WEB.msg.error("提示","保存失败");
	        	}
	        }
	    });
	},
	updateGdsCat : function(){
		var param ={
			catId : $("#editCatId").val(),
			catName : $("#catName"),
			showOrder : $("#showOrder")
		};
		$.ajax({
	        url:WEB_ROOT+"/gdscat/updategdscat",
	        async:true,
	        type:'POST',
	        dataType:'json',
	        data:param,
	        success:function (data) {
	        	if(data.success){
	        		WEB.msg.info("提示", "更新成功", function(r) {
	        			var param = {
	        					catId:$("#catId").val()
	        				};
        				GdsCat.querycatnodeinfo(param);
	                 });
	        	}else{
	        		WEB.msg.error("更新失败");
	        	}
	        }
	    });
	},
	deleteGdsCat : function(catId){
		WEB.msg.confirm("提示", "确定要删除当前分类和该分类下的所有分类吗？", function(r) {
			$.ajax({
		        url:WEB_ROOT+"/gdscat/deletegdscat",
		        async:true,
		        type:'POST',
		        dataType:'json',
		        data:{catId:catId},
		        success:function (data) {
		        	if(data.success){
		        		WEB.msg.info("提示", "删除成功", function(r) {
		        			var param = {
		        					catId:$("#catId").val()
		        				};
	        				GdsCat.querycatnodeinfo(param);
		                 });
		        	}else{
		        		WEB.msg.error("删除失败");
		        	}
		        }
		    });
         });
	},
	queryEditCatInfo : function(catId){
		$.ajax({
	        async:true,
	        url:WEB_ROOT+"/gdscat/queryeditcatinfo",
	        type:'post',
	        dataType:'json',
	        data:{catId:catId},
	        success:function (data) {
	        	var catInfo = data.obj;
	        	if(data.success  &&　catInfo != null){
	        		$("#editCatId").val(catInfo.catId);
	        		$("#catName").val(catInfo.catName);
	        		$("#showOrder").val(catInfo.showOrder);
	        	}
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



