$(function(){
	var param = {
		pageNo : 1
	}
	BrowsingHistory.gridUserfootprint(param);
	//查询按钮
	$("#searchBtn").click(function(){
		var param = {
			pageNo : 1,
			gdsId : $.trim($("#gdsId").val())
		}
		BrowsingHistory.gridUserfootprint(param);
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
		BrowsingHistory.gridUserfootprint(param);
		e.preventDefault();
	})
});

/**
 * 分页点击事件
 * @param pageNo
 */
function pagerClick(pageNo) {
	var param = {
		gdsId : $.trim($("#gdsId").val()),
		pageNo:pageNo
	};
	BrowsingHistory.gridUserfootprint(param);
}
var BrowsingHistory = {
	gridUserfootprint : function(param){
		param.gdsName = $.trim($("#gdsName").val());
		param.catFirst = $(".tabSelect.active").attr('catFirst');
		$.ajax({
	        url:WEB_ROOT+"/userfootprint/griduserfootprint",
	        async:true,
	        type:'POST',
	        dataType:'html',
	        data:param,
	        success:function (data) {
	            $("#userFootPrintList").html(data);
	        }
	    });
	},
	deleteUserFootPrint : function(fpId){
		WEB.msg.confirm("提示", "确定要删除当前记录信息吗？", function(r) {
			$.ajax({
		        url:WEB_ROOT+"/userfootprint/deleteuserfootprint",
		        async:true,
		        type:'POST',
		        dataType:'json',
		        data:{fpId:fpId},
		        success:function (data) {
		        	if(data.success){
		        		WEB.msg.info("提示", "删除成功", function(r) {
		        			var param = {
	        				};
		        			BrowsingHistory.gridUserfootprint(param);
		                 });
		        	}else{
		        		WEB.msg.error("删除失败");
		        	}
		        }
		    });
         });
	}
};



