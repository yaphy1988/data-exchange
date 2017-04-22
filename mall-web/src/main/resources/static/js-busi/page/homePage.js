/**
 * 
 */
/** 获取地市列表 */
function saveMadeData() {
	var needTiel    = $("#currCityCode").val(); 
	var needcontent = $("#currCityCode").val(); 
	var lnkposen    = $("#currCityCode").val(); 
	var lnkphone    = $("#currCityCode").val(); 
	var lnkemail    = $("#currCityCode").val(); 
	var url = WEB_ROOT + "/homePage/queryCitys";
	var param = {
		type : "busi",
		provinceCode : provinceCode
	};
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		async : false,
		data : param,
		success : function(data) {
			$("#countrys").empty();
			$("#countrys").append("<option value=''>--全部--</>");
			
			$("#citys").empty();
			$("#citys").append("<option value=''>--全部--</>");
			if(data != null && data != "")
			{
				$.each(data, function(i, n) {
				if(n.areaCode == citycode && citycode != ""){
					$("#citys").append("<option value='"+n.areaCode+"' selected = 'selected'>"+n.areaName+"</>");
				} else {
					$("#citys").append("<option value='"+n.areaCode+"'>"+n.areaName+"</>");
				} ;
			});
	    	//如果是地市级管理员
	    	if($('#staffLevel').val()=='03' || $('#staffLevel').val()=='04'|| $('#staffLevel').val()=='05'){
	    		$("#citys").attr('disabled',true);
	    		queryCountry(provinceCode, $("#citys").val());

	    	}else{
	    		$("#citys").attr('disabled',false);
	    	}
		}
	}});
}