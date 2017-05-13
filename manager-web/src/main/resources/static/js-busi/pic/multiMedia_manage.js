$(function(){
	/**
	 * 获取图片库列表
	 */
	var param = {
		pageNo:1
	};
	PicLib.gridPicLib(param);
	/**
	 * 保存图片库事件绑定
	 */
	$("#saveBtn").click(function(){
		PicLib.savePicLib();
	});
	
	$("#picUpLoad").bind("change", function(e) {
		PicLib.uploadImage(this);
		e.preventDefault();
	});
	/**
	 * 查询图片库
	 */
	$("#searchBtn").click(function(){
		var param = {
			pageNo:1
		};
		PicLib.gridPicLib(param);
	});
});

/**
 * 分页点击事件
 * @param pageNo
 */
function pagerClick(pageNo) {
	var param = {
		pageNo:pageNo
	};
	PicLib.gridPicLib(param);
}
var PicLib = {
	/**
	 * 点击编辑
	 */
	editPicLib : function(obj,libName,licPic,libId){
		$("#libName").val(libName);
		$("#libPic").val(licPic);
		$("#libId").val(libId);
		$("#libPicUrl").attr("src",$(obj).parents(".edit").find("img").attr('src'));
		$("#add_pic").modal();
		e.preventDefault();
	},
	/**
	 * 查询图片库列表
	 */
	gridPicLib : function(param){
		param.libName = $.trim($("#searhLibName").val());
		$.ajax({
	        url:WEB_ROOT+"/picmanage/gridpiclib",
	        async:true,
	        type:'POST',
	        dataType:'html',
	        data:param,
	        success:function (data) {
	            $("#picLibList").html(data);
	        }
	    });
	},
	/**
	 * 保存新增图片库
	 */
	savePicLib : function(){
		var param ={
			libName : $.trim($("#libName").val()),
			libPic : $.trim($("#libPic").val())
		};
		$.ajax({
	        url:WEB_ROOT+"/picmanage/savepiclib",
	        async:true,
	        type:'POST',
	        dataType:'json',
	        data:param,
	        success:function (data) {
	        	if(data.success){
	        		WEB.msg.info("提示", "保存成功", function(r) {
	        			var param = {
        					pageNo:1
        				};
        				PicLib.gridPicLib(param);
        				/**
	        			 * 清除弹出的各种隐藏域和input值
	        			 */
        				PicLib.clearWindInfo();
	                 });
	        	}else{
	        		WEB.msg.error("提示","保存失败");
	        	}
	        }
	    });
	},
	/**
	 * 保存编辑图片库信息
	 */
	updatePicLib : function(){
		var param ={
			catId : $("#editCatId").val(),
			catName : $.trim($("#catName").val()),
			showOrder : $.trim($("#showOrder").val())
		};
		$.ajax({
	        url:WEB_ROOT+"/picmanage/updatepiclib",
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
	 * 删除图片库信息
	 */
	deletePicLib : function(libId){
		WEB.msg.confirm("提示", "确定要删除当前图片库和该分库下的所有图片吗？", function(r) {
			$.ajax({
		        url:WEB_ROOT+"/picmanage/deletepiclib",
		        async:true,
		        type:'POST',
		        dataType:'json',
		        data:{libId:libId},
		        success:function (data) {
		        	if(data.success){
		        		WEB.msg.info("提示", "删除成功", function(r) {
		        			var param = {
	        					pageNo:1
	        				};
	        				PicLib.gridPicLib(param);
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
		$("#libId").val('');
		$("#libPicUrl").attr("src",'')
		
	},
	uploadImage : function (object) {
	    var filepath = $(object).val();
	    if(filepath==""){
	    	return false;
	    }
		var width=$(object).attr("width");
		var height=$(object).attr("height");
		if(width==undefined||height==undefined){
			width="";
			height="";
		}
		filepath = (filepath + '').toLowerCase();
		var regex = new RegExp(
				'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$', 'gi');
		/** 上传图片文件格式验证 */
		if (!filepath || !filepath.match(regex)) {
			alert('请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp).');
			return;
		}
		var url =WEB_ROOT+'/gdsEdit/uploadImage';
		var callback = function(data, status) {

			/** 上传成功，隐藏上传组件，并显示该图片 */
			if (data.flag == true) {
				$("#libPic").val(data.imageId);
				$("#libPicUrl").attr("src",data.imagePath)
			} else {
				alert(data.error);
			}
			$("#picUpLoad").bind("change", function(e) {
				PicLib.uploadImage(this);
				e.preventDefault();
			});
		};
		var params={
				width:width,
				height:height
		}
		PicLib.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json",params, callback);
	},
	/**
	 * 上传文件
	 * @param {} url
	 * @param {} secureuri
	 * @param {} fileElementId
	 * @param {} type
	 * @param {} dataType
	 * @param {} callback
	 */
	ajaxFileUpload : function (url, secureuri, fileElementId, type, dataType,params, callback) {
		$.ajaxFileUpload({
			url : url, // 用于文件上传的服务器端请求地址
			secureuri : secureuri, // 一般设置为false
			fileElementId : fileElementId, // 文件上传空间的id属性 <input
			type : type, // get 或 post
			data:params,
			dataType : dataType, // 返回值类型
			success : callback, // 服务器成功响应处理函数
			error : function(data, status, e) // 服务器响应失败处理函数
			{
				alert(e);
			}
		});
	}
};

