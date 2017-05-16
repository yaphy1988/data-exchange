var basePath = WEB_ROOT;
$(function(){

})
/**
 * 保存编辑模块
 */
function savePageModule(){
	var moduleId=$("#moduleId").val();
	var moduleName=$("#moduleName").val();
	var modulePid=$("#modulePid").val();
	var moduleCount=$("#moduleCount").val();
	var orderNo=$("#orderNo").val();
	var status=$('input[name="status"]:checked').val();
	var remark=$("#remark").val();
	if(moduleName==""){
		WEB.msg.info("提示","模块名称不能为空");
		return;	
	}
	if(moduleName.length>128){
		WEB.msg.info("提示","模块名称不能超过128个字符");
		return;
	}
	if(moduleCount==""){
		WEB.msg.info("提示","展示数量不能为空");
		return;
	}
	if(!isInteger(moduleCount)){
		WEB.msg.info("提示","展示数量只能是正整数！");
		return;
	}
	if(remark.length>128){
		WEB.msg.info("提示","备注不能超过128个字符");
		return;
	}
	var params={
			moduleName:moduleName,
			moduleId:moduleId,
			modulePid:modulePid,
			moduleCount:moduleCount,
			orderNo:orderNo,
			remark:remark,
			status:status
			};
	var url=basePath+"/pageManage/savePageModule";
	$.ajax({
			url:url,
			cache:false,
			async:true,
			dataType:'json',
			data : params,
			success:function(data){
           	 WEB.msg.info("提示","保存成功",function(){
             	window.location.href = basePath+"/pageManage/moduleManage";
           	 });
		}
	});
}

//校验输入是否为正整数
function isInteger(str){
	 var reg = /^(([1-9][0-9]*))$/;
	 return reg.test(str);
}