function  alertt() {
	alert("提示一下");
}
/**
 * 
 */
/** 获取地市列表 */
function saveMadeData() {
	var needTiel    =  "needTiel";//$("#needTiel").val();
	var needcontent = "needcontent";//$("#needcontent").val();
	var lnkposen    = "lnkposen";//$("#lnkposen").val();
	var lnkphone    = "lnkphone";//$("#lnkphone").val();
	var lnkemail    = "lnkemail";//$("#lnkemail").val();
	var url = WEB_ROOT + "/homePage/saveMadeData";
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