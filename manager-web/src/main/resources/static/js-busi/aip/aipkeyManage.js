var basePath = WEB_ROOT;
$(function(){

});


function queryClient(pageNo){
	var userName=$("#userName").val();
	var state=$("#statusSel").val();
	// alert(userName+":"+state);
	var data={
			username:$("#userName").val(),
			status:$("#statusSel").val(),
			pageNo:pageNo
	};	
	$.ajax({
		url:basePath+"/aipKeyManage/getClientInfoPage",
		data:data,
		dataType:'html',
		type:'post',
		success:function(data){
			$("#aipClientListDiv").html(data);
		}	
	})
	
}

function pagerClick(pageNo) {
	queryClient(pageNo);
}

function changeStatus(clientId,status){
	var data={
			clientId:clientId,
			status:status
	}
	$.ajax({
		url:basePath+"/aipKeyManage/updateStateByClentId",
		data:data,
		dataType:'json',
		type:'post',
		async:false,
		success:function(data){
			if(data.success){
				WEB.msg.info("提示",data.msg);
				queryClient(1);
			}else{
				WEB.msg.info("提示",data.msg);
			}		
		}	
	})
}

function edit(obj){
	// WEB.msg.info("提示",obj.clientId+"|"+obj.username);
	$("#editAipkeyModal_clientId").val(obj.clientId);
	$("#editAipkeyModal_clientSecret").val(obj.clientSecret);
	$("#editAipkeyModal_user").attr("userId",obj.userId);
	$("#editAipkeyModal_user").val(obj.username);
	$("#editAipkeyModal_effectiveTime").val(formatterDateTime(new Date(obj.effectiveTime)));
	$("#editAipkeyModal_password").val(obj.password);
	$("#editAipkeyModal_status").find("option").each(function () {
		if ($(this).val() == obj.status){
			$(this).attr("selected","selected");
		}
	})
	$("#editAipkeyModal").modal("show");
}

function createClient(){
	$("#addAipkeyModal").modal('show');
}

function delAllSel(){
	var clients=[];
	$('input[name="clientBox"]:checked').each(function(){
		clients.push($(this).attr("value"));
	});
	var data={
			ClientIdList:clients,
			status:'0'
	}
	$.ajax({
		url:basePath+"/aipKeyManage/batchUpdateStateByClentId",
		data:data,
		dataType:'json',
		type:'post',
		traditional:true,
		success:function(data){
			if(data.success){
				WEB.msg.info("提示",data.msg);
				queryClient(1);
			}else{
				WEB.msg.info("提示",data.msg);
			}		
		}	
	})
}

function clickSingleBox(obj){
	if(!$(obj).is(":checked")){
		$("#allBox").attr("checked",false);
	}
}
function clickAllBox(obj){
	if($(obj).is(":checked")){
		$('input[name="clientBox"]').attr("checked",true);
	}
}

function queryStaffInfoList(pageNo) {
	var params = {};
	var staffId = $("#staffInfoModal_staffIdQuery").val();
	var staffName = $("#staffInfoModal_staffNameQuery").val();
	if (staffId == undefined || $.trim(staffId) == ""){
		staffId = "";
	}
	if (staffName == undefined || $.trim(staffName) == ""){
		staffName = "";
	}
	params.staffId = $.trim(staffId);
	params.staffName = $.trim(staffName);
	if (pageNo == undefined || $.trim(pageNo) == "" || $.trim(pageNo) == "0"){
		pageNo = 1;
	}
	params.pageNo = pageNo;
	$.ajax({
		url:WEB_ROOT + "/aipKeyManage/queryStaffInfoPage",
		async:true,
		data:params,
		dataType:'html',
		type:'post',
		success:function (data) {
			$("#staffInfoModal_staffListDiv").html(data);
			// $('#pagerId').pager({callback: staffPagerClick});
		}
	})
}

function staffPagerClick(pageNo) {
	queryStaffInfoList(pageNo)
}

function initStaffModalClick() {
	$("#staffInfoModal").modal('show');
	queryStaffInfoList(1);
}

/**
 * 确认选中的用户
 */
function confirmStaffSel() {
	var len = $("input:radio[name='staffInfoModal_staffSel']:checked").length;
	if (len==0){
		WEB.msg.info("提示","请选择一个用户！");
		return;
	}
	if (len>1){
		WEB.msg.info("提示","只能选择一个用户！")
		return;
	}
	if (len == 1){
		var staffId = $("input:radio[name='staffInfoModal_staffSel']:checked").attr("staffId");
		var staffName = $("input:radio[name='staffInfoModal_staffSel']:checked").attr("staffName");
		if (staffId == undefined || $.trim(staffId) == ""){
			WEB.msg.info("提示","系统错误，请重试或联系管理员！");
			return ;
		}
		$("#addAipkeyModal_staffInfo").attr("staffId",staffId);
		$("#addAipkeyModal_staffInfo").val(staffName);
	}
	$("#staffInfoModal").modal("hide");
}

/**
 * 提交保存aipkey信息
 */
function confirmNewAipClient() {
	var params = {};
	var staffId = $("#addAipkeyModal_staffInfo").attr("staffId");
	var staffName = $("#addAipkeyModal_staffInfo").val();
	if (staffId == undefined || $.trim(staffId) == ""){
		WEB.msg.info("提示","用户为空，请先选择用户！");
		return;
	}
	params.userId = staffId;
	params.username = staffName;

	var effectiveTime = $("#addAipkeyModal_effectiveTime").val();
	if (effectiveTime==undefined || $.trim(effectiveTime) == ""){
		WEB.msg.info("提示","有效期为空，请先选择有效期！");
		return;
	}
	params.effectiveTime = effectiveTime;

	$.ajax({
		url:WEB_ROOT + "/aipKeyManage/saveAipkey",
		data:params,
		dataType:'json',
		type:'post',
		async:false,
		success:function (jsonObj) {
			if (jsonObj.success){
				WEB.msg.info("提示","保存成功！");
				$("#addAipkeyModal").modal('hide');
				queryClient(1);
			}else{
				WEB.msg.info("提示",jsonObj.msg);
			}
		}
	})
}

function confirmEditAipClient() {
	var params = {};
	var clientId = $("#editAipkeyModal_clientId").val();
	var clientSecret = $("#editAipkeyModal_clientSecret").val();
	var userId = $("#editAipkeyModal_user").attr("userId");
	var username = $("#editAipkeyModal_user").val();
	var status = $("#editAipkeyModal_status").val();
	var effectiveTime = $("#editAipkeyModal_effectiveTime").val();
	var password = $("#editAipkeyModal_password").val();
	params.clientId = clientId;
	params.clientSecret = clientSecret;
	params.userId = userId;
	params.username = username;
	params.status = status;
	params.effectiveTime = effectiveTime;
	params.password = password;
	$.ajax({
		url:WEB_ROOT + "/aipKeyManage/saveAipkey",
		data:params,
		dataType:'json',
		type:'post',
		async:false,
		success:function (jsonObj) {
			if (jsonObj.success){
				WEB.msg.info("提示","保存成功！");
				$("#editAipkeyModal").modal('hide');
				queryClient(1);
			}else{
				WEB.msg.info("提示",jsonObj.msg);
			}
		}
	})
}

/**
 * 格式化去日期（含时间）
 */
formatterDateTime = function(date) {
	var datetime = date.getFullYear()
		+ "-"// "年"
		+ ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1))
		+ "-"// "月"
		+ (date.getDate() < 10 ? "0" + date.getDate() : date
			.getDate())
		+ " "
		+ (date.getHours() < 10 ? "0" + date.getHours() : date
			.getHours())
		+ ":"
		+ (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
			.getMinutes())
		+ ":"
		+ (date.getSeconds() < 10 ? "0" + date.getSeconds() : date
			.getSeconds());
	return datetime;
}

