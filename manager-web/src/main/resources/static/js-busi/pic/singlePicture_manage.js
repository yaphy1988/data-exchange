$(function(){
	var param = {
		pageNo:1
	};
	PicInfo.gridPicInfo(param);
	/**
	 * 保存图片库事件绑定
	 */
	$("#saveBtn").click(function(){
		if($("#editFlag").val()=="true"){
			PicInfo.updatePicInfo();
		}else{
			PicInfo.savePicInfo();
		}
		
	});
	
	$("#picUpLoad").bind("change", function(e) {
		PicInfo.uploadImage(this);
		e.preventDefault();
	});
	/**
	 * 查询图片库
	 */
	$("#searchBtn").click(function(){
		var param = {
			pageNo:1
		};
		PicInfo.gridPicInfo(param);
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
	PicInfo.gridPicInfo(param);
}
var PicInfo = {
	/**
	 * 新增图片
	 */	
	newPicInfo : function(){
		$("#libName").val($("#searchLibName").val());
		$("#libId").val($("#searchLibId").val());
	},	
	/**
	 * 点击查看图片
	 */
	showPicInfo : function(obj){
		$("#showPicUrl").attr("src",$(obj).attr('picUrl'));
		$("#view_pic").modal();
	},
	/**
	 * 点击编辑
	 */
	editPicInfo : function(obj,picId,picName,picUuid){
		$("#libName").val($("#searchLibName").val());
		$("#libId").val($("#searchLibId").val());
		$("#picName").val(picName);
		$("#picId").val(picId);
		$("#picUuid").val(picUuid);
		$("#picUrl").attr("src",$(obj).parents(".edit").find("img").attr('src'));
		$("#editFlag").val("true");
		$("#add_pic").modal();
	},
	/**
	 * 查询列表
	 */
	gridPicInfo : function(param){
		param.picName = $.trim($("#searchPicName").val());
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
			picUuid : $("#picUuid").val(),
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
        					pageNo:1
        				};
	        			PicInfo.gridPicInfo(param);
	        			$(".close").trigger('click');
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
			picId : $("#picId").val(),
			picName : $.trim($("#picName").val()),
			libId : $("#libId").val(),
			picSpe : $("#picSpe").val(),
			picUuid : $("#picUuid").val()
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
        					pageNo:1
        				};
        				PicInfo.gridPicInfo(param);
        				$(".close").trigger('click');
	        			/**
	        			 * 清除弹出的各种隐藏域和input值
	        			 */
        				PicInfo.clearWindInfo();
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
		WEB.msg.confirm("提示", "确定要删除当前图片吗？", function(r) {
			$.ajax({
		        url:WEB_ROOT+"/picmanage/deletepicinfo",
		        async:true,
		        type:'POST',
		        dataType:'json',
		        data:{picId:picId},
		        success:function (data) {
		        	if(data.success){
		        		WEB.msg.info("提示", "删除成功", function(r) {
		        			var param = {
	        					pageNo:1
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
		$("#picName").val('');
		$("#picUuid").val('');
		$("#editFlag").val('');
		$("#libName").val('');
		$("#libId").val('');
		$("#picUrl").attr('src','');
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
				$("#picUuid").val(data.imageId);
				$("#picUrl").attr("src",data.imagePath)
			} else {
				alert(data.error);
			}
			$("#picUpLoad").bind("change", function(e) {
				PicInfo.uploadImage(this);
				e.preventDefault();
			});
		};
		var params={
				width:width,
				height:height
		}
		PicInfo.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json",params, callback);
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

