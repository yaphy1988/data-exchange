$(function(){
	$(".custom_made").die().live('click',function(){
		$("#myModal").show();
	});
});
/**
 * 
 */
/** 保存数据定制信息 */
function saveMadeData() {
	
/*	var staff_id = $("#staff_id").val();
	if(staff_id == "")
	{
	    WEB.msg.info("提示",'请先登录');
	    return;
	}*/
	var needTiel    =  encodeURI2($("#needTiel").val());
	var needcontent =  encodeURI2($("#needcontent").val());
	var lnkposen    =  encodeURI2($("#lnkposen").val());
	var lnkphone    =  $("#lnkphone").val();
	var lnkemail    =  $("#lnkemail").val();
	var url = WEB_ROOT + "/homePage/saveMadeData";
    if(!needTiel){
    	showwarm('needTielDiv','请输入标题');
		return;	 
 	 }
    if(!needcontent){
    	showwarm('needcontentDiv','请输入内容');
		return;	 
 	 }
    if(!lnkposen){
    	showwarm('lnkposenDiv','请输入联系人');
		return;	 
 	 }
	if(!WEB.check.isMobile(lnkphone))
	{
		showwarm('lnkphoneDiv','请输入正确的手机号码');
		return;
	}
	if(!WEB.check.isEmail(lnkemail))
	{
 		showwarm('lnkemailDiv','请输入正确的邮箱地址');
		return;
	} 

	$("#commitData").attr("disabled", true);
	param = {
		needTiel : needTiel,
		needcontent : needcontent,
		lnkposen : lnkposen,
		lnkphone : lnkphone,
		lnkemail : lnkemail
	};
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		async : false,
		data : param,
		success : function(data) {
		   if(data.success)  {
			    WEB.msg.info("提示",'保存成功');
			    $("#myModal").hide(); 
				$("#commitData").attr("disabled", false);

  		   }
	}});
}
function hideMadeData()
{
	  $("#myModal").hide(); 
 }