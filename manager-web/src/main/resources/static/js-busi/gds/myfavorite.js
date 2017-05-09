$(function(){
	var param = {
		pageNo : 1
	}
	MyFavorite.gridUserCollection(param);
	//查询按钮
	$("#searchBtn").click(function(){
		var param = {
			pageNo : 1,
			gdsId : $.trim($("#gdsId").val())
		}
		MyFavorite.gridUserCollection(param);
	});
	$.validator.addMethod("number",
		function(value, element, params) {
		var length = value.length;
		var number = /^([0-9]+)$/;
		return this.optional(element)
				|| (number.test(value));
	}, "只能输入整数");
	//tab 切换
	$(".nav-tabs li").live('click',function(e){
		var param = {
			pageNo : 1,
			gdsId : $.trim($("#gdsId").val())
		}
		MyFavorite.gridUserCollection(param);
		e.preventDefault();
	})
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
var MyFavorite = {
	gridUserCollection : function(param){
		param.catFirst = $(".tabSelect.active").attr('catFirst');
		$.ajax({
	        url:WEB_ROOT+"/usercollection/gridusercollection",
	        async:true,
	        type:'POST',
	        dataType:'html',
	        data:param,
	        success:function (data) {
	            $("#userCollectionList").html(data);
	        }
	    });
	},
	deleteUserCollect : function(colId){
		WEB.msg.confirm("提示", "确定要删除当前分类和该分类下的所有分类吗？", function(r) {
			$.ajax({
		        url:WEB_ROOT+"/usercollection/deleteusercollect",
		        async:true,
		        type:'POST',
		        dataType:'json',
		        data:{colId:colId},
		        success:function (data) {
		        	if(data.success){
		        		WEB.msg.info("提示", "删除成功", function(r) {
		        			var param = {
	        				};
		        			MyFavorite.gridUserCollection(param);
		                 });
		        	}else{
		        		WEB.msg.error("删除失败");
		        	}
		        }
		    });
         });
	}
};



