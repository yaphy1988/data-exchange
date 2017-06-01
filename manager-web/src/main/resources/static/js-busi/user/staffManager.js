/**
 * Created by yx on 2017/5/31.
 */
$(function () {
    queryAuthStaffPage(1);
})

function queryAuthStaffPage(pageNo){
    var params = {};
    params.pageNo = pageNo;
    $.ajax({
        url:WEB_ROOT + "/authStaff/queryAuthStaffPage",
        data:params,
        dataType:'html',
        type:'post',
        async:true,
        success:function (data) {
            $("#staffListDiv").html(data);
        }
    })
}

function pagerClick(pageNo){
    queryAuthStaffPage(pageNo);
}

function doStaffFlag(obj) {
    var params = {};
    var targetStaffFlag = $(obj).attr("targetStaffFlag");
    var staffFlag = $(obj).attr("staffFlag");
    var staffId = $(obj).attr("staffId");

    params.staffId = staffId;
    params.staffFlag = staffFlag;
    params.targetStaffFlag = targetStaffFlag;

    $.ajax({
        url:WEB_ROOT + "/authStaff/doStaffFlag",
        data:params,
        dataType:'json',
        type:'post',
        async:true,
        success:function (jsonObj) {
            if (jsonObj.success){
                var msg = "";
                if (targetStaffFlag == "0"){
                    msg = "失效成功！";
                }else if (targetStaffFlag == "1"){
                    msg = "生效成功！";
                }else if (targetStaffFlag == "2"){
                    msg = "加锁成功！";
                }
                WEB.msg.info("提示",msg,function () {
                    queryAuthStaffPage(1);
                });
            }else{
                var msg = "";
                if (targetStaffFlag == "0"){
                    msg = "失效失败！";
                }else if (targetStaffFlag == "1"){
                    msg = "生效失败！";
                }else if (targetStaffFlag == "2"){
                    msg = "加锁失败！";
                }
                WEB.msg.info("提示",msg);
            }
        }
    })
}

function staffInfoInit(isEdit) {
    var params = {};
    if (isEdit == "1"){
        var staffId = $("input[name='staffId']:checked").attr("staffId");
        params.staffId = staffId;
    }
    params.isEdit = isEdit;
    $.ajax({
        url:WEB_ROOT + "/authStaff/initStaffInfo",
        data:params,
        dataType:'html',
        type:'post',
        async:true,
        success:function (data) {
            $("#staffInfoModalDiv").html(data);
            $("#staffInfoModal").modal("show");
        }
    })
}

function submitStaffInfo() {
    var params = {};

    if (initParams(params) == null){
        return;
    }

    var isEdit = $("#isEdit").val();
    params.isEdit = isEdit;
    
    $.ajax({
        url:WEB_ROOT + "/authStaff/saveStaffInfo",
        type:'post',
        dataType:'json',
        data:params,
        async:false,
        success:function (jsonObj) {
            if (jsonObj.success){
                WEB.msg.info("提示",jsonObj.msg);
                $("#staffInfoModal").modal("hide");
                queryAuthStaffPage(1);
            }else{
                WEB.msg.info("提示",jsonObj.msg);
            }
        }
    })

}

/**
 * 初始化参数保存
 * @param params
 */
function initParams(params) {
    //校验用户ID
    if(checkstaffId() ==false){
        return null;
    }

    var staffId = $.trim($("#staffInfoModal_staffId").val());

    var staffName = $("#staffInfoModal_staffName").val();
    if (staffName == undefined || $.trim(staffName) == ""){
        WEB.msg.info("提示","用户名不能为空！");
        return null;
    }
    staffName = $.trim(staffName);

    var staffType = $("#staffInfoModal_staffType").val();

    var aliasName = $("#staffInfoModal_aliasName").val();
    if (aliasName == undefined || $.trim(aliasName) == ""){
        WEB.msg.info("提示","别名不能为空！");
        return null;
    }
    aliasName = $.trim(aliasName);

    var gender = $("#staffInfoModal_gender").val();
    var job = $("#staffInfoModal_job").val();
    var birthday = $("#staffInfoModal_birthday").val();

    var serialNumber = $("#staffInfoModal_serialNumber").val();
    if(serialNumber==undefined || $.trim(serialNumber) == ""){
        WEB.msg.info('提示','手机号码不能为空');
        return null;
    }else if(!WEB.check.isMobile(serialNumber)){
        WEB.msg.info('提示','手机号码格式不正确');
        return null;
    }
    serialNumber = $.trim(serialNumber);

    var email = $("#staffInfoModal_email").val();
    var qq = $("#staffInfoModal_qq").val();
    var weChat = $("#staffInfoModal_weChat").val();
    var contactInfo = $("#staffInfoModal_contactInfo").val();

    params.staffId = staffId;
    params.staffName = staffName;
    params.staffType = staffType;
    params.aliasName = aliasName;
    params.gender = gender;
    params.job = job;
    params.birthdayStr = birthday;
    params.serialNumber = serialNumber;
    params.email = email;
    params.qq = qq;
    params.weChat = weChat;
    params.contactInfo = contactInfo;
    return params
}

//校验用户ID
function checkstaffId(){
    var staffId = $("#staffInfoModal_staffId").val();
    var reg = /^[A-Za-z0-9]+$/;
    if(!staffId){
        // showwarm('staffIdDiv','用户ID不能为空');
        WEB.msg.info("提示","用户ID不能为空");
        return false;
    }else if(!reg.test(staffId)){
        WEB.msg.info('提示','用户ID格式不正确！');
        return false;
    }else if(staffId.length<6||staffId.length>24){
        WEB.msg.info('提示','用户ID为6-24位字母数字！');
        return false;
    }
    return true;
}