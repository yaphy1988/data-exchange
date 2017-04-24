function  showMyModal() {
	$("#myModal").show();
}
function encodeURI2(strinfo) {
	//中文编码一次，后台解析即可
	var strinfo1 = encodeURI(strinfo);
	return strinfo1;
}
/**
 * 
 */
/** 获取地市列表 */
function saveMadeData() {
	var needTiel    =  encodeURI2($("#needTiel").val());
	var needcontent =  encodeURI2($("#needcontent").val());
	var lnkposen    =  encodeURI2($("#lnkposen").val());
	var lnkphone    =  $("#lnkphone").val();
	var lnkemail    =  $("#lnkemail").val();
	var url = WEB_ROOT + "/homePage/saveMadeData";
	if(!WEB.check.isMobile(lnkphone))
	{
		WEB.msg.info("提示",'请输入正确的手机号码');
		return;
	}
	if(!WEB.check.isEmail(lnkemail))
	{
		WEB.msg.info("提示",'请输入正确的邮箱地址');
		return;
	}
	
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
			 alert("保存成功");
	}});
}