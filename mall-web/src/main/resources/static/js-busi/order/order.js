var basePath =  WEB_ROOT;
 $(function(){
		// 数量input框的x修改
		$("#gdscount").bind('change', function() {
			var amount =  $("#gdscount").val();
			if (amount == 0) { 
				WEB.msg.confirm("提示信息", "您确定要删除该商品吗？", deleteGoods, function() {
					$("#gdscount").val(1);
				}, true);
			} else {
				amountUpdate($("#gdscount").val());
			}
		}); 
})  
function deleteGoods()
{
     //跳转到首页
	 self.location=basePath+"homePage/pageInit"; 
}
function reduce()
{
//减少数量
	 var num =  parseInt($("#gdscount").val());
	 if(num > 1)
	 {
		 $("#gdscount").val(num - 1);
	 }
	 //提交到后台
	 WEB.msg.info("提示","后台去更新数量:"+$("#gdscount").val());
 }
 function addnum()
 {
    //增加数量
	 var num =  parseInt($("#gdscount").val());
  	$("#gdscount").val(num + 1);
	 //提交到后台
	 WEB.msg.info("提示","后台去更新数量:"+$("#gdscount").val());
 }
 function amountUpdate(num)
{
   // 后台更新数据	 
	 WEB.msg.info("提示","后台去更新数量:"+$("#gdscount").val());
}
 
/*//获取数据定制的图片
function queryModue103(moduleId){
	var url = basePath+'/homePage/queryPageModuleAd';
	var params={moduleId:moduleId};
	var callBack =function(data){
		var htmlOl = '';
		var htmDiv = '';
		if(data.success){
			$(data.moduleAdList).each(function(i,d){
				$("#datasetImg").attr("src",d.vfsId); 
			});
		} 
	};
	doAjax(url,params,callBack);
}*/
 