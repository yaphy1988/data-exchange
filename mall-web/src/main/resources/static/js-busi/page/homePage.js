/**
 * 
 */
/** 获取地市列表 */
function saveMadeData() {
	var needTiel    = $("#needTiel").val();
	var needcontent = $("#needcontent").val();
	var lnkposen    = $("#lnkposen").val();
	var lnkphone    = $("#lnkphone").val();
	var lnkemail    = $("#lnkemail").val();
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