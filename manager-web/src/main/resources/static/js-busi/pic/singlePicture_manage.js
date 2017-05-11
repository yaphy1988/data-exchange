$(function(){
	var param = {
		pageNo:1
	};
	PicInfo.gridPicInfo(param);
});

/**
 * 分页点击事件
 * @param pageNo
 */
function pagerClick(pageNo) {
	var param = {
		pageNo:pageNo
	};
	PicInfo.gridPicInfo(param);
}
var PicInfo = {
	/**
	 * 点击编辑
	 */
	editPicInfo : function(picName,licPic,libId,picUrl){
		$("#libName").val();
		$("#libPic").val();
		$("#add_pic").modal();
		e.preventDefault();
	},
	/**
	 * 查询列表
	 */
	gridPicInfo : function(param){
		param.libName = $.trim($("#searhPicName").val());
		param.libId = $("#searchLibId").val();
		$.ajax({
	        url:WEB_ROOT+"/picmanage/gridpicinfo",
	        async:true,
	        type:'POST',
	        dataType:'html',
	        data:param,
	        success:function (data) {
	            $("#picInfoList").html(data);
	        }
	    });
	},
	/**
	 * 新增保存图片
	 */
	savePicInfo : function(){
		var param ={
			picId : $("#picId").val(),
			picName : $.trim($("#picName").val()),
			libId : $("#libId").val(),
			picSpe : $("#picSpe").val()
		};
		$.ajax({
	        url:WEB_ROOT+"/picmanage/savepicinfo",
	        async:true,
	        type:'POST',
	        dataType:'json',
	        data:param,
	        success:function (data) {
	        	if(data.success){
	        		WEB.msg.info("提示", "保存成功", function(r) {
	        			var param = {
        					pageNo:pageNo
        				};
	        			PicInfo.gridPicInfo(param);
        				/**
	        			 * 清除弹出的各种隐藏域和input值
	        			 */
	        			PicInfo.clearWindInfo();
	                 });
	        	}else{
	        		WEB.msg.error("提示","保存失败");
	        	}
	        }
	    });
	},
	/**
	 * 保存编辑更新的图片
	 */
	updatePicInfo : function(){
		var param ={
			catId : $("#editCatId").val(),
			catName : $.trim($("#catName").val()),
			showOrder : $.trim($("#showOrder").val())
		};
		$.ajax({
	        url:WEB_ROOT+"/picmanage/updatepicinfo",
	        async:true,
	        type:'POST',
	        dataType:'json',
	        data:param,
	        success:function (data) {
	        	if(data.success){
	        		WEB.msg.info("提示", "更新成功", function(r) {
	        			var param = {
        					pageNo:pageNo
        				};
        				PicLib.gridPicLib(param);
	        			/**
	        			 * 清除弹出的各种隐藏域和input值
	        			 */
	        			PicLib.clearWindInfo();
	                });
	        	}else{
	        		WEB.msg.error("更新失败");
	        	}
	        }
	    });
	},
	/**
	 * 删除图片
	 */
	deletePicInfo : function(picId){
		WEB.msg.confirm("提示", "确定要删除当前图片库和该分库下的所有图片吗？", function(r) {
			$.ajax({
		        url:WEB_ROOT+"/picmanage/deletepiclib",
		        async:true,
		        type:'POST',
		        dataType:'json',
		        data:{picId:picId},
		        success:function (data) {
		        	if(data.success){
		        		WEB.msg.info("提示", "删除成功", function(r) {
		        			var param = {
	        					pageNo:pageNo
	        				};
		        			PicInfo.gridPicInfo(param);
		                });
		        	}else{
		        		WEB.msg.error("删除失败");
		        	}
		        }
		    });
         });
	},
	/**
	 * 清除弹出的各种隐藏域和input值
	 */
	clearWindInfo : function(){
		$("#libName").val('');
		$("#libPic").val('');
	}
};

