$(function(){
	var param = {};
	Search.gridGdsInfo(param);
	Search.init();
});
var Search = {
		init : function(){
			//关闭选中的条件事件绑定
			$(".close").die().live('click',function(){
				var $this = $(this);
				$this.parents("a").remove();
				var catId = $this.attr('id');
				$(".gl_group").find("a[catId="+catId+"]").parents(".gl_items").show();
				if($("#selectedCondition").children("a").length==0){
					$("#condictionFlag").attr('style',"visibility:hidden");
				}
			});
			
			//分类选择事件绑定
			$(".catSelecte").die().live('click',function(){
				var $this = $(this);
				var html = "<a href='javascript:void(0)'><em>"+$this.attr('catPName')+":</em><span>"+$this.attr('catName')+"</span><i class='close' id="+$this.attr('catId')+">×</i></a>";
				$("#selectedCondition").append(html);
				$this.parents(".gl_items").hide();
				$("#condictionFlag").attr('style',"visibility:visiable");
			});
		},
		/**
		 * 查询商品列表
		 */
		gridGdsInfo : function(param){
			$.ajax({
				url:WEB_ROOT+'/search/gridgdsinfo',
				cache:false,
				async:true,
				dataType:'html',
				data : param,
				success:function(data){
					$('#search_list').html(data);
					/**
					 * 分页组件
					 */
					$('#pagerId').pager({callback: function(index){
						var param = {pageNo : index};
						Search.gridGdsInfo(param);
					}});
				}
			});
		}
};